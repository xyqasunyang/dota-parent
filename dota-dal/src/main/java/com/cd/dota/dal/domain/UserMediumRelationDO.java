package com.cd.dota.dal.domain;

public class UserMediumRelationDO extends PageDO{

	private Integer userMediumRelationId;
	
	private Integer userId;
	
	private Integer mediumId;

	public Integer getUserMediumRelationId() {
		return userMediumRelationId;
	}

	public void setUserMediumRelationId(Integer userMediumRelationId) {
		this.userMediumRelationId = userMediumRelationId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getMediumId() {
		return mediumId;
	}

	public void setMediumId(Integer mediumId) {
		this.mediumId = mediumId;
	}
	
	
}
