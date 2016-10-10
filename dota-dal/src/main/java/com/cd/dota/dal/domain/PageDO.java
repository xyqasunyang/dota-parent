package com.cd.dota.dal.domain;

import java.util.Date;

public class PageDO {

	/** 0 未删除 1 已经删除 */
	private Integer isDelete;
	/** 创建日期 */
	private Date createTime;
	/** 创建人员 */
	private Long createUser;
	/** 修改日期 */
	private Date updateTime;
	/** 修改人员 */
	private Long updateUser;

	private Integer pageSize;

	/** set:当前页码 ,get:当前分页起始数 */
	private Integer pageIndex;

	/** 分页起始页 */
	private Integer pageStartNum;

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	

	public Long getCreateUser() {
		return createUser;
	}

	public void setCreateUser(Long createUser) {
		this.createUser = createUser;
	}
	

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Long getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(Long updateUser) {
		this.updateUser = updateUser;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public Integer getPageStartNum() {
		if (pageSize == null || pageIndex == null) {
			pageStartNum = null;
		} else {
			if (pageIndex <= 0) {
				pageStartNum = 0;
			} else {
				pageStartNum = (pageIndex - 1) * pageSize;
			}
		}
		return pageStartNum;
	}

	public void setPageStartNum(Integer pageStartNum) {
		this.pageStartNum = pageStartNum;
	}

}
