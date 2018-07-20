<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>ABOUT JAYK</title>
<link rel="stylesheet" href="${path }/css/w3.css">
<script>
	function board_submit(){
		if(document.f.subject.value == ''){
			alert("������ �Է��ϼ���");
			document.f.subject.focus();
			return;
		}
		if(document.f.content.value == ''){
			alert("���븦 �Է��ϼ���");
			document.f.content.focus();
			return;
		}
		var trans_text = document.getElementById("textarea");
		trans_text.value =  $('#textarea').val().replace(/\n/g,"<br>");
		
		document.f.submit();
	}
</script>
</head>
<body>
<div class="w3-content w3-container w3-padding-64" id="contact">
  <h3 class="w3-center">��Ʈ������ �Խ��ϱ�</h3>
  <div class="w3-row w3-padding-32 w3-section">
	<form action="portWrite.cj" method="post" enctype="multipart/form-data" name="f">
		<input type="hidden" name="writer" value="${sessionScope.login }">
		<table align="center" border="1" cellpadding="0" cellspacing="0">
			<tr><td align="center">����</td><td><input class="w3-input" type="text" name="subject"></td></tr>
			<tr><td align="center">����</td><td><textarea rows="15" cols="80" name="content" id="textarea"></textarea></td></tr>
			<tr><td align="center">÷������</td><td><input class="w3-input" type="file" name="imagefile"></td></tr>
			<tr><td align="center" colspan="2"><a class="w3-button" href="javascript:board_submit()">�Խù����</a></td></tr>
		</table>
	</form>
  </div>
</div>
</body>
</html>