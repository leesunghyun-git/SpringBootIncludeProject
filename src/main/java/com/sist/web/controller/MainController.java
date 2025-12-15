package com.sist.web.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sist.web.service.FoodService;
import com.sist.web.service.FoodServiceImpl;
import com.sist.web.vo.FoodVO;

import lombok.RequiredArgsConstructor;
// 화면 변경 =? 요청 처리
/*
 * 	1. MVC 동작 과정
 * 	2. 어노테이션 역할
 * 		=> 메모리 할당
 * 	3. ORM (Mybatis)
 * 	4. HttpSession / Cookie 처리
 * 	5. 요청 데이터 => @RequestParam / @ModelAttribute
 * 					Vue , React (JSON) => @RequestBody
 * 	6. @Controller / @RestController
 *  7. @ControllerAdvice : 예외 처리 공통으로 처리
 *  8. @Aspect : AOP
 *  9. interceptor
 *  10. FileUpload
 *  ---------------------------------------------------------
 *  11. Security / Batch / WebSocket / NodeJS
 * 
 * 									 ------------ TypeScript
 * 	FullStack : Javascript / Jquery / Vue / React
 * 								|	   |	  |
 * 											redux/tanstackQuery
 * 									vuex/pinia
 * 							  Ajax
 * 
 */
@Controller
@RequiredArgsConstructor
public class MainController {
	private final FoodService fService;
	@GetMapping("/")
	public String main_home(@RequestParam(name="page",required = false) String page,Model model)
	{
		if(page==null)
			page="1";
		int curPage=Integer.parseInt(page);
		int rowSize = 12;
		int start = (curPage-1)*rowSize;
		List<FoodVO> list = fService.foodListData(start);
		int totalPage = fService.foodTotalPage();
		
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
		model.addAttribute("curCat", "home");
		// => main에 전송 => include는 request를 공유
		model.addAttribute("main_html", "main/home");
		return "main/main";
	}
}
