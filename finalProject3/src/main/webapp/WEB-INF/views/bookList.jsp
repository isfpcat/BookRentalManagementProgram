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
		<div class="title">고객대여리스트</div>
		<div style="text-align:center">
		  <div class="board-container">
		    <table>
		      <tr>
		        <th class="userId">고객번호</th>
		        <th class="Id">대여번호</th>
		        <th class="code">도서코드</th>
		        <th class="price">대여금액</th>
		        <th class="rentDate">가입일자</th>
		      </tr>
		      <c:forEach var="book" items="${list}">
		        <tr>
		          <td class="userId">${book.userId}</td>
		          <td class="id">${book.id}</td>
		          <td class="code">${book.code}</td>
		          <td class="code">${book.price}</td>
		          <td class="rentDate">${book.formatDate(book.rentDate)}</td>
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