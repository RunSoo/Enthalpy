package com.enthalpy.model.dto;

public class Comment {
	private int id;
	private int boardId;
	private String userId;
    private String writer;
    private String contents;
    private String regDate;
    private String modifiedDate;
	public Comment() {
		super();
	}
	public Comment(int id, int boardId, String userId, String writer, String contents, String regDate,
			String modifiedDate) {
		super();
		this.id = id;
		this.boardId = boardId;
		this.userId = userId;
		this.writer = writer;
		this.contents = contents;
		this.regDate = regDate;
		this.modifiedDate = modifiedDate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	@Override
	public String toString() {
		return "Comment [id=" + id + ", boardId=" + boardId + ", userId=" + userId + ", writer=" + writer
				+ ", contents=" + contents + ", regDate=" + regDate + ", modifiedDate=" + modifiedDate + "]";
	}
}
