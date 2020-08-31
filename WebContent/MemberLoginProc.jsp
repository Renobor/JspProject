<%@page import="DB.MovieDAO"%>
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
	
	String id=request.getParameter("id");
	String pass=request.getParameter("pass");
	//회원 아이디와 패스워드가 일치하는지 비교
	
	MovieDAO rdao =new MovieDAO();
	
	
	int result=rdao.getMember(id,pass);
	
	if(result==0){
	%>
	<script>
		alert("회원 아이디 또는 패스워드가 틀립니다");
		location.href='MovieMain.jsp?center=MemberLogin.jsp';
	</script>
	<%
	}else{
		//로그인 처리가 되었다면
		session.setAttribute("id", id);
		response.sendRedirect("MovieMain.jsp");
	}
	
%>

</body>
</html>