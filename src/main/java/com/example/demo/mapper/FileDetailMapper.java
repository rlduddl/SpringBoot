package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.vo.FileDetailVO;

@Mapper
public interface FileDetailMapper {
	
	FileDetailVO selectFileByFileDetailId(FileDetailVO fileDetailVO);

	void insertDetailFile(FileDetailVO vo);
	
}
