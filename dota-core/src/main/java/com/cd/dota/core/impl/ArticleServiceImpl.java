package com.cd.dota.core.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cd.dota.common.BatchResultDTO;
import com.cd.dota.common.ResultDTO;
import com.cd.dota.core.service.ArticleService;
import com.cd.dota.dal.domain.ArticleDO;
import com.cd.dota.dal.persistence.MediumDAO;
import com.cd.dota.dal.persistence.ArticleDAO;

@Service
public class ArticleServiceImpl implements ArticleService {

	Logger logger = Logger.getLogger(ArticleServiceImpl.class);

	@Autowired
	ArticleDAO articleDAO;
	@Autowired
	MediumDAO mediumDAO;

	@Override
	@Transactional
	public ResultDTO<Integer> addArticle(ArticleDO articleDO) {
		ResultDTO<Integer> result = new ResultDTO<Integer>();
		try {
			int count = articleDAO.addArticle(articleDO);
			if (count < 0) {
				result.setSuccess(false);
				result.setErrorDetail("创建用户失败");
				return result;
			}
			result.setModule(count);
		} catch (Exception e) {
			result.setSuccess(false);
			logger.error("ArticleServiceImpl -> addArticle:", e);
		}
		return result;
	}

	@Override
	@Transactional
	public ResultDTO<Integer> updateArticle(ArticleDO articleDO) {
		ResultDTO<Integer> result = new ResultDTO<Integer>();
		try {
			int count = articleDAO.updateArticle(articleDO);
			if (count < 0) {
				result.setSuccess(false);
				result.setErrorDetail("修改用户失败");
				return result;
			}
			result.setModule(count);
		} catch (Exception e) {
			result.setSuccess(false);
			logger.error("ArticleServiceImpl -> updateArticle:", e);
		}
		return result;
	}

	@Override
	public ResultDTO<ArticleDO> selectArticleById(Integer articleId) {
		ResultDTO<ArticleDO> result = new ResultDTO<ArticleDO>();
		try {
			ArticleDO articleDO = articleDAO.selectArticleById(articleId);
			if (null == articleDO) {
				result.setSuccess(false);
				result.setErrorDetail("获取用户失败");
				return result;
			}
			result.setModule(articleDO);
		} catch (Exception e) {
			result.setSuccess(false);
			logger.error("ArticleServiceImpl -> selectArticleById:", e);
		}
		return result;
	}

	@Override
	public BatchResultDTO<ArticleDO> selectArticles(ArticleDO articleDO) {
		BatchResultDTO<ArticleDO> result = new BatchResultDTO<ArticleDO>();
		try {
			List<ArticleDO> articleDOs = articleDAO.selectArticles(articleDO);
			result.setModule(articleDOs);
		} catch (Exception e) {
			result.setSuccess(false);
			logger.error("ArticleServiceImpl -> selectArticles:", e);
		}
		return result;
	}


}
