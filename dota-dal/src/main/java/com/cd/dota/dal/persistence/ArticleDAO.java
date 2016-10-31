package com.cd.dota.dal.persistence;

import java.util.List;

import com.cd.dota.dal.domain.ArticleDO;

public interface ArticleDAO {
	
	public int addArticle(ArticleDO articleDO);

	public int updateArticle(ArticleDO articleDO) ;
	
	public ArticleDO selectArticleById(Integer articleId);
	
	public List<ArticleDO> selectArticles(ArticleDO articleDO);

}
