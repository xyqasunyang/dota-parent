package com.cd.dota.dal.persistence;

import java.util.List;

import com.cd.dota.dal.domain.UserMediumRelationDO;
public interface UserMediumRelationDAO {
	
	public int addUserMediumRelation(UserMediumRelationDO userMediumRelationDO);
	
	public int addUserMediumRelations(List<UserMediumRelationDO> userMediumRelationDOs);
	
	public int updateUserMediumRelation(UserMediumRelationDO userMediumRelationDO) ;
	
	public UserMediumRelationDO selectUserMediumRelationById(Integer userMediumRelationId);
	
	public List<UserMediumRelationDO> selectUserMediumRelations(UserMediumRelationDO userMediumRelationDO);
}
