// File : WebContent/httpRequest.js
// XMLHttpRequest : 서버와 통신을 하는 자바스크립트 객체
var httpRequest = null;

function getXmlHttpRequest(){
	if(window.ActivXObject) { // 익스플로러
		try{
			return new ActiveXObject("Msxml2.XMLHTTP"); // 6.0 이후 버전
		}catch(e){
			try{
				return new ActiveXObject("Microsoft.XMLHTTP"); // 6.0 이전 버전
			}catch(e2){
				return null;
			}
		}
	}else if(window.XMLHttpRequest){ // 그외 브라우저.
		return new XMLHttpRequest();
	}else{
		return null;
	}
}
/*
 *  url : hello.jsp
 *  params : name=홍길동
 *  callback : helloFromServer
 *  method : POST
 */

function sendRequest(url,params,callback, method){
	//httpRequest : XMLHttpRequest 객체 저장
	httpRequest = getXmlHttpRequest();
	var httpMethod = method?method:'GET';
	if(httpMethod != 'GET' && httpMethod != 'POST'){
		httpMethod='GET';
	}
	//httpMethod = GET | POST 만 저장
	var httpParams = (params == null || params=='')?null:params;
	var httpUrl = url;
	if(httpMethod=='GET' && httpParams != null){
		httpUrl = httpUrl + "?" + httpParams;
	}
	
	//true : 비동기 방식으로 서버에 연결함.
	httpRequest.open(httpMethod, httpUrl, true);
	httpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	// onreadystatechange : 콜백함수 등록
	// 콜백함수등록 : 서버에 요청 이후 서버에서 응답이 오는 경우 자동으로 호출되는 함수 설정
	httpRequest.onreadystatechange=callback;
	// 서버에 요청함. : post 방식인 경우 파라미터를 전송함
	httpRequest.send(httpMethod=='POST'?httpParams:null);
}