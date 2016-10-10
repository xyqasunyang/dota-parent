package com.cd.dota.core.service;

import com.cd.dota.common.BatchResultDTO;
import com.cd.dota.common.ResultDTO;
import com.cd.dota.dal.domain.MediumDO;
import com.cd.dota.dal.domain.UserDO;

public interface MediumService {
	public ResultDTO<Integer> addMedium(MediumDO mediumDO);

	public ResultDTO<Integer> updateMedium(MediumDO mediumDO);

	public ResultDTO<MediumDO> selectMediumById(Integer mediumId);

	public BatchResultDTO<MediumDO> selectMediums(MediumDO mediumDO);
	
	public BatchResultDTO<MediumDO> selectMediumsByUserId(UserDO userDO);
	
	public BatchResultDTO<MediumDO> firstChoose(UserDO userDO);
	
	public ResultDTO<MediumDO> getMedium(Integer userId,Integer mediumId);
	
	public BatchResultDTO<MediumDO> guessLike(UserDO userDO);
}
