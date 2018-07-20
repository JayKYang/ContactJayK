package action;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import model.BoardDao;
import model.BusinessCard;
import model.GuestBook;
import model.GuestReply;
import model.Member;
import model.MemberDao;
import model.PortBoard;

public class ContactJayKAction {
	HashPass hp = new HashPass();
	BoardDao Bdao = new BoardDao();
	MemberDao Mdao = new MemberDao();
	
	// VIEW
	public ActionForward about(HttpServletRequest request, HttpServletResponse response) {
		return new ActionForward(false, "model/about.jsp");
	}

	public ActionForward memberList(HttpServletRequest request, HttpServletResponse response) {
		String origin_path = request.getServletContext().getContextPath()+"/";
		String login;
		
		login = (String)request.getSession().getAttribute("login");
		if(login==null) {
			request.setAttribute("msg", "JayK�� �̿��� �� �ֽ��ϴ�.");
			request.setAttribute("url", origin_path+"loginForm.cj");
			return new ActionForward(false,"../alert.jsp");
		}else if(!login.equals("dumbyura@naver.com")){
			request.setAttribute("msg", "JayK�� �̿��� �� �ֽ��ϴ�.");
			request.setAttribute("url", origin_path+"about.cj");
			return new ActionForward(false,"../alert.jsp");
		}
		
		List<Member> list = Mdao.list();
		request.setAttribute("list", list);
		
		return new ActionForward(false, "memberList.jsp");
	}

	// MEMBER
	public ActionForward loginForm(HttpServletRequest request, HttpServletResponse response) {
		String origin_path = request.getServletContext().getContextPath()+"/";
		return new ActionForward(false, "/model/member/loginForm.jsp");
	}
	public ActionForward login(HttpServletRequest request, HttpServletResponse response) {
		//1. id, pass �Ķ���� ����
		String path = request.getServletContext().getContextPath()+"/";
		String email = request.getParameter("email");
		String password = null;
		try {
			password = hp.password(request.getParameter("password"));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//2. id ���� ���� ����
		Member mem = Mdao.selectOne(email);
				
		String msg = "���̵� Ȯ���ϼ���";
		String url = path+"loginForm.cj";
		//System.out.println(url);
		if(mem == null) {			
		}else {
			if(password.equals(mem.getPassword())) {
				msg = mem.getName() + "���� �α��� �ϼ̽��ϴ�.";
				url = path+"about.cj";
				//System.out.println(url);
				request.getSession().setAttribute("login", email);
				request.getSession().setAttribute("name", mem.getName());
			}else {
				msg = "��й�ȣ�� Ʋ���ϴ�.";
			}
		}
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		
		return new ActionForward(false, "../alert.jsp");
	}
	public ActionForward logout(HttpServletRequest request, HttpServletResponse response) {
		String origin_path = request.getServletContext().getContextPath()+"/";
		String id = (String)request.getSession().getAttribute("login") ;
		String name = (String)request.getSession().getAttribute("name");
		request.getSession().removeAttribute("login");
		request.getSession().removeAttribute("name");
		request.getSession().invalidate();
		request.setAttribute("msg", name+"�� �α׾ƿ��� �Ϸ�Ǿ����ϴ�.");
		request.setAttribute("url", origin_path+"about.cj");
		
		return new ActionForward(false, "../alert.jsp");
	}
	
	/*public void mailsend(HttpServletRequest request, HttpServletResponse response) {
		String recipient = request.getParameter("recipient");
		String msg="";
		MailBean mb = new MailBean(recipient);
		if(mb.sendMail()){
			msg = "���������� ���½��ϴ�.";
			request.setAttribute("code", mb.getCode());
		}
		else{
			msg = "���������� ������ ������ϴ�.";		
		}
		request.setAttribute("msg", msg);
		
		//return new ActionForward(false, "../prompt.jsp");
	}*/
	
	public ActionForward joinForm(HttpServletRequest request, HttpServletResponse response) {
		return new ActionForward(false, "model/member/joinForm.jsp");
	}
	public ActionForward join(HttpServletRequest request, HttpServletResponse response) {
		String path = request.getServletContext().getContextPath()+"/";
		
		Member mem = new Member();
		String password=null;
		try {
			password = hp.password(request.getParameter("password"));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		mem.setEmail(request.getParameter("email"));
		mem.setName(request.getParameter("name"));
		mem.setPassword(password);
		mem.setTel(request.getParameter("tel").equals("")?null:request.getParameter("tel"));
		mem.setCompany(request.getParameter("company").equals("")?null:request.getParameter("company"));
		String msg = "ȸ�����Կ� �����Ͽ����ϴ�.";
		String url = path+"about.cj";
		//2. Member ��ü�� db�� insert �ϱ�.
		int result = Mdao.insert(mem);
		if(result > 0) {
			msg = "ȸ�����Կ� �����Ͽ����ϴ�.";
		}else {
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return new ActionForward(false,"../alert.jsp");
	}
	
	public ActionForward info(HttpServletRequest request, HttpServletResponse response) {
		String login = (String)request.getSession().getAttribute("login");
		String id = (String)request.getParameter("email");
		String path = request.getServletContext().getContextPath()+"/";
		if(login == null || login.equals("")) {
			request.setAttribute("msg", "�α��� �� �̿��ϼ���");
			request.setAttribute("url", path+"loginForm.cj");
			return new ActionForward(false, "../alert.jsp");
		}
		else {
			if(!login.equals(id) && !login.equals("dumbyura@naver.com")) {
				request.setAttribute("msg", "���� ������ �̿��� �����մϴ�.");
				request.setAttribute("url", path+"about.cj");
				return new ActionForward(false, "../alert.jsp");
			}
		}
		Member mem = Mdao.selectOne(id);
		request.setAttribute("mem", mem);
		return new ActionForward(false,"info.jsp");
	}
	
	public ActionForward updateInfo(HttpServletRequest request, HttpServletResponse response) {
		String login = (String)request.getSession().getAttribute("login");
		String id = (String)request.getParameter("email");
		String path = request.getServletContext().getContextPath()+"/";
		if(login == null || login.equals("")) {
			request.setAttribute("msg", "�α��� �� �̿��ϼ���");
			request.setAttribute("url", path+"loginForm.cj");
			return new ActionForward(false, "../alert.jsp");
		}
		else {
			if(!login.equals(id) && !login.equals("dumbyura@naver.com")) {
				request.setAttribute("msg", "���� ������ �̿��� �����մϴ�.");
				request.setAttribute("url", path+"about.cj");
				return new ActionForward(false, "../alert.jsp");
			}
		}
		String password=null;
		try {
			password = hp.password(request.getParameter("password"));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Member mem = Mdao.selectOne(id);
		String msg = null;
		String url = null;
//		System.out.println(mem.getPassword() + " ////" + password);
		if(mem.getPassword().equals(password)) {
			mem.setPassword(password);
			mem.setName(request.getParameter("name"));
			mem.setTel(request.getParameter("tel"));
			mem.setEmail(request.getParameter("email"));
			mem.setCompany(request.getParameter("company"));
			
			msg = "ȸ���������� ����";
			url = "info.cj?email="+id;
			
			if(Mdao.update(mem)>0) {
				msg = "ȸ���������� ����";
				url = "info.cj?email="+id;
				request.getSession().setAttribute("name", mem.getName());
				if(request.getSession().getAttribute("login").equals("dumbyura@naver.com")) {
					url = "memberList.cj";
				}
			}else {
			}
		}else {
			msg = "��й�ȣ�� Ʋ�Ƚ��ϴ�.";
			url = "info.cj?email="+id;
		}
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		
		return new ActionForward(false,"../alert.jsp");
	}
	
	public ActionForward deleteMember(HttpServletRequest request, HttpServletResponse response) {
		String login = (String)request.getSession().getAttribute("login");
		String id = (String)request.getParameter("email");
		String path = request.getServletContext().getContextPath()+"/";
		if(login == null || login.equals("")) {
			request.setAttribute("msg", "�α��� �� �̿��ϼ���");
			request.setAttribute("url", path+"loginForm.cj");
			return new ActionForward(false, "../alert.jsp");
		}
		else {
			if(!login.equals(id) && !login.equals("dumbyura@naver.com")) {
				request.setAttribute("msg", "���� ������ �̿��� �����մϴ�.");
				request.setAttribute("url", path+"about.cj");
				return new ActionForward(false, "../alert.jsp");
			}
		}
		String msg = null;
		String url = null;
		String password=null;
		Member mem = Mdao.selectOne(id);
		
		if(!login.equals("dumbyura@naver.com")) {
			try {
				password = hp.password(request.getParameter("password"));
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
			if(login.equals("dumbyura@naver.com")) {
				if(Mdao.delete(id)>0) {
					msg = id + "���� ȸ�� Ż�� �Ϸ� �Ǿ����ϴ�.";
					url = "memberList.cj";
				}else {
					url = "memberList.cj";
				}
			}else {
				if(mem.getPassword().equals(password)) {
					if(Mdao.delete(login)>0) {
						msg = mem.getName() + "���� ȸ�� Ż�� �Ϸ� �Ǿ����ϴ�.";
						url = path+"about.cj";
						request.getSession().removeAttribute("login");
						request.getSession().invalidate();
					}else {
						
					}				
				}else {
					msg = "��й�ȣ�� Ʋ�Ƚ��ϴ�.";
					url = "info.cj?email="+id;
				}
			}
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return new ActionForward(false,"../alert.jsp");
	}
	
	
	// BOARD
	public ActionForward portfolio(HttpServletRequest request, HttpServletResponse response) {
//		Date today = new Date();
		int boardcount = Bdao.PortBoardCount(); // ��ϵ� �� �Խù� �Ǽ�
		List<PortBoard> list = Bdao.portlist(0); // ȭ�鿡 ��µ� �Խù� ��ü��
		request.setAttribute("boardcount", boardcount);
		request.setAttribute("list", list);

		return new ActionForward(false, "model/portfolio.jsp");
	}
	public ActionForward portload(HttpServletRequest request, HttpServletResponse response) {
//		Date today = new Date();
		int boardcount = Bdao.PortBoardCount(); // ��ϵ� �� �Խù� �Ǽ�
		List<PortBoard> list = Bdao.portlist(Integer.parseInt(request.getParameter("count"))); // ȭ�鿡 ��µ� �Խù� ��ü��
		request.setAttribute("boardcount", boardcount);
		request.setAttribute("list", list);
		
		return new ActionForward(false, "/model/board/portload.jsp");
	}
	
	public ActionForward portWriteForm(HttpServletRequest request, HttpServletResponse response) {
		return new ActionForward(false, "portWriteForm.jsp");
	}
	public ActionForward portUpdateForm(HttpServletRequest request, HttpServletResponse response) {
		String login = (String)request.getSession().getAttribute("login");
		String origin_path = request.getServletContext().getContextPath()+"/";
//		System.out.println(origin_path);
		String no = request.getParameter("no");
		String url = origin_path+"portfolio.cj";
		String msg = "������������ �̵��մϴ�.";
		//String id = (String)request.getParameter("writer");
		if(login == null || login.equals("")) {
			request.setAttribute("msg", "�α��� �� �̿��ϼ���");
			request.setAttribute("url", origin_path+"loginForm.cj");
			return new ActionForward(false,"/model/alert.jsp");
		}
		else {
			if(!login.equals("dumbyura@naver.com")) {
				msg="JayK�� �̿��� �����մϴ�.";
				url=origin_path+"portfolio.cj";
				request.setAttribute("msg", msg);
				request.setAttribute("url", url);
				return new ActionForward(false,"/model/alert.jsp");
			}
		}
		
		PortBoard pb = Bdao.selectOne(no);
		request.setAttribute("board", pb);
		
		return new ActionForward(false, "/model/board/portUpdateForm.jsp");
	}
	public ActionForward portWrite(HttpServletRequest request, HttpServletResponse response) {
		String orgin_path = request.getServletContext().getContextPath()+"/";
		String path = request.getServletContext().getRealPath("/") + "model/board/imagefile/";
//		System.out.println(path);
		int num = Bdao.PortBoardCount();
		MultipartRequest multi;
		
		try {
			multi = new MultipartRequest(request, path, 10 * 1024 * 1024, "euc-kr",new DefaultFileRenamePolicy());
			PortBoard bo = new PortBoard();
			bo.setSubject(multi.getParameter("subject"));
			bo.setWriter(multi.getParameter("writer"));
			bo.setContent(multi.getParameter("content"));
			bo.setNo(++num);
			if(multi.getFilesystemName("imagefile")==null || multi.getFilesystemName("imagefile").equals("")) {
				bo.setImagefile(multi.getFilesystemName("imagefile"));
			}else {
				bo.setImagefile(multi.getFilesystemName("imagefile"));
			}
			int result = Bdao.portInsert(bo);
			String msg = null;
			String url = orgin_path+"portfolio.cj";
			if (result > 0) {
				msg = "��� �Ϸ�";
			} else {
				msg = "��� ����";
			}
			request.setAttribute("msg", msg);
			request.setAttribute("url", url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// �Խù� ����ϱ�
		return new ActionForward(false, "../alert.jsp");
	}
	public ActionForward modifyPortfolio(HttpServletRequest request, HttpServletResponse response) {
		String login = (String)request.getSession().getAttribute("login");
		String orgin_path = request.getServletContext().getContextPath()+"/";
		PortBoard bo = new PortBoard();
		String url = orgin_path+"portfolio.cj";
		String msg = "���� ����.";
		String path = request.getServletContext().getRealPath("/") + "model/board/imagefile/";
		MultipartRequest multi;
		//String id = (String)request.getParameter("writer");
		if(login == null || login.equals("")) {
			request.setAttribute("msg", "�α��� �� �̿��ϼ���");
			request.setAttribute("url", "loginform.cj");
			return new ActionForward(false, "/model/alert.jsp");
		}
		else {
			if(!login.equals("dumbyura@naver.com")) {
				msg="JayK�� �̿��� �����մϴ�.";
				url=orgin_path+"portfolio.cj";
				return new ActionForward(false, "/model/alert.jsp");
			}
		}

		
		try {
			multi = new MultipartRequest(request, path, 10 * 1024 * 1024, "euc-kr",new DefaultFileRenamePolicy());
			
			bo.setNo(Integer.parseInt(multi.getParameter("no")));
			bo.setSubject(multi.getParameter("subject"));
			bo.setWriter(multi.getParameter("writer"));
			bo.setContent(multi.getParameter("content"));
			if(multi.getFilesystemName("imagefile")==null || multi.getFilesystemName("imagefile").equals("")) {
				bo.setImagefile(multi.getFilesystemName("imagefile"));
			}else {
				bo.setImagefile(multi.getFilesystemName("imagefile"));
			}
			int result = Bdao.portfolioUpdate(bo);
			if (result > 0) {
				msg = "��Ʈ������ ���� ����";
			} else {
				msg = "��� ����";
			}
			request.setAttribute("msg", msg);
			request.setAttribute("url", url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return new ActionForward(false,"/model/alert.jsp");
		
	}
	public ActionForward deletePortfolio(HttpServletRequest request, HttpServletResponse response) {
		String login = (String)request.getSession().getAttribute("login");
		String orgin_path = request.getServletContext().getContextPath()+"/";
		String no = request.getParameter("no");
		String url = orgin_path+"portfolio.cj";
		String msg = "������ ���� �߽��ϴ�.";
		//String id = (String)request.getParameter("writer");
		if(login == null || login.equals("")) {
			request.setAttribute("msg", "�α��� �� �̿��ϼ���");
			request.setAttribute("url", orgin_path+"loginForm.cj");
			return new ActionForward(false, "../alert.jsp");
		}
		else {
			if(!login.equals("dumbyura@naver.com")) {
				msg="JayK�� �̿��� �����մϴ�.";
				url=orgin_path+"portfolio.cj";
				request.setAttribute("msg", msg);
				request.setAttribute("url", url);
				return new ActionForward(false, "../alert.jsp");
			}
		}
		
		if(Bdao.deletePortfolio(no)>0) {
			msg = no + "�� �� ������ �Ϸ��߽��ϴ�.";
		}else {
			
		}	
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);

		return new ActionForward(false, "../alert.jsp");
	}
	
	public ActionForward deleteContact(HttpServletRequest request, HttpServletResponse response) {
		String login = (String)request.getSession().getAttribute("login");
		String id = (String)request.getParameter("email");
		String orgin_path = request.getServletContext().getContextPath()+"/";
		String no = request.getParameter("no");
		String url = orgin_path+"contact.cj";
		String msg = "������ ���� �߽��ϴ�.";
		if(login == null || login.equals("")) {
			request.setAttribute("msg", "�α��� �� �̿��ϼ���");
			request.setAttribute("url", "/model/loginform.cj");
			return new ActionForward(false, "../alert.jsp");
		}
		else {
			if(!login.equals(id) && !login.equals("dumbyura@naver.com")) {
				msg="�̿������ �����ϴ�.";
				url=orgin_path+"about.cj";
				request.setAttribute("msg", msg);
				request.setAttribute("url", url);
				return new ActionForward(false, "../alert.jsp");
			}
		}
		
		if(Bdao.deleteBusinessCard(no)>0) {
			msg ="���� ������ �Ϸ��߽��ϴ�.";
		}else {
			msg ="���� ������ �����߽��ϴ�.";
			
		}	
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		
		return new ActionForward(false, "/model/alert.jsp");
	}
	
	public ActionForward deleteReply(HttpServletRequest request, HttpServletResponse response) {
		String login = (String)request.getSession().getAttribute("login");
		String id = (String)request.getParameter("email");
		String origin_path = request.getServletContext().getContextPath()+"/";
		String no = request.getParameter("no");
		String url = origin_path+"visitor.cj";
		String msg = "������ ���� �߽��ϴ�.";
		if(login == null || login.equals("")) {
			request.setAttribute("msg", "�α��� �� �̿��ϼ���");
			request.setAttribute("url", origin_path+"loginForm.cj");
			return new ActionForward(false, "../alert.jsp");
		}
		else {
			if(!login.equals(id) && !login.equals("dumbyura@naver.com")) {
				msg="�̿������ �����ϴ�.";
				url=origin_path+"visitor.cj";
				request.setAttribute("msg", msg);
				request.setAttribute("url", url);
				return new ActionForward(false, "../alert.jsp");
			}
		}
		
		if(Bdao.deleteReply(no)>0) {
			msg ="��� ������ �Ϸ��߽��ϴ�.";
		}else {
			msg ="��� ������ �����߽��ϴ�.";
			
		}	
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		
		return new ActionForward(false, "/model/alert.jsp");
	}
	
	public ActionForward deleteVisitor(HttpServletRequest request, HttpServletResponse response) {
		String login = (String)request.getSession().getAttribute("login");
		String id = (String)request.getParameter("email");
		String orgin_path = request.getServletContext().getContextPath()+"/";
		String no = request.getParameter("no");
		String url = orgin_path+"visitor.cj";
		String msg = "������ ���� �߽��ϴ�.";
		if(login == null || login.equals("")) {
			request.setAttribute("msg", "�α��� �� �̿��ϼ���");
			request.setAttribute("url", orgin_path+"loginForm.cj");
			return new ActionForward(false, "../alert.jsp");
		}
		else {
			if(!login.equals(id) && !login.equals("dumbyura@naver.com")) {
				msg="�̿������ �����ϴ�.";
				url=orgin_path+"visitor.cj";
				request.setAttribute("msg", msg);
				request.setAttribute("url", url);
				return new ActionForward(false, "../alert.jsp");
			}
		}
		
		if(Bdao.deleteVisitor(no)>0) {
			msg ="���� ������ �Ϸ��߽��ϴ�.";
		}else {
			msg ="���� ������ �����߽��ϴ�.";
			
		}	
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		
		return new ActionForward(false, "/model/alert.jsp");
	}
	public ActionForward visitor(HttpServletRequest request, HttpServletResponse response) {
		List<GuestBook> list = Bdao.guestbooklist(0); // ȭ�鿡 ��µ� �Խù� ��ü��
		request.setAttribute("list", list);
		return new ActionForward(false, "/model/visitor.jsp");
	}
	public ActionForward replyView(HttpServletRequest request, HttpServletResponse response) {
		List<GuestReply> replylist = Bdao.guestreplylist(Integer.parseInt(request.getParameter("num"))); // ȭ�鿡 ��µ� �Խù� ��ü��
		int replycount = Bdao.GuestReplyCount(Integer.parseInt(request.getParameter("num")));
		request.setAttribute("replycount", replycount);	
		request.setAttribute("replylist", replylist);
		
		return new ActionForward(false, "/model/board/replyView.jsp");
	}
	public ActionForward visitorWrite(HttpServletRequest request, HttpServletResponse response) {
		String login = (String)request.getSession().getAttribute("login");
		String id = (String)request.getParameter("writer");
		String orgin_path = request.getServletContext().getContextPath()+"/";
		if(login == null || login.equals("")) {
			request.setAttribute("msg", "�α��� �� �̿��ϼ���");
			request.setAttribute("url", orgin_path+"visitor.cj");
			return new ActionForward(false, "../alert.jsp");
		}
		else {
			if(!login.equals(id) && !login.equals("dumbyura@naver.com")) {
				request.setAttribute("msg", "���� ������ �̿��� �����մϴ�.");
				request.setAttribute("url", orgin_path+"visitor.cj");
				return new ActionForward(false, "../alert.jsp");
			}
		}
		
		int num = Bdao.GuestBookForAddNum();
	
		GuestBook gb = new GuestBook();
		gb.setNickname(request.getParameter("nickname"));
		gb.setWriter(request.getParameter("writer"));
		gb.setContent(request.getParameter("content"));
		gb.setNo(++num);
		int result = Bdao.guestInsert(gb);
		String msg = null;
		String url = orgin_path+"visitor.cj";
		if (result > 0) {
			msg = "��� �Ϸ�";
		} else {
			msg = "��� ����";
		}
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
	
		// �Խù� ����ϱ�
		return new ActionForward(false, "../alert.jsp");
	}
	
	public ActionForward replyWriter(HttpServletRequest request, HttpServletResponse response) {
		String orgin_path = request.getServletContext().getContextPath()+"/";
		
		String msg = "�亯 ��� �Ϸ�";
		String url = orgin_path+"visitor.cj";				
		//String pageNum = request.getParameter("pageNum");
	//	int num = Bdao.GuestBookCount();
		GuestReply gr = new GuestReply();
		int num = Bdao.GuestReplyForAddNum();
		gr.setRep_no(++num);
		gr.setRef(num);
		gr.setRef_no(Integer.parseInt(request.getParameter("re_no")));
		gr.setNickname(request.getParameter("re_writer"));
		gr.setEmail((String)request.getSession().getAttribute("login"));
		gr.setContent(request.getParameter("re_content"));
		//gr.setReflevel(Integer.parseInt(request.getParameter("reflevel")));
		//gr.setRefstep(Integer.parseInt(request.getParameter("refstep")));

		if (!Bdao.reply(gr)) {
			msg = "�亯 ��� ����";
		}

		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return new ActionForward(false, "../alert.jsp");
	}
	
	public ActionForward insertCard(HttpServletRequest request, HttpServletResponse response) {
		String orgin_path = request.getServletContext().getContextPath()+"/";
		String login = (String)request.getSession().getAttribute("login");
		String id = (String)request.getParameter("email");
		
		if(login == null || login.equals("")) {
			request.setAttribute("msg", "�α��� �� �̿��ϼ���");
			request.setAttribute("url", orgin_path+"model/memeber/loginForm.cj");
			return new ActionForward(false, "../alert.jsp");
		}
		else {
			if(!login.equals(id) && !login.equals("dumbyura@naver.com")) {
				request.setAttribute("msg", "���� ������ �̿��� �����մϴ�.");
				request.setAttribute("url", orgin_path+"about.cj");
				return new ActionForward(false, "../alert.jsp");
			}
		}
		
		String msg = "���� ������ JayK�� Mail�� ���۵Ǿ����ϴ�.";
		String url = orgin_path+"contact.cj";				
		//String pageNum = request.getParameter("pageNum");
		//	int num = Bdao.GuestBookCount();
		BusinessCard bc = new BusinessCard();
		int num = Bdao.businessForAddNum();
		bc.setNo(++num);
		bc.setName(request.getParameter("name"));
		bc.setCompany(request.getParameter("company"));
		bc.setTel(request.getParameter("tel"));
		bc.setEmail(request.getParameter("email"));
		bc.setComment(request.getParameter("comment"));
		
		if (!(Bdao.BusinessCardInsert(bc)>0)) {
			msg = "���� ������ ����";
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return new ActionForward(false, "../alert.jsp");
	}
	
	public ActionForward contact(HttpServletRequest request, HttpServletResponse response) {
		String login = (String)request.getSession().getAttribute("login");
		int boardcount = Bdao.BusinessCardCount(login); 
		int cardallcount = Bdao.BusinessCardAllCount(); 
		BusinessCard sendCard = Bdao.businesscardList(login); 
		Member mb = Mdao.selectOne(login);
		request.setAttribute("sendCard", sendCard);
		request.setAttribute("boardcount", boardcount);	
		request.setAttribute("cardallcount", cardallcount);	
		request.setAttribute("mem", mb);

		if(login.equals("dumbyura@naver.com")) {
			List<BusinessCard> list = Bdao.businesscardAllList(); 
			request.setAttribute("list", list);
			
			return new ActionForward(false, "model/admincontact.jsp");
		}else {
			return new ActionForward(false, "model/contact.jsp");
		}
	}
	
	public ActionForward updateCard(HttpServletRequest request, HttpServletResponse response) {
		String login = (String)request.getSession().getAttribute("login");
		String id = (String)request.getParameter("email");
		String orgin_path = request.getServletContext().getContextPath()+"/";
		if(login == null || login.equals("")) {
			request.setAttribute("msg", "�α��� �� �̿��ϼ���");
			request.setAttribute("url", orgin_path+"model/memeber/loginForm.cj");
			return new ActionForward(false, "../alert.jsp");
		}
		else {
			if(!login.equals(id) && !login.equals("dumbyura@naver.com")) {
				request.setAttribute("msg", "���� ������ �̿��� �����մϴ�.");
				request.setAttribute("url", orgin_path+"about.cj");
				return new ActionForward(false, "../alert.jsp");
			}
		}
		String msg = "���� �� ���� ����";
		String url = orgin_path+"contact.cj";

		BusinessCard bc = new BusinessCard();
		bc.setNo(Integer.parseInt(request.getParameter("no")));
		bc.setName(request.getParameter("name"));
		bc.setCompany(request.getParameter("company"));
		bc.setTel(request.getParameter("tel"));
		bc.setEmail(request.getParameter("email"));
		bc.setComment(request.getParameter("comment"));
		
		if(Bdao.BusinessCardUpdate(bc)>0) {
			msg = "���� �� ���� ����";
		}
		else {
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		
		return new ActionForward(false,"../alert.jsp");
	}
}
