package com.gurjinder.server.dao;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.gurjinder.server.dao.entity.DeviceProfile;

public interface DeviceProfileRepository extends
		MongoRepository<DeviceProfile, ObjectId> {
	public DeviceProfile findByUsername(String username);

	public DeviceProfile findByStoreName(String storeName);
}
