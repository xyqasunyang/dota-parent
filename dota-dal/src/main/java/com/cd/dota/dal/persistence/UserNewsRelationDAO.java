package com.cd.dota.dal.persistence;

import java.util.List;

import com.cd.dota.dal.domain.UserNewsRelationDO;
public interface UserNewsRelationDAO {
	
	public int addUserNewsRelation(UserNewsRelationDO userNewsRelationDO);

	public int updateUserNewsRelation(UserNewsRelationDO userNewsRelationDO) ;
	
	public UserNewsRelationDO selectUserNewsRelationById(Integer userNewsRelationId);
	
	public List<UserNewsRelationDO> selectUserNewsRelations(UserNewsRelationDO userNewsRelationDO);
	
	public List<UserNewsRelationDO> getCollection(UserNewsRelationDO userNewsRelationDO);
	
}
