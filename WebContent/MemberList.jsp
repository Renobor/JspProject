<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<center>
	<h2>모든 회원 보기</h2>
	<table width="800" border="1" bordercolor="gray">
	 <tr height="40">
	   <td align="center" width="50">아이디</td>
	   <td align="center" width="200">이메일</td>
	   <td align="center" width="150">전화</td>
	   <td align="center" width="150">취미</td>
	   <td align="center" width="50">직업</td>
	   <td align="center" width="50">나이</td>
	   <td align="center" width="50">회원탈퇴</td>
	   
	  </tr>
	  
	  
	 <c:forEach var="bean" items="${v }">
	 <!-- 벡터로 넘어온 v를 bean으로 받아줬다. -->
	 
	   <tr height="40">
	   <td align="center" width="50">${bean.id }</td>
	   <td align="center" width="200"><a href="#">${bean.email }</a></td>
	   <td align="center" width="150">${bean.tel }</td>
	   <td align="center" width="150">${bean.hobby }</td>
	   <td align="center" width="50">${bean.job }</td>
	   <td align="center" width="50">${bean.age }</td>
	   <td align="center" width="50">
	   <input type="button" value="회원탈퇴" onclick="location.href='MovieMain.jsp?center=MemberDelForm.jsp?id=${bean.id }'">
	   
<!-- 	    <button onclick="location.href='MovieMain.jsp?center=MemberDelForm.jsp'">삭제하기</button> -->
<!-- 	    </button></td> -->
	  
	  </tr>
	   
	  
	 </c:forEach>
	</table>
  
</body>
</html>