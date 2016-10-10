package com.cd.dota.dal.domain;

public class MediumDO extends PageDO {

	private Integer mediumId;

	private String name;

	private String logo;

	private Integer type;

	private String IntroduceS;

	private String IntroduceL;

	private String websiteUrl;

	private String readUrl;

	private Integer status;

	/** --------------------------------- */
	// 是否被关注，0未关注，1已关注
	private Integer isAttention;
	

	public Integer getMediumId() {
		return mediumId;
	}

	public void setMediumId(Integer mediumId) {
		this.mediumId = mediumId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getIntroduceS() {
		return IntroduceS;
	}

	public void setIntroduceS(String introduceS) {
		IntroduceS = introduceS;
	}

	public String getIntroduceL() {
		return IntroduceL;
	}

	public void setIntroduceL(String introduceL) {
		IntroduceL = introduceL;
	}

	public String getWebsiteUrl() {
		return websiteUrl;
	}

	public void setWebsiteUrl(String websiteUrl) {
		this.websiteUrl = websiteUrl;
	}

	public String getReadUrl() {
		return readUrl;
	}

	public void setReadUrl(String readUrl) {
		this.readUrl = readUrl;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getIsAttention() {
		return isAttention;
	}

	public void setIsAttention(Integer isAttention) {
		this.isAttention = isAttention;
	}

	

}