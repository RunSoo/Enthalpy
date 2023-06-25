package com.enthalpy.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enthalpy.model.dto.DailyCal;
import com.enthalpy.model.dto.User;
import com.enthalpy.model.service.DailyCalService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/dailyCal")
public class DailyCalRestController {
	@Autowired
	private DailyCalService dailyCalService;
	
	@ApiOperation(value="해당 유저의 모든 일별칼로리 조회")
	@GetMapping("/")
	public ResponseEntity<?> list(String userId) {
		List<DailyCal> list = dailyCalService.getAllDailyCal(userId);
		if (list==null || list.size()==0) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<DailyCal>>(list, HttpStatus.OK);
	}
	
	@ApiOperation(value="해당 유저의 월별 칼로리 조회")
	@GetMapping("/monthly")
	public ResponseEntity<Integer> monthly(HttpSession session, DailyCal dailyCal) {	// dailyCal 객체에는 regDate에 20xx-xx-xx 까지의 정보가 들어 있어야 한다
		User tmp = (User)session.getAttribute("loginUser");
		dailyCal.setUserId(tmp.getId());
		System.out.println(dailyCal);
		int res = dailyCalService.getMonthlyCalSum(dailyCal);
		return new ResponseEntity<Integer>(res, HttpStatus.OK);
	}
}
