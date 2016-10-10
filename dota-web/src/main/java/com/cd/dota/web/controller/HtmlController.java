package com.cd.dota.web.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 用于html的跳转
 * @author suny
 *
 */
//@Controller
public class HtmlController {

	@RequestMapping(method = RequestMethod.GET, value = { "/*.html" })
	public String change(HttpSession session, HttpServletRequest request, HttpServletResponse response, ModelMap map) {
		String url = request.getRequestURI();
		Pattern p = Pattern.compile("/(.+?)\\.html");
		Matcher matcher = p.matcher(url);
		matcher.find();
		String template = matcher.group(1);
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Cache-Control", "no-store");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		return template;
	}
}
