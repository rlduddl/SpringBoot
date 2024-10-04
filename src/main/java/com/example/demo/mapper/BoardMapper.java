package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.vo.BoardVO;

@Mapper
public interface BoardMapper {
	
	// CRUD

	void insertBoard(BoardVO boardVO);
	void deleteBoard(BoardVO boardVO);
	void updateBoard(BoardVO boardVO);
	List<BoardVO> selectList(BoardVO boardVO);

	
}
