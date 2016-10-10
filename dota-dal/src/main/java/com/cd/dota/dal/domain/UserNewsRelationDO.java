package com.cd.dota.dal.domain;

public class UserNewsRelationDO extends PageDO{
	
	private Integer userNewsRelationId;
	
	private Integer userId;
	
	private Integer newsId;
	
	private Integer isLike;
	
	private Integer isCollect;
	
	private Integer mediumId;
	
	/**-------------------*/
	private String newsTitle;
	
	private String newsCover;
	
	private String mediumName;
	
	private String mediumLogo;

	public Integer getUserNewsRelationId() {
		return userNewsRelationId;
	}

	public void setUserNewsRelationId(Integer userNewsRelationId) {
		this.userNewsRelationId = userNewsRelationId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getNewsId() {
		return newsId;
	}

	public void setNewsId(Integer newsId) {
		this.newsId = newsId;
	}

	public Integer getIsLike() {
		return isLike;
	}

	public void setIsLike(Integer isLike) {
		this.isLike = isLike;
	}

	public Integer getIsCollect() {
		return isCollect;
	}

	public void setIsCollect(Integer isCollect) {
		this.isCollect = isCollect;
	}

	public Integer getMediumId() {
		return mediumId;
	}

	public void setMediumId(Integer mediumId) {
		this.mediumId = mediumId;
	}

	public String getNewsTitle() {
		return newsTitle;
	}

	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}

	public String getNewsCover() {
		return newsCover;
	}

	public void setNewsCover(String newsCover) {
		this.newsCover = newsCover;
	}

	public String getMediumName() {
		return mediumName;
	}

	public void setMediumName(String mediumName) {
		this.mediumName = mediumName;
	}

	public String getMediumLogo() {
		return mediumLogo;
	}

	public void setMediumLogo(String mediumLogo) {
		this.mediumLogo = mediumLogo;
	}

	
}
