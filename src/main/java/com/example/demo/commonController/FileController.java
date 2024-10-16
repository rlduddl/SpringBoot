package com.example.demo.commonController;

import java.nio.charset.StandardCharsets;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.service.FileService;
import com.example.demo.vo.FileDetailVO;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/file")
@AllArgsConstructor
public class FileController {
	
	private FileService fileService;

	@PostMapping("/save")
	public ResponseEntity<?> fileSave(
		@RequestParam(value = "file") MultipartFile file) {
		fileService.saveFile(file);
		return ResponseEntity.ok("1234");
	}
	
	@GetMapping("/viewById")
	public ResponseEntity<?> viewById(
			@RequestParam(value = "fileMstId", required = true, defaultValue = "0") Long fileMstId,
			@RequestParam(value = "fileDetailId", required = true, defaultValue = "0") Long fileDetailId
			) {
		FileDetailVO vo = new FileDetailVO();
		vo.setFileMstId(fileMstId);
		vo.setFileDetailId(fileDetailId);
		
		FileDetailVO file = fileService.selectFileByFileDetailId(vo);
		
		// header + content
		HttpHeaders httpHeaders = new HttpHeaders();
		String fileName = file.getFileName();
		
		/**
		 * CONTENT_DISPOSITION : 서버가 브라우저에게 콘텐츠를 어떻게 처리할건지 방식지정
		 * attachment : 브라우저가 해당 파일을 다운로드할 수 있게 한다. (filename이 이름으로 사용됨), inline : 파일을 브라우저에 바로 표시 (예:pdf)
		 */
		/**
		 * ISO_8859_1 : 라틴문자
		 */
		
		httpHeaders.add(httpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1) + "\"");
		
		httpHeaders.setContentType(MediaType.valueOf(file.getFileMiMe()));
		
		// 디스크에서 실물 파일을 찾아서 header와 함께 리턴
		Resource resource = fileService.loadAsResource(file.getFileLocation() + file.getFileName());
		
		log.info(resource.toString());
		
		return ResponseEntity.ok().headers(httpHeaders).body(resource);
	}
	
}





