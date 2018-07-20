package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import mapper.MemberMapper;

// Model 담당 클래스 : 회원  db관련 모든 메서드 멤버로 가짐
public class MemberDao {
	private final static String ns="mapper.MemberMapper.";
	
	public Member selectOne(String email) { // 로그인시에 입력된 id 파라미터 값
		SqlSession session = DBConnection.getConnection();
		try {
			Map map = new HashMap();
			map.put("email", email);
			return (Member)session.selectOne(ns+"select",map); // selectOne의 리턴타입은 Object
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBConnection.close(session);
		}
		return null;
	}
	
	//mem : 화면에서 입력된 회원가입 정보를 저장하고 있는 객체
	public int insert(Member mem) {
		SqlSession session = DBConnection.getConnection();
		try {
			return session.getMapper(MemberMapper.class).insert(mem);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBConnection.close(session);
		}
		return 0;
	}
	public int update(Member mem) { // 로그인시에 입력된 id 파라미터 값
		SqlSession session = DBConnection.getConnection();
		try {
			return session.getMapper(MemberMapper.class).update(mem); 
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBConnection.close(session);
		}
		return 0;
	}
	public int delete(String id) { // 로그인시에 입력된 id 파라미터 값
		SqlSession session = DBConnection.getConnection();
		try {
			return session.getMapper(MemberMapper.class).delete(id);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBConnection.close(session);
		}
		return 0;
	}
	/*
	 * 등록된 회원 정보 전체를 조회 메서드
	 * 한개의 레코드는 한개의 Member 객체에 저장하여 List 전달 
	 */
	public List<Member> list(){ 
		SqlSession session = DBConnection.getConnection();
		try {
			return session.selectList(ns+"select");
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBConnection.close(session);
		}
		return null;
	}
}
