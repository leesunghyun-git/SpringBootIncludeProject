package com.sist.web.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sist.web.mapper.FoodMapper;
import com.sist.web.vo.FoodVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FoodServiceImpl implements FoodService{
	private final FoodMapper fmapper;
	@Override
	public List<FoodVO> foodListData(int start) {
		// TODO Auto-generated method stub
		return fmapper.foodListData(start);
	}
	@Override
	public int foodTotalPage() {
		// TODO Auto-generated method stub
		return fmapper.foodTotalPage();
	}
	@Override
	public FoodVO foodDetailData(int fno) {
		fmapper.foodHitIncrement(fno);
		return fmapper.foodDetailData(fno);
	}
	@Override
	public List<FoodVO> foodFindData(Map map) {
		// TODO Auto-generated method stub
		return fmapper.foodFindData(map);
	}
	@Override
	public int foodFindTotalPage(Map map) {
		// TODO Auto-generated method stub
		return fmapper.foodFindTotalPage(map);
	}
}
