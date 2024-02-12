<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>도서 고객 및 대여관리 프로그램 ver 1.0</title>
	<link rel="stylesheet" href="<c:url value='resources/css/common.css'/>">
	<link rel="stylesheet" href="<c:url value='resources/css/menu.css'/>">
	<link rel="stylesheet" href="<c:url value='resources/css/registerForm.css'/>">
	<script src="https://code.jquery.com/jquery-1.11.3.js"></script>
</head>
<header>
	<h2>도서 고객 및 대여관리 프로그램 ver 1.0</h2>
</header>
<body>
	<div id="menu">
		<ul>
			<li><a href="<c:url value='/register'/>">고객등록</a></li>
			<li><a href="<c:url value='/list'/>">고객목록조회/수정</a></li>
			<li><a href="<c:url value='/rentList'/>">고객대여리스트</a></li>
			<li><a href="<c:url value='/billing'/>">고객대여금액</a></li>
			<li><a href="<c:url value='/'/>">Home</a></li>
		</ul>
	</div>
	<div id="content">
	   <div class="title">${not empty param.id ? "고객수정화면" : "고객등록화면"}</div>
	   <form id="form" action="" method="post">
	   	<div class="row">
		    <label for="">고객번호</label>
		    <input class="input-field-pk" 
		    	   type="text" 
		    	   name="id"
		    	   readonly="readonly"
		    	   value="${not empty param.id ? param.id : userCount+1}">
   	   	</div>
   	   	<div id="id" class="msg"></div>
   	   	
   	   	<div class="row">
		    <label for="">이름</label>
		    <input class="input-field" 
		    	   type="text"
		    	   name="name"
		    	   placeholder="홍길동"
		    	   value="${not empty param.id ? user.name : ''}">
	    </div>
		<div id="name" class="msg"></div>
	    
	    <div class="row">
		    <label for="">전화번호</label>
		    <input class="input-field" 
		    	   type="text"
		    	   name="phone"
		    	   placeholder="010-1234-5678"
		    	   value="${not empty param.id ? user.phone : ''}">
		</div>
		<div id="phone" class="msg"></div>
	    
	    <div class="row">
		    <label for="">이메일</label>
		    <input class="input-field"
		    	   type="text"
		    	   name="email"
		    	   placeholder="abcd@library.com"
		    	   value="${not empty param.id ? user.email : ''}">
	    </div>
	    <div id="email" class="msg"></div>
	    
	    <div class="row">
		    <label for="">등급</label>
		    <input class="input-field" 
		           type="text"
		           name="grade"
		           placeholder="P,G,S"
		           value="${not empty param.id ? user.grade : ''}">
	    </div>
	    <div id="grade" class="msg"></div>
	    
	    <button type="button" id="registerBtn">${not empty param.id ? "업데이트" : "등록"}</button>
	    <button type="button" id="cancelBtn">취소</button>
	   </form> 
	  </div>
<footer>
	<h2>나도 할 수 있는 Java & Spring 웹 개발 종합반(8wlgns)</h2>
</footer>
   <script>
   	   $("#registerBtn").on("click", function(){
   		  let form = $("#form");
   		  
	      if($("#registerBtn").text() == "업데이트"){
	    	  form.attr("action", "<c:url value='/update'/>");
	      } else {
	    	  form.attr("action", "<c:url value='/register'/>");
	      }
	      
	      if(formCheck())
	        form.submit();
	   });
   	   
   	   $("#cancelBtn").on("click", function(){
   		  if($("#registerBtn").text() == "업데이트"){
   			  location.href="<c:url value='/'/>";
	      } else {
	    	  $(".input-field").val('');
	      }
 	   });
   
	   function setMessage(elementId, msg){
		    if(msg != ''){
		    	document.getElementById(elementId).innerHTML = `<i class="fa fa-exclamation-circle">${'${msg}'}</i>`;	
		    } else {
		    	document.getElementById(elementId).innerHTML = null;
		    }
	   }
	   
       function clearAllWarningMessage(){
           setMessage("id", '');
           setMessage("name", '');
           setMessage("phone", '');
           setMessage("email", '');
           setMessage("grade", '');
       }
	   
       function formCheck() {
    	   clearAllWarningMessage();
           
           let frm = document.getElementById("form");
           let msg ='';
           let phoneReg = /^([0-9]{3,})-([0-9]{4,})-([0-9]{4,})$/;
           let emailReg = /^[\w]+@([\w-]+).[\w-].*$/;
           let gradeReg = /P|G|S/;
           
           if(frm.id.value.length < 1){
        	   msg = "id가 없습니다. 서버 오류이므로 관리자에게 문의부탁드립니다. (admin@naver.com)"
        	   setMessage("id", msg);
           }
           
           if(frm.name.value.length < 1){
        	   msg = "이름을 적어주세요."
        	   setMessage("name", msg);
           }
           
           if(frm.phone.value.length < 1){
        	   msg = "전화번호를 적어주세요."
        	   setMessage("phone", msg);
           }
           if(frm.email.value.length < 1){
        	   msg = "이메일을 적어주세요. test@naver.com"
               setMessage("email", msg);
           }
           
           if(!phoneReg.test(frm.phone.value)){
        	   msg = "전화번호 형식을 다음과 같이 적어주세요. 010-1234-1234"
        	   setMessage("phone", msg);
           }
           
           if(!emailReg.test(frm.email.value)){
        	   msg = "이메일 형식을 다음과 같이 적어주세요. test@naver.com"
               setMessage("email", msg);
           }
           
           if(!gradeReg.test(frm.grade.value)){
        	   msg = "등급을 다음 값중 하나로 적어주세요. P, G, S"
               setMessage("grade", msg);
           }
           
           if(msg != ''){
        	   return false;
           }
           return true;
       }
   </script>
</body>
</html>