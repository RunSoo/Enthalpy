package com.enthalpy.model.service;

import java.util.List;

import com.enthalpy.model.dto.User;

public interface UserService {
	// 회원 가입
	public boolean signup(User user);
	
	// 로그인
	public User login(String id, String password);
	
	// 회원 정보 수정
	public boolean updateInfo(User user);
	
	// 아이디 중복 확인
	public User searchUser(String id);
	
	// 닉네임 중복 확인
	public User searchUserByNickname(String nickName);
	
	// 전체 회원 조회
	public List<User> getUserList();
	
	// 목표 수정하기
	public boolean updateGoal(User user);
	
	// 목표 삭제하기
	public boolean deleteGoal(String id);
	
	// 목표 가져오기
	public int selectGoal(String id);
}
