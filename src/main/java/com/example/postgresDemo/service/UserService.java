package com.example.postgresDemo.service;

import java.util.List;

import com.example.postgresDemo.model.User;

public interface UserService {
	User insert(User user);
	void inserBatch(List<User> users);
	List<User> loadAllUser();
	User findUserById(Long id);
	String findNameById(Long id);
	int getTotalNumberUser();
	User update(User user);
	void delete(Long id);
}
