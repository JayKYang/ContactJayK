<%@ page contentType="text/html; charset=euc-kr" %>
<% request.setCharacterEncoding("euc-kr"); %>
 
<jsp:useBean id="mb"  class="mail.MailBean" />
<jsp:setProperty name="mb" property="*" />

<%
	if(mb.sendMail()){
		out.println("<center><H2>������ �߼� �Ǿ����ϴ�.</H2>");
		out.println("<HR><a href=mail_form.html>�����ۼ��ϱ�</a></center>");
	}
	else{
		out.println("<script>alert('���������� ������ ������ϴ�.');history.go(-1);</script>");		
	}
%>