package com.cd.dota.core.service;

import com.cd.dota.common.BatchResultDTO;
import com.cd.dota.common.ResultDTO;
import com.cd.dota.dal.domain.NewsDO;

public interface NewsService {
	public ResultDTO<Integer> addNews(NewsDO newsDO);

	public ResultDTO<Integer> updateNews(NewsDO newsDO);
	
	public ResultDTO<Integer> isLiked(Integer userId,Integer newsId,Integer isLiked);

	public ResultDTO<NewsDO> selectNewsById(Integer newsId,Integer userId);

	public BatchResultDTO<NewsDO> selectNewss(NewsDO newsDO);
	
}
