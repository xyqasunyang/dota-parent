package com.cd.dota.common;

import java.util.HashMap;
import java.util.List;

/**
 * 
 * @author suny
 *
 */
public class ResultEntity extends HashMap<String, Object> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int SUCCESS = 0;
	public static final int ERROR = -1;

	/**
	 * 实例化一个返回实体
	 * 
	 * @param code
	 *            结果代码
	 */
	public ResultEntity(int code) {
		this.put("code", code);
	}

	public ResultEntity() {
	}

	/**
	 * 设置结果代码
	 * 
	 * @param code
	 */
	public void setCode(int code) {
		this.put("code", code);
	}

	public int getCode() {
		return (Integer) this.get("code");
	}

	public void setMsg(String msg){
		this.put("msg",msg);
	}
	
	public String getMsg(){
		return this.getMsg();
	}
	/**
	 * 设置结果集总数量
	 * 
	 * @param code
	 */
	public void setCount(int count) {
		this.put("count", count);
	}

	public int getCount() {
		return (Integer) this.get("count");
	}

	/**
	 * 设置结果集
	 * 
	 * @param list
	 */
	public void setList(List<?> list) {
		this.put("list", list);
	}
	
	public void setObject(Object object) {
		this.put("object", object);
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> getList() {
		return (List<T>) this.get("list");
	}

	/**
	 * 设置错误信息
	 * 
	 * @param errorMsg
	 */
	public void setError(String error) {
		this.put("error", error);
	}

	public String getError(String error) {
		return (String) this.get("error");
	}

	/**
	 * 设置需要跳转的页面地址
	 * 
	 * @param redirect
	 */
	public void setRedirect(String redirect) {
		this.put("redirect", redirect);
	}

	public String getRedirect() {
		return (String) this.get("v");
	}

}
