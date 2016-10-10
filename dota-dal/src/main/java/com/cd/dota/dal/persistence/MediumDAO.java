package com.cd.dota.dal.persistence;

import java.util.List;

import com.cd.dota.dal.domain.MediumDO;
import com.cd.dota.dal.domain.UserDO;
public interface MediumDAO {
	
	public int addMedium(MediumDO mediumDO);

	public int updateMedium(MediumDO mediumDO) ;
	
	public MediumDO selectMediumById(Integer mediumId);
	
	public List<MediumDO> selectMediums(MediumDO mediumDO);
	
	public List<MediumDO> selectMediumsByUserId(UserDO userDO);
	
	public List<MediumDO> firstChoose(UserDO userDO);
	
	public List<MediumDO> guessLike(UserDO userDO);
}
