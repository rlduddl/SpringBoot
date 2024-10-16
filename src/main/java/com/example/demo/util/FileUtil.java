package com.example.demo.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import org.apache.tika.Tika;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.exception.BadRequestException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileUtil {
	
	/**
	 * ?뚯씪 ?좏삎 ?뚯븙
	 * @param file
	 * @return
	 */
	public static boolean fileMimeType(InputStream file) {
		// ?낅줈??媛?ν븳 ?뚯씪 ?좏삎 紐⑸줉
		List<String> typeList = Arrays.asList("image/jpg", "image/png", "image/jpeg", "image/gif", "application/x-tika-ooxml");
		try {
			// tika瑜??댁슜?댁꽌 ?ㅻЪ ?뚯씪???좏삎??泥댄겕?쒕떎.
			String mimeType = new Tika().detect(file);
			
			log.info("mimeType : {}", mimeType);
			
			// typeList瑜?loop ?뚮젮??mimeType怨??쇱튂?섎뒗寃??덉쑝硫?return true ?꾨땲硫?false
			return typeList.stream().anyMatch(item -> item.equalsIgnoreCase(mimeType));
		} catch (IOException e) {
			return false;
		}
	}

	/**
	 * ?뚮씪誘명꽣濡?諛쏆? ?뚯씪???쒕씪?대툕???꾩쓽??臾몄옄?대줈 由щ꽕?꾪빐?????
	 * @param uploadPath
	 * @param file
	 * @return
	 */
	public static String fileSave(String uploadPath, MultipartFile file) {
		// 1. MIME 泥댄겕?댁꽌 ?낅줈??媛?ν븳 ?뺤떇???뚯씪?몄? ?뺤씤
		try {
			boolean isMime = fileMimeType(file.getInputStream());
			if (isMime) {
				
				// uploadPath濡?諛쏆? 寃쎈줈瑜?留뚮뱺??
				File uploadFileDir = new File(uploadPath);
				if (!uploadFileDir.exists()) {
					uploadFileDir.mkdirs();
				}
				
				// ?뚯씪 ??μ떆 以묐났?섏? ?딄쾶 ?섍린 ?꾪빐??由щ꽕??????ν븳??
				String genId = UUID.randomUUID().toString();
				
				log.info(genId);
				
				String orgFileName = file.getOriginalFilename();
				String fileExt = getExtension(orgFileName);
				String saveFileName = genId + "." + fileExt;
				log.info(saveFileName);
				// 寃쎈줈 留뚮뱾湲?
				// ?쒕씪?대툕/??κ린蹂명뤃?????????뚯씪紐?
				String savePath = calcPath(uploadPath);
				// ?대떦 寃쎈줈???뚯씪 蹂듭궗
				File target = new File(uploadPath + savePath, saveFileName);
				FileCopyUtils.copy(file.getBytes(), target);
				// 寃쎈줈 + ?뚯씪紐낆쓣 由ы꽩 
				return makeFilePath(uploadPath, savePath, saveFileName);
			}
			return "";
		} catch (Exception e) {
			throw new BadRequestException("?낅줈?쒓? 遺덇??ν븳 ?뚯씪 ?뺤떇?낅땲??");
		}
	}
	
	/**
	 * ?뚯씪 蹂듭궗 ?꾨즺 ??由ы꽩???꾩껜 臾몄옄??
	 * @param uploadPath
	 * @param path
	 * @param fileName
	 * @return
	 */
	public static String makeFilePath(String uploadPath, String path, String fileName) {
		String filePath = uploadPath + path + File.separator + fileName;
		return filePath.substring(uploadPath.length()).replace(File.separatorChar, '/');
	}
	
	/**
	 * ???????⑥뒪瑜?留뚮뱾?댁꽌 ?대뜑 ?앹꽦 諛??⑥뒪 由ы꽩
	 * @param uploadPath
	 * @return
	 */
	public static String calcPath(String uploadPath) {
		Calendar calendar = Calendar.getInstance();
		log.info(calendar.toString());
		// 寃쎈줈/?곕룄
		// File.separator 瑜??ъ슜?섎㈃ OS???곕씪 / ?먮뒗 \媛 ?먮룞?쇰줈 李랁엺??
		String yearPath = File.separator + String.valueOf(calendar.get(Calendar.YEAR));
		log.info(yearPath);
		// ????異쒕젰 2024\9
		// 1 11 12 13..... 19 2 20
		// 01 02 ..... 10 ... 19 20
		String monthPath = yearPath + File.separator + new DecimalFormat("00").format(calendar.get(Calendar.MONTH) + 1);
		log.info(monthPath);
		// ??????異쒕젰
		String datePath = monthPath  + File.separator + new DecimalFormat("00").format(calendar.get(Calendar.DATE));
		log.info(datePath);
		// ?대뜑 ?앹꽦
		makeDirectories(uploadPath, yearPath, monthPath, datePath);
		return datePath;
	}
	
	/**
	 * ?ㅼ젣 寃쎈줈???대뜑 ?앹꽦
	 * @param uploadPath
	 * @param paths
	 */
	public static void makeDirectories(String uploadPath, String... paths) {
		// 寃쎈줈???대뜑媛 ?덉쑝硫?return
		if (new File(paths[paths.length - 1]).exists()) {
			return;
		}
		// paths瑜?loop ?뚮젮???대뜑 ?앹꽦
		// ?낅줈?쒓꼍濡?2024
		// ?낅줈?쒓꼍濡?2024/10
		// ?낅줈?쒓꼍濡?2024/10/15
		for (String path : paths) {
			File dirPath = new File(uploadPath + path);
			if (!dirPath.exists()) dirPath.mkdir();
		}
	}
	
	/**
	 * ?뚯씪?먯꽌 ?뺤옣?먮쭔 由ы꽩
	 * @param fileName
	 * @return
	 */
	public static String getExtension(String fileName) {
		int dotPosition = fileName.lastIndexOf('.');
		if (-1 != dotPosition && (fileName.length() - 1 > dotPosition)) {
			return fileName.substring(dotPosition + 1);
		} else {
			return "";
		}
	}
	
	
}
