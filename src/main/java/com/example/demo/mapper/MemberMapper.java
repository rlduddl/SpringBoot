package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.vo.MemberVO;

// DAO, MAPPER
@Repository
public class MemberMapper {
	
	@Autowired
	private SqlSession session;

	//public MemberMapper(SqlSession session) {
	//	this.session = session;
	//}
	
	/**
	 * ?뚯썝 紐⑸줉 
	 * @param memberVO
	 * @return
	 */
	public List<MemberVO> selectList(MemberVO memberVO) {
		return session.selectList("member.selectList", memberVO);
	}
	
	/**
	 * ?뚯썝 ?뺣낫
	 * @param memberVO
	 * @return
	 */
	public MemberVO selectOne(MemberVO memberVO) {
		return session.selectOne("member.selectOne", memberVO);
	}
	
	/**
	 * ?뚯썝 ???
	 * @param memberVO
	 */
	public void insert(MemberVO memberVO) {
		session.insert("member.insert", memberVO);
	}
	
	/**
	 * ?뺣낫 ?섏젙
	 * @param memberVO
	 */
	public void update(MemberVO memberVO) {
		session.update("member.update", memberVO);
	}
	
	/**
	 * ??젣
	 * @param memberVO
	 */
	public void delete(MemberVO memberVO) {
		session.delete("member.delete", memberVO);
	}
	
	/**
	 * ?뚯썝 ?덊눜 泥섎━
	 * @param idx
	 */
	public void memberDrop(Long idx) {
		session.update("member.memberDrop", idx);
	}
	
	/**
	 * ?꾩씠??以묐났 泥댄겕
	 * @param userID
	 * @return
	 */
	public int checkUserID(String userID) {
		return session.selectOne("member.checkUserID", userID);
	}
	
	/**
	 * ?대찓??以묐났 泥댄겕
	 * @param email
	 * @return
	 */
	public int checkEmail(String email) {
		return session.selectOne("member.checkEmail", email);
	}
	
	/**
	 * ?대찓?쇰줈 ?꾩씠??李얘린
	 * @param email
	 * @return
	 */
	public String findID(String email) {
		return session.selectOne("member.findID", email);
	}
	
	/**
	 * ?꾩씠?붿? ?대찓?쇰줈 ?ъ슜?먯쓽 PK媛??살뼱 ?닿린
	 * @param memberVO
	 * @return
	 */
	public Long findPW(MemberVO memberVO) {
		return session.selectOne("member.findPW", memberVO);
	}
	
	/**
	 * pk媛믪쑝濡?鍮꾨?踰덊샇 蹂寃?
	 * @param memberVO
	 */
	public void updatePW(MemberVO memberVO) {
		session.update("member.updatePW", memberVO);
	}
	
	/**
	 * ?뚯썝?뺣낫 蹂寃?
	 * @param memberVO
	 */
	public void updateInfo(MemberVO memberVO) {
		session.update("member.updateInfo", memberVO);
	}
	
}
