package com.cd.dota.web.controller;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cd.dota.common.ResultEntity;
import com.cd.dota.common.WebClient;
import com.cd.dota.core.service.ChatService;
import com.cd.dota.core.service.RedisService;
import com.cd.dota.dal.domain.ChatDO;

@Controller
@RequestMapping("/robot")
public class RobotController {

	Logger logger = Logger.getLogger(RobotController.class);
	@Autowired
	RedisService redisService;
	@Autowired
	ChatService chatService;

	@RequestMapping("/robot.html")
	public String robot() {
		return "robot";
	}

	@SuppressWarnings("deprecation")
	@RequestMapping("/robot.do")
	@ResponseBody
	public Object Robot(String info, HttpServletRequest request) {
		ResultEntity result = new ResultEntity(ResultEntity.SUCCESS);
		try {
			String ip = request.getRemoteAddr();
			logger.info(ip + ":" + info);
			ChatDO chatDO = new ChatDO();
			chatDO.setIp(ip);
			chatDO.setContent(info);
			Properties p = new Properties();
			p.load(getClass().getResourceAsStream("/appkey.properties"));
			String key = (String) p.get("robot");
			HashMap<String, Object> param = new HashMap<String, Object>();
			param.put("key", key);
			info = URLEncoder.encode(info);
			param.put("info", info);
			String jsonResult = WebClient.SendGet("http://op.juhe.cn/robot/index", param);
			JSONObject json = new JSONObject(jsonResult);
			// 返回状态码正确时
			if (json.getString("error_code").equals("0")) {
				JSONObject results = json.getJSONObject("result");
				String text = results.getString("text");
				result.setObject(text);
				chatDO.setReply(text.toString());
				chatService.addChat(chatDO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
