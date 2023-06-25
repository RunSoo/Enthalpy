package com.enthalpy.model.dto;

public class Like {
	private int boardId;
	private String userId;
	public Like() {
		super();
	}
	public Like(int boardId, String userId) {
		super();
		this.boardId = boardId;
		this.userId = userId;
	}
	public int getBoardId() {
		return boardId;
	}
	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "Like [boardId=" + boardId + ", userId=" + userId + "]";
	}
}
