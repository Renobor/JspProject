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
	String pass=request.getParameter("password");
	int num=Integer.parseInt(request.getParameter("num"));
	
	BoardDAO bdao=new BoardDAO();
	String password=bdao.getPass(num);
	
	if(pass.equals(password)){
		bdao.deleteBoard(num);
		
		response.sendRedirect("MovieMain.jsp?center=BoardList.jsp");
		
		
	}else{
		%>
		<script type="text/javascript">
		alert("비밀번호가 다릅니다.");
		history.back();
		</script>
		
		<%
	
	}
	
	
%>




<%
	
	
%>

</body>
</html>