<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>JayK's 포트폴리오</title>
<script>
$(document).ready(function() {
	$("#load").click(function() {
		$.ajax({
			url : "model/board/portload.cj",
			data : "count="+$("#count").val(), 
			type : "POST",
			success : function(data) {
				//$("body").append(data);
				$("#loadspace").append(data);
				$("#count").val(parseInt($("#count").val())+4);
				if(parseInt($("#count").val()) >= parseInt(${boardcount})){
					$("#load").hide();
				}
			},
			error : function(xhr,status,error){
				alert("portfolio.jsp error");
			}
		})
	});
});
</script>
<style>
.image {
	position: relative;
	/* float: left; /* optional */
}

.text {
	position: absolute;
	top: 100px;
	/* in conjunction with left property, decides the text position */
	right:100px;
}
</style>
</head>
<body>

  <h3 class="w3-center">포트폴리오(${boardcount})</h3>
<!-- Container (Portfolio Section) -->
<div class="w3-content w3-container" id="portfolio">
  <p class="w3-center"><em>게시글 클릭시 자세히 확인할 수 있습니다.</em></p>
  <c:if test="${sessionScope.login=='dumbyura@naver.com' }">
	  <p class="w3-center"><a href="${path }/model/board/portWriteForm.cj" class="w3-button w3-light-grey">게시하기</a></p>
  </c:if>
  <!-- Responsive Grid. Four columns on tablets, laptops and desktops. Will stack on mobile devices/small screens (100% width) -->
  <div class="w3-center">
  	<div id="loadspace">
	  <div class="w3-row-padding w3-center w3-section">
		<c:forEach var="portfolio" items="${list}">
		    <div class="image w3-col m3" style="width:233px; height:233px;">
		    	<c:if test="${!empty portfolio.imagefile }">
                		<img src="model/board/imagefile/${portfolio.imagefile}" id="${portfolio.subject }" alt="${portfolio.content }" title="${portfolio.no }" style="position:absoulte; height:100%; width:100%" onclick="onClick(this)" class="w3-hover-grayscale image1"/>
                		<div class="text subject">
                		</div>
		    	</c:if>
		    	<c:if test="${empty portfolio.imagefile }">
			    	<img src="${path }/images/default.jpg" id="${portfolio.subject }" alt="${portfolio.content }" title="${portfolio.no }" style="position:absoulte; height:100%; width:100%" onclick="onClick(this)" class="w3-hover-opacity image1">
		    		<div class="text subject">
                	</div>
		    	</c:if>
	    	</div>
		</c:forEach>
	  </div>
	</div>
    <input type="hidden" id="count" value="4">
    <c:if test="${boardcount > 4 }">
		<button class="w3-button w3-padding-large w3-light-grey" id="load" style="margin:20px">LOAD MORE</button>
	</c:if>
  </div>
</div>
<!-- Modal for full size images on click-->	
<div id="modal01" class="w3-modal w3-black w3-animate-zoom" onclick="this.style.display='none'" style="">
<div style="width:100%; height:100%;" align="center">
	<span class="w3-large w3-black w3-bar-item w3-hide-small w3-display-topright">
		<c:if test="${sessionScope.login=='dumbyura@naver.com' }">
		<a id="deleteportfoilo" class="w3-bar-item w3-button w3-hide-small w3-right"><i class="fa fa-trash"></i></a>
	    <a id="modifyportfoilo" class="w3-bar-item w3-button w3-hide-small"><i class="fa fa-file"></i></a>
 		</c:if>
 	</span>
	<!-- <span class="w3-button w3-large w3-black w3-display-topright" title="Close Modal Image"><i class="fa fa-remove"></i></span>
	 --><table class="w3-table w3-center" align="center">
		<tr><td id="title" class="w3-xxlarge w3-center"></td></tr>
		<tr>
		<td class="w3-center"><hr>
				<img id="img01" class="w3-image" style="width:40%; left:20%;">

		</td></tr>
		<tr><td><p id="caption" class="w3-large w3-left" style="margin-left:10%;"></p></td></tr>
	</table>
</div>
</div>

</body>
</html>