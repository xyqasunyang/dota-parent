package com.cd.dota.web.controller;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cd.dota.common.BatchResultDTO;
import com.cd.dota.common.ResultDTO;
import com.cd.dota.common.ResultEntity;
import com.cd.dota.core.service.ArticleService;
import com.cd.dota.dal.domain.ArticleDO;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	Logger logger = Logger.getLogger(BokeController.class);
	@Autowired
	ArticleService articleService;
	
	
	@RequestMapping("/admin.html")
	public String joke(ModelMap map) {
		return "screen/admin";
	}
	
	
	@RequestMapping("/addArticle.do")
	@ResponseBody
	public Object addArticle(ArticleDO articleDO, HttpSession session) {
		ResultEntity result = new ResultEntity(ResultEntity.SUCCESS);
		ResultDTO<Integer> resultDTO = articleService.addArticle(articleDO);
		if (resultDTO.isFailed()) {
			result.setCode(ResultEntity.ERROR);
			result.setError(resultDTO.getErrorDetail());
			return result;
		}
		return result;
	}
	
	@RequestMapping("/addArticle.html")
	public String addArticle(ModelMap map) {
		return "screen/addArticle";
	}
	
	@RequestMapping("/addArticleContent.html")
	public String addArticleContent(ModelMap map) {
		return "screen/addArticleContent";
	}
	
	@RequestMapping("/articleManage.html")
	public String articleManage(ModelMap map) {
		BatchResultDTO<ArticleDO> resultDTO = articleService.selectArticles(null);
		map.put("articles", resultDTO.getModule());
		return "screen/articleManage";
	}
}
