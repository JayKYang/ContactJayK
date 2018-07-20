<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%-- /WebContent/decorator/decorator.jsp --%>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>    
<!DOCTYPE html>
<html>
<title>Contact JayK</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Lato">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<link rel="stylesheet" href="${path }/css/w3.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<head>
	<decorator:head/>
</head>
	
<body>

<!-- First Parallax Image with Logo Text -->
<div class="bgimg-1 w3-display-container w3-opacity-min" id="home">
  <div class="w3-display-middle" style="white-space:nowrap;">
    <span class="w3-center w3-padding-large w3-black w3-xlarge w3-wide w3-animate-opacity">Who's <span class="w3-hide-small">JayK</span> ?</span>
  </div>
</div>

<!-- Navbar (sit on top) -->
<div class="w3-top">
  <div class="w3-bar w3-white" id="myNavbar">
  	<span class="w3-bar-item w3-hide-small">
    <a class="w3-bar-item w3-button w3-hover-black w3-hide-medium w3-hide-large w3-right" href="javascript:void(0);" onclick="toggleFunction()" title="Toggle Navigation Menu">
      <i class="fa fa-bars"></i>
    </a>
    <!-- <a href="#home" class="w3-bar-item w3-button"><i class="material-icons" style="font-size:15px;">home</i>HOME</a> -->
    <a href="${path }/about.cj" class="w3-bar-item w3-button w3-hide-small"><i class="material-icons" style="font-size:15px;">face</i>JayK?</a>
    <a href="${path }/portfolio.cj" class="w3-bar-item w3-button w3-hide-small"><i class="material-icons" style="font-size:15px;">apps</i>포트폴리오</a>
    <a href="${path }/visitor.cj" class="w3-bar-item w3-button w3-hide-small"><i class="material-icons" style="font-size:15px;">comments</i>방명록</a>
    <c:if test="${!empty sessionScope.login}">
	    <a href="${path }/contact.cj" class="w3-bar-item w3-button w3-hide-small"><i class="material-icons" style="font-size:15px;">contact_mail</i>연락하기</a>
    </c:if>
    </span>
    <c:if test="${empty sessionScope.login}">
	    <span class="w3-bar-item w3-hide-small w3-right">
	    	<a class="w3-bar-item w3-hide-small w3-black">Guest 님</a>
	    	<a href="${path }/loginForm.cj" class="w3-bar-item w3-button w3-hide-small"><i class="material-icons" style="font-size:15px;">account_circle</i>로그인</a>
	    	<a href="${path }/joinForm.cj" class="w3-bar-item w3-button w3-hide-small w3-right"><i class="material-icons" style="font-size:15px;">vpn_key</i>회원가입</a>
    	</span>
    	<!-- <a id="loginBtn" href="javascript:pop_login()" class="w3-bar-item w3-button w3-hide-small w3-right"><i class="material-icons" style="font-size:15px;">account_circle</i>Join In</a> -->
    </c:if>
    <c:if test="${!empty sessionScope.login}">
    	<span class="w3-bar-item w3-hide-small w3-right">
	    	<a class="w3-bar-item w3-hide-small w3-black">${sessionScope.name} 님</a>
	    	<a href="${path }/model/member/info.cj?email=${sessionScope.login}" class="w3-bar-item w3-button w3-hide-small"><i class="material-icons" style="font-size:15px;">info</i>내 정보 보기</a>
	    	<a href="${path }/model/member/logout.cj" class="w3-bar-item w3-button w3-hide-small "><i class="material-icons" style="font-size:15px;">account_circle</i>로그아웃</a>
    	</span>
    </c:if>
    
<!--     <a href="#" class="w3-bar-item w3-button w3-hide-small w3-right w3-hover-red">
      <i class="fa fa-search"></i>
    </a> -->
  </div>

  <!-- Navbar on small screens -->
  <div id="navDemo" class="w3-bar-block w3-white w3-hide w3-hide-large w3-hide-medium w3-center">
    <a href="${path }/about.cj" class="w3-bar-item w3-button" onclick="toggleFunction()">JayK?</a>
    <a href="${path }/portfolio.cj" class="w3-bar-item w3-button" onclick="toggleFunction()">포트폴리오</a>
    <a href="${path }/visitor.cj" class="w3-bar-item w3-button" onclick="toggleFunction()">방명록</a>
    <c:if test="${!empty sessionScope.login}">
	    <a href="${path }/contact.cj" class="w3-bar-item w3-button" onclick="toggleFunction()">연락하기</a>
	    <a href="${path }/model/member/info.cj?email=${sessionScope.login}" class="w3-bar-item w3-button" onclick="toggleFunction()">내 정보 보기</a>
    </c:if>
    <!-- <a href="#" class="w3-bar-item w3-button">SEARCH</a> -->
  </div>
</div>

<!-- Container (About Section) -->
<decorator:body/>





<!-- Footer -->
<footer class="w3-center w3-black w3-padding-64 w3-opacity w3-hover-opacity-off">
  <a href="#home" class="w3-button w3-light-grey"><i class="fa fa-arrow-up w3-margin-right"></i>To the top</a>
  <div class="w3-xlarge w3-section">
<!--     <i class="fa fa-facebook-official w3-hover-opacity"></i>
    <i class="fa fa-instagram w3-hover-opacity"></i>
    <i class="fa fa-snapchat w3-hover-opacity"></i>
    <i class="fa fa-pinterest-p w3-hover-opacity"></i>
    <i class="fa fa-twitter w3-hover-opacity"></i>
    <i class="fa fa-linkedin w3-hover-opacity"></i> -->
  </div>
  Copyright by <i class="material-icons" style="font-size:15px;">copyright</i><a href="http://jinkyu.tistory.com" title="W3.CSS" target="_blank" class="w3-hover-text-green">JayK Yang</a>
</footer>
 
<!-- Add Google Maps -->
<script>

function onClick(element) {
  document.getElementById("img01").src = element.src;
  document.getElementById("modal01").style.display = "block";
  var captionText2 = document.getElementById("title");
  captionText2.innerHTML = element.id;
  var captionText = document.getElementById("caption");
  captionText.innerHTML = element.alt;
  var portnum = element.title;
  document.getElementById("deleteportfoilo").href = "${path }/model/board/deletePortfolio.cj?no="+portnum;
  document.getElementById("modifyportfoilo").href = "${path }/model/board/portUpdateForm.cj?no="+portnum;
}

window.onscroll = function() {myFunction()};
function myFunction() {
    var navbar = document.getElementById("myNavbar");
    if (document.body.scrollTop > 100 || document.documentElement.scrollTop > 100) {
        navbar.className = "w3-bar" + " w3-card" + " w3-animate-top" + " w3-white";
    } else {
        navbar.className = navbar.className.replace(" w3-card w3-animate-top w3-white", "");
    }
}

function toggleFunction() {
    var x = document.getElementById("navDemo");
    if (x.className.indexOf("w3-show") == -1) {
        x.className += " w3-show";
    } else {
        x.className = x.className.replace(" w3-show", "");
    }
}
</script>
</body>
</html>
