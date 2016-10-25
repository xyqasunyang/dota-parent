package com.cd.dota.web.controller;

import java.util.HashMap;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
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
@RequestMapping("/qq")
public class QqEvaluateController {

	Logger logger = Logger.getLogger(QqEvaluateController.class);
	@Autowired
	RedisService redisService;

	@RequestMapping("/qqevaluate.html")
	public String index() {
		return "qqevaluate";
	}

	@RequestMapping("/evaluate.do")
	public String evaluate(String qq, ModelMap map) {
		try {
			String qqResult = redisService.get(qq + "analysis");
			if (qqResult != null) {
				String conclusion = redisService.get(qq + "conclusion").toString();
				String analysis = redisService.get(qq + "analysis").toString();
				map.put("conclusion", conclusion);
				map.put("analysis", analysis);
				return "evaluate";
			}
			Properties p = new Properties();
			p.load(getClass().getResourceAsStream("/appkey.properties"));
			String key = (String) p.get("qq");
			HashMap<String, Object> param = new HashMap<String, Object>();
			param.put("key", key);
			param.put("qq", qq);
			String jsonResult = WebClient.SendGet("http://japi.juhe.cn/qqevaluate/qq", param);
			JSONObject json = new JSONObject(jsonResult);
			// 返回状态码正确时
			if (json.getString("error_code").equals("0")) {
				JSONObject result = json.getJSONObject("result");
				JSONObject data = result.getJSONObject("data");
				String conclusion = data.getString("conclusion");
				String analysis = data.getString("analysis");
				System.out.println("conclusion:" + conclusion + ",analysis:" + analysis);
				map.put("conclusion", conclusion);
				map.put("analysis", analysis);
				redisService.set(qq + "conclusion", conclusion, 60 * 60 * 24);
				redisService.set(qq + "analysis", analysis, 60 * 60 * 24);
			} else {
				map.put("error", "请输入正确的QQ号");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "evaluate";
	}

	@RequestMapping("/tosay.html")
	public String tosay(String qq, ModelMap map, HttpServletRequest request) {
		String ip = request.getRemoteAddr();
		try {
			map.put("ip", ip);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "tosay";
	}

	@RequestMapping("/getIp.do")
	@ResponseBody
	public Object getIp(HttpServletRequest request) {
		ResultEntity result = new ResultEntity(ResultEntity.SUCCESS);
		String ip = request.getRemoteAddr();
		result.put("ip", ip);
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping("/swipe.html")
	public String swipe() {
		return "swipe";
	}

}
