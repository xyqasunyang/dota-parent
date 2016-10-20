package com.cd.dota.web.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cd.dota.common.SerializeUtil;
import com.cd.dota.common.WebClient;
import com.cd.dota.core.service.RedisService;

@Controller
@RequestMapping("/history")
public class TodayController {

	Logger logger = Logger.getLogger(TodayController.class);
	@Autowired
	RedisService redisService;
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	@RequestMapping("/history.html")
	public String today(ModelMap map) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date today = new Date();
			String time = sdf.format(today);
			String todayResult = redisService.get(time);
			if (todayResult != null) {
				List<HashMap<String, String>> list = (List<HashMap<String, String>>) SerializeUtil.unserialize(redisService.get(time.getBytes()));
				map.put("lists", list);
				return "history";
			}
			Properties p = new Properties();
			p.load(getClass().getResourceAsStream("/appkey.properties"));
			String key = (String) p.get("today");
			HashMap<String, Object> param = new HashMap<String, Object>();
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
				List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
				for (int i = 0; i < result.length(); i++) {
					String des = result.getJSONObject(i).getString("des");
					String pic = result.getJSONObject(i).getString("pic");
					HashMap<String, String> hashMap = new HashMap<String, String>();
					hashMap.put("des", des);
					hashMap.put("pic", pic);
					list.add(hashMap);
				}
				map.put("lists", list);
				redisService.set(time.getBytes(), SerializeUtil.serialize(list),60*60*24);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "history";
	}

}
