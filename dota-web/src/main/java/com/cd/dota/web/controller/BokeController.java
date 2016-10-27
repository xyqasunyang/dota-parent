package com.cd.dota.web.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cd.dota.core.service.RedisService;

@Controller
@RequestMapping("/blogs")
public class BokeController {

	
	Logger logger = Logger.getLogger(BokeController.class);
	@Autowired
	RedisService redisService;

	@RequestMapping("/index.html")
	public String Joke(ModelMap map) {
		return "screen/index";
	}
}
