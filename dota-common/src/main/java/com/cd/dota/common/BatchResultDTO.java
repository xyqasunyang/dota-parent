package com.cd.dota.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author suny
 *
 * @param <T>
 */
public class BatchResultDTO<T> extends BaseResultDTO {

	private static final long serialVersionUID = 2340894710205695287L;

	private Map<Long, String> faileds = new HashMap<Long, String>();// 失败列表
	private List<T> module;

	public List<T> getModule() {
		return module;
	}

	public void setModule(List<T> module) {
		this.module = module;
	}

	/**
	 * 添加操作失败记录到失败列表
	 * 
	 * @param id
	 * @param errorCode
	 */
	public void addFailed(Long id, String errorCode) {
		setResultCode(errorCode);
		faileds.put(id, errorCode);
	}

	/**
	 * 获取操作失败记录以及对应失败的原因
	 * 
	 * @return
	 */
	public Map<Long, String> getFaileds() {
		return faileds;
	}

}
