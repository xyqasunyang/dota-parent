package com.cd.dota.web.application;

import java.util.HashMap;

import com.cd.dota.common.WebClient;

public class Test {

	public static void main(String[] args) throws Exception {
		HashMap<String,Object> param = new HashMap<String,Object>();
//		param.put("wsdl", "getRiskList");
		param.put("uid", "HZDATA20161020");
		param.put("pwd", "12345678");
		param.put("ent_name", "上海仓鼎商务信息咨询有限公司");
		String str = WebClient.sendPost("http://www.zhyxy.net/services/RiskListInfoService?wsdl", param);
		System.out.println(str);
	}
}
