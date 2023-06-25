package com.enthalpy.model.dto;

public class Board {
	private int id;
	private String title;
	private String writer;
	private String userId;
	private String regDate;
	private String modifiedDate;
	private int activity_id;
	private String activity;
	private int duration;
	private String contents;
	private String orgImg;
	private String img;
	private int viewCnt;
	private int likeCnt;
	private int key;
	public Board() {
		super();
	}
	public Board(int id, String title, String writer, String userId, String regDate, String modifiedDate,
			int activity_id, String activity, int duration, String contents, String orgImg, String img, int viewCnt,
			int likeCnt, int key) {
		super();
		this.id = id;
		this.title = title;
		this.writer = writer;
		this.userId = userId;
		this.regDate = regDate;
		this.modifiedDate = modifiedDate;
		this.activity_id = activity_id;
		this.activity = activity;
		this.duration = duration;
		this.contents = contents;
		this.orgImg = orgImg;
		this.img = img;
		this.viewCnt = viewCnt;
		this.likeCnt = likeCnt;
		this.key = key;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
	public int getActivity_id() {
		return activity_id;
	}
	public void setActivity_id(int activity_id) {
		this.activity_id = activity_id;
	}
	public String getActivity() {
		return activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getOrgImg() {
		return orgImg;
	}
	public void setOrgImg(String orgImg) {
		this.orgImg = orgImg;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public int getViewCnt() {
		return viewCnt;
	}
	public void setViewCnt(int viewCnt) {
		this.viewCnt = viewCnt;
	}
	public int getLikeCnt() {
		return likeCnt;
	}
	public void setLikeCnt(int likeCnt) {
		this.likeCnt = likeCnt;
	}
	public int getKey() {
		return key;
	}
	public void setKey(int key) {
		this.key = key;
	}
	@Override
	public String toString() {
		return "Board [id=" + id + ", title=" + title + ", writer=" + writer + ", userId=" + userId + ", regDate="
				+ regDate + ", modifiedDate=" + modifiedDate + ", activity_id=" + activity_id + ", activity=" + activity
				+ ", duration=" + duration + ", contents=" + contents + ", orgImg=" + orgImg + ", img=" + img
				+ ", viewCnt=" + viewCnt + ", likeCnt=" + likeCnt + ", key=" + key + "]";
	}
}
