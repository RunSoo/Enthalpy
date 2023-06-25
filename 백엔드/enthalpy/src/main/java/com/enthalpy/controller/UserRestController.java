package com.enthalpy.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.enthalpy.model.dto.User;
import com.enthalpy.model.service.UserService;

import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/user")
public class UserRestController {
	
	@Autowired
	private UserService userService;
	
	@ApiOperation(value="회원 가입")
	@PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestBody User user){
		if (userService.signup(user)) {
			return new ResponseEntity<User>(user, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
		}
	}
	
	@ApiOperation(value="로그인")
	@PostMapping("/login")
	public ResponseEntity<?> login(User user, HttpSession session){
		User tmp = userService.login(user.getId(), user.getPassword());
		if (tmp==null) {
			return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
		}
		session.setAttribute("loginUser", tmp);
		System.out.println((User)session.getAttribute("loginUser"));
		return new ResponseEntity<String>(tmp.getName(), HttpStatus.OK);
	}
	
	@ApiOperation(value="로그아웃")
	@GetMapping("/logout")
	public ResponseEntity<Void> logout(HttpSession session){
		session.invalidate();
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@ApiOperation(value="회원 정보 수정")
	@PostMapping("/info")
	public ResponseEntity<?> updateInfo(@RequestBody User user, HttpSession session){
		boolean result = userService.updateInfo(user);
		if (!result) {
			return new ResponseEntity<Void>(HttpStatus.NOT_MODIFIED);
		}
		User tmp = userService.searchUser(user.getId());
		if (tmp==null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		session.removeAttribute("loginUser");
		session.setAttribute("loginUser", tmp);
		return new ResponseEntity<String>(tmp.getName(), HttpStatus.OK);
	}
	
	@ApiOperation(value="아이디 중복 확인")
	@GetMapping("/idCheck")
	public ResponseEntity<Boolean> idCheck(String id){
		User tmp = userService.searchUser(id);
		if (tmp==null) {
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		}
		return new ResponseEntity<Boolean>(false, HttpStatus.OK);
	}

	@ApiOperation(value="닉네임 중복 확인")
	@GetMapping("/nickCheck")
	public ResponseEntity<Boolean> nickCheck(String nickname){
		User tmp = userService.searchUserByNickname(nickname);
		if (tmp==null) {
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		}
		return new ResponseEntity<Boolean>(false, HttpStatus.OK);
	}
	
	@ApiOperation(value="회원 정보 확인")
	@GetMapping("/info")
	public ResponseEntity<?> checkInfo(HttpSession session){
		User tmp = (User) session.getAttribute("loginUser");
		if (tmp==null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(tmp, HttpStatus.OK);
	}
	
	@ApiOperation(value="전체 회원 조회")
	@GetMapping("/list")
	public ResponseEntity<?> list() {
		List<User> list = userService.getUserList();
		if (list == null || list.size() == 0) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<User>>(list, HttpStatus.OK);
	}
	
	@ApiOperation(value="목표 설정 및 수정")
	@PutMapping("/goal")
	public ResponseEntity<Void> setGoal(@RequestBody User user) {
		if (userService.updateGoal(user)) return new ResponseEntity<Void>(HttpStatus.OK);
		return new ResponseEntity<Void>(HttpStatus.NOT_MODIFIED);
	}
	
	@ApiOperation(value="목표 삭제")
	@PutMapping("/goal/del")
	public ResponseEntity<Void> deleteGoal(@RequestBody String id) {
		System.out.println("들어오냐?");
		System.out.println(id);
		if (userService.deleteGoal(id)) return new ResponseEntity<Void>(HttpStatus.OK);
		return new ResponseEntity<Void>(HttpStatus.NOT_MODIFIED);
	}
	
	@ApiOperation(value="목표 조회")
	@GetMapping("/goal")
	public ResponseEntity<Integer> getGoal(String id) {
		return new ResponseEntity<Integer>(userService.selectGoal(id), HttpStatus.OK);
	}
}
