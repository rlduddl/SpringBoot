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
		 * CONTENT_DISPOSITION : ?쒕쾭媛 釉뚮씪?곗??먭쾶 肄섑뀗痢좊? ?대뼸寃?泥섎━?좉굔吏 諛⑹떇吏??
		 * attachment : 釉뚮씪?곗?媛 ?대떦 ?뚯씪???ㅼ슫濡쒕뱶?????덇쾶 ?쒕떎. (filename???대쫫?쇰줈 ?ъ슜??, inline : ?뚯씪??釉뚮씪?곗???諛붾줈 ?쒖떆 (??pdf)
		 */
		/**
		 * ISO_8859_1 : ?쇳떞臾몄옄
		 */
		
		httpHeaders.add(httpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1) + "\"");
		
		httpHeaders.setContentType(MediaType.valueOf(file.getFileMiMe()));
		
		// ?붿뒪?ъ뿉???ㅻЪ ?뚯씪??李얠븘??header? ?④퍡 由ы꽩
		Resource resource = fileService.loadAsResource(file.getFileLocation() + file.getFileName());
		
		log.info(resource.toString());
		
		return ResponseEntity.ok().headers(httpHeaders).body(resource);
	}
	
}





