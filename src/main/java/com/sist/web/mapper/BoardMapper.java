package com.sist.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.sist.web.vo.BoardVO;

@Mapper
@Repository
public interface BoardMapper {
	@Select("SELECT no,subject,name,TO_CHAR(regdate,'YYYY-MM-DD') as dbday,hit "
			+ "FROM springboard "
			+ "ORDER BY no DESC "
			+ "OFFSET #{start} ROWS FETCH NEXT 10 ROWS ONLY")
	public List<BoardVO> boardListData(int start);
	
	@Select("SELECT CEIL(COUNT(*)/10.0) FROM springboard")
	public int boardTotalPage();
	
	// 상세보기 => @GetMapping
	@Select("SELECT no,name,subject,content,hit,TO_CHAR(regdate,'YYYY-MM-DD') as dbday "
			+ "FROM springboard "
			+ "WHERE no=#{no}")
	public BoardVO boardDetailData(int no);
	@Update("UPDATE springboard SET hit=hit+1 WHERE no=#{no}")
	public void boardHitIncrement(int no);
	// 글쓰기 => @PostMapping
	@Insert("INSERT INTO springboard(no,name,subject,content,pwd) VALUES ("
			+ "sb_no_seq.nextval , #{name}, #{subject},#{content}, #{pwd})")
	public void boardInsert(BoardVO vo);
	// 수정 => @PutMappign
	@Select("SELECT pwd FROM Springboard WHERE no = #{no}")
	public String boardGetPassword(int no);
	
	@Update("UPDATE springboard set "
			+ "name = #{name} , subject = #{subject}, content = #{content} WHERE no = #{no}")
	public void boardUpdate(BoardVO vo);
	// 삭제 => @DeleteMapping
	@Delete("DELETE FROM springboard WHERE no = #{no}")
	public void boardDelete(int no);
	// ----------------------------- RestFUl
}
