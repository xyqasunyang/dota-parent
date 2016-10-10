package com.cd.dota.core.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cd.dota.common.BatchResultDTO;
import com.cd.dota.common.ResultDTO;
import com.cd.dota.core.service.UserService;
import com.cd.dota.core.util.UserValidateUtil;
import com.cd.dota.dal.domain.UserDO;
import com.cd.dota.dal.persistence.MediumDAO;
import com.cd.dota.dal.persistence.UserDAO;

@Service
public class UserServiceImpl implements UserService {

	Logger logger = Logger.getLogger(UserServiceImpl.class);

	@Autowired
	UserDAO userDAO;
	@Autowired
	MediumDAO mediumDAO;

	@Override
	@Transactional
	public ResultDTO<Integer> addUser(UserDO userDO) {
		ResultDTO<Integer> result = new ResultDTO<Integer>();
		try {
			int count = userDAO.addUser(userDO);
			if (count < 0) {
				result.setSuccess(false);
				result.setErrorDetail("创建用户失败");
				return result;
			}
			result.setModule(count);
		} catch (Exception e) {
			result.setSuccess(false);
			logger.error("UserServiceImpl -> addUser:", e);
		}
		return result;
	}

	@Override
	@Transactional
	public ResultDTO<Integer> updateUser(UserDO userDO) {
		ResultDTO<Integer> result = new ResultDTO<Integer>();
		try {
			int count = userDAO.updateUser(userDO);
			if (count < 0) {
				result.setSuccess(false);
				result.setErrorDetail("修改用户失败");
				return result;
			}
			result.setModule(count);
		} catch (Exception e) {
			result.setSuccess(false);
			logger.error("UserServiceImpl -> updateUser:", e);
		}
		return result;
	}

	@Override
	public ResultDTO<UserDO> selectUserById(Integer userId) {
		ResultDTO<UserDO> result = new ResultDTO<UserDO>();
		try {
			UserDO userDO = userDAO.selectUserById(userId);
			if (null == userDO) {
				result.setSuccess(false);
				result.setErrorDetail("获取用户失败");
				return result;
			}
			result.setModule(userDO);
		} catch (Exception e) {
			result.setSuccess(false);
			logger.error("UserServiceImpl -> selectUserById:", e);
		}
		return result;
	}

	@Override
	public BatchResultDTO<UserDO> selectUsers(UserDO userDO) {
		BatchResultDTO<UserDO> result = new BatchResultDTO<UserDO>();
		try {
			List<UserDO> userDOs = userDAO.selectUsers(userDO);
			result.setModule(userDOs);
		} catch (Exception e) {
			result.setSuccess(false);
			logger.error("UserServiceImpl -> selectUsers:", e);
		}
		return result;
	}

	@Override
	@Transactional
	public ResultDTO<Object> login(UserDO userDO, Boolean hasPassword) {
		ResultDTO<Object> result = new ResultDTO<Object>();
		try {
			// 用户名密码登录
			if (hasPassword) {
				// 判断用户名密码是否合规
				boolean isEmpty = UserValidateUtil.validateUser(userDO.getAccount(), userDO.getPassword());
				if (isEmpty) {
					List<UserDO> userDOs = userDAO.selectUsers(userDO);
					if (!userDOs.isEmpty()) {
						result.setModule(userDOs.get(0));
						return result;
					}
				}
				result.setSuccess(false);
				result.setErrorDetail("用户名密码错误");
			} else {
				// 硬件号登录
				if (userDO.getHardwareNumber() != null && !"".equals(userDO.getHardwareNumber())) {
					List<UserDO> userDOs = userDAO.selectUsers(userDO);
					// 硬件号查询，已存在直接登录，不存在则创建
					if (!userDOs.isEmpty()) {
						result.setModule(userDOs.get(0));
						return result;
					} else {
						userDAO.addUser(userDO);
						result.setModule(userDO);
					}
				}
			}
		} catch (Exception e) {
			result.setSuccess(false);
			logger.error("UserServiceImpl -> login:", e);
		}
		return result;
	}

}
