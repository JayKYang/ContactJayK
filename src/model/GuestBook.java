package model;

import java.util.Date;

public class GuestBook {
	private int no;
	private String nickname;
	private String content;
	private Date regdate;
	private String writer;
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	@Override
	public String toString() {
		return "VisitBoard [no=" + no + ", nickname=" + nickname + ", content=" + content + ", regdate=" + regdate
				+ ", writer=" + writer + "]";
	}
	
}
