package com.example.postgresDemo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.postgresDemo.model.User;
import com.example.postgresDemo.dao.Userdao;
import com.example.postgresDemo.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private Userdao userDao;
	
	@Override
	public User insert(User user) {
		// TODO Auto-generated method stub
		userDao.insert(user);
		return userDao.findUserById(userDao.getLastInsertId());
	}

	@Override
	public void inserBatch(List<User> users) {
		// TODO Auto-generated method stub
		userDao.inserBatch(users);
	}

	@Override
	public List<User> loadAllUser() {
		// TODO Auto-generated method stub
		return userDao.loadAllUser();
	}

	@Override
	public User findUserById(Long id) {
		// TODO Auto-generated method stub
		return userDao.findUserById(id);
	}

	@Override
	public String findNameById(Long id) {
		// TODO Auto-generated method stub
		return userDao.findNameById(id);
	}

	@Override
	public int getTotalNumberUser() {
		// TODO Auto-generated method stub
		return userDao.getTotalNumberUser();
	}

	@Override
	public User update(User user) {
		// TODO Auto-generated method stub
		userDao.update(user);
		return userDao.findUserById(user.getId());
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		userDao.delete(id);
	}
}
