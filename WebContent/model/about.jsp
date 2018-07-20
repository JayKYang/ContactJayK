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
	<div class="w3-content w3-container w3-padding-64" id="about">
		<h3 class="w3-center">
			<a class="w3-xxxlarge">PROFILE</a><a class="w3-large"> &nbsp;
				신상정보</a>
		</h3>
		<div class="w3-content w3-container w3-padding-30"
			style="padding-bottom: 50px;">
			<div style="width: 40%; float: left; padding-left: 80px;">
				<a class="w3-xxlarge w3-hover-opacity">양진규</a><br> <a
					class="w3-xxlarge w3-hover-opacity">1991</a>년<a
					class="w3-xxlarge w3-hover-opacity">10</a>월 <a
					class="w3-xxlarge w3-hover-opacity">12</a>일<br> <a
					class="w3-xxlarge w3-hover-opacity"><i
					class="fa fa-map-marker fa-fw w3-hover-text-black w3-xlarge w3-margin-right"></i>수원</a>출생
				&nbsp;<a class="w3-large w3-hover-opacity">|&nbsp;</a> <a
					class="w3-xxlarge w3-hover-opacity">화성</a>거주
			</div>

			<div style="width: 60%; float: left; padding-left: 120px;">
				<a class="w3-xxlarge w3-hover-opacity"><i
					class="fa fa-phone fa-fw w3-hover-text-black w3-xlarge w3-margin-right"></i>&nbsp;
					+82 10-4396-2468</a><br> <a class="w3-xxlarge w3-hover-opacity"><i
					class="fa fa-envelope fa-fw w3-hover-text-black w3-xlarge w3-margin-right"></i>
					&nbsp; dumbyura@naver.com</a><br> <a
					href="http://instagram.com/jinq.kr"
					class="w3-xxlarge w3-hover-opacity" style="text-decoration: none;"><i
					class="fa fa-instagram"></i>&nbsp; jinq.kr</a><br>
			</div>
		</div>
		<hr>
		<h3 class="w3-center">
			<a class="w3-xxxlarge">SW SKILL</a><a class="w3-large"> &nbsp;
				보유기술</a>
		</h3>
		<div class="w3-content w3-container w3-padding-30"
			style="padding-bottom: 50px;">
			<div class="w3-content w3-container" style="width: 30%; float: left;">
				<p class="w3-large w3-center w3-padding-16">
					<i class="material-icons" style="font-size: 15px;">language</i>
					LANGUAGE
				</p>
				<p class="w3-wide">C</p>
				<div class="w3-light-grey">
					<div class="w3-container w3-padding-small w3-dark-grey w3-center"
						style="width: 95%">95%</div>
				</div>
				<p class="w3-wide">JAVA</p>
				<div class="w3-light-grey">
					<div class="w3-container w3-padding-small w3-dark-grey w3-center"
						style="width: 85%">85%</div>
				</div>
				<p class="w3-wide">SQL</p>
				<div class="w3-light-grey">
					<div class="w3-container w3-padding-small w3-dark-grey w3-center"
						style="width: 90%">90%</div>
				</div>
			</div>
			<div class="w3-content w3-container" style="width: 30%; float: left;">
				<p class="w3-large w3-center w3-padding-16">
					<i class="material-icons" style="font-size: 15px;">inbox</i>
					DATABASE
				</p>
				<p class="w3-wide">Oracle</p>
				<div class="w3-light-grey">
					<div class="w3-container w3-padding-small w3-dark-grey w3-center"
						style="width: 90%">90%</div>
				</div>
				<p class="w3-wide">Mysql</p>
				<div class="w3-light-grey">
					<div class="w3-container w3-padding-small w3-dark-grey w3-center"
						style="width: 85%">85%</div>
				</div>
			</div>
			<div class="w3-content w3-container" style="width: 30%; float: left;">
				<p class="w3-large w3-center w3-padding-16">
					<i class="material-icons" style="font-size: 15px;">inbox</i>
					OS
				</p>
				<p class="w3-wide">LINUX</p>
				<div class="w3-light-grey">
					<div class="w3-container w3-padding-small w3-dark-grey w3-center"
						style="width: 80%">80%</div>
				</div>
				<p class="w3-wide">WINDOW</p>
				<div class="w3-light-grey">
					<div class="w3-container w3-padding-small w3-dark-grey w3-center"
						style="width: 85%">85%</div>
				</div>
			</div>
		</div>
		<hr>
		<h3 class="w3-center">
			<a class="w3-xxxlarge">INTERESTED</a><a class="w3-large"> &nbsp;
				WORD CLOUD</a>
		</h3>
		<div class="w3-content w3-container w3-padding-30"
			style="padding-bottom: 50px;">
			<div class="w3-content w3-container" style="width: 100%; float: left;">
				<div class="w3-row">
					<img src="${path }/images/interested.png"
						class="w3-round w3-image"
						alt="Photo of Me" width="100%">

				</div>
			</div>
		</div>
		<div class="w3-content w3-container w3-padding-64" id="about">
		<hr>
			<h3 class="w3-center">
				<a class="w3-xxxlarge">JayK</a><a class="w3-large"> &nbsp; Q & A</a>
			</h3>
			<p class="w3-center">
				<em>JayK에게 물어보세요</em>
			</p>
			<div class="w3-row">
				<div class="w3-col m6 w3-center w3-padding-large">
					<img src="${path }/images/avatar2.png"
						class="w3-round w3-image w3-opacity w3-hover-opacity-off"
						alt="Photo of Me" width="160">
				</div>

				<!-- Hide this text on small devices -->
			</div>
		</div>
	</div>
</body>
</html>