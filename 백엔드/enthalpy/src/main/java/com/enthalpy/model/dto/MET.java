package com.enthalpy.model.dto;

public class MET {
	private int id;
	private String activity;
	private String img;
	private int value;
	public MET() {
		super();
	}
	public MET(int id, String activity, String img, int value) {
		super();
		this.id = id;
		this.activity = activity;
		this.img = img;
		this.value = value;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getActivity() {
		return activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "MET [id=" + id + ", activity=" + activity + ", img=" + img + ", value=" + value + "]";
	}
}
