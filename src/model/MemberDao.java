package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import mapper.MemberMapper;

// Model ��� Ŭ���� : ȸ��  db���� ��� �޼��� ����� ����
public class MemberDao {
	private final static String ns="mapper.MemberMapper.";
	
	public Member selectOne(String email) { // �α��νÿ� �Էµ� id �Ķ���� ��
		SqlSession session = DBConnection.getConnection();
		try {
			Map map = new HashMap();
			map.put("email", email);
			return (Member)session.selectOne(ns+"select",map); // selectOne�� ����Ÿ���� Object
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBConnection.close(session);
		}
		return null;
	}
	
	//mem : ȭ�鿡�� �Էµ� ȸ������ ������ �����ϰ� �ִ� ��ü
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
	public int update(Member mem) { // �α��νÿ� �Էµ� id �Ķ���� ��
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
	public int delete(String id) { // �α��νÿ� �Էµ� id �Ķ���� ��
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
	 * ��ϵ� ȸ�� ���� ��ü�� ��ȸ �޼���
	 * �Ѱ��� ���ڵ�� �Ѱ��� Member ��ü�� �����Ͽ� List ���� 
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
