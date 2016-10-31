package com.cd.dota.web.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cd.dota.common.BatchResultDTO;
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
	public String java(ModelMap map) {
		return "screen/java";
	}

	@RequestMapping("/html.html")
	public String html(ModelMap map) {
		return "screen/html";
	}

	@RequestMapping("/linux.html")
	public String linux(ModelMap map) {
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

}
