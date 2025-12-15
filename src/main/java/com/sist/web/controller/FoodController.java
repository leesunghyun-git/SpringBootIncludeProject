package com.sist.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sist.web.service.FoodService;
import com.sist.web.vo.FoodVO;

import lombok.RequiredArgsConstructor;
@Controller
@RequiredArgsConstructor
public class FoodController {
	private final FoodService fService;
	
	@GetMapping("/food/detail")
	public String food_detail(@RequestParam("fno")int fno,Model model)
	{
		FoodVO vo = fService.foodDetailData(fno);
		model.addAttribute("vo", vo);
		model.addAttribute("curCat", "food");
		model.addAttribute("main_html", "food/detail");
		return "main/main";
	}
	// Get + Post = RequestMapping => 사용 금지 권장 (경고)
	@RequestMapping("/food/find")
	public String food_find(@RequestParam(name="column",required = false)String column,@RequestParam(name="ss",required = false)String ss, 
			@RequestParam(name="page",required = false)String page, Model model)
	{
		if (column==null)
			column="all";
		if (ss==null)
			ss="";
		if (page==null)
			page="1";
		int curPage=Integer.parseInt(page);
		int rowSize = 12;
		int start = (curPage-1)*rowSize;
		Map map = new HashMap<>();
		map.put("column",column);
		map.put("ss", ss);
		map.put("start", start);
		List<FoodVO> list = fService.foodFindData(map);
		int totalPage = fService.foodFindTotalPage(map);
		final int BLOCK=10;
		int startPage=((curPage-1)/BLOCK*BLOCK)+1;
		int endPage=((curPage-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalPage)
			endPage=totalPage;
		model.addAttribute("list", list);
		model.addAttribute("curPage", curPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("ss", ss);
		model.addAttribute("column", column);
		model.addAttribute("curCat", "food");
		model.addAttribute("main_html", "food/find");
		return "main/main";
	}
	
}
