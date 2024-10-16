package com.example.demo.service;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.exception.BadRequestException;
import com.example.demo.mapper.FileDetailMapper;
import com.example.demo.mapper.FileMasterMapper;
import com.example.demo.util.FileUtil;
import com.example.demo.vo.FileDetailVO;
import com.example.demo.vo.FileMasterVO;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FileService {
	
	private final FileMasterMapper fileMasterMapper;
	private final FileDetailMapper fileDetailMapper;
	private final Path rootLocation;
	
	public FileService (FileMasterMapper fileMasterMapper, String uploadPath, FileDetailMapper fileDetailMapper) {
		this.fileMasterMapper = fileMasterMapper;
		this.rootLocation = Paths.get(uploadPath);
		this.fileDetailMapper = fileDetailMapper;
	}
	
	private Path loadPath(String fileName) {
		return rootLocation.resolve(fileName);
	}
	
	public Resource loadAsResource(String fileName) {
		try {
			// ?뚯씪?대쫫 ?욎뿉 /濡??쒖옉?섎㈃ ??젣
			if (fileName.toCharArray()[0] == '/') {
				fileName = fileName.substring(1);
			}
			log.info(fileName);
			Path file = rootLocation.resolve(rootLocation.toString() + fileName);
			log.info(file.toUri().toString());
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				log.info(resource.toString());
				return resource;
			} else {
				throw new BadRequestException(fileName + "???놁뒿?덈떎.");
			}
		} catch (Exception e) {
			throw new BadRequestException(fileName + "???놁뒿?덈떎.");
		}
	}
	
	/**
	 * 泥⑤??뚯씪 留덉뒪??ID ?앹꽦 ??由ы꽩
	 * @param fileMasterVO
	 * @return
	 */
	public Long insertFileMaster(FileMasterVO fileMasterVO) {
		fileMasterMapper.insertFileMaster(fileMasterVO);
		return fileMasterVO.getFileMstId();
	}
	
	public void saveFile(MultipartFile file) {
		// ?⑸웾, MIME, ?뚯씪紐?
		log.info(String.valueOf(file.getSize()));
		log.info(file.getContentType());
		log.info(file.getOriginalFilename());
		
		// ?뚯씪 議댁옱 ?좊Т 泥댄겕
		if (file.isEmpty()) throw new BadRequestException("?뚯씪???놁뒿?덈떎.");
		
		String saveFileName = FileUtil.fileSave(rootLocation.toString(), file);	
		// 由ы꽩 諛쏆? ?뚯씪 寃쎈줈?먯꽌 /yyyy/mm/dd/ 留?蹂꾨룄濡?遺꾨━?쒕떎.
		String[] saveFileArray = saveFileName.split("/");
		StringBuilder fileDirString = new StringBuilder();
		for (int i = 0; i < saveFileArray.length; i++) {
			if (i < saveFileArray.length - 1) {
				fileDirString.append(saveFileArray[i]).append(File.separator);
			}
		}
		// FILE_LOCATION 媛?異쒕젰
		log.info(fileDirString.toString());
		// FILE_PATH
		// saveFileName ??泥ル쾲吏??덊꽣媛 / ?대㈃ ??젣
		if (saveFileName.toCharArray()[0] == '/') {
			saveFileName = saveFileName.substring(1);
		}
		String fullPathName = rootLocation.toString() + File.separator + saveFileName;
		log.info(fullPathName);
		
		FileDetailVO fileDetailVO = FileDetailVO.builder()
				.fileMiMe(file.getContentType())
				.fileDest("111")
				.fileLocation(fileDirString.toString())
				.filePath(fullPathName)
				.fileSize(file.getSize())
				.fileExt(FileUtil.getExtension(saveFileName))
				.fileNumber(0L)
				.fileName(saveFileArray[saveFileArray.length - 1])
				.orgFileName(file.getOriginalFilename())
				.regID("regID")
				.updID("updID")
				.fileMstId(1L)
				.build();
		
		log.info(fileDetailVO.toString());
		fileDetailMapper.insertDetailFile(fileDetailVO);
		
		log.info(String.valueOf(fileDetailVO.getFileDetailId()));
		
	}
	
	/**
	 * 泥⑤??뚯씪 異쒕젰
	 * @param fileDetailVO
	 * @return
	 */
	public FileDetailVO selectFileByFileDetailId(FileDetailVO fileDetailVO) {
		FileDetailVO vo = fileDetailMapper.selectFileByFileDetailId(fileDetailVO);
		log.info(vo.toString());
		return vo;
	}

}
