package com.enthalpy.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.enthalpy.model.dao.CommentDao;
import com.enthalpy.model.dto.Comment;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private CommentDao commentDao;

	@Override
	public List<Comment> getAllComment(int boardId) {
		return commentDao.selectAll(boardId);
	}
	
	@Transactional
	@Override
	public boolean writeComment(Comment comment) {
		boolean result= commentDao.insertComment(comment) == 1;
		int lastInsertedId = commentDao.getLastInsertedId();
		comment.setId(lastInsertedId);
		return result;
	}
	
	@Transactional
	@Override
	public boolean removeComment(int id) {
		return commentDao.deleteComment(id) == 1;
	}

	@Transactional
	@Override
	public boolean modifyComment(Comment comment) {
		return commentDao.updateComment(comment) == 1;
	}

}
