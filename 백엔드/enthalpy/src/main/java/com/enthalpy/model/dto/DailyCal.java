package com.enthalpy.model.dto;

public class DailyCal {
	private int id;
	private String userId;
	private String regDate;
	private int calories;
	private int activity_id;
	private int duration;
	public DailyCal() {
		super();
	}
	public DailyCal(int id, String userId, String regDate, int calories, int activity_id, int duration) {
		super();
		this.id = id;
		this.userId = userId;
		this.regDate = regDate;
		this.calories = calories;
		this.activity_id = activity_id;
		this.duration = duration;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public int getCalories() {
		return calories;
	}
	public void setCalories(int calories) {
		this.calories = calories;
	}
	public int getActivity_id() {
		return activity_id;
	}
	public void setActivity_id(int activity_id) {
		this.activity_id = activity_id;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	@Override
	public String toString() {
		return "DailyCal [id=" + id + ", userId=" + userId + ", regDate=" + regDate + ", calories=" + calories
				+ ", activity_id=" + activity_id + ", duration=" + duration + "]";
	}
}
