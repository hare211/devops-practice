package com.sist.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sist.web.projection.BoardProjection;
import com.sist.web.service.BoardService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BoardController {
	
	private final BoardService bService;
	
	@GetMapping("/board/list")
	public String board_list(@RequestParam(name = "page", defaultValue = "1") String page, Model model) {
		int curPage = Integer.parseInt(page);
		
		Map<String, Object> map = new HashMap<>();
		
		map = bService.boardListData(curPage);
		
		@SuppressWarnings("unchecked")
		List<BoardProjection> list = (List<BoardProjection>)map.get("list");
		int totalPage = (int) map.get("totalPage");
		
		model.addAttribute("list", list);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("curPage", curPage);
		model.addAttribute("main_html", "board/list");
		return "index";
	}
	
	@GetMapping("/board/insert")
	public String board_insert(Model model) {
		model.addAttribute("main_html", "board/insert");
		return "index";
	}
	
	@GetMapping("/board/detail")
	public String board_detail(Model model) {
		model.addAttribute("main_html", "board/detail");
		return "index";
	}
}
