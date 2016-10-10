package com.cd.dota.core.service;

import java.util.List;

import com.cd.dota.common.BatchResultDTO;
import com.cd.dota.common.ResultDTO;
import com.cd.dota.dal.domain.UserMediumRelationDO;

public interface UserMediumRelationService {
	public ResultDTO<Integer> addUserMediumRelation(
			UserMediumRelationDO userMediumRelationDO);
	
	public ResultDTO<Integer> addUserMediumRelations(
			List<UserMediumRelationDO> userMediumRelationDOs);

	public ResultDTO<Integer> updateUserMediumRelation(
			UserMediumRelationDO userMediumRelationDO);

	public ResultDTO<UserMediumRelationDO> selectUserMediumRelationById(
			Integer userMediumRelationId);

	public BatchResultDTO<UserMediumRelationDO> selectUserMediumRelations(
			UserMediumRelationDO userMediumRelationDO);

}
