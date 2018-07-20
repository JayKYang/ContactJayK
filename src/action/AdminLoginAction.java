package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/*
 *	�����ڷ� �α��� �� ��츸 ������ �����ϵ��� ���� Ŭ����
 *	������ ������ ���� �� Ŭ������ ��ӹ޾� ����ϵ��� ��
 */
public abstract class AdminLoginAction implements Action {
	protected String login;
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		login = (String)request.getSession().getAttribute("login");
		if(login==null) {
			request.setAttribute("msg", "�����ڷ� �α��� �� �̿��ϼ���.");
			request.setAttribute("url", "loginForm.jsp");
			return new ActionForward(false,"../alert.jsp");
		}else if(!login.equals("admin")){
			request.setAttribute("msg", "�����ڸ� �̿��� �� �ֽ��ϴ�.");
			request.setAttribute("url", "main.me");
			return new ActionForward(false,"../alert.jsp");
		}
		return doExecute(request,response);
	}
	public abstract ActionForward doExecute(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
}
