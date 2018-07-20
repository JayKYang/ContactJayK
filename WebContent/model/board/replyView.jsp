<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="path" value="${pageContext.request.contextPath}"/> 
	<c:if test="${replycount!=0 }">
		<c:forEach var="guestreply" items="${replylist}">
			<tr><td style="text-align:center;"> 
				<c:if test="${b.reflevel}>0">
				 	<c:forEach begin="1" end="${b.reflevel}">
						&nbsp;&nbsp;&nbsp;&nbsp;
				   	</c:forEach>
				      └
				</c:if>
				<div>
				<a class="w3-right" href="${path }/model/board/deleteReply.cj?no=${guestreply.rep_no }&email=${guestreply.email }" class="w3-bar-item w3-button w3-hide-small w3-right w3-small">&nbsp;&nbsp;<i class="fa fa-trash"></i></a>
					<a class="w3-left"><b>${guestreply.nickname }</b></a>  ${guestreply.content }  <a class="w3-right"><fmt:formatDate value="${guestreply.regdate }" pattern="yyyy-MM-dd HH:mm:ss" /></a>
					
					
				</div>
			</td></tr>
		</c:forEach>
	</c:if>
	<c:if test="${replycount==0 }">
		<tr><td style="text-align:center;"> 
			현재 등록된 댓글이 없습니다.
		</td></tr>
	</c:if>
