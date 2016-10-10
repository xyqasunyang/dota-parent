package com.cd.dota.core.service;

import com.cd.dota.common.ResultDTO;
import com.cd.dota.dal.domain.FeedbackDO;

public interface FeedbackService {
	public ResultDTO<Integer> addFeedback(FeedbackDO feedbackDO);
	
}
