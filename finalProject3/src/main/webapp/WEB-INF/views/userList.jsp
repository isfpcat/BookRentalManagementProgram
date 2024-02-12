<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>도서 고객 및 대여관리 프로그램 ver 1.0</title>
  <link rel="stylesheet" href="<c:url value='resources/css/common.css'/>">
  <link rel="stylesheet" href="<c:url value='resources/css/menu.css'/>">
  <link rel="stylesheet" href="<c:url value='resources/css/table.css'/>">
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
		<div class="title">고객목록조회/수정</div>
		<div style="text-align:center">
		  <div class="board-container">
		    <table>
		      <tr>
		        <th class="id">고객번호</th>
		        <th class="name">고객이름</th>
		        <th class="phone">전화번호</th>
		        <th class="joinDate">가입일자</th>
		        <th class="email">이메일</th>
		        <th class="grade">고객등급</th>
		      </tr>
		      <c:forEach var="user" items="${list}">
		        <tr>
		          <td class="id">
			          <a href="<c:url value="/info?id=${user.id}"/>">
			          	${user.id}
			          </a>
		          </td>
		          <td class="name">${user.name}</td>
		          <td class="phone">${user.phone}</td>
		          <td class="joinDate">${user.formatDate(user.joinDate)}</td>
		          <td class="email">${user.email}</td>
		          <c:choose>
		            <c:when test="${user.grade == 'P'}">
		              <td class="grade">Platinum</td>
		            </c:when>
		            <c:when test="${user.grade == 'G'}">
		              <td class="grade">Gold</td>
		            </c:when>
		            <c:when test="${user.grade == 'S'}">
		              <td class="grade">Silver</td>
		            </c:when>
		          </c:choose>
		        </tr>
		      </c:forEach>
		    </table>
		    <br>
		  </div>
		</div>
	</div>
</body>
<footer>
	<h2>나도 할 수 있는 Java & Spring 웹 개발 종합반(8wlgns)</h2>
</footer>
</html>