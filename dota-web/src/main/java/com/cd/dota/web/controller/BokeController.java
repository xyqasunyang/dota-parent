package com.cd.dota.web.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cd.dota.common.BatchResultDTO;
import com.cd.dota.common.ResultDTO;
import com.cd.dota.core.service.ArticleService;
import com.cd.dota.core.service.RedisService;
import com.cd.dota.core.service.UserService;
import com.cd.dota.dal.domain.ArticleDO;

@Controller
@RequestMapping("/blogs")
public class BokeController {

	Logger logger = Logger.getLogger(BokeController.class);
	@Autowired
	RedisService redisService;
	@Autowired
	UserService userService;
	@Autowired
	ArticleService articleService;

	@RequestMapping("/index.html")
	public String joke(ModelMap map) {
		BatchResultDTO<ArticleDO> resultDTO = articleService.selectArticles(null);
		map.put("articles", resultDTO.getModule());
		return "screen/index";
	}

	@RequestMapping("/share.html")
	public String share(ModelMap map) {
		return "screen/share";
	}

	@RequestMapping("/java.html")
	public String java(ModelMap map,ArticleDO article) {
		article.setClassify(0);
		BatchResultDTO<ArticleDO> resultDTO = articleService.selectArticles(article);
		map.put("articles", resultDTO.getModule());
		return "screen/java";
	}

	@RequestMapping("/html.html")
	public String html(ModelMap map,ArticleDO article) {
		article.setClassify(1);
		BatchResultDTO<ArticleDO> resultDTO = articleService.selectArticles(article);
		map.put("articles", resultDTO.getModule());
		return "screen/html";
	}

	@RequestMapping("/linux.html")
	public String linux(ModelMap map,ArticleDO article) {
		article.setClassify(2);
		BatchResultDTO<ArticleDO> resultDTO = articleService.selectArticles(article);
		map.put("articles", resultDTO.getModule());
		return "screen/linux";
	}

	@RequestMapping("/work.html")
	public String work(ModelMap map) {
		return "screen/work";
	}

	@RequestMapping("/about.html")
	public String about(ModelMap map) {
		return "screen/about";
	}
	
	@RequestMapping("/article{id}.html")
	public String addArticle(ModelMap map,@PathVariable("id") Integer id) {
		ResultDTO<ArticleDO> result = articleService.selectArticleById(id);
		map.put("article", result.getModule());
		return "screen/article";
	}

}
