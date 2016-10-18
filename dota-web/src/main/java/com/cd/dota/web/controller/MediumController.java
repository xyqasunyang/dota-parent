package com.cd.dota.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cd.dota.common.BatchResultDTO;
import com.cd.dota.common.ResultDTO;
import com.cd.dota.common.ResultEntity;
import com.cd.dota.core.service.MediumService;
import com.cd.dota.core.service.NewsService;
import com.cd.dota.core.service.UserMediumRelationService;
import com.cd.dota.core.service.UserNewsRelationService;
import com.cd.dota.dal.domain.MediumDO;
import com.cd.dota.dal.domain.NewsDO;
import com.cd.dota.dal.domain.UserDO;
import com.cd.dota.dal.domain.UserMediumRelationDO;
import com.cd.dota.dal.domain.UserNewsRelationDO;

@RestController
@RequestMapping("/medium")
public class MediumController {

	Logger logger = Logger.getLogger(MediumController.class);

	@Autowired
	MediumService mediumService;
	@Autowired
	UserMediumRelationService userMediumRelationService;
	@Autowired
	NewsService newsService;
	@Autowired
	UserNewsRelationService userNewsRelationService;

	/**
	 * 首次登录的媒体列表
	 * 
	 * @param userDO
	 * @param session
	 * @return
	 */
	@RequestMapping("/firstMediumList.do")
	public Object firstMediumList(UserDO userDO, HttpSession session,HttpServletRequest request) {
		ResultEntity result = new ResultEntity(ResultEntity.SUCCESS);
		BatchResultDTO<MediumDO> resultDTO = mediumService.firstChoose(userDO);
		if (resultDTO.isFailed()) {
			result.setCode(ResultEntity.ERROR);
			result.setError(resultDTO.getErrorDetail());
			return result;
		}
		result.setObject(resultDTO.getModule());
		return result;
	}

	/**
	 * 首次关注媒体
	 * 
	 * @param userMediumRelationDO
	 * @param session
	 * @return
	 */
	@RequestMapping("/firstAttentionMedium.do")
	public Object firstAttentionMedium(Integer userId, HttpSession session, String mediumIds) {
		ResultEntity result = new ResultEntity(ResultEntity.SUCCESS);
		String[] mediumId = mediumIds.split(",");
		List<UserMediumRelationDO> userMediumRelationDOs = new ArrayList<UserMediumRelationDO>();
		for (String id : mediumId) {
			UserMediumRelationDO userMediumRelationDO = new UserMediumRelationDO();
			userMediumRelationDO.setMediumId(Integer.valueOf(id));
			userMediumRelationDO.setUserId(userId);
			userMediumRelationDOs.add(userMediumRelationDO);
		}
		ResultDTO<Integer> resultDTO = userMediumRelationService.addUserMediumRelations(userMediumRelationDOs);
		if (resultDTO.isFailed()) {
			result.setCode(ResultEntity.ERROR);
			result.setError(resultDTO.getErrorDetail());
			return result;
		}
		result.setObject(resultDTO.getModule());
		return result;
	}

	/**
	 * 关注/取消关注媒体
	 * 
	 * @param userMediumRelationDO
	 * @param session
	 * @return
	 */
	@RequestMapping("/attentionMedium.do")
	public Object attentionMedium(UserMediumRelationDO userMediumRelationDO, HttpSession session) {
		ResultEntity result = new ResultEntity(ResultEntity.SUCCESS);
		ResultDTO<Integer> resultDTO = userMediumRelationService.addUserMediumRelation(userMediumRelationDO);
		if (resultDTO.isFailed()) {
			result.setCode(ResultEntity.ERROR);
			result.setError(resultDTO.getErrorDetail());
			return result;
		}
		result.setObject(resultDTO.getModule());
		return result;
	}

	/**
	 * 新闻正文
	 * 
	 * @param newsId
	 * @param session
	 * @return
	 */
	@RequestMapping("/news.do")
	public Object article(Integer userId, Integer newsId, HttpSession session) {
		ResultEntity result = new ResultEntity(ResultEntity.SUCCESS);
		ResultDTO<NewsDO> resultDTO = newsService.selectNewsById(newsId, userId);
		if (resultDTO.isFailed()) {
			result.setCode(ResultEntity.ERROR);
			result.setError(resultDTO.getErrorDetail());
			return result;
		}
		ResultDTO<MediumDO> resultDTOM = mediumService.getMedium(userId, resultDTO.getModule().getMediumId());
		UserNewsRelationDO userNewsRelationDO = new UserNewsRelationDO();
		userNewsRelationDO.setMediumId(resultDTOM.getModule().getMediumId());
		userNewsRelationDO.setNewsId(newsId);
		userNewsRelationDO.setUserId(userId);
		BatchResultDTO<UserNewsRelationDO> list = userNewsRelationService.selectUserNewsRelations(userNewsRelationDO);
		if (list.getModule().isEmpty()) {
			userNewsRelationService.addUserNewsRelation(userNewsRelationDO);
		}
		result.put("medium", resultDTOM.getModule());
		result.put("news", resultDTO.getModule());
		return result;
	}

	/**
	 * 点赞/取消点赞
	 * 
	 * @param newsId
	 * @param isLiked
	 *            0取消点赞 1点赞
	 * @param session
	 * @return
	 */
	@RequestMapping("/like.do")
	public Object like(Integer userId, Integer newsId, Integer isLiked, HttpSession session) {
		ResultEntity result = new ResultEntity(ResultEntity.SUCCESS);
		ResultDTO<Integer> resultDTO = newsService.isLiked(userId, newsId, isLiked);
		if (resultDTO.isFailed()) {
			result.setCode(ResultEntity.ERROR);
			result.setError(resultDTO.getErrorDetail());
			return result;
		}
		result.put("count", resultDTO.getModule());
		return result;
	}

	/**
	 * 媒体搜索
	 * 
	 * @param userDO
	 * @param session
	 * @return
	 */
	@RequestMapping("/searchMedium.do")
	public Object searchMedium(UserDO userDO, HttpSession session) {
		ResultEntity result = new ResultEntity(ResultEntity.SUCCESS);
		BatchResultDTO<MediumDO> resultDTO = mediumService.firstChoose(userDO);
		if (resultDTO.isFailed()) {
			result.setCode(ResultEntity.ERROR);
			result.setError(resultDTO.getErrorDetail());
			return result;
		}
		result.setObject(resultDTO.getModule());
		return result;
	}

	/**
	 * 猜你喜欢
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping("/guessLike.do")
	public Object guessLike(UserDO user, HttpSession session) {
		ResultEntity result = new ResultEntity(ResultEntity.SUCCESS);
		BatchResultDTO<MediumDO> resultDTO = mediumService.guessLike(user);
		if (resultDTO.isFailed()) {
			result.setCode(ResultEntity.ERROR);
			result.setError(resultDTO.getErrorDetail());
			return result;
		}
		result.setObject(resultDTO.getModule());
		return result;
	}

	/**
	 * 最新收录
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping("/newRecord.do")
	public Object newRecord(UserDO user, HttpSession session) {
		ResultEntity result = new ResultEntity(ResultEntity.SUCCESS);
		user.setPageIndex(1);
		user.setPageSize(3);
		BatchResultDTO<MediumDO> resultDTO = mediumService.firstChoose(user);
		if (resultDTO.isFailed()) {
			result.setCode(ResultEntity.ERROR);
			result.setError(resultDTO.getErrorDetail());
			return result;
		}
		result.setObject(resultDTO.getModule());
		return result;
	}

	/**
	 * 新闻列表
	 * 
	 * @param newsDO
	 * @param mediumId
	 * @param session
	 * @return
	 */
	@RequestMapping("/newsList.do")
	public Object newsList(Integer userId, NewsDO newsDO, Integer mediumId, HttpSession session) {
		ResultEntity result = new ResultEntity(ResultEntity.SUCCESS);
		ResultDTO<MediumDO> resultDTO = mediumService.getMedium(userId, mediumId);
		if (resultDTO.isFailed()) {
			result.setCode(ResultEntity.ERROR);
			result.setError(resultDTO.getErrorDetail());
			return result;
		}
		newsDO.setMediumId(mediumId);
		BatchResultDTO<NewsDO> batchResultDTO = newsService.selectNewss(newsDO);
		result.put("medium", resultDTO.getModule());
		result.put("news", batchResultDTO.getModule());
		return result;
	}

	/**
	 * 收藏列表
	 * 
	 * @param userNewsRelationDO
	 * @return
	 */
	@RequestMapping("/collectionList.do")
	public Object collectionList(UserNewsRelationDO userNewsRelationDO) {
		ResultEntity result = new ResultEntity(ResultEntity.SUCCESS);
		BatchResultDTO<UserNewsRelationDO> resultDTO = userNewsRelationService.getCollection(userNewsRelationDO);
		if (resultDTO.isFailed()) {
			result.setCode(ResultEntity.ERROR);
			result.setError(resultDTO.getErrorDetail());
			return result;
		}
		result.setList(resultDTO.getModule());
		return result;
	}
	
}
