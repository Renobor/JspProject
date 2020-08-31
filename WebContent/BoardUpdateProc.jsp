<%@page import="DB.BoardDAO"%>
<%@page import="DB.BoardBean"%>
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

<jsp:useBean id="boardBean" class="DB.BoardBean">
	<jsp:setProperty name="boardBean" property="*"/>
</jsp:useBean>
<%

	BoardDAO bdao=new BoardDAO();
	
	String pass=bdao.getPass(boardBean.getNum());
	
	//기존 패스워드값과 update시 작성했던 pass값이 같은지 비교
	
	//기존의 pass값(board.getPassword)와 getPass의값을 비교한다.
	if(pass.equals(boardBean.getPassword())){
		bdao.updateBoard(boardBean);
		response.sendRedirect("BoardList.jsp");
		
	}else{
	%>	
	
	<script type="text/javascript">
	alert("패스워드 일치하지 않음");
	history.back();
	
	</script>
		
<% 		
	}
%>

</body>
</html>