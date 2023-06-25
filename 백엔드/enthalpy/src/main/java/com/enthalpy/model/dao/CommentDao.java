package com.enthalpy.model.dao;

import java.util.List;

import com.enthalpy.model.dto.Comment;

public interface CommentDao {
	List<Comment> selectAll(int boardId);
	
	int insertComment(Comment comment);
	
	int deleteComment(int id);
	
	int updateComment(Comment comment);
	
	int getLastInsertedId();
}
