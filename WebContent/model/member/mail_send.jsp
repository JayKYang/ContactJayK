<%@ page contentType="text/html; charset=euc-kr" %>
<% request.setCharacterEncoding("euc-kr"); %>
 
<jsp:useBean id="mb"  class="mail.MailBean" />
<jsp:setProperty name="mb" property="*" />

<%
	if(mb.sendMail()){
		out.println("<center><H2>메일이 발송 되었습니다.</H2>");
		out.println("<HR><a href=mail_form.html>메일작성하기</a></center>");
	}
	else{
		out.println("<script>alert('메일전송중 문제가 생겼습니다.');history.go(-1);</script>");		
	}
%>