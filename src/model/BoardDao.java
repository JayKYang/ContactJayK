package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import mapper.BoardMapper;

// board 테이블 관련 모든 메서드를 멤버로 가지고 있는 클래스
public class BoardDao {
	private final static String ns="mapper.BoardMapper.";
	public int portInsert(PortBoard board) {
		SqlSession session = DBConnection.getConnection();
		try {
			return session.getMapper(BoardMapper.class).portInsert(board);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(session);
		}
		return 0;
	}
	public int guestInsert(GuestBook board) {
		SqlSession session = DBConnection.getConnection();
		try {
			return session.getMapper(BoardMapper.class).guestInsert(board);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(session);
		}
		return 0;
	}

	public int PortBoardCount() {
		SqlSession session = DBConnection.getConnection();
		try {
			return session.getMapper(BoardMapper.class).PortBoardCount();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(session);
		}

		return 0;
	}
	public int GuestBookForAddNum() {
		SqlSession session = DBConnection.getConnection();
		try {
			return session.getMapper(BoardMapper.class).GuestBookForAddNum();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(session);
		}
		
		return 0;
	}
	public int BusinessCardCount(String email) {
		SqlSession session = DBConnection.getConnection();
		try {
			return session.getMapper(BoardMapper.class).businessCount(email);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(session);
		}
		return 0;
	}
	public int businessForAddNum() {
		SqlSession session = DBConnection.getConnection();
		try {
			return session.getMapper(BoardMapper.class).businessForAddNum();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(session);
		}
		
		return 0;
	}
	
	public int GuestReplyCount(int num) {
		SqlSession session = DBConnection.getConnection();
		try {
			return session.getMapper(BoardMapper.class).GuestReplyCount(num);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(session);
		}
		
		return 0;
	}
	public int BusinessCardAllCount() {
		SqlSession session = DBConnection.getConnection();
		try {
			return session.getMapper(BoardMapper.class).BusinessCardAllCount();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(session);
		}
		
		return 0;
	}
	public int deletePortfolio(String no) {
		SqlSession session = DBConnection.getConnection();
		try {
			return session.getMapper(BoardMapper.class).deletePortfolio(no);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBConnection.close(session);
		}
		return 0;
	}

	public List<PortBoard> portlist(int count) {
		SqlSession session = DBConnection.getConnection();
		Map map = new HashMap();
		map.put("count", count);
		map.put("limit", 4);

		try {		
			List<PortBoard> list = session.selectList(ns+"portselect",map);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(session);
		}
		return null;
	}
	public List<BusinessCard> businesscardAllList() {
		SqlSession session = DBConnection.getConnection();
//		Map map = new HashMap();
//		map.put("count", count);
		
		try {		
			List<BusinessCard> list = session.selectList(ns+"businesscardSelect");
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(session);
		}
		return null;
	}
	public List<GuestBook> guestbooklist(int count) {
		SqlSession session = DBConnection.getConnection();
//		int limit = 4;
//		int startrow = (pageNum - 1) * limit;
//		Map map = new HashMap();
//		map.put("count", count);
//		map.put("limit", 4);
//		map.put("column", column);
//		map.put("find", find);
		
		try {		
			List<GuestBook> list = session.selectList(ns+"guestselect");
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(session);
		}
		return null;
	}
	public BusinessCard businesscardList(String email) {
		SqlSession session = DBConnection.getConnection();
//		int limit = 4;
//		int startrow = (pageNum - 1) * limit;
		Map map = new HashMap();
		map.put("email", email);
//		map.put("limit", 4);
//		map.put("column", column);
//		map.put("find", find);
		
		try {		
			BusinessCard sendCard = session.selectOne(ns+"businessselect",map);
			return sendCard;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(session);
		}
		return null;
	}
	public List<GuestReply> guestreplylist(int ref_no) {
		SqlSession session = DBConnection.getConnection();
//		int limit = 4;
//		int startrow = (pageNum - 1) * limit;
		Map map = new HashMap();
		map.put("ref_no", ref_no);
//		map.put("limit", 5);
//		map.put("column", column);
//		map.put("find", find);
		
		try {		
			List<GuestReply> list = session.selectList(ns+"guestreplyselect",map);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(session);
		}
		return null;
	}

	public PortBoard selectOne(String num) {
		SqlSession session = DBConnection.getConnection();
		Map map = new HashMap();
		map.put("num",num);
		try {
				return session.selectOne(ns + "portselect", map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(session);
		}
		return null;
	}

//	public void addReadCnt(String num) {
//		SqlSession session = DBConnection.getConnection();
//		String sql = "update board set readcnt=readcnt+1 where num=?";
//		try {
//			session.getMapper(BoardMapper.class).addReadCnt(num);
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			DBConnection.close(session);
//		}
//	}

	/*
	 * board : 원글 정보 : num, ref, reflevel, refstep 입력 정보(답글입력) : name, subject,
	 * content, pass 답글 등록 : db insert 필요 num : 최대 num + 1 ref : 원글의 ref 동일함
	 */
	/*
	 * num ref reflevel refstep 3 3 0 0 2 2 0 0 6 2 1 1 4 2 1 2 7 2 2 3 5 2 2 4 1 1
	 * 0 0
	 * 
	 */
	public int GuestReplyForAddNum() {
		SqlSession session = DBConnection.getConnection();
		try {
			return session.getMapper(BoardMapper.class).GuestReplyForAddNum();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(session);
		}

		return 0;
	}
	
	
	// board : 화면에서 수정 입력된 정보 저장
	public boolean reply(GuestReply board) {
		// board에 저장된 num의 최대값 리턴. 답변글 등록시 num 하나 증가
		int num = this.GuestReplyForAddNum();
		int ref = board.getRef(); // 원글의 ref 값
		int reflevel = board.getReflevel(); // 원글의 reflevel 값
		int refstep = board.getRefstep(); // 원글의 refstep 값
		SqlSession session = DBConnection.getConnection();
		try {
			session.getMapper(BoardMapper.class).reply(board);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			DBConnection.close(session);
		}
		board.setRef(ref); // 원글의 ref와 같아야 하므로 변경 할 필요가 없다.
		board.setReflevel(++reflevel);
		board.setRefstep(++refstep);
		board.setRep_no(++num);
		int result = this.replyInsert(board);
		if (result > 0) {
			return true;
		}
		else {
			return false;
		}
	}
	public int replyInsert(GuestReply gr) {
		SqlSession session = DBConnection.getConnection();
		try {
			return session.getMapper(BoardMapper.class).replyInsert(gr);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(session);
		}
		return 0;
	}
	
	public int BusinessCardInsert(BusinessCard bc) {
		SqlSession session = DBConnection.getConnection();
		try {
			return session.getMapper(BoardMapper.class).businessCardInsert(bc);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(session);
		}
		return 0;
	}
	public int BusinessCardUpdate(BusinessCard bc) {
		SqlSession session = DBConnection.getConnection();
		try {
			return session.getMapper(BoardMapper.class).BusinessCardUpdate(bc);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(session);
		}
		return 0;
	}
	
	public int portfolioUpdate(PortBoard board) {
		System.out.println(board);
		SqlSession session = DBConnection.getConnection();
		try {
			return session.getMapper(BoardMapper.class).portfolioUpdate(board);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBConnection.close(session);
		}
		return 0;
	}	
	public int deleteBusinessCard(String num) {
		SqlSession session = DBConnection.getConnection();
		try {
			return session.getMapper(BoardMapper.class).deleteBusinessCard(num);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBConnection.close(session);
		}
		return 0;
	}
	public int deleteVisitor(String num) {
		SqlSession session = DBConnection.getConnection();
		try {
			return session.getMapper(BoardMapper.class).deleteVisitor(num);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBConnection.close(session);
		}
		return 0;
	}
	public int deleteReply(String num) {
		SqlSession session = DBConnection.getConnection();
		try {
			return session.getMapper(BoardMapper.class).deleteReply(num);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBConnection.close(session);
		}
		return 0;
	}
}
