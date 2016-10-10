package com.cd.dota.dal.domain;

public class UserDO extends PageDO {

	private Integer userId;

	private String hardwareNumber;

	private Integer registerWay;

	private Integer thirdId;

	private String account;

	private String telephone;

	private String password;

	private String token;

	private String faceImg;

	/** ------------------------------ */
	// 模糊匹配
	private String search;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getHardwareNumber() {
		return hardwareNumber;
	}

	public void setHardwareNumber(String hardwareNumber) {
		this.hardwareNumber = hardwareNumber;
	}

	public Integer getThirdId() {
		return thirdId;
	}

	public void setThirdId(Integer thirdId) {
		this.thirdId = thirdId;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getFaceImg() {
		return faceImg;
	}

	public void setFaceImg(String faceImg) {
		this.faceImg = faceImg;
	}

	public Integer getRegisterWay() {
		return registerWay;
	}

	public void setRegisterWay(Integer registerWay) {
		this.registerWay = registerWay;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

}
