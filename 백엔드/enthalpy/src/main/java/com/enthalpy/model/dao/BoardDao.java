package com.enthalpy.model.dao;

import java.util.List;

import com.enthalpy.model.dto.Board;
import com.enthalpy.model.dto.SearchCondition;

public interface BoardDao {
	List<Board> selectAll();
	
	Board selectOne(int id);
	
	int insertBoard(Board board);
	
	int deleteBoard(int id);
	
	int updateBoard(Board board);

	int updateBoardNotImg(Board board);
	
	void updateViewCnt(int id);
	
	void deleteViewCnt(int id);
	
	List<Board> search(SearchCondition con);
	
	void addLikeCnt(int id);
	
	void delLikeCnt(int id);
	
	int getLastInsertedId();
	
	List<Board> searchById(SearchCondition con);
}
