package com.cd.dota.core.service;

import com.cd.dota.common.ResultDTO;
import com.cd.dota.dal.domain.ChatDO;

public interface ChatService {

	public ResultDTO<Integer> addChat(ChatDO chatDO);

}
