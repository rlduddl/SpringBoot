package com.example.demo.commonController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.service.FileService;

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
			@RequestParam(value = "file") MultipartFile file
			) {
		fileService.saveFile(file);
		return ResponseEntity.ok("1234");
	}

}
