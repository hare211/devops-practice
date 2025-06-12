package com.sist.web.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sist.web.entity.FoodEntity;
import com.sist.web.projection.FoodListProjection;
import com.sist.web.service.FoodService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class FoodController {
	private final FoodService fService;
	
	@GetMapping("/")
	public String foodMainPage(@RequestParam(name = "page", defaultValue = "1") String page, Model model) {
		int curPage = Integer.parseInt(page);
		
		int rowSize = 12;
		int start = (rowSize * curPage) - (rowSize - 1);
		int end = (rowSize * curPage);
		
		List<FoodListProjection> list = fService.getPagedFoodList(start, end);
		int totalPage = fService.getFoodTotalPage();
		
		final int BLOCK = 10;
		int startPage = ((curPage - 1) / BLOCK * BLOCK) + 1;
		int endPage = ((curPage - 1) / BLOCK * BLOCK) + BLOCK;
		
		if (endPage > totalPage) {
			endPage = totalPage;
		}
		
		model.addAttribute("list", list);
		model.addAttribute("curPage", curPage);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		
		return "index";
	}
	
	@GetMapping("/detail")
	public String foodDetailPage(@RequestParam(name = "fno") Integer fno, Model model) {
		if (fno == null) {
			return "redirect:/";
		}
		FoodEntity detail = fService.getFoodDetail(fno);
		model.addAttribute("vo", detail);
		return "detail";
	}
}
