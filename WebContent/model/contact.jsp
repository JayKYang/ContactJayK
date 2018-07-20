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
<div class="w3-content w3-container w3-padding-64" id="contact">
   <h3 class="w3-center" style="padding-top:3%;">명함 보내기</h3>
  <p class="w3-center"><em>JayK에게 명함 한장 남겨주세요.</em></p>


  <div class="w3-row w3-padding-32 w3-section">
    <div class="w3-col m8 w3-panel w3-left" style="width:55%;">
   	<c:if test="${boardcount == 0 }">
      <form action="model/board/insertCard.cj" method="post">
   	</c:if>
   	<c:if test="${boardcount != 0 }">
      <form action="model/board/updateCard.cj" method="post">
      <input type="hidden" name="no" value="${sendCard.no }"/>
   	</c:if>
   	
      <table class="w3-table" style="margin-top:10%;">
      		<tr>
      			<th class="w3-large" style="text-align:center">전송할 명함</th><th>&nbsp;</th>
      		</tr>
            <tr>
            	<td>이름 : <input class="w3-input" type="text" required name="name" value="${mem.name}" readonly></td>
            		<td class="w3-center" rowspan="5" style="padding-top:15%; padding-right:5%;width:30%;"><button class="w3-button w3-white w3-right w3-section" type="submit"><i class="fa fa-paper-plane"></i></button></td>
            </tr>
            <tr>
            	<td>소속 : <input class="w3-input" type="text" required name="company" value="${mem.company}" readonly></td>
            	</tr>
            <tr>
            	<td>연락처 : <input class="w3-input" type="text" required name="tel" value="${mem.tel}" readonly></td>
            </tr>
            <tr>
            	<td>이메일 : <input class="w3-input" type="email" required name="email" value="${mem.email}" readonly></td>
            </tr>
			<tr>
				<td>메세지 : <input class="w3-input" type="text" name="comment"></td>
			</tr>
			<tr>
				<td><p class="w3-center"><em>회원정보 수정/확인 후 전송하세요 </em></p></td>
			</tr>
      </table>
      </form>
    </div>
   	<div class="w3-row-padding w3-animate-left" style="margin:10% -16px 8px 30px; float:left; padding:20px; border:1px solid black;">
		<table class="w3-table">
      		<c:if test="${boardcount  != 0}">
		 <tr>
				<td><a href="${path }/model/board/deleteContact.cj?no=${sendCard.no }&email=${sendCard.email}" class="w3-bar-item w3-button w3-hide-small w3-right w3-small"><i class="fa fa-trash"></i></a></td>
			</tr>
			</c:if>
      		<tr>
      			<th class="w3-large" style="text-align:center">내가 보낸 명함</th>
      		</tr>
      		<c:if test="${boardcount  != 0}">
            <tr style="padding-top:50px;">
            	<td><a class="w3-large">이름&nbsp; :&nbsp; </a><a>${sendCard.name}</a></td>
            </tr>
            <tr>
            	<td><a class="w3-large">소속 &nbsp;: &nbsp;</a><a>${sendCard.company}</a></td>
            <tr>       
            	<td><a class="w3-large">연락처&nbsp; :&nbsp; </a><a>${sendCard.tel}</a></td>
            </tr>     
            <tr>      
            	<td><a class="w3-large">이메일&nbsp; :&nbsp; </a><a>${sendCard.email}</a></td>
            </tr>      
			<tr>       
				<td><a class="w3-large">메세지 &nbsp;:&nbsp; </a><a>${sendCard.comment}</a></td>
			</tr>      
			<tr>       
				<td><a class="w3-large">보낸 시간 &nbsp;:&nbsp; </a><a><fmt:formatDate value="${sendCard.senddate}" pattern="yyyy-MM-dd HH:mm:ss" /></a></td>
			</tr>
			<tr>
				<td><p class="w3-center"><em>전송한 명함을 수정하시려면 재 전송 해주세요.</em></p></td>
			</tr>
			</c:if>
			<c:if test="${boardcount == 0 }">
				<tr>
					<td><p class="w3-center"><em>현재 보낸 명함이 없습니다.</em></p></td>
				</tr>
			</c:if>
      </table>
    </div>
  </div>
</div>
</body>
</html>

