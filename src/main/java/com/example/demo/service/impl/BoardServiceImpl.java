package com.example.demo.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.BoardMapper;
import com.example.demo.service.CrudService;
import com.example.demo.vo.BoardVO;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class BoardServiceImpl implements CrudService<BoardVO> {
	
	private BoardMapper boardMapper;

	@Override
	public List<BoardVO> selectList(BoardVO e) {
		return boardMapper.selectList(e);
	}

	@Override
	public BoardVO selectOne(BoardVO e) {
		return null;
	}
	
	public BoardVO selectBoard(Long idx) {
		BoardVO boardVO = boardMapper.selectOne(idx);
		log.info(boardVO.toString());
		return boardVO;
	}

	@Override
	public void insert(BoardVO e) {
		boardMapper.insertBoard(e);
	}

	@Override
	public void update(BoardVO e) {
		boardMapper.update(e);
	}

	@Override
	public void delete(BoardVO e) {
		boardMapper.delete(e.getIdx());
	}
	
	public void delete(Long idx) {
		boardMapper.delete(idx);
	}

}
