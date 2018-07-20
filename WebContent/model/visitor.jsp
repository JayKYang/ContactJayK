<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<% request.setCharacterEncoding("euc-kr"); %>
<c:set var="path" value="${pageContext.request.contextPath}"/>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>JAYK 방명록</title>
<link rel="stylesheet" href="${path }/css/w3.css">
<script>
	function reg_reply(){
		if($('#nickname').val() == ''){
			alert("작성자를 입력하세요");
			$('#nickname').focus();
			return;
		}
		if($('#content').val() == ''){
			alert("내용을 입력하세요");
			$('#content').focus();
			return;
		}
		$("#reg_reply_f").submit();
	}
	
	function rep_submit(object){
		if($('#re_writer'+object).val() == ''){
			alert("작성자를 입력하세요");
			$('#re_writer'+object).focus();
			return;
		}
		if($('#re_content'+object).val() == ''){
			alert("내용을 입력하세요");
			$('#re_content'+object).focus();
			return;
		}
		var re_content = document.getElementById("re_content");
		var re_writer = document.getElementById("re_writer");
		var re_no = document.getElementById("re_no");
		re_content.value = $('#re_content'+object).val();
		re_writer.value = $('#re_writer'+object).val();
		re_no.value = $('#re_no'+object).val();
		//alert(re_content.value);
		$("#rep_f").submit();
	}

function readmore(object){
	
	//check = !check;
	var re_no = document.getElementById("re_no");
	re_no.value = $('#re_no'+object).val();
	//alert(re_content.value);
		$.ajax({
			url : "model/board/replyView.cj",
			data : "num="+re_no.value, 
			type : "POST",
			success : function(data) {
				//$("body").append(data);
				$("#loadreply"+object).append(data);
				$("#readmore"+object).hide();
			},
			error : function(xhr,status,error){
				alert("visitor.jsp readmore() error");
			}
		})
}
</script>
</head>
<body>
<div class="w3-content w3-container w3-padding-64" id="contact">
  <h3 class="w3-center">방명록 남기기<hr></h3>
	  <div class="w3-row w3-padding-32 w3-section">
		<form action="model/board/visitorWrite.cj" method="post" id="reg_reply_f">
				<c:if test="${!empty sessionScope.login }">
					<input type="hidden" name="writer" value="${sessionScope.login }">
					<table class="w3-table" align="center" style="width: 50%;">
						<tr>
							<td style="width: 80px;">작성자</td>
							<td><input class="w3-input" type="text" name="nickname"
								id="nickname" value=${sessionScope.name } readonly></td>
						</tr>
						<tr>
							<td colspan="2"><textarea rows="5" cols="80" name="content"
									id="content" style="width: 100%;"></textarea></td>
						</tr>
						<tr>
							<td colspan="2">
								<!-- 		<a class="w3-button w3-right" href="javascript:board_submit()">게시</a> -->
								<button type="button" class="w3-button w3-light-grey w3-right"
									id="visitorWrite" onclick="reg_reply()">게시</button>
							</td>
						</tr>
					</table>
				</c:if>

				<c:if test="${empty sessionScope.login }">
					<table class="w3-table" align="center" style="width: 50%;">
						<tr>
							<td style="width: 80px;">작성자</td>
							<td><input class="w3-input" type="text" name="nickname"
								id="nickname" value="Guest" readonly></td>
						</tr>
						<tr>
							<td colspan="2"><textarea rows="5" cols="80" name="content"
									id="content" style="width: 100%;" disabled>로그인 후 이용 가능합니다</textarea></td>
						</tr>
						<tr>
							<td colspan="2">
								<!-- 		<a class="w3-button w3-right" href="javascript:board_submit()">게시</a> -->
								<button type="button" class="w3-button w3-light-grey w3-right"
									id="visitorWrite" onclick="reg_reply()">게시</button>
							</td>
						</tr>
					</table>
				</c:if>
			</form>
	  </div>
  <div class="w3-row w3-padding-32 w3-section">
  	<div id="loadspace" class="w3-center">
		<c:forEach var="guestbook" items="${list}">
		  	<div class="w3-row-padding w3-center w3-section">
		  		<input type="hidden" name="re_no${guestbook.no }" id="re_no${guestbook.no }" value="${guestbook.no }">
					<table class="w3-table" align="center" style="width:50%">
						<%-- <tr>
							<td><a href="${path }/model/board/deleteVisitor.cj?no=${guestbook.no }&email=${guestbook.writer }" class="w3-bar-item w3-button w3-hide-small w3-right w3-small"><i class="fa fa-trash"></i></a></td>
						</tr> --%>
						<tr><td><a href="${path }/model/board/deleteVisitor.cj?no=${guestbook.no }&email=${guestbook.writer }" class="w3-bar-item w3-button w3-hide-small w3-right w3-small"><i class="fa fa-trash"></i></a><b class="w3-xlarge">${guestbook.nickname }</b><p class="w3-right"><fmt:formatDate value="${guestbook.regdate }" pattern="yyyy-MM-dd HH:mm:ss" />&nbsp;</p><hr></td></tr>
						<tr><td class="w3-center">${guestbook.content }<hr></td></tr>
						<tr><td><input type="text" id="re_writer${guestbook.no }" class="w3-input w3-boredered" style="width:15%; float:left;" placeholder="작성자" value="${sessionScope.name }">
						<input type="text" name="re_content${guestbook.no }" id="re_content${guestbook.no }" class="w3-input w3-boredered" style="width:70%; float:left;" placeholder="내용을 입력해주세요." value="">
						<button class="w3-button w3-light-grey" style="width:15%;" onclick="javascript:rep_submit(${guestbook.no })">등록</button></td></tr>
						<tr><td>댓글
						<c:if test="${replycount != 0 }">
							<a class="w3-right"><i class="material-icons w3-hover w3-button" id="readmore${guestbook.no }" onclick="javascript:readmore(${guestbook.no })">keyboard_arrow_down</i></a><hr>
						</c:if>
						</td></tr>
					</table>
					<table class="w3-table"  align="center" style="width:50%;" id="loadreply${guestbook.no }">
					
					</table>
			</div>
			<hr>
		</c:forEach>
		<form action="model/board/replyWriter.cj" method="post" id="rep_f">
			<input type="hidden" name="re_writer" id="re_writer">
			<input type="hidden" name="re_content" id="re_content">
			<input type="hidden" name="re_no" id="re_no">
		</form>
  	</div>
  </div>
  <input type="hidden" id="count" value="5">
</div>
</body>
</html>