package com.cd.dota.core.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cd.dota.common.BatchResultDTO;
import com.cd.dota.common.ResultDTO;
import com.cd.dota.core.service.UserNewsRelationService;
import com.cd.dota.dal.domain.MediumDO;
import com.cd.dota.dal.domain.NewsDO;
import com.cd.dota.dal.domain.UserNewsRelationDO;
import com.cd.dota.dal.persistence.MediumDAO;
import com.cd.dota.dal.persistence.NewsDAO;
import com.cd.dota.dal.persistence.UserNewsRelationDAO;

@Service
public class UserNewsRelationServiceImpl implements UserNewsRelationService {

	Logger logger = Logger.getLogger(UserNewsRelationServiceImpl.class);

	@Autowired
	UserNewsRelationDAO userNewsRelationDAO;
	@Autowired
	MediumDAO mediumDAO;
	@Autowired
	NewsDAO newsDAO;

	@Override
	@Transactional
	public ResultDTO<Integer> addUserNewsRelation(UserNewsRelationDO userNewsRelationDO) {
		ResultDTO<Integer> result = new ResultDTO<Integer>();
		try {
			int count = userNewsRelationDAO.addUserNewsRelation(userNewsRelationDO);
			if (count < 0) {
				result.setSuccess(false);
				result.setErrorDetail("创建用户失败");
				return result;
			}
			result.setModule(count);
		} catch (Exception e) {
			result.setSuccess(false);
			logger.error("UserNewsRelationServiceImpl -> addUserNewsRelation:", e);
		}
		return result;
	}

	@Override
	@Transactional
	public ResultDTO<Integer> updateUserNewsRelation(UserNewsRelationDO userNewsRelationDO) {
		ResultDTO<Integer> result = new ResultDTO<Integer>();
		try {
			int count = userNewsRelationDAO.updateUserNewsRelation(userNewsRelationDO);
			if (count < 0) {
				result.setSuccess(false);
				result.setErrorDetail("修改用户失败");
				return result;
			}
			result.setModule(count);
		} catch (Exception e) {
			result.setSuccess(false);
			logger.error("UserNewsRelationServiceImpl -> updateUserNewsRelation:", e);
		}
		return result;
	}

	@Override
	public ResultDTO<UserNewsRelationDO> selectUserNewsRelationById(Integer userNewsRelationId) {
		ResultDTO<UserNewsRelationDO> result = new ResultDTO<UserNewsRelationDO>();
		try {
			UserNewsRelationDO userNewsRelationDO = userNewsRelationDAO.selectUserNewsRelationById(userNewsRelationId);
			if (null == userNewsRelationDO) {
				result.setSuccess(false);
				result.setErrorDetail("获取用户失败");
				return result;
			}
			result.setModule(userNewsRelationDO);
		} catch (Exception e) {
			result.setSuccess(false);
			logger.error("UserNewsRelationServiceImpl -> selectUserNewsRelationById:", e);
		}
		return result;
	}

	@Override
	public BatchResultDTO<UserNewsRelationDO> selectUserNewsRelations(UserNewsRelationDO userNewsRelationDO) {
		BatchResultDTO<UserNewsRelationDO> result = new BatchResultDTO<UserNewsRelationDO>();
		try {
			List<UserNewsRelationDO> userNewsRelationDOs = userNewsRelationDAO.selectUserNewsRelations(userNewsRelationDO);
			result.setModule(userNewsRelationDOs);
		} catch (Exception e) {
			result.setSuccess(false);
			logger.error("UserNewsRelationServiceImpl -> selectUserNewsRelations:", e);
		}
		return result;
	}

	@Override
	public BatchResultDTO<UserNewsRelationDO> getCollection(UserNewsRelationDO userNewsRelationDO) {
		BatchResultDTO<UserNewsRelationDO> result = new BatchResultDTO<UserNewsRelationDO>();
		try {
			List<UserNewsRelationDO> userNewsRelationDOs = userNewsRelationDAO.selectUserNewsRelations(userNewsRelationDO);
			for (UserNewsRelationDO userNewsRelation : userNewsRelationDOs) {
				NewsDO news = newsDAO.selectNewsById(userNewsRelation.getNewsId());
				if (news == null)
					continue;
				userNewsRelation.setNewsCover(news.getCover());
				userNewsRelation.setNewsTitle(news.getTitle());
				MediumDO medium = mediumDAO.selectMediumById(userNewsRelation.getMediumId());
				if (medium == null)
					continue;
				userNewsRelation.setMediumLogo(medium.getLogo());
				userNewsRelation.setMediumName(medium.getName());
			}
			result.setModule(userNewsRelationDOs);
		} catch (Exception e) {
			result.setSuccess(false);
			logger.error("UserNewsRelationServiceImpl -> selectUserNewsRelations:", e);
		}
		return result;
	}

}
