package com.cd.dota.core.util;

import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.cd.dota.common.WebClient;

@Component
public class GetTeamFromVP {

	//修改入库以及爬取规则
	@Scheduled(cron = "0 0 0 * * ?")
	public static void addGame() throws Exception {
		String str = WebClient.SendGet(
				"http://www.vpgame.com/gateway/v1/match/?callback=jQuery1910012524966542318605_1476342033333&page=1&category=&status=open&limit=20&lang=zh_CN&_=1476108000000",
				null).trim();
		str = str.substring(str.indexOf("{"), str.length() - 1).trim();
		JSONObject json = new JSONObject(str);
		JSONArray array = json.getJSONArray("body");
		for (int i = 0; i < array.length(); i++) {
			String date = array.getJSONObject(i).getString("date");
			String round = array.getJSONObject(i).getString("round");
			Date today = new Date();
			@SuppressWarnings("deprecation")
			int day = today.getDate();
			String stringDay = date.split("-")[2];
			stringDay = stringDay.substring(0, stringDay.length() - 9);
			int dayOfDate = Integer.valueOf(stringDay);
			if (day != dayOfDate)
				continue;
			JSONObject team = array.getJSONObject(i).getJSONObject("team");
			JSONObject teamLeft = team.getJSONObject("left");
			String logoLeft = teamLeft.getString("logo");
			String nameLeft = teamLeft.getString("name");
			JSONObject teamRight = team.getJSONObject("right");
			String logoRight = teamRight.getString("logo");
			String nameRight = teamRight.getString("name");
			System.out.println("时间：" + date + "," + "战队：" + nameLeft + " VS " + nameRight + "," + round);
			System.out.println(logoLeft+logoRight);
		}
	}
}
