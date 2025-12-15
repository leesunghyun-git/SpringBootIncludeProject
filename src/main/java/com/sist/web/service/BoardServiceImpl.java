package com.sist.web.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sist.web.mapper.BoardMapper;
import com.sist.web.vo.BoardVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
	private final BoardMapper bMapper;
	@Override
	public List<BoardVO> boardListData(int start) {
		// TODO Auto-generated method stub
		return bMapper.boardListData(start);
	}
	@Override
	public int boardTotalPage() {
		// TODO Auto-generated method stub
		return bMapper.boardTotalPage();
	}
	@Override
	public BoardVO boardDetailData(int no) {
		// TODO Auto-generated method stub
		bMapper.boardHitIncrement(no);
		return bMapper.boardDetailData(no);
	}
	@Override
	public void boardInsert(BoardVO vo) {
		// TODO Auto-generated method stub
		bMapper.boardInsert(vo);
	}
	@Override
	public BoardVO boardUpdateData(int no) {
		// TODO Auto-generated method stub
		return bMapper.boardDetailData(no);
	}
	@Override
	public boolean boardUpdate(BoardVO vo) {
		// TODO Auto-generated method stub
		boolean bCheck=false;
		if(vo.getPwd().equals(bMapper.boardGetPassword(vo.getNo())))
		{
			bMapper.boardUpdate(vo);
			bCheck=true;
		}
		return bCheck;
	}
	@Override
	public boolean boardDelete(int no,String pwd) {
		boolean bCheck=false;
		if(bMapper.boardGetPassword(no).equals(pwd))
		{
			bMapper.boardDelete(no);
			bCheck=true;
		}
		return bCheck;
	}
}
