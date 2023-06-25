package com.enthalpy.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enthalpy.model.dto.Comment;
import com.enthalpy.model.service.CommentService;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/comment")
public class CommentRestController {
	
	@Autowired
	private CommentService commentService;
	
	@ApiOperation(value="댓글 목록 조회")
	@GetMapping("/{boardId}")
	public ResponseEntity<?> list(@PathVariable int boardId) {
		List<Comment> list = commentService.getAllComment(boardId);
		for (Comment comment : list) {
			System.out.println(comment);
		}
		if (list == null || list.size() == 0) return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<Comment>>(list, HttpStatus.OK);
	}
	
	@ApiOperation(value="댓글 등록")
	@PostMapping("/")
	public ResponseEntity<?> write(@RequestBody Comment comment, HttpSession session) {
		System.out.println(comment);
//		User tmp = (User) session.getAttribute("loginUser");
//		comment.setUserId(tmp.getId());
		if (commentService.writeComment(comment)) return new ResponseEntity<Comment>(comment, HttpStatus.CREATED);
		return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
	}
	
	@ApiOperation(value="댓글 삭제")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable int id) {
		commentService.removeComment(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@ApiOperation(value="댓글 수정")
	@PutMapping("/")
	public ResponseEntity<Void> update(@RequestBody Comment comment) {
		System.out.println(comment);
		System.out.println(commentService.modifyComment(comment));
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	// 이 뒤부터 시작하거라 ..
}
