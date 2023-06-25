package com.enthalpy.model.dao;

import java.util.List;

import com.enthalpy.model.dto.User;

public interface UserDao {
	int insertUser(User user);
	
	User selectById(String id);
	
	int updateUser(User user);
	
	List<User> selectAll();
	
	User selectByNickName(String nickName);
	
	int updateGoal(User user);
	
	int deleteGoal(String id);
	
	int selectGoal(String id);
}
