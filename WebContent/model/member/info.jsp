<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="decorator"
	uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>ABOUT JAYK</title>
<link rel="stylesheet" href="${path }/css/w3.css">
<script>
function getPost(mode)
{
	var theForm = document.info_form;
if(mode == "01")
{
	theForm.method = "post"; 
	theForm.action = "deleteMember.cj?email=${mem.email }";
}
else if(mode == "02")
{
 	theForm.method = "post";
 	theForm.action = "updateInfo.cj?email=${mem.email }";
}
else if(mode == "03")
{
 	theForm.method = "post";
 	theForm.action = "memberList.cj";
}
theForm.submit();
}
</script>
</head>
<body>
	<div style="padding:30px;"></div>
	<form name="info_form" action="updateInfo.cj?email=${sessionScope.login }" method="POST">
	<div class="w3-content w3-container w3-padding-32" id="about" style="padding-bottom: 50px; border:1px solid black; border-radius:25px;">
		<h3 class="w3-center">
			<a class="w3-xxxlarge">내 정보</a>
		</h3>
		<div class="w3-content w3-container w3-padding-30">
			<div style="width: 40%; float: left; padding-left: 80px;">
					<a class="w3-large">이메일 : </a><br>
					<input class="w3-input" type="text" name="email" value="${mem.email }" readonly><br>
					<a class="w3-large">비밀번호 : </a><br>
					<input class="w3-input" type="password" name="password" required><br>
					<%-- <a class="w3-xlarge w3-hover-opacity">${mem.email }</a><br> --%>
					<a class="w3-large">이름 : </a><br>
					<input class="w3-input" type="text" name="name" value="${mem.name }"><br>
					<%-- <a class="w3-xlarge w3-hover-opacity">${mem.name }</a><br>  --%>
					<a class="w3-large">연락처 : </a><br>
					<input class="w3-input" type="text" name="tel" value="${mem.tel }"><br>
				 	<%--<a class="w3-xlarge w3-hover-opacity">${mem.tel }</a><br> --%>
					<a class="w3-large">소속 : </a><br>
					<input class="w3-input" type="text" name="company" value="${mem.company }"><br>
					<%-- <a class="w3-xlarge w3-hover-opacity">${mem.company }</a><br> --%>
			</div>
			<div style="padding-top:20%;">
				<p class="w3-center"><em>정보 수정을 원하시면 기입된 내용 수정을 한 후<br> 패스워드 입력 후 수정확인버튼을 눌러주세요.</em></p>
			</div>
		</div>
	</div>
	<div class="w3-content w3-container w3-padding-32">
		<c:if test="${sessionScope.login == 'dumbyura@naver.com'}">
			<a onClick="getPost('03')" class="w3-button w3-light-grey w3-right">회원 목록</a>
			<a onClick="getPost('02')" class="w3-button w3-light-grey w3-right">정보 수정</a>
		</c:if>
		<c:if test="${sessionScope.login != 'dumbyura@naver.com'}">
			<a onClick="getPost('01')" class="w3-button w3-light-grey w3-right">회원 탈퇴</a>
			<a onClick="getPost('02')" class="w3-button w3-light-grey w3-right">정보 수정</a>
		</c:if>
	</div>
				</form>
	<div style="padding:30px;"></div>
	
</body>
</html>