package com.example.demo.boardController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.vo.MemberVO;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/board")
@Slf4j
public class BoardController {
	
	@GetMapping("/list2")
	public String boardList2(Model model) {
		return "board/list2";
	}
	
	@GetMapping("/list")
	public String boardList(Model model) {
		model.addAttribute("title", "게시물 목록");
		model.addAttribute("htmlTag", "<p>나는 P태그다</p>");
		model.addAttribute("age", 30);
		
		List<MemberVO> list = new ArrayList<>();
		
		MemberVO memberVO = MemberVO.builder()
				.userID("abcd")
				.username("홍길동")
				.build();
		
		list.add(memberVO);
		
		MemberVO memberVO2 = MemberVO.builder()
				.userID("efghj")
				.username("홍길순")
				.build();
		
		list.add(memberVO2);
		
		MemberVO memberVO3 = MemberVO.builder()
				.userID("zzzz")
				.username("이순신")
				.build();
		
		list.add(memberVO3);
		
		model.addAttribute("memberVO", memberVO);
		
		model.addAttribute("list", list);
		
		return "board/list";
	}
	
	@GetMapping("/view/{idx}")
	public String boardView(@PathVariable Long idx, Model model) {
		model.addAttribute("idx", idx);
		return "board/view";
	}
	
	
	
	
	
}
