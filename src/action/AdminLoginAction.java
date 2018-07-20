package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/*
 *	관리자로 로그인 한 경우만 실행이 가능하도록 검증 클래스
 *	관리자 업무인 경우는 이 클래스를 상속받아 사용하도록 함
 */
public abstract class AdminLoginAction implements Action {
	protected String login;
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		login = (String)request.getSession().getAttribute("login");
		if(login==null) {
			request.setAttribute("msg", "관리자로 로그인 후 이용하세요.");
			request.setAttribute("url", "loginForm.jsp");
			return new ActionForward(false,"../alert.jsp");
		}else if(!login.equals("admin")){
			request.setAttribute("msg", "관리자만 이용할 수 있습니다.");
			request.setAttribute("url", "main.me");
			return new ActionForward(false,"../alert.jsp");
		}
		return doExecute(request,response);
	}
	public abstract ActionForward doExecute(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
}
