package com.example.demo.vo;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// 硫ㅻ쾭蹂?섏쓽 getter ?먮룞 ?앹꽦
@Getter
// 硫ㅻ쾭蹂?섏쓽 setter ?먮룞 ?앹꽦
@Setter
// 媛앹껜??toString 硫붿꽌???먮룞 ?앹꽦
@ToString
// ?뚮씪誘명꽣 ?녿뒗 湲곕낯 而⑥뒪?몃윮???먮룞 ?앹꽦
@NoArgsConstructor
// 紐⑤뱺 留대쾭 蹂?섎? ?뚮씪誘명꽣濡쒗븯??而⑥뒪?몃윮???앹꽦
// @AllArgsConstructor
public class MemberVO {
	private Long idx;
	private int isAdmin;
	private String userID;
	private String password;
	private String username;
	private String email;
	private LocalDateTime regDate;
	private int isUse;
	private LocalDateTime dropDate;
	
	@Builder
	public MemberVO(Long idx, int isAdmin, String userID, String password, String username, String email,
			LocalDateTime regDate, int isUse, LocalDateTime dropDate) {
		this.idx = idx;
		this.isAdmin = isAdmin;
		this.userID = userID;
		this.password = password;
		this.username = username;
		this.email = email;
		this.regDate = regDate;
		this.isUse = isUse;
		this.dropDate = dropDate;
	}
	
	
	
	
	
	
	
	
	
}
