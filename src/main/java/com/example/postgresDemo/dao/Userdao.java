package com.example.postgresDemo.dao;

import java.util.List;

import com.example.postgresDemo.model.User;

public interface Userdao {
	void insert(User user);
	void inserBatch(List<User> users);
	List<User> loadAllUser();
	User findUserById(Long id);
	String findNameById(Long id);
	int getTotalNumberUser();
	Long getLastInsertId();
	void update(User user);
	void delete(Long id);
}
