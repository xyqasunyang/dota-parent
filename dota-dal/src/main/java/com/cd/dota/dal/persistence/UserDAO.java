package com.cd.dota.dal.persistence;

import java.util.List;

import com.cd.dota.dal.domain.UserDO;
public interface UserDAO {
	
	public int addUser(UserDO userDO);

	public int updateUser(UserDO userDO) ;
	
	public UserDO selectUserById(Integer userId);
	
	public List<UserDO> selectUsers(UserDO userDO);
}
