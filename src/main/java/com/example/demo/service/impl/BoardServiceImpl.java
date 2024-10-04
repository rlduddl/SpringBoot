package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.mapper.BoardMapper;
import com.example.demo.service.CrudService;
import com.example.demo.vo.BoardVO;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class BoardServiceImpl implements CrudService<BoardVO>{
	
	
	private BoardMapper boardMapper;
	
	@Override
	public List<BoardVO> selectList(BoardVO e) {
		return boardMapper.selectList(e);		
	}


	@Override
	public BoardVO selectOne(BoardVO e) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void update(BoardVO e) {
		boardMapper.updateBoard(e);
		log.info("=============");
		log.info(String.valueOf(e.getIdx()));
		log.info("=============");
		
	}


	@Override
	public void delete(BoardVO e) {
		boardMapper.deleteBoard(e);
		log.info("=============");
		log.info(String.valueOf(e.getIdx()));
		log.info("=============");
		
	}


	@Override
	public void insert(BoardVO e) {
		
		boardMapper.insertBoard(e);
		log.info("=============");
		log.info(String.valueOf(e.getIdx()));
		log.info("=============");
		
	}
}
