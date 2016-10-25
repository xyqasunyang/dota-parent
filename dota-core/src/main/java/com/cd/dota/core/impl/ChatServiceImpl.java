package com.cd.dota.core.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cd.dota.common.ResultDTO;
import com.cd.dota.core.service.ChatService;
import com.cd.dota.dal.domain.ChatDO;
import com.cd.dota.dal.persistence.ChatDAO;

@Service
public class ChatServiceImpl implements ChatService {

	Logger logger = Logger.getLogger(ChatServiceImpl.class);

	@Autowired
	ChatDAO ChatDAO;

	@Override
	@Transactional
	public ResultDTO<Integer> addChat(ChatDO ChatDO) {
		ResultDTO<Integer> result = new ResultDTO<Integer>();
		try {
			int count = ChatDAO.addChat(ChatDO);
			if (count < 0) {
				result.setSuccess(false);
				result.setErrorDetail("创建反馈失败");
				return result;
			}
			result.setModule(count);
		} catch (Exception e) {
			result.setSuccess(false);
			logger.error("ChatServiceImpl -> addChat:", e);
		}
		return result;
	}



}
