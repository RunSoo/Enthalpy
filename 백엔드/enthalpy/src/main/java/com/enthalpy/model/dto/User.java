package com.enthalpy.model.dto;

public class User {
	private String id;
	private String password;
	private String nickname;
	private String name;
	private int height;
	private int weight;
	private char gender; // 의심리스트!
	private int bmr;
	private String dob;
	private int goal;
	public User() {
		super();
	}
	public User(String id, String password, String nickname, String name, int height, int weight, char gender, int bmr,
			String dob, int goal) {
		super();
		this.id = id;
		this.password = password;
		this.nickname = nickname;
		this.name = name;
		this.height = height;
		this.weight = weight;
		this.gender = gender;
		this.bmr = bmr;
		this.dob = dob;
		this.goal = goal;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	public int getBmr() {
		return bmr;
	}
	public void setBmr(int bmr) {
		this.bmr = bmr;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public int getGoal() {
		return goal;
	}
	public void setGoal(int goal) {
		this.goal = goal;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", password=" + password + ", nickname=" + nickname + ", name=" + name + ", height="
				+ height + ", weight=" + weight + ", gender=" + gender + ", bmr=" + bmr + ", dob=" + dob + ", goal="
				+ goal + "]";
	}
}
