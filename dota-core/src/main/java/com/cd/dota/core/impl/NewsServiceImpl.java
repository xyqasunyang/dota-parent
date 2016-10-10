package com.cd.dota.core.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cd.dota.common.BatchResultDTO;
import com.cd.dota.common.ResultDTO;
import com.cd.dota.core.service.NewsService;
import com.cd.dota.core.service.RedisService;
import com.cd.dota.dal.domain.NewsDO;
import com.cd.dota.dal.domain.UserNewsRelationDO;
import com.cd.dota.dal.persistence.MediumDAO;
import com.cd.dota.dal.persistence.NewsDAO;
import com.cd.dota.dal.persistence.UserNewsRelationDAO;

@Service
public class NewsServiceImpl implements NewsService {

	Logger logger = Logger.getLogger(NewsServiceImpl.class);

	@Autowired
	NewsDAO newsDAO;
	@Autowired
	MediumDAO mediumDAO;
	@Autowired
	UserNewsRelationDAO userNewsRelationDAO;
	@Autowired
	RedisService redisService;

	@Override
	@Transactional
	public ResultDTO<Integer> addNews(NewsDO newsDO) {
		ResultDTO<Integer> result = new ResultDTO<Integer>();
		try {
			int count = newsDAO.addNews(newsDO);
			if (count < 0) {
				result.setSuccess(false);
				result.setErrorDetail("创建新闻失败");
				return result;
			}
			result.setModule(count);
		} catch (Exception e) {
			result.setSuccess(false);
			logger.error("NewsServiceImpl -> addNews:", e);
		}
		return result;
	}

	@Override
	@Transactional
	public ResultDTO<Integer> updateNews(NewsDO newsDO) {
		ResultDTO<Integer> result = new ResultDTO<Integer>();
		try {
			int count = newsDAO.updateNews(newsDO);
			if (count < 0) {
				result.setSuccess(false);
				result.setErrorDetail("修改新闻失败");
				return result;
			}
			result.setModule(count);
		} catch (Exception e) {
			result.setSuccess(false);
			logger.error("NewsServiceImpl -> updateNews:", e);
		}
		return result;
	}

	@Override
	public ResultDTO<NewsDO> selectNewsById(Integer newsId,Integer userId) {
		ResultDTO<NewsDO> result = new ResultDTO<NewsDO>();
		try {
			NewsDO newsDO = newsDAO.selectNewsById(newsId);
			if (null == newsDO) {
				result.setSuccess(false);
				result.setErrorDetail("获取新闻失败");
				return result;
			}
			UserNewsRelationDO userNewsRelationDO = new UserNewsRelationDO();
			userNewsRelationDO.setUserId(userId);
			userNewsRelationDO.setNewsId(newsId);
			userNewsRelationDO.setIsLike(1);
			List<UserNewsRelationDO> isLike = userNewsRelationDAO.selectUserNewsRelations(userNewsRelationDO);
			if(isLike.isEmpty()){
				newsDO.setIsLike(0);
			}else{
				newsDO.setIsLike(1);
			}
			userNewsRelationDO.setIsLike(null);
			userNewsRelationDO.setIsCollect(1);
			List<UserNewsRelationDO> isCollect = userNewsRelationDAO.selectUserNewsRelations(userNewsRelationDO);
			if(isCollect.isEmpty()){
				newsDO.setIsCollect(0);
			}else{
				newsDO.setIsCollect(1);
			}
			result.setModule(newsDO);
		} catch (Exception e) {
			result.setSuccess(false);
			logger.error("NewsServiceImpl -> selectNewsById:", e);
		}
		return result;
	}

	@Override
	public BatchResultDTO<NewsDO> selectNewss(NewsDO newsDO) {
		BatchResultDTO<NewsDO> result = new BatchResultDTO<NewsDO>();
		try {
			List<NewsDO> newsDOs = newsDAO.selectNewss(newsDO);
			result.setModule(newsDOs);
		} catch (Exception e) {
			result.setSuccess(false);
			logger.error("NewsServiceImpl -> selectNewss:", e);
		}
		return result;
	}

	@Override
	@Transactional
	public ResultDTO<Integer> isLiked(Integer userId,Integer newsId, Integer isLiked) {
		ResultDTO<Integer> result = new ResultDTO<Integer>();
		try {
			NewsDO newsDO = newsDAO.selectNewsById(newsId);
			UserNewsRelationDO userNewsRelationDO = new UserNewsRelationDO();
			userNewsRelationDO.setUserId(userId);
			userNewsRelationDO.setNewsId(newsId);
			userNewsRelationDO.setIsLike(1);
			List<UserNewsRelationDO> resultList = userNewsRelationDAO.selectUserNewsRelations(userNewsRelationDO);
			if(isLiked==0&&!resultList.isEmpty()){
				//取消点赞
				newsDO.setLikeQuantity(newsDO.getLikeQuantity()-1);
				resultList.get(0).setIsDelete(1);
				userNewsRelationDAO.updateUserNewsRelation(resultList.get(0));
			}else if(isLiked==1&&resultList.isEmpty()){
				newsDO.setLikeQuantity(newsDO.getLikeQuantity()+1);
				userNewsRelationDO.setMediumId(newsDO.getMediumId());
				userNewsRelationDAO.addUserNewsRelation(userNewsRelationDO);
			}
			newsDAO.updateNews(newsDO);
			//将点赞数存入redis中
//			redisService.set(dotaConstant.LIKEQUANTITY, newsDO.getLikeQuantity().toString());
			result.setModule(newsDO.getLikeQuantity());
		} catch (Exception e) {
			result.setSuccess(false);
			logger.error("NewsServiceImpl -> isLiked:", e);
		}
		return result;
	}



}
