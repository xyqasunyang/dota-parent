package com.cd.dota.core.service;

import com.cd.dota.common.BatchResultDTO;
import com.cd.dota.common.ResultDTO;
import com.cd.dota.dal.domain.UserDO;

public interface UserService {
	public ResultDTO<Integer> addUser(UserDO userDO);

	public ResultDTO<Integer> updateUser(UserDO userDO);

	public ResultDTO<UserDO> selectUserById(Integer userId);

	public BatchResultDTO<UserDO> selectUsers(UserDO userDO);
	
	public ResultDTO<Object> login(UserDO userDO,Boolean hasPassword);
}
