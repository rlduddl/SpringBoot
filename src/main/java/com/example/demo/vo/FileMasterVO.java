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
public class FileMasterVO {
	
	private Long fileMstId;
	private LocalDateTime regDate;
	private String regID;
	
}
