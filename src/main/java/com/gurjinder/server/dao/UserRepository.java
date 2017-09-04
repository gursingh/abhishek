package com.gurjinder.server.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.gurjinder.server.dao.entity.User;

public interface UserRepository extends MongoRepository<User, String> {
	public User findByUsername(String username);

	public User findByToken(String token);
}
