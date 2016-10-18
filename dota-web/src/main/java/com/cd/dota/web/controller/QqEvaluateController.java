package com.cd.dota.web.controller;

import java.util.HashMap;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cd.dota.common.WebClient;
import com.cd.dota.core.service.RedisService;

@Controller
@RequestMapping("/qq")
public class QqEvaluateController {

	Logger logger = Logger.getLogger(QqEvaluateController.class);
	@Autowired
	RedisService redisService;
	
	
	@RequestMapping("/evaluate.do")
	public String evaluate(String qq) {
		try {
			String qqResult = redisService.get(qq);
			if(qqResult!=null){
				return "";
			}
			Properties p = new Properties();
			p.load(getClass().getResourceAsStream("/appkey.properties"));
			String key = (String) p.get("qq");
			HashMap<String, Object> param = new HashMap<String, Object>();
			param.put("key", key);
			param.put("qq", qq);
			String jsonResult = WebClient.SendGet("http://japi.juhe.cn/qqevaluate/qq", param);
			JSONObject json = new JSONObject(jsonResult);
			//返回状态码正确时
			if(json.getString("error_code").equals("0")){
				JSONObject result = json.getJSONObject("result");
				JSONObject data = result.getJSONObject("data");
				String conclusion = data.getString("conclusion");
				String analysis = data.getString("analysis");
				System.out.println("conclusion:"+conclusion+",analysis:"+analysis);
				redisService.set(qq, conclusion+"|"+analysis);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "index";
	}
	
}
