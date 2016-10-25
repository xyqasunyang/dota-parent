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

import com.cd.dota.common.ResultEntity;
import com.cd.dota.common.WebClient;
import com.cd.dota.core.service.RedisService;

@Controller
@RequestMapping("/joke")
public class JokeController {

	Logger logger = Logger.getLogger(JokeController.class);
	@Autowired
	RedisService redisService;

	@RequestMapping("/joke.html")
	public String Joke(ModelMap map) {
		try {
			SimpleDateFormat today = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
			String day = today.format(new Date());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = sdf.parse(day);
			String dateStr = date.getTime()+"";
			dateStr = dateStr.substring(0, 10);
			Properties p = new Properties();
			p.load(getClass().getResourceAsStream("/appkey.properties"));
			String key = (String) p.get("joke");
			HashMap<String, Object> param = new HashMap<String, Object>();
			param.put("key", key);
			param.put("page", 1);
			param.put("pagesize", 20);
			param.put("sort", "desc");
			param.put("time", dateStr);
			String jsonResult = WebClient.SendGet("http://japi.juhe.cn/joke/content/list.from", param);
			JSONObject json = new JSONObject(jsonResult);
			// 返回状态码正确时
			if (json.getString("error_code").equals("0")) {
				JSONObject result = json.getJSONObject("result");
				JSONArray data = result.getJSONArray("data");
				List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
				for (int i = 0; i < data.length(); i++) {
					String content = data.getJSONObject(i).getString("content");
					String updatetime = data.getJSONObject(i).getString("updatetime");
					HashMap<String, String> hashMap = new HashMap<String, String>();
					hashMap.put("content", content);
					hashMap.put("updatetime", updatetime);
					list.add(hashMap);
				}
				map.put("lists", list);
				// redisService.set(time, str);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "joke";
	}
	
	@RequestMapping("/joke.do")
	@ResponseBody
	public Object Jokedo(ModelMap map,Integer page) {
		ResultEntity results = new ResultEntity(ResultEntity.SUCCESS);
		try {
			SimpleDateFormat today = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
			String day = today.format(new Date());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = sdf.parse(day);
			String dateStr = date.getTime()+"";
			dateStr = dateStr.substring(0, 10);
			Properties p = new Properties();
			p.load(getClass().getResourceAsStream("/appkey.properties"));
			String key = (String) p.get("joke");
			HashMap<String, Object> param = new HashMap<String, Object>();
			param.put("key", key);
			param.put("page", page);
			param.put("pagesize", 20);
			param.put("sort", "desc");
			param.put("time", dateStr);
			String jsonResult = WebClient.SendGet("http://japi.juhe.cn/joke/content/list.from", param);
			JSONObject json = new JSONObject(jsonResult);
			// 返回状态码正确时
			if (json.getString("error_code").equals("0")) {
				JSONObject result = json.getJSONObject("result");
				JSONArray data = result.getJSONArray("data");
				List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
				for (int i = 0; i < data.length(); i++) {
					String content = data.getJSONObject(i).getString("content");
					String updatetime = data.getJSONObject(i).getString("updatetime");
					HashMap<String, String> hashMap = new HashMap<String, String>();
					hashMap.put("content", content);
					hashMap.put("updatetime", updatetime);
					list.add(hashMap);
				}
				map.put("lists", list);
				results.setList(list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}

}
