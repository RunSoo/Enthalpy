package com.enthalpy.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enthalpy.model.dao.LikeDao;
import com.enthalpy.model.dto.Like;

@Service
public class LikeServiceImpl implements LikeService {
	
	@Autowired
	private LikeDao likeDao;

	@Override
	public boolean deleteLike(Like like) {
		return likeDao.deleteLike(like) == 1;
	}

	@Override
	public boolean insertLike(Like like) {
		return likeDao.insertLike(like) == 1;
	}

	@Override
	public boolean hasLiked(Like like) {
		return likeDao.selectLike(like) != 0;
	}

}
