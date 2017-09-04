package com.gurjinder.server.dao;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.gurjinder.server.dao.entity.SalesManProfile;

public interface SalesManProfileRepository extends
		MongoRepository<SalesManProfile, ObjectId> {
	public SalesManProfile findByUsername(String username);
}
