package com.example.demo.vo;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class FileDetailVO {

	private Long fileDetailId;
	private Long fileMstId;
	private String fileDest;
	private Long fileNumber;
	private String fileLocation;
	private String filePath;
	private String fileName;
	private String orgFileName;
	private String fileExt;
	private String fileMiMe;
	private Long fileSize;
	private LocalDateTime regDate;
	private String regID;
	private LocalDateTime updDate;
	private String updID;
	
}
