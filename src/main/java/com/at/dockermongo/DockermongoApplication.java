package com.at.dockermongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.at.dockermongo.model.User;
import com.at.dockermongo.repository.UserRepository;
import com.at.dockermongo.services.SequenceGeneratorService;

@SpringBootApplication
public class DockermongoApplication implements CommandLineRunner {

	@Autowired
	private UserRepository repository;
	@Autowired
	private SequenceGeneratorService sequenceGenerator;
	
	public static void main(String[] args) {
		SpringApplication.run(DockermongoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		repository.deleteAll();

		// save a couple of customers
		repository.save(new User(sequenceGenerator.generateSequence(User.SEQUENCE_NAME),"Luis", "Garcia"));
		repository.save(new User(sequenceGenerator.generateSequence(User.SEQUENCE_NAME),"Antonio", "Perez"));

		// fetch all customers
		System.out.println("Customers found with findAll():");
		System.out.println("-------------------------------");
		for (User user : repository.findAll()) {
			System.out.println(user);
		}
		System.out.println();

		// fetch an individual customer
		System.out.println("Customer found with findByFirstName('Luis'):");
		System.out.println("--------------------------------");
		System.out.println(repository.findByFirstName("Luis"));

		System.out.println("Customers found with findByLastName('Perez'):");
		System.out.println("--------------------------------");
		for (User user : repository.findByLastName("Perez")) {
			System.out.println(user);
		}		
	}
}
