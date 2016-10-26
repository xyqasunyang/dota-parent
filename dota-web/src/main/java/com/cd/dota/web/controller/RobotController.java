package com.cd.dota.web.controller;

import java.io.IOException;
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
	public Object Robot(String info, String address, HttpServletRequest request) {
		ResultEntity result = new ResultEntity(ResultEntity.SUCCESS);
		try {
			String returnInfo = lianyufan(info);
			String ip = request.getRemoteAddr();
			logger.info(ip + ":" + info);
			ChatDO chatDO = new ChatDO();
			chatDO.setIp(ip);
			chatDO.setAddress(address);
			chatDO.setContent(info);
			Properties p = new Properties();
			p.load(getClass().getResourceAsStream("/appkey.properties"));
			String key = (String) p.get("robot");
			HashMap<String, Object> param = new HashMap<String, Object>();
			param.put("key", key);
			if (!returnInfo.equals(info)) {
				chatDO.setReply(returnInfo);
				result.setObject(returnInfo);
				chatService.addChat(chatDO);
				return result;
			}
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

	public static String lianyufan(String info) throws IOException {
		if (info.contains("连宇凡")|| info.contains("来一发")) {
			int i = (int) (Math.random() * 5);
			switch (i) {
			case 0:
				return "连宇凡是一位待人温和而且非常亲切的人";
			case 1:
				return "你是不是喜欢他？";
			case 2:
				return "你一定很想他";
			case 3:
				return "想他就去找他啊";
			case 4:
				return "我可喜欢他了";
			}
		} else if (info.contains("吴莹莹") || info.contains("511")) {
			int i = (int) (Math.random() * 3);
			switch (i) {
			case 0:
				return "再美再漂亮还是来一发女朋友啊";
			case 1:
				return "这不是来一发女朋友么？";
			case 2:
				return "不要再问了  再美再漂亮还是来一发女朋友啊";
			}
		} else if (info.contains("孙阳")) {

			return "孙阳是大帅哥啊！";

		}
		return info;

	}
}
