package com.cd.dota.core.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cd.dota.common.BatchResultDTO;
import com.cd.dota.common.ResultDTO;
import com.cd.dota.core.service.UserMediumRelationService;
import com.cd.dota.dal.domain.UserMediumRelationDO;
import com.cd.dota.dal.persistence.MediumDAO;
import com.cd.dota.dal.persistence.UserMediumRelationDAO;

@Service
public class UserMediumRelationServiceImpl implements UserMediumRelationService {

	Logger logger = Logger.getLogger(UserMediumRelationServiceImpl.class);

	@Autowired
	UserMediumRelationDAO userMediumRelationDAO;
	@Autowired
	MediumDAO mediumDAO;

	@Override
	@Transactional
	public ResultDTO<Integer> addUserMediumRelation(UserMediumRelationDO userMediumRelationDO) {
		ResultDTO<Integer> result = new ResultDTO<Integer>();
		try {
			List<UserMediumRelationDO> list = userMediumRelationDAO.selectUserMediumRelations(userMediumRelationDO);
			if (list.isEmpty()) {
				// 如果为空，新增关系
				userMediumRelationDAO.addUserMediumRelation(userMediumRelationDO);
			} else {
				// 不为空则更新
				userMediumRelationDO.setUserMediumRelationId(list.get(0).getUserMediumRelationId());
				userMediumRelationDAO.updateUserMediumRelation(userMediumRelationDO);
			}
		} catch (Exception e) {
			result.setSuccess(false);
			logger.error("UserMediumRelationServiceImpl -> addUserMediumRelation:", e);
		}
		return result;
	}

	@Override
	@Transactional
	public ResultDTO<Integer> addUserMediumRelations(List<UserMediumRelationDO> userMediumRelationDOs) {
		ResultDTO<Integer> result = new ResultDTO<Integer>();
		try {
			userMediumRelationDAO.addUserMediumRelations(userMediumRelationDOs);
		} catch (Exception e) {
			result.setSuccess(false);
			logger.error("UserMediumRelationServiceImpl -> addUserMediumRelations:", e);
		}
		return result;
	}

	@Override
	@Transactional
	public ResultDTO<Integer> updateUserMediumRelation(UserMediumRelationDO userMediumRelationDO) {
		ResultDTO<Integer> result = new ResultDTO<Integer>();
		try {
			int count = userMediumRelationDAO.updateUserMediumRelation(userMediumRelationDO);
			if (count < 0) {
				result.setSuccess(false);
				result.setErrorDetail("修改用户媒体关系失败");
				return result;
			}
			result.setModule(count);
		} catch (Exception e) {
			result.setSuccess(false);
			logger.error("UserMediumRelationServiceImpl -> updateUserMediumRelation:", e);
		}
		return result;
	}

	@Override
	public ResultDTO<UserMediumRelationDO> selectUserMediumRelationById(Integer userMediumRelationId) {
		ResultDTO<UserMediumRelationDO> result = new ResultDTO<UserMediumRelationDO>();
		try {
			UserMediumRelationDO userMediumRelationDO = userMediumRelationDAO.selectUserMediumRelationById(userMediumRelationId);
			if (null == userMediumRelationDO) {
				result.setSuccess(false);
				result.setErrorDetail("获取用户媒体关系失败");
				return result;
			}
			result.setModule(userMediumRelationDO);
		} catch (Exception e) {
			result.setSuccess(false);
			logger.error("UserMediumRelationServiceImpl -> selectUserMediumRelationById:", e);
		}
		return result;
	}

	@Override
	public BatchResultDTO<UserMediumRelationDO> selectUserMediumRelations(UserMediumRelationDO userMediumRelationDO) {
		BatchResultDTO<UserMediumRelationDO> result = new BatchResultDTO<UserMediumRelationDO>();
		try {
			List<UserMediumRelationDO> userMediumRelationDOs = userMediumRelationDAO.selectUserMediumRelations(userMediumRelationDO);
			result.setModule(userMediumRelationDOs);
		} catch (Exception e) {
			result.setSuccess(false);
			logger.error("UserMediumRelationServiceImpl -> selectUserMediumRelations:", e);
		}
		return result;
	}

}
