package com.cd.dota.dal.persistence;

import java.util.List;

import com.cd.dota.dal.domain.NewsDO;
public interface NewsDAO {
	
	public int addNews(NewsDO newsDO);

	public int updateNews(NewsDO newsDO) ;
	
	public NewsDO selectNewsById(Integer newsId);
	
	public List<NewsDO> selectNewss(NewsDO newsDO);
}
