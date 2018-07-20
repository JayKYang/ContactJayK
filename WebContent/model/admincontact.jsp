<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>ABOUT JAYK</title>
<link rel="stylesheet" href="${path }/css/w3.css">
</head>
<body>
<!-- Third Parallax Image with Portfolio Text -->
<!-- <div class="bgimg-3 w3-display-container w3-opacity-min">
  <div class="w3-display-middle">
     <span class="w3-xxlarge w3-text-white w3-wide">CONTACT</span>
  </div>
</div> -->

<!-- Container (Contact Section) -->
  <h3 class="w3-center" style="padding-top:3%;">받은 명함</h3>
<div class="w3-container w3-padding-64" id="contact">
  <div class="w3-row-padding w3-animate-left" style="margin:0 -16px 8px 30px; padding:20px;">
      <c:if test="${cardallcount  != 0}">
      <c:forEach var="card" items="${list }">
      <div style="border:1px solid black; border-radius:25px; float:left; margin:2%;">
			<table class="w3-table" style="width:100%;">
				<tr>
					<td><a href="${path }/model/board/deleteContact.cj?no=${card.no }&email=${card.email}" class="w3-bar-item w3-button w3-hide-small w3-right w3-small"><i class="fa fa-trash"></i></a></td>
				</tr>
	            <tr style="padding-top:50px;">
	            	<td><a class="w3-large">이름&nbsp; :&nbsp; </a><a>${card.name}</a></td>
	            </tr>
	            <tr>
	            	<td><a class="w3-large">소속 &nbsp;: &nbsp;</a><a>${card.company}</a></td>
	            <tr>       
	            	<td><a class="w3-large">연락처&nbsp; :&nbsp; </a><a>${card.tel}</a></td>
	            </tr>     
	            <tr>      
	            	<td><a class="w3-large">이메일&nbsp; :&nbsp; </a><a>${card.email}</a></td>
	            </tr>      
				<tr>       
					<td><a class="w3-large">메세지 &nbsp;:&nbsp; </a><a>${card.comment}</a></td>
				</tr>      
				<tr>       
					<td><a class="w3-large">보낸 시간 &nbsp;:&nbsp; </a><a><fmt:formatDate value="${card.senddate}" pattern="yyyy-MM-dd HH:mm:ss" /></a></td>
				</tr>
	      	</table>
	      	<br>
      </div>
      </c:forEach>
      </c:if>
      <div>
		<c:if test="${cardallcount == 0 }">
			<table class="">
				<tr>
					<td><p class="w3-center"><em>현재 받은 명함이 없습니다.</em></p></td>
				</tr>
			</table>
		</c:if>
      </div>
    </div>
</div>
</body>
</html>