package com.cd.dota.core.service;

import com.cd.dota.common.BatchResultDTO;
import com.cd.dota.common.ResultDTO;
import com.cd.dota.dal.domain.UserNewsRelationDO;

public interface UserNewsRelationService {
	public ResultDTO<Integer> addUserNewsRelation(UserNewsRelationDO userNewsRelationDO);

	public ResultDTO<Integer> updateUserNewsRelation(UserNewsRelationDO userNewsRelationDO);

	public ResultDTO<UserNewsRelationDO> selectUserNewsRelationById(Integer userNewsRelationId);

	public BatchResultDTO<UserNewsRelationDO> selectUserNewsRelations(UserNewsRelationDO userNewsRelationDO);
	
	public BatchResultDTO<UserNewsRelationDO> getCollection(UserNewsRelationDO userNewsRelationDO);

}
