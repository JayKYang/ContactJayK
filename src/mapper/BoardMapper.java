package mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import model.BusinessCard;
import model.GuestBook;
import model.GuestReply;
import model.PortBoard;

public interface BoardMapper {
	@Insert("insert into portfolioboard(no,writer,subject,content,imagefile,regdate) values(#{no},#{writer},#{subject},#{content},#{imagefile},now())")
	int portInsert(PortBoard pb);

	@Insert("insert into guestbook(no,nickname,content,regdate,writer) values(#{no},#{nickname},#{content},now(),#{writer})")
	int guestInsert(GuestBook gb);

	@Insert("insert into guestreply(rep_no,ref_no,nickname,email,regdate,content,ref,reflevel,refstep) values(#{rep_no}, #{ref_no}, #{nickname},#{email}, now(), #{content}, #{ref}, #{reflevel}, #{refstep})")
	int replyInsert(GuestReply gr);

	@Insert("insert into businesscard(no,name,company,tel,email,comment,senddate) values(#{no}, #{name}, #{company},#{tel}, #{email}, #{comment},now())")
	int businessCardInsert(BusinessCard bc);

	@Update("update businesscard set name=#{name},company=#{company},tel = #{tel},comment=#{comment},senddate=now() where no=#{no}")
	int BusinessCardUpdate(BusinessCard bc);
	
	@Select("select count(*) from portfolioboard")
	int PortBoardCount();

	@Delete("delete from portfolioboard where no = #{no}")
	int deletePortfolio(String no);

	@Delete("delete from businesscard where no = #{no}")
	int deleteBusinessCard(String no);

	@Delete("delete from guestbook where no = #{no}")
	int deleteVisitor(String no);

	@Delete("delete from guestreply where rep_no = #{no}")
	int deleteReply(String no);

	@Select("select count(*) from businesscard")
	int BusinessCardAllCount();

	@Select("select ifnull(max(no),0) from guestbook")
	int GuestBookForAddNum();

	@Select("select ifnull(max(rep_no),0) from guestreply")
	int GuestReplyForAddNum();

	@Select("select count(*) from guestreply where ref_no=#{ref_no}")
	int GuestReplyCount(int ref_no);

	@Select("select count(*) from businesscard where email = #{email}")
	int businessCount(String email);

	@Select("select ifnull(max(no),0) from businesscard")
	int businessForAddNum();

	@Update("update guestreply set refstep = refstep+1 where ref=#{ref} and refstep>#{refstep}")
	void reply(GuestReply gr);
	
	@Update("update portfolioboard set writer=#{writer},subject=#{subject},content=#{content},imagefile=#{imagefile},regdate=now() where no=#{no}")
	int portfolioUpdate(PortBoard pb);
	
/*	@Delete("delete from board where num=#{value}")
	int delete(String num);

	

	@Update("update board set name=#{name}, subject=#{subject}, content=#{content}, file1=#{file1} where num=#{num}")
	int update(PortBoard board);

	@Update("update board set readcnt=readcnt+1 where num=#{num}")
	void addReadCnt(String num);*/

}
