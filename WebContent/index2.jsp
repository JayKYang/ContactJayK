<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<html>
<head>
<title>Who's JayK?</title>
<c:if test="${!empty sessionScope.login}">
	<script>
		location.href="${path}/about.cj";
	</script>
</c:if>
<script src="httpRequest.js"></script>
<script>
function pop_login(){
	sendRequest("${path}/model/member/loginForm.jsp","",printLogin,"POST");
}
function printLogin(){
	if(httpRequest.readyState == 4){
		if(httpRequest.status == 200){
			loginContent.innerHTML = httpRequest.responseText;
		}else{
			alert("서버 오류 :" + httpRequest.status);
		}
	}	
}
</script>
<style>

/* The Modal (background) */
.modal {
	display: none; /* Hidden by default */
	position: fixed; /* Stay in place */
	z-index: 1; /* Sit on top */
	padding-top: 100px; /* Location of the box */
	left: 0;
	top: 0;
	width: 100%; /* Full width */
	height: 100%; /* Full height */
	overflow: auto; /* Enable scroll if needed */
	background-color: rgb(0, 0, 0); /* Fallback color */
	background-color: rgba(0, 0, 0, 0.4); /* Black w/ opacity */
}

/* Modal Content */
.modal-content {
	background-color: #fefefe;
	margin: auto;
	/*padding: 20px;*/
	border: 1px solid gray;
	max-width: 550px;
	min-height: 600px;
}
</style>
<style>
body {
	background-image: url("./images/index2.png");
	background-size:cover;
	background-repeat:no-repeat;
	padding-top: 15%;
	color: #6a6f8c;
	font: 600 16px/18px 'Open Sans', sans-serif;
}

div {
	width: 100%;
	text-align: center;
}
</style>
</head>
<body>
	<div style="margin-top: 165px;">
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a id="loginBtn" style="color: blue; font-size: 30px; font-style: italic;" href="javascript:pop_login()">CLICK_ME();</a>
		<!-- <a	style="color: green">// 진</a><br> -->
	</div>
	<!-- LoginForm(Modal)  -->
	<!-- Modal content -->
	<div id="loginModal" class="modal">
	  	<div class="modal-content">
	    	<div id="loginContent"></div>
	  	</div>
	</div>
<script>
//Get the modal
var modal = document.getElementById('loginModal');

// Get the button that opens the modal
var btn = document.getElementById("loginBtn");


// When the user clicks the button, open the modal 
btn.onclick = function() {
    modal.style.display = "block";
}


// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}
</script>

</body>
</html>