package com.enthalpy.model.service;

import com.enthalpy.model.dto.Like;

public interface LikeService {
	public boolean deleteLike(Like like);
	
	public boolean insertLike(Like like);
	
	public boolean hasLiked(Like like);
}
