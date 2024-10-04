package com.example.demo.vo;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//멤버변수의 getter 자동 생성
@Getter
//멤버변수의 setter 자동 생성
@Setter
//객체의 toString 메서드 자동 생성
@ToString
//파라미터 없는 기본 컨스트럭터 자동 생성
@NoArgsConstructor
//모든 맴버 변수를 파라미터로하는 컨스트럭터 생성
//@AllArgsConstructor
public class BoardVO {
	private int idx;
	private int cate; // 1000 : 공지사항게시판, 2000 : 자유게시판, 3000 : 갤러리게시판, 4000 : 뉴스게시판
	private String title;
	private String content;
	private String regID;
	private LocalDateTime regDate;
	private String updID;
	private LocalDateTime updDate;
}
