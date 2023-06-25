package com.enthalpy.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.enthalpy.model.dto.Board;
import com.enthalpy.model.dto.DailyCal;
import com.enthalpy.model.dto.Like;
import com.enthalpy.model.dto.MET;
import com.enthalpy.model.dto.SearchCondition;
import com.enthalpy.model.service.BoardService;
import com.enthalpy.model.service.DailyCalService;
import com.enthalpy.model.service.LikeService;
import com.enthalpy.model.service.METService;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/board")
public class BoardRestController {
	@Autowired
	private BoardService boardService;

	@Autowired
	private LikeService likeService;

	@Autowired
	private DailyCalService dailyCalService;

	@Autowired
	private METService metService;

	@Autowired
	ResourceLoader resLoader;

	@ApiOperation(value="게시글 조회")
	@GetMapping("/list")
	public ResponseEntity<?> list(SearchCondition con){
		List<Board> list = boardService.search(con);
		if (list==null || list.size()==0) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Board>>(list, HttpStatus.OK);
	}

	@ApiOperation(value="게시글 유저별로 조회")
	@GetMapping("/list/user")
	public ResponseEntity<?> listOfUSer(@RequestBody SearchCondition con){
		System.out.println(con);
		List<Board> list = boardService.searchByID(con);
		if (list==null || list.size()==0) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Board>>(list, HttpStatus.OK);
	}

	@ApiOperation(value="상세 보기")
	@GetMapping("/list/{id}")
	public ResponseEntity<?> detail(@PathVariable int id, String userId){
		System.out.println(id);
		System.out.println(userId);
		Board board = boardService.getBoardDetail(id, userId);
		if (board==null) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Board>(board, HttpStatus.OK);
	}

	@ApiOperation(value="등록")
	@PostMapping("/list")
	public ResponseEntity<?> write(@ModelAttribute Board board, @RequestPart(value="file", required=false)MultipartFile file){
	    if (board.getUserId() == null || board.getUserId().equals("")) return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);

	    try {
	        if (file != null && !file.isEmpty()) {
	            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

	            String uploadPath = "경로";

	            Path path = Paths.get(uploadPath + fileName);
	            Files.createDirectories(path.getParent());

	            board.setImg(fileName);

	            file.transferTo(path.toFile());
	        }
            
            


            DailyCal dailyCal = new DailyCal();
            dailyCal.setUserId(board.getUserId());
            dailyCal.setActivity_id(board.getActivity_id());
            dailyCal.setDuration(board.getDuration());
            if (dailyCalService.existingDailyCal(dailyCal)) {
                if (!dailyCalService.modifyDailyCal(dailyCal)) return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
            }
            else {
                if (!dailyCalService.writeDailyCal(dailyCal)) return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
            }
            try {
            	Board createdBoard = boardService.writeBoard(board);
                if (createdBoard != null) {
                	System.out.println(createdBoard);
                    return new ResponseEntity<Board>(createdBoard, HttpStatus.OK);
                } else {
                    dailyCalService.subtractDailyCal(dailyCal);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
	    } catch (IOException e) {
	        e.printStackTrace();
	        return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
    }
	
	@ApiOperation(value="수정")
	@PutMapping("/list")
	public ResponseEntity<Void> update(@RequestBody Board board, @RequestPart(value="file", required=false)MultipartFile file){
		
		if (board.getKey() == 0) {
			try {
				if (file != null && !file.isEmpty()) {
					String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
					String uploadPath = "경로";
					
					Path path = Paths.get(uploadPath + fileName);
					Files.createDirectories(path.getParent());
					
					board.setImg(fileName);
					
					file.transferTo(path.toFile());
				}
				
				if (boardService.updateBoard(board)) {
					return new ResponseEntity<Void>(HttpStatus.OK);
				}
				return new ResponseEntity<Void>(HttpStatus.NOT_MODIFIED);
			} catch (IOException e) {
				e.printStackTrace();
				return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			if (boardService.modifyBoard(board)) {
				return new ResponseEntity<Void>(HttpStatus.OK);
			}
			return new ResponseEntity<Void>(HttpStatus.NOT_MODIFIED);
		}
	}

	@ApiOperation(value="삭제")
	@DeleteMapping("/list/{id}")
	public ResponseEntity<Void> delete(@PathVariable int id){
		Board board = boardService.getBoardDetail(id);
		DailyCal dailyCal = new DailyCal();
		dailyCal.setUserId(board.getUserId());
		dailyCal.setActivity_id(board.getActivity_id());
		dailyCal.setDuration(board.getDuration());
		dailyCal.setRegDate(board.getRegDate());
		if (!dailyCalService.subtractDailyCal(dailyCal)) return new ResponseEntity<Void>(HttpStatus.NOT_MODIFIED);
		if (boardService.removeBoard(id)) {
			dailyCalService.delDailyCal(dailyCal);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		dailyCalService.modifyDailyCal(dailyCal); // 이케하려면 매퍼 조금 손봐야되긴할듯..날가 CURDATE라..
		return new ResponseEntity<Void>(HttpStatus.NOT_MODIFIED);
	}
	
	@ApiOperation(value="좋아요 누르기")
	@PostMapping("/like")
	public ResponseEntity<?> doLike(@RequestBody Like like){
		System.out.println(like);
		int id = like.getBoardId();
		if (likeService.hasLiked(like)) {
			if (likeService.deleteLike(like)) {
				boardService.unlike(id);
				Board board = boardService.getBoardDetail(id);
				return new ResponseEntity<Board>(board, HttpStatus.OK);
			}
		}
		else {
			if (likeService.insertLike(like)) {
				boardService.dolike(id);
				Board board = boardService.getBoardDetail(id);
				return new ResponseEntity<Board>(board, HttpStatus.OK);
			}
		}
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value="전체 운동 가져오기")
	@GetMapping("/MET")
	public ResponseEntity<?> getAllMEt(){
		List<MET> list = metService.getMETList();
		if (list.isEmpty() || list.size()==0) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<MET>>(list, HttpStatus.OK);
	}
}
