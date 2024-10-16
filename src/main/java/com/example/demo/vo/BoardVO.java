package com.example.demo.vo;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//硫ㅻ쾭蹂?섏쓽 getter ?먮룞 ?앹꽦
@Getter
//硫ㅻ쾭蹂?섏쓽 setter ?먮룞 ?앹꽦
@Setter
//媛앹껜??toString 硫붿꽌???먮룞 ?앹꽦
@ToString
//?뚮씪誘명꽣 ?녿뒗 湲곕낯 而⑥뒪?몃윮???먮룞 ?앹꽦
@NoArgsConstructor
//紐⑤뱺 留대쾭 蹂?섎? ?뚮씪誘명꽣濡쒗븯??而⑥뒪?몃윮???앹꽦
//@AllArgsConstructor
// VO : Value Object
// DTO : Data Transfer Object
// BoardVO == BoardDTO
public class BoardVO {
	private Long idx;
	private Long cate; // 1000 : 怨듭??ы빆, 2000 : ?먯쑀寃뚯떆?? 3000 : 媛ㅻ윭由ш쾶?쒗뙋
	private String title;
	private String content;
	private String regID;
	private LocalDateTime regDate;
	private String updID;
	private LocalDateTime updDate;
	private Long fileMstId;
	
	
	
	
}
