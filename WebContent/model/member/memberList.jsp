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
</head>
<body>
	<div style="padding:30px;"></div>
	<div class="w3-content w3-container w3-padding-32" id="about" style="padding-bottom: 50px; border:1px solid black; border-radius:25px;">
		<h3 class="w3-center">
			<a class="w3-xxxlarge">회원 목록</a>
		</h3>
		<table class="w3-table w3-bordered" align="center" >
		<tr><th>아이디</th><th>이름</th><th>연락처</th><th>소속</th><th>강제탈퇴</th></tr>
		<c:forEach var="mem" items="${list}">
		<tr>
			<td><a class="w3-button" href="info.cj?email=${mem.email }">${mem.email }</a></td>
			<td>${mem.name}</td>
			<td>${mem.tel }</td>
			<td>${mem.company }</td>
			<td>
				<a class="w3-button" href="deleteMember.cj?email=${mem.email }"><i class="material-icons">delete</i></a>
			</td>
		</tr>
		</c:forEach>
	</table>
	</div>
	<div style="padding:30px;"></div>
	
</body>
</html>