package com.cd.dota.core.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cd.dota.common.BatchResultDTO;
import com.cd.dota.common.ResultDTO;
import com.cd.dota.core.service.MediumService;
import com.cd.dota.dal.domain.MediumDO;
import com.cd.dota.dal.domain.UserDO;
import com.cd.dota.dal.domain.UserMediumRelationDO;
import com.cd.dota.dal.persistence.MediumDAO;
import com.cd.dota.dal.persistence.UserMediumRelationDAO;

@Service
public class MediumServiceImpl implements MediumService {

	Logger logger = Logger.getLogger(MediumServiceImpl.class);

	@Autowired
	MediumDAO mediumDAO;
	@Autowired
	UserMediumRelationDAO userMediumRelationDAO;

	@Override
	@Transactional
	public ResultDTO<Integer> addMedium(MediumDO mediumDO) {
		ResultDTO<Integer> result = new ResultDTO<Integer>();
		try {
			int count = mediumDAO.addMedium(mediumDO);
			if (count < 0) {
				result.setSuccess(false);
				result.setErrorDetail("创建媒体失败");
				return result;
			}
			result.setModule(count);
		} catch (Exception e) {
			result.setSuccess(false);
			logger.error("MediumServiceImpl -> addMedium:", e);
		}
		return result;
	}

	@Override
	@Transactional
	public ResultDTO<Integer> updateMedium(MediumDO mediumDO) {
		ResultDTO<Integer> result = new ResultDTO<Integer>();
		try {
			int count = mediumDAO.updateMedium(mediumDO);
			if (count < 0) {
				result.setSuccess(false);
				result.setErrorDetail("修改媒体失败");
				return result;
			}
			result.setModule(count);
		} catch (Exception e) {
			result.setSuccess(false);
			logger.error("MediumServiceImpl -> updateMedium:", e);
		}
		return result;
	}

	@Override
	public ResultDTO<MediumDO> selectMediumById(Integer mediumId) {
		ResultDTO<MediumDO> result = new ResultDTO<MediumDO>();
		try {
			MediumDO mediumDO = mediumDAO.selectMediumById(mediumId);
			if (null == mediumDO) {
				result.setSuccess(false);
				result.setErrorDetail("获取媒体失败");
				return result;
			}
			result.setModule(mediumDO);
		} catch (Exception e) {
			result.setSuccess(false);
			logger.error("MediumServiceImpl -> selectMediumById:", e);
		}
		return result;
	}

	@Override
	public BatchResultDTO<MediumDO> selectMediums(MediumDO mediumDO) {
		BatchResultDTO<MediumDO> result = new BatchResultDTO<MediumDO>();
		try {
			List<MediumDO> mediumDOs = mediumDAO.selectMediums(mediumDO);
			result.setModule(mediumDOs);
		} catch (Exception e) {
			result.setSuccess(false);
			logger.error("MediumServiceImpl -> selectMediums:", e);
		}
		return result;
	}

	@Override
	public BatchResultDTO<MediumDO> selectMediumsByUserId(UserDO userDO) {
		BatchResultDTO<MediumDO> result = new BatchResultDTO<MediumDO>();
		try {
			List<MediumDO> mediumDOs = mediumDAO.selectMediumsByUserId(userDO);
			result.setModule(mediumDOs);
		} catch (Exception e) {
			result.setSuccess(false);
			logger.error("MediumServiceImpl -> selectMediumByUserId:", e);
		}
		return result;
	}

	@Override
	public BatchResultDTO<MediumDO> firstChoose(UserDO userDO) {
		BatchResultDTO<MediumDO> result = new BatchResultDTO<MediumDO>();
		try {
			List<MediumDO> mediumDOs = mediumDAO.firstChoose(userDO);
			result.setModule(mediumDOs);
		} catch (Exception e) {
			result.setSuccess(false);
			logger.error("MediumServiceImpl -> firstChoose:", e);
		}
		return result;
	}

	@Override
	public ResultDTO<MediumDO> getMedium(Integer userId, Integer mediumId) {
		ResultDTO<MediumDO> result = new ResultDTO<MediumDO>();
		try {
			MediumDO mediumDO = mediumDAO.selectMediumById(mediumId);
			if (null == mediumDO) {
				result.setSuccess(false);
				result.setErrorDetail("获取媒体失败");
				return result;
			}
			UserMediumRelationDO userMediumRelationDO = new UserMediumRelationDO();
			userMediumRelationDO.setUserId(userId);
			userMediumRelationDO.setMediumId(mediumId);
			List<UserMediumRelationDO> userMediumRelationDOs = userMediumRelationDAO
					.selectUserMediumRelations(userMediumRelationDO);
			if(userMediumRelationDOs.isEmpty()){
				mediumDO.setIsAttention(0);
			}else{
				mediumDO.setIsAttention(1);
			}
			result.setModule(mediumDO);
		} catch (Exception e) {
			result.setSuccess(false);
			logger.error("MediumServiceImpl -> selectMediumById:", e);
		}
		return result;
	}

	
	@Override
	public BatchResultDTO<MediumDO> guessLike(UserDO userDO) {
		BatchResultDTO<MediumDO> result = new BatchResultDTO<MediumDO>();
		try {
			userDO.setPageIndex(1);
			userDO.setPageSize(3);
			List<MediumDO> mediumDOs = mediumDAO.guessLike(userDO);
			result.setModule(mediumDOs);
		} catch (Exception e) {
			result.setSuccess(false);
			logger.error("MediumServiceImpl -> firstChoose:", e);
		}
		return result;
	}
}
