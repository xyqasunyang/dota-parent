package com.cd.dota.core.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cd.dota.common.ResultDTO;
import com.cd.dota.core.service.FeedbackService;
import com.cd.dota.dal.domain.FeedbackDO;
import com.cd.dota.dal.persistence.FeedbackDAO;

@Service
public class FeedbackServiceImpl implements FeedbackService {

	Logger logger = Logger.getLogger(FeedbackServiceImpl.class);

	@Autowired
	FeedbackDAO feedbackDAO;

	@Override
	@Transactional
	public ResultDTO<Integer> addFeedback(FeedbackDO feedbackDO) {
		ResultDTO<Integer> result = new ResultDTO<Integer>();
		try {
			int count = feedbackDAO.addFeedback(feedbackDO);
			if (count < 0) {
				result.setSuccess(false);
				result.setErrorDetail("创建反馈失败");
				return result;
			}
			result.setModule(count);
		} catch (Exception e) {
			result.setSuccess(false);
			logger.error("FeedbackServiceImpl -> addFeedback:", e);
		}
		return result;
	}



}
