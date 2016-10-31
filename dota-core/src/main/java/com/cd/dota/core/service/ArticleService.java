package com.cd.dota.core.service;

import com.cd.dota.common.BatchResultDTO;
import com.cd.dota.common.ResultDTO;
import com.cd.dota.dal.domain.ArticleDO;

public interface ArticleService {
	public ResultDTO<Integer> addArticle(ArticleDO articleDO);

	public ResultDTO<Integer> updateArticle(ArticleDO articleDO);

	public ResultDTO<ArticleDO> selectArticleById(Integer articleId);

	public BatchResultDTO<ArticleDO> selectArticles(ArticleDO articleDO);
	
}
