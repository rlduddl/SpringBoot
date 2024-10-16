package com.example.demo.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.MemberMapper;
import com.example.demo.payload.request.ChangePwRequest;
import com.example.demo.payload.request.JoinRequest;
import com.example.demo.service.CrudService;
import com.example.demo.util.StringUtil;
import com.example.demo.vo.MemberVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Service
public class MemberServiceImpl implements CrudService<MemberVO> {
	@Autowired
	private MemberMapper mapper;

	@Override
	public List<MemberVO> selectList(MemberVO e) {
		return mapper.selectList(e);
	}

	@Override
	public MemberVO selectOne(MemberVO e) {
		return mapper.selectOne(e);
	}
	
	public MemberVO selectOne(String userID, String password) {
		MemberVO vo = new MemberVO();
		vo.setUserID(userID);
		vo.setPassword(password);
		return mapper.selectOne(vo);
	}

	@Override
	public void insert(MemberVO e) {
		mapper.insert(e);
	}

	@Override
	public void update(MemberVO e) {
		mapper.update(e);
	}

	@Override
	public void delete(MemberVO e) {
		mapper.delete(e);
	}

	public void memberDrop(Long idx) {
		mapper.memberDrop(idx);
	}
	
	public HashMap<String, Object> checkUserID(String userID) {
		int cnt = mapper.checkUserID(userID);
		// 由ы꽩?댁빞 ?섎뒗 媛앹껜媛 ?⑥닚???レ옄 ?곗궛??寃곌낵??낆씠硫?踰덇굅濡?쾶 VO瑜??앹꽦?섏? 留먭퀬 hashMap???곕㈃ ?몃━?섎떎.
		HashMap<String, Object> map = new HashMap<>();
		map.put("isExist", cnt == 0 ? false : true);
		return map;
	}
	
	public HashMap<String, Object> checkEmail(String email) {
		int cnt = mapper.checkEmail(email);
		// 由ы꽩?댁빞 ?섎뒗 媛앹껜媛 ?⑥닚???レ옄 ?곗궛??寃곌낵??낆씠硫?踰덇굅濡?쾶 VO瑜??앹꽦?섏? 留먭퀬 hashMap???곕㈃ ?몃━?섎떎.
		HashMap<String, Object> map = new HashMap<>();
		map.put("isExist", cnt == 0 ? false : true);
		return map;
	}
	
	public HashMap<String, Object> memberJoin(JoinRequest joinRequest) {
		// 由ы꽩 (諛쏅뒗寃??ы븿) ??媛앹껜媛 ?뱀젙?섍린 ?대젮??寃쎌슦 hashmap???ъ슜?쒕떎.
		HashMap<String, Object> map = new HashMap<>();
		
		// hashMap 由ы꽩 ???꾨옒???댁슜?쇰줈 援ъ꽦
		// 媛???깃났 ?щ? true, false
		// 硫붿떆吏 "?깃났" "?ㅽ뙣" "臾댁뼵媛 ?ъ쑀濡??명븳 ?ㅽ뙣"
		
		// 1. ?꾩씠??以묐났 泥댄겕
		HashMap<String, Object> idMap = this.checkUserID(joinRequest.getUserID());
		boolean idExist = (boolean) idMap.get("isExist");
		if (idExist) {
			map.put("result", false);
			map.put("message", "?꾩씠?붽? ?ъ슜以묒엯?덈떎.");
			return map;
		}
		
		// 2. ?대찓??以묐났 泥댄겕
		HashMap<String, Object> emailMap = this.checkEmail(joinRequest.getEmail());
		boolean emailExist = (boolean) emailMap.get("isExist");
		if (emailExist) {
			map.put("result", false);
			map.put("message", "?대찓?쇱씠 ?ъ슜以묒엯?덈떎.");
			return map;
		}
		
		// 3. 鍮꾨쾲 ?뺤씤 (鍮꾨쾲2媛쒓? ?숈씪?쒖?, 鍮꾨쾲 湲몄씠 4???댁긽)
		String pw1 = joinRequest.getPassword();
		String pw2 = joinRequest.getPassword2();
		if (!pw1.equals(pw2)) {
			map.put("result", false);
			map.put("message", "鍮꾨?踰덊샇媛 ?쇱튂?섏? ?딆뒿?덈떎.");
			return map;
		}
		if (pw1.length() < 4 || pw2.length() < 4) {
			map.put("result", false);
			map.put("message", "鍮꾨?踰덊샇??4湲???댁긽 ?낅젰?섏꽭??");
			return map;
		}
		
		// 4. ?ъ슜???대쫫 ?덈뒗吏 泥댄겕 (4???댁긽)
		String username = joinRequest.getUsername();
		if (username.length() < 2) {
			map.put("result", false);
			map.put("message", "?대쫫? 2湲???댁긽 ?낅젰?섏꽭??");
			return map;
		}
		
		MemberVO memberVO = MemberVO.builder()
				.email(joinRequest.getEmail())
				.userID(joinRequest.getUserID())
				.password(pw1)
				.username(username)
				.build();
		
		this.insert(memberVO);
		
		map.put("result", true);
		map.put("message", "媛?낆씠 ?꾨즺?섏뿀?듬땲??");
		
		return map;
	}
	
	public HashMap<String, Object> findID(String email) {
		HashMap<String, Object> map = new HashMap<>();
		String userID = mapper.findID(email);
		
		map.put("result", userID == null ? false : true);
		map.put("message", userID == null ? "李얠쑝?쒕뒗 ?꾩씠?붽? ?놁뒿?덈떎." : "李얠쑝?쒕뒗 ?꾩씠?붾뒗 " + userID + "?낅땲??");
		return map;
	}

	public Object changePW(ChangePwRequest changePwRequest) {
		// ?대찓?? ?꾩씠?붾줈 ?대떦 row 議댁옱 ?щ? ?뺤씤 (row媛 ?덉쑝硫?idx媛믪씠 由ы꽩?쒕떎.)
		MemberVO memberVO = MemberVO.builder()
				.email(changePwRequest.getEmail())
				.userID(changePwRequest.getUserID())
				.build();
		Long idx = mapper.findPW(memberVO);
		
		HashMap<String, Object> map = new HashMap<>();
		
		// row媛 ?놁쑝硫?怨꾩젙 紐살갼??硫붿떆吏 由ы꽩
		if (idx == null) {
			map.put("result", false);
			map.put("message", "怨꾩젙??李얠쓣 ???놁뒿?덈떎.");
			return map;
		}
		
		// 怨꾩젙???덉쑝硫??쒕뜡?섍쾶 臾몄옄?댁쓣 ?앹꽦?댁꽌 
		// idx媛믪뿉 ?대떦?섎뒗 鍮꾨?踰덊샇 蹂寃?
		String randomPw = StringUtil.generateRandomString(10);
		
		// 湲곗〈??memberVO媛 ?덇린?뚮Ц??蹂꾨룄濡??앹꽦 ?섏? ?딄퀬 湲곗〈 蹂?섎챸 ?쒖슜
		memberVO = MemberVO.builder()
				.password(randomPw)
				.idx(idx).build();
		mapper.updatePW(memberVO);
		
		// ?꾨즺 硫붿떆吏??鍮꾨?踰덊샇瑜??ｌ뼱??由ы꽩
		map.put("result", true);
		map.put("message", "?꾩떆 鍮꾨?踰덊샇??" + randomPw + "?낅땲??");
		
		return map;
	}

	public boolean updateInfo(HttpServletRequest request, MemberVO memberVO) {
		HttpSession session = request.getSession();
		MemberVO userInfo = (MemberVO) session.getAttribute("userInfo");
		
		if (userInfo == null) {
			return false;
		}
		
		// pk媛믪씠 ?몄뀡???덈뒗嫄곕옉 ?ㅻⅤ硫?濡쒖쭅??異붽????섎룄 ?덈떎.
		if (!memberVO.getIdx().equals(userInfo.getIdx()) ) {
			return false;
		}
		
		// ?몄뀡???덈뒗 userID? ?뚮씪誘명꽣濡??섏뼱??userID媛 ?ㅻⅤ硫?????떆 濡쒖쭅??異붽??섎뒗 寃껊룄 諛⑸쾿?대떎.
		if (!memberVO.getUserID().equals(userInfo.getUserID())) {
			return false;
		}
		
		if (!memberVO.getPassword().equals("")) {
			memberVO.setPassword("");
		}
		
		mapper.updateInfo(memberVO);
		
		return true;
	}
	
	
	
}
