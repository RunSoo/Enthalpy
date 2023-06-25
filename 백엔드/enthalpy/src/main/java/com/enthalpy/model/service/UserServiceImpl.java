package com.enthalpy.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enthalpy.model.dao.UserDao;
import com.enthalpy.model.dto.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Override
	public boolean signup(User user) {
		return userDao.insertUser(user)==1;
	}

	@Override
	public User login(String id, String password) {
		User tmp = userDao.selectById(id);
		if (tmp!=null && tmp.getPassword().equals(password))
			return tmp;
		return null;
	}

	@Override
	public boolean updateInfo(User user) {
		return userDao.updateUser(user)==1;
	}

	@Override
	public User searchUser(String id) {
		return userDao.selectById(id);
	}

	@Override
	public List<User> getUserList() {
		return userDao.selectAll();
	}

	@Override
	public User searchUserByNickname(String nickName) {
		return userDao.selectByNickName(nickName);
	}

	@Override
	public boolean updateGoal(User user) {
		System.out.println(user);
		return userDao.updateGoal(user) == 1;
	}

	@Override
	public boolean deleteGoal(String id) {
		return userDao.deleteGoal(id) == 1;
	}

	@Override
	public int selectGoal(String id) {
		return userDao.selectGoal(id);
	}
}
