package com.cd.dota.common;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

public class WebClient {
	public static String downloadString(String url) throws IOException {
		URL _url = new URL(url);
		InputStream is = _url.openStream();
		Scanner sc = new Scanner(is, "utf-8");
		String result = "";
		while (sc.hasNextLine()) {
			result += sc.nextLine();
		}
		sc.close();
		return result;
	}

	public static byte[] downloadData(String url) throws IOException {
		URL _url = new URL(url);
		URLConnection con = _url.openConnection();
		con.setReadTimeout(10000);
		con.connect();
		Map<String, List<String>> map = con.getHeaderFields();
		BufferedInputStream bis = new BufferedInputStream(con.getInputStream());
		byte[] buffer = new byte[Integer.parseInt(map.get("Content-Length")
				.get(0))];
		int readed = 0;
		while (readed < buffer.length) {
			readed += bis.read(buffer, readed, buffer.length - readed);
		}
		return buffer;
	}

	public static String uploadData(String url, byte[] bytes)
			throws IOException {
		URL _url = new URL(url);
		HttpURLConnection con = (HttpURLConnection) _url.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded");
		con.setDoInput(true);
		con.setDoOutput(true);
		// 握手
		con.connect();
		// post数据
		BufferedOutputStream os = new BufferedOutputStream(
				con.getOutputStream());
		os.write(bytes);
		os.close();
		// 打开输入流
		InputStream is = con.getInputStream();
		Scanner sc = new Scanner(is, "utf-8");
		String result = "";
		while (sc.hasNextLine()) {
			result += sc.nextLine();
		}
		sc.close();
		return result;
	}

	public static String sendPost(String url, HashMap<String, Object> param) {
		String responseMsg = "";

		// 1.构造HttpClient的实例
		HttpClient httpClient = new HttpClient();
		httpClient.getParams().setContentCharset("UTF-8");

		// 2.构造PostMethod的实例
		PostMethod postMethod = new PostMethod(url);
		// postMethod.setRequestHeader("Cookie","security_SESSIONID_sub=1xhd4nuegmfpl84hgume2mjlv");
		// 3.把参数值放入到PostMethod对象中
		if (param != null) {
			for (String key : param.keySet()) {
				postMethod.addParameter(key, String.valueOf(param.get(key)));
			}
		}
		try { // 4.执行postMethod,调用http接口
			httpClient.executeMethod(postMethod);// 200

			// 5.读取内容
			responseMsg = postMethod.getResponseBodyAsString().trim();

			// 6.处理返回的内容

		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally { // 7.释放连接
			postMethod.releaseConnection();
		}
		return responseMsg;
	}

	public static String SendGet(String url, HashMap<String, Object> param) {
		String responseMsg = "";

		HttpClient httpClient = new HttpClient();
		httpClient.getParams().setContentCharset("UTF-8");
		String str = "";
		if (param != null) {
			for (String key : param.keySet()) {
				if (str.equals("")) {
					str = "?" + str + key + "=" + param.get(key);
				} else {
					str = str + "&" + key + "=" + param.get(key);
				}
			}
		}
		GetMethod getMethod = new GetMethod(url + str);
		try {
			httpClient.executeMethod(getMethod);// 200

			responseMsg = getMethod.getResponseBodyAsString().trim();

		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally { // 7.释放连接
			getMethod.releaseConnection();
		}
		return responseMsg;
	}
}
