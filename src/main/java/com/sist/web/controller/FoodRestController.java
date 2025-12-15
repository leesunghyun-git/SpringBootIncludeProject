package com.sist.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.service.FoodService;
import com.sist.web.vo.FoodVO;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins="*")
public class FoodRestController {
	private final FoodService fService;
	/*
	 * 	@RestController : 다른 언어 연결 => 전송
	 * 	@RequiredArgsConstructor => 생성자에서 객체 주소 설정
	 * 	@Autowired : 스프링에서 자동으로 객체 주소 주입
	 * 	@RequestParam : 데이터 값 1개 받는 경우
	 * 	@ModelAttribute : vo단위로 값을 받는 경우
	 * 	@RequestBody : JSON이 전송될때 객체 변환
	 * 	@ResponseBody : 전송 => JSON/문자열 전송 => @RestController로 변경
	 * 	@Aspect : 공통으로 적용된 기능 => 데이터 관리 : 공통 모듈
	 * 	-----------------------------
	 * 	@Repository : DAO 구분 => 데이터베이스 관련된 클래스
	 * 	@Component : 일반 객체 => 스프링에서 객체를 생성해서 관리
	 * 	@Service : BI => DAO여러개나 메소드 여러개를 통합해서 사용
	 * 	@Controller : 데이터 + 화면 제어
	 * 	@RestController : 데이터를 전송할 목적 
	 * 	@ControllerAdvice : Controller => catch사용
	 * 	@RestControllerAdvice => 선택적 메모리 할당 (기능별 분리 = 가독성)	
	 * 
	 * 
	 */
	@GetMapping("/food/detail_vue/")
	public ResponseEntity<FoodVO> food_detail_vue(@RequestParam("fno")int fno)
	{
		FoodVO vo = null;
		try {
			vo = fService.foodDetailData(fno);
		}catch(Exception ex)
		{
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(vo,HttpStatus.OK);
	}
	@GetMapping("/food/find_vue/")
	public ResponseEntity<Map> food_find_vue(@RequestParam(name="column",required = false)String column,@RequestParam(name="ss",required = false)String ss, 
			@RequestParam(name="page",required = false)String page)
	{		
		Map map = new HashMap<>();
		try {
		if (column==null)
			column="all";
		if (ss==null)
			ss="";
		if(page==null)
			page="1";
		System.out.println("1");
		System.out.println(column);
		System.out.println(ss);
		System.out.println(page);
		int curPage=Integer.parseInt(page);
		int rowSize = 12;
		int start = (curPage-1)*rowSize;
		
		map.put("column",column);
		map.put("ss", ss);
		map.put("start", start);
		List<FoodVO> list = fService.foodFindData(map);
		int totalPage = fService.foodFindTotalPage(map);
		map = new HashMap<>();
		final int BLOCK=10;
		int startPage=((curPage-1)/BLOCK*BLOCK)+1;
		int endPage=((curPage-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalPage)
			endPage=totalPage;
		map.put("list", list);
		map.put("curPage", curPage);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		map.put("totalPage", totalPage);
		map.put("ss", ss);
		map.put("column", column);
		}
		catch(Exception ex)
		{
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(map,HttpStatus.OK);
	}
}
