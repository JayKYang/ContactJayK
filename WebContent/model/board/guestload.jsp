<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/> 
<div class="w3-row-padding w3-center w3-section">
	<c:forEach var="guestbook" items="${list}">
		<div class="w3-row-padding w3-center w3-section">
				<table class="w3-table" border="1">
					<tr><td>${guestbook.nickname }</td></tr>
					<tr><td>${guestbook.content }</td></tr>
					<tr><td>´ñ±Û</td></tr>
				</table>
			</div>
	</c:forEach>
</div>
