package com.example.demo.boardController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.mapper.BoardMapper;
import com.example.demo.service.impl.BoardServiceImpl;
import com.example.demo.vo.BoardVO;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// Rest API
// Representational State Transfer
// 자원(Resource) : REST에서 모든 것은 자원으로 표현된다.
// 자원은 URI(Uniform Resource Identifier)를 통해 고유하게 식별된다.
// (예 : 사용자 정보를 열람 /member/user?idx=1  ----->  /member/user/1   
// URI, URL
// URL : Uniform Resource Locator (예 : 특정한 자원의 위치를 나타낸다.)  http://도메인/member/user/info.html
// 상태 전이(Stateless) : RESTful API는 상태가 없다. 각 요청은 독립적이고, 클라이언트의 상태를 서버가 저장하지 않는다. 클라이언트는 필요한 정보를 요청에 포함해야한다.
// 표현(Representation) : 클라이언트가 자원의 표현을 요청하고, 서버는 JSON, XML 형식으로 반환한다.
// HTTP 메서드 : GET, POST (일반적인 request형식)
// 		GET : 자원요청
//		POST : 새로운 자원 생성
//		PUT : 자원 수정 (전체)
//		PATCH : 자원 수정 (부분)
//		DELETE : 자원 삭제

@RestController
@RequestMapping("/api/board")
@Slf4j
@AllArgsConstructor
public class RestBoardController {
	
	private BoardServiceImpl boardService;
	
	@PostMapping("/insert")
	public ResponseEntity<?> insertBoard(
			@RequestBody BoardVO boardVO
			){
		boardService.insert(boardVO);
		return ResponseEntity.ok(true);
	}
	
	@GetMapping("/selectList")
	public ResponseEntity<?> selectList(
			@RequestBody BoardVO boardVO
			){
		log.info("lll");
		return ResponseEntity.ok(boardService.selectList(boardVO));
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> updateBoard(
			@RequestBody BoardVO boardVO
			){
		boardService.update(boardVO);
		return ResponseEntity.ok(true);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<?> deleteBoard(
			@RequestBody BoardVO boardVO
			){
		boardService.delete(boardVO);
		return ResponseEntity.ok(true);
	}
	
	
	

}
