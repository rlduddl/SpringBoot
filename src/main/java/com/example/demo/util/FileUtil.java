package com.example.demo.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.apache.tika.Tika;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.exception.BadRequestException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileUtil {
	
	/**
	 * 파일 유형 파악
	 * @param file
	 * @return
	 */
	public static boolean fileMimeType(InputStream file) {
		// 업로드 가능한 파일 유형 목록
		List<String> typeList = Arrays.asList("image/jpg", "image/png", "image/jpeg", "image/gif", "application/x-tika-ooxml", "text/plain");
		try {
			String mimeType = new Tika().detect(file);
			
			log.info("mimeType : {}", mimeType);
			
			// typeList를 loop 돌려서 mimeType과 일치하는게 있으면 return true 아니면 false
			
			
			return typeList.stream().anyMatch(item -> item.equalsIgnoreCase(mimeType));
				
//			boolean result = false;
//			for (String item : typeList) {
//				result = item.equalsIgnoreCase(mimeType);
//			}
//			return result;
			
		} catch (IOException e) {
			return false;
		}
	
	}
	
	/**
	 * 파라미터로 받은 파일을 드라이브에 임의의 문자열로 리네임해서 저장
	 * @param uploadPath
	 * @param file
	 * @return
	 */
	public static String fileSave(String uploadPath, MultipartFile file) {
		// 1. MIME 체크해서 업로드 가능한 형식의 파일인지 확인
		try {
			boolean isMime = fileMimeType(file.getInputStream());
			if(isMime) {
				
				// uploadPath로 받은 경로를 만든다.
				File uploadFileDir = new File(uploadPath);
				if (!uploadFileDir.exists()) {
					uploadFileDir.mkdirs();
				}
				
				// 파일 저장시 중복되지 않게 하기 위해서 리네임 후 저장한다.
				String genId = UUID.randomUUID().toString();
	
				
				log.info(genId);
				
				String orgFileName = file.getOriginalFilename();
				String fileExt = getExtension(orgFileName);
				String saveFileName = genId + "." + fileExt;
				// 확장자만 출력
				log.info(fileExt);
				// genId + 확장자
				log.info(saveFileName);
				
			}
			return "";
		} catch (Exception e) {
			throw new BadRequestException("업로드가 불가능한 파일 형식입니다.");
		}
	
	}
	
	/**
	 * 파일에서 확장자만 리턴
	 * @param fileName
	 * @return
	 */
	public static String getExtension(String fileName) {
		int dotPosition = fileName.lastIndexOf('.');
		if (dotPosition != -1 && dotPosition < fileName.length() - 1) {
			return fileName.substring(dotPosition + 1);
		} else {
			return "";
		}
	}
}
