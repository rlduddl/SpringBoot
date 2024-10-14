package com.example.demo.service;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.exception.BadRequestException;
import com.example.demo.mapper.FileMasterMapper;
import com.example.demo.util.FileUtil;
import com.example.demo.vo.FileMasterVO;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FileService {

	private final FileMasterMapper fileMasterMapper;
	private final Path rootLocation;
	
	public FileService (FileMasterMapper fileMsterMapper, String uploadPath) {
		this.fileMasterMapper = fileMsterMapper;
		this.rootLocation = Paths.get(uploadPath);
	}
	
	/**
	 * 첨부파일 마스터 ID 생성 후 리턴
	 * @param fileMasterVO
	 * @return
	 */
	public Long insertFileMaster(FileMasterVO fileMasterVO) {
		fileMasterMapper.insertFileMaster(fileMasterVO);
		return fileMasterVO.getFileMstId();
	}
	
	public void saveFile(MultipartFile file) {
		// 용량, MIME, 파일명
		log.info(String.valueOf(file.getSize()));
		log.info(file.getContentType());
		log.info(file.getOriginalFilename());
		
		// 파일 존재 유무 체크
		if (file.isEmpty()) throw new BadRequestException("파일이 없습니다");
		
		String res = FileUtil.fileSave(rootLocation.toString(), file);
		
		
	}

}
