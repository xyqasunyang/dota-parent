package com.cd.dota.dal.domain;

import java.util.Date;

public class NewsDO extends PageDO{

	private Integer newsId;
	
	private Integer mediumId;
	
	private Integer columnId;
	
	private String title; 
	
	private String author;
	
	private String cover;
	
	private String abstracts;
	
	private String content;
	
	private String originalUrl;
	
	private String originalId;
	
	private Date publishTime;
	
	private Date captureTime;
	
	private Integer readQuantity;
	
	private Integer likeQuantity;
	
	private Integer shareQuantity;

	/**------------------------*/
	
	private Integer isCollect;
	
	private Integer isLike;
	
	public Integer getNewsId() {
		return newsId;
	}

	public void setNewsId(Integer newsId) {
		this.newsId = newsId;
	}

	public Integer getMediumId() {
		return mediumId;
	}

	public void setMediumId(Integer mediumId) {
		this.mediumId = mediumId;
	}

	public Integer getColumnId() {
		return columnId;
	}

	public void setColumnId(Integer columnId) {
		this.columnId = columnId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public String getAbstracts() {
		return abstracts;
	}

	public void setAbstracts(String abstracts) {
		this.abstracts = abstracts;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getOriginalUrl() {
		return originalUrl;
	}

	public void setOriginalUrl(String originalUrl) {
		this.originalUrl = originalUrl;
	}

	public String getOriginalId() {
		return originalId;
	}

	public void setOriginalId(String originalId) {
		this.originalId = originalId;
	}

	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	public Date getCaptureTime() {
		return captureTime;
	}

	public void setCaptureTime(Date captureTime) {
		this.captureTime = captureTime;
	}

	public Integer getReadQuantity() {
		return readQuantity;
	}

	public void setReadQuantity(Integer readQuantity) {
		this.readQuantity = readQuantity;
	}

	public Integer getLikeQuantity() {
		return likeQuantity;
	}

	public void setLikeQuantity(Integer likeQuantity) {
		this.likeQuantity = likeQuantity;
	}

	public Integer getShareQuantity() {
		return shareQuantity;
	}

	public void setShareQuantity(Integer shareQuantity) {
		this.shareQuantity = shareQuantity;
	}

	public Integer getIsCollect() {
		return isCollect;
	}

	public void setIsCollect(Integer isCollect) {
		this.isCollect = isCollect;
	}

	public Integer getIsLike() {
		return isLike;
	}

	public void setIsLike(Integer isLike) {
		this.isLike = isLike;
	}
	
	
}
