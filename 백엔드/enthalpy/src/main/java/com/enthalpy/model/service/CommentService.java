package com.enthalpy.model.service;

import java.util.List;

import com.enthalpy.model.dto.Comment;

public interface CommentService {
	public List<Comment> getAllComment(int boardId);
	
	public boolean writeComment(Comment comment);
	
	public boolean removeComment(int id);
	
	public boolean modifyComment(Comment comment);
}
