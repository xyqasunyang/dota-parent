package com.cd.dota.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cd.dota.common.WebClient;
import com.cd.dota.core.service.RedisService;

@Controller
@RequestMapping("/qq")
public class TodayController {

	Logger logger = Logger.getLogger(TodayController.class);
	@Autowired
	RedisService redisService;

	@RequestMapping("/today.do")
	@ResponseBody
	public String today(String qq) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time = sdf.format(new Date());
			String todayResult = redisService.get(time);
			if (todayResult != null) {
				return "";
			}
			Properties p = new Properties();
			p.load(getClass().getResourceAsStream("/appkey.properties"));
			String key = (String) p.get("today");
			HashMap<String, Object> param = new HashMap<String, Object>();
			Date today = new Date();
			param.put("key", key);
			param.put("v", "1.0");
			param.put("month", today.getMonth());
			param.put("day", today.getDate());
			String jsonResult = WebClient.SendGet("http://api.juheapi.com/japi/toh", param);
			JSONObject json = new JSONObject(jsonResult);
			// 返回状态码正确时
			if (json.getString("error_code").equals("0")) {
				JSONArray result = json.getJSONArray("result");
				String str = "";
				for (int i = 0; i < result.length(); i++) {
					String des = result.getJSONObject(i).getString("des");
					String pic = result.getJSONObject(i).getString("pic");
					str = des + "|" + pic + "||" + str;
				}
				redisService.set(time, str);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "dota";
	}


}
