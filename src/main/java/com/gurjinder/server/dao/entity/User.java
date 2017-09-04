package com.gurjinder.server.dao.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "USER")
public class User {
	@Id
	@Field(value = "_id")
	private String username;
	private String password;
	private String token;
	private String role;
	private String ipAdddres;
	private int port;
	private Date ipUpdationTime;;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getIpAdddres() {
		return ipAdddres;
	}

	public void setIpAdddres(String ipAdddres) {
		this.ipAdddres = ipAdddres;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public Date getIpUpdationTime() {
		return ipUpdationTime;
	}

	public void setIpUpdationTime(Date ipUpdationTime) {
		this.ipUpdationTime = ipUpdationTime;
	}
}
