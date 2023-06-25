package com.enthalpy.model.dao;

import com.enthalpy.model.dto.Like;

public interface LikeDao {
	int deleteLike(Like like);
	
	int insertLike(Like like);
	
	int selectLike(Like like);
}
