package com.cd.dota.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cd.dota.common.DotaConstant;
import com.cd.dota.common.ResultDTO;
import com.cd.dota.common.ResultEntity;
import com.cd.dota.core.service.FeedbackService;
import com.cd.dota.core.service.UserService;
import com.cd.dota.dal.domain.FeedbackDO;
import com.cd.dota.dal.domain.UserDO;

/**
 * 用户信息，账号注册登录等
 * 
 * @author suny
 *
 */
@RestController
@RequestMapping("/user")
public class UserController {

	Logger logger = Logger.getLogger(UserController.class);

	@Autowired
	UserService userService;
	@Autowired
	FeedbackService feedbackService;

	/**
	 * 登陆
	 * 
	 * @param userDO
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/login.do")
	@ResponseBody
	public Object login(UserDO userDO, HttpSession session) {
		ResultEntity result = new ResultEntity(ResultEntity.SUCCESS);
		ResultDTO<Object> resultDTO = userService.login(userDO, true);
		if (resultDTO.isFailed()) {
			result.setCode(ResultEntity.ERROR);
			result.setError(resultDTO.getErrorDetail());
			return result;
		}
		session.setAttribute(DotaConstant.LOGINSESSION, resultDTO.getModule());
		return result;
	}

	/**
	 * 初始化无账号密码登录
	 * 
	 * @param userDO
	 * @return
	 */
	@RequestMapping("/init.do")
	public Object init(UserDO userDO, HttpSession session) {
		ResultEntity result = new ResultEntity(ResultEntity.SUCCESS);
		ResultDTO<Object> resultDTO = userService.login(userDO, false);
		if (resultDTO.isFailed()) {
			result.setCode(ResultEntity.ERROR);
			result.setError(resultDTO.getErrorDetail());
			return result;
		}
		session.setAttribute(DotaConstant.LOGINSESSION, resultDTO.getModule());
		result.setObject(resultDTO.getModule());
		return result;
	}

	/**
	 * 反馈
	 * 
	 * @param feedbackDO
	 * @param session
	 * @return
	 */
	@RequestMapping("/feedback.do")
	public Object feedback(FeedbackDO feedbackDO, HttpSession session) {
		ResultEntity result = new ResultEntity(ResultEntity.SUCCESS);
		ResultDTO<Integer> resultDTO = feedbackService.addFeedback(feedbackDO);
		if (resultDTO.isFailed()) {
			result.setCode(ResultEntity.ERROR);
			result.setError(resultDTO.getErrorDetail());
			return result;
		}
		return result;
	}
}
