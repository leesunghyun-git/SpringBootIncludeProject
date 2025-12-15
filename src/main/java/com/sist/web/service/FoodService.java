package com.sist.web.service;

import java.util.List;
import java.util.Map;

import com.sist.web.vo.FoodVO;

public interface FoodService {
	
	public List<FoodVO> foodListData(int start);
	public int foodTotalPage();
	public FoodVO foodDetailData(int fno);
	public List<FoodVO> foodFindData(Map map);
	public int foodFindTotalPage(Map map);
}
