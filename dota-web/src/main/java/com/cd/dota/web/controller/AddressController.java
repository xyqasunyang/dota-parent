package com.cd.dota.web.controller;

import java.util.HashMap;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cd.dota.common.ResultEntity;
import com.cd.dota.common.WebClient;

@Controller
@RequestMapping("/address")
public class AddressController {

	
	Logger logger = Logger.getLogger(AddressController.class);
	
	
	
	@RequestMapping("/address.do")
	@ResponseBody
	public Object address(String latitude,String longitude, HttpServletRequest request) {
		ResultEntity result = new ResultEntity(ResultEntity.SUCCESS);
		try {
			Properties p = new Properties();
			p.load(getClass().getResourceAsStream("/appkey.properties"));
			String ak = (String) p.get("address");
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("output", "json");
			map.put("ak", ak);
			map.put("location", latitude+","+longitude);
			String returnResult = WebClient.SendGet("http://api.map.baidu.com/geocoder/v2/", map);
			JSONObject json = new JSONObject(returnResult);
			String address = json.getJSONObject("result").getString("formatted_address");
			result.setObject(address);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
