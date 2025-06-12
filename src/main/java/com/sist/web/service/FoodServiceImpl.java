package com.sist.web.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sist.web.dao.FoodRepository;
import com.sist.web.entity.FoodEntity;
import com.sist.web.projection.FoodListProjection;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FoodServiceImpl implements FoodService {
	private final FoodRepository fDao;
	
	@Override
	public List<FoodListProjection> getPagedFoodList(int start, int end) {
		List<FoodListProjection> list = new ArrayList<>();
		list = fDao.findPagedFoodList(start, end);
		return list;
	}
	
	@Override
	public FoodEntity getFoodDetail(int fno) {
		FoodEntity detail = new FoodEntity();
		detail = fDao.findByFno(fno);
		return detail;
	}
	
	@Override
	public int getFoodTotalPage() {
		int count = (int) fDao.count();
		int totalPage =  (int)(Math.ceil(count / 12.0));
		return totalPage;
	}
}
