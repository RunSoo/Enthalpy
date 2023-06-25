package com.enthalpy.model.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.enthalpy.model.dao.BoardDao;
import com.enthalpy.model.dto.Board;
import com.enthalpy.model.dto.SearchCondition;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardDao boardDao;
	
	@Autowired
	ResourceLoader resLoader;
	
	private void fileHandling(Board board, MultipartFile file) throws IOException {
		// 파일을 저장할 폴더 지정
		Resource res = resLoader.getResource("resources/upload");
		if (file != null && file.getSize() > 0) {
			// prefix를 포함한 전체 이름
			board.setImg(System.currentTimeMillis() + "_" + file.getOriginalFilename());
			board.setOrgImg(file.getOriginalFilename());

			// 변경된 파일 이름이 적용된 Board를 BoardService를 통해 DB에 저장한다.

			file.transferTo(new File(res.getFile().getCanonicalPath() + "/" + board.getImg()));
		}
	}

	@Override
	public List<Board> getBoardList() {
		return boardDao.selectAll();
	}

	@Override
	public Board getBoardDetail(int id, String userId) {
		Board board = boardDao.selectOne(id);
		if (!board.getUserId().equals(userId)) boardDao.updateViewCnt(id);
		board = boardDao.selectOne(id);
		return board;
	}

	@Override
	public Board getBoardDetail(int id) {
		Board board = boardDao.selectOne(id);
		return board;
	}
	
	@Transactional
	@Override
	public Board writeBoard(Board board) {
		boolean success = boardDao.insertBoard(board) == 1;
		System.out.println(11);
	    if (success) {
	    	System.out.println(22);
	        int lastInsertedId = boardDao.getLastInsertedId();
	        System.out.println(33);
	        board.setId(lastInsertedId);
	        System.out.println(44);
	        return board;
	    }
	    System.out.println(55);
	    return null;
	}
	
	@Transactional
	@Override
	public boolean removeBoard(int id) {
		return boardDao.deleteBoard(id) == 1;
	}

	@Transactional
	@Override
	public boolean updateBoard(Board board) {
		return boardDao.updateBoard(board) == 1;
	}

	@Transactional
	@Override
	public boolean modifyBoard(Board board) {
		return boardDao.updateBoardNotImg(board) == 1;
	}

	@Override
	public List<Board> search(SearchCondition con) {
		return boardDao.search(con);
	}

	@Override
	public void dolike(int id) {
		boardDao.addLikeCnt(id);
	}

	@Override
	public void unlike(int id) {
		boardDao.delLikeCnt(id);
	}

	@Override
	public List<Board> searchByID(SearchCondition con) {
		return boardDao.searchById(con);
	}

}
