<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/> 
<div class="w3-row-padding w3-center w3-section">
	<c:forEach var="portfolio" items="${list}">
		<div class="image w3-col m3" style="width: 233px; height: 233px;">
			<c:if test="${!empty portfolio.imagefile }">
                		<img src="model/board/imagefile/${portfolio.imagefile}" id="${portfolio.subject }" alt="${portfolio.content }" style="position:absoulte; height:100%; width:100%" onclick="onClick(this)" class="w3-hover-grayscale image1"/>
                		<div class="text subject">
                		</div>
		    	</c:if>
		    	<c:if test="${empty portfolio.imagefile }">
			    	<img src="${path }/images/default.jpg" id="${portfolio.subject }" alt="${portfolio.content }" style="position:absoulte; height:100%; width:100%" onclick="onClick(this)" class="w3-hover-opacity image1">
		    		<div class="text subject">
                	</div>
		    	</c:if>
		</div>
	</c:forEach>
</div>
