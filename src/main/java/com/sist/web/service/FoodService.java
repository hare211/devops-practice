package com.sist.web.service;

import java.util.List;

import com.sist.web.entity.FoodEntity;
import com.sist.web.projection.FoodListProjection;

public interface FoodService {
	List<FoodListProjection> getPagedFoodList(int start, int end);
	FoodEntity getFoodDetail(int fno);
	int getFoodTotalPage();
}
