package controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.ActionForward;
import action.ContactJayKAction;

/**
 * Servlet implementation class ControllerMethodServlet
 */
@WebServlet(
		urlPatterns = { "*.cj" }, 
		initParams = { 
				@WebInitParam(
						name = "properties", 
						value = "method.properties"
						) 
				}
		)

public class ControllerMethodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Properties pr = new Properties(); // <String,String> Map ��ü
	@Override
	public void init(ServletConfig config) throws ServletException{
//		props : method.properties
		String props = config.getInitParameter("properties");
		FileInputStream f = null;
		try {
			// f : method.properties ������ �б� �غ�
			f= new FileInputStream(config.getServletContext().getRealPath("/")+"WEB-INF/"+props);
			pr.load(f);
		}catch(IOException e) {
			throw new ServletException(e);
		}finally {
			try {
				if(f!=null) f.close();
				
			}catch(IOException e) {}
		}
	}
	public ControllerMethodServlet() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("euc-kr");
		ActionForward forward = null;
		String command = null;
		try {
			command = request.getRequestURI();
//			System.out.println("command : " + command);  command : /jspStudy2/*.bo
			if(command.indexOf(request.getContextPath())==0){
				command = command.substring(request.getContextPath().length()); // /*.bo
//				System.out.println(request.getContextPath().length()); 10
//				System.out.println(request.getContextPath());   /jspStudy2
//				System.out.println("command : " + command); command : /*.bo
			}
			// command = /hello.bo ��û URI�� ����. pr ��ü�� Ű�� ����
			Class[] paramType = new Class[] {HttpServletRequest.class, HttpServletResponse.class};
			Object[] paramObjs = new Object[] {request,response};
			//methodName : hello
			String methodName = pr.getProperty(command);
			// BoardAllAction.class : BoardAllAction Ŭ���� ���� ����
			// getMethod('hello',paramType) : BoardAllAction Ŭ������ ��� �޼��� �߿�
			// 									�޼����̸��� hello �̰�, �Ķ���Ͱ� HttpServletRequest, HttpServletResponse �� �޼��带 ����
			Method method = ContactJayKAction.class.getMethod(methodName, paramType); // ?
			ContactJayKAction action = new ContactJayKAction();
			// invoke : �޼��带 ȣ�����ִ� �Լ�
			forward = (ActionForward)method.invoke(action, paramObjs);
		}catch(Exception e) {
			throw new ServletException(e);
		}
		if(forward != null) {
			if(forward.isRedirect()) {
				response.sendRedirect(forward.getView());
			}else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getView());
				dispatcher.forward(request,response);
			}
		}else {
			response.sendRedirect("nopage.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
