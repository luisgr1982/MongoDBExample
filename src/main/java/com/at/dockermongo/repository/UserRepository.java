package com.at.dockermongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.at.dockermongo.model.User;

public interface UserRepository extends MongoRepository<User, Long> {
	List<User> findByLastName(String lastName);
	User findByFirstName(String firstName);
}
