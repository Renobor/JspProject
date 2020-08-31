<%@page import="DB.BoardBean"%>
<%@page import="DB.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<% 
	request.setCharacterEncoding("utf-8");
%>

<jsp:useBean id="boardbean" class = "DB.BoardBean">

	<jsp:setProperty name="boardbean" property="*"/>
</jsp:useBean>



<!-- boardbean을 사용하기위한 셋팅과정 -->

<% 

 	BoardDAO bdao=new BoardDAO();
	
// 	DAO에서 본격적인 디비연동해서 만드는 메서드를 생성하기위해 dao를 만든다
	
 	bdao.insertBoard(boardbean);
	
	response.sendRedirect("MovieMain.jsp?center=BoardList.jsp");
	

%>





</body>
</html>