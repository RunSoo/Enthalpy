package com.enthalpy.model.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.enthalpy.model.dto.Board;
import com.enthalpy.model.dto.SearchCondition;

public interface BoardService {
	// 게시글 전체 조회
	public List<Board> getBoardList();
	
	// 게시글 하나만 조회
	public Board getBoardDetail(int id, String userId);
	public Board getBoardDetail(int id);
	
	// 게시글 작성
	public Board writeBoard(Board board);
	
	// 게시글 삭제
	public boolean removeBoard(int id);
	
	// 게시글 수정 - 이미지O
	public boolean updateBoard(Board board);
	
	// 게시글 수정
	public boolean modifyBoard(Board board);
	
	// 게시글 검색
	public List<Board> search(SearchCondition con);
	
	public List<Board> searchByID(SearchCondition con);
	
	public void dolike(int id);
	
	public void unlike(int id);
}
