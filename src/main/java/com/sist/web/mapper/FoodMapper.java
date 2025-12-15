package com.sist.web.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.sist.web.vo.FoodVO;

@Mapper
@Repository
public interface FoodMapper {
	@Select("SELECT fno,name,poster,address "
			+ "FROM menupan_food ORDER BY fno ASC "
			+ "OFFSET #{start} ROWS FETCH NEXT 12 ROWS ONLY")
	public List<FoodVO> foodListData(int start);
	
	@Select("SELECT CEIL(COUNT(*)/12.0) FROM menupan_food")
	public int foodTotalPage();
	
	@Select("SELECT fno,name,poster,address,parking,theme,type,price,content,time,phone,score FROM menupan_food WHERE fno =#{fno}")
	public FoodVO foodDetailData(int fno);
	
	@Update("UPDATE menupan_food SET hit=hit+1 WHERE fno=#{fno}")
	public void foodHitIncrement(int fno);
	
	public List<FoodVO> foodFindData(Map map);
	public int foodFindTotalPage(Map map);
	
}
