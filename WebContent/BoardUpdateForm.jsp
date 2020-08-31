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

		int num=Integer.parseInt(request.getParameter("num"));
		
		BoardDAO bdao=new BoardDAO();
		BoardBean bean=bdao.getOneUpdateBoard(num);
		
%>

<form action="BoardUpdateProc.jsp" method="post">
<table width="600" border="1" bgcolor="skyblue">
	<tr height="40">
	<td width="120" align="center">작성자</td>
	<td width="120" align="center"><%=bean.getWriter() %></td>
	<td width="120" align="center">작성일</td>
	<td width="180" align="center"><%=bean.getReg_date() %></td>
	</tr>
	
	<!-- width 넓이  colspan 가로 합치기  size는 텍스트박스 사이즈-->
	
	
	<tr height="40">
		<td width="120" align="center">제목</td>
		<td width="480" colspan="3" >&nbsp;<input type="text" name="subject" value="<%=bean.getSubject() %>" size="65">
		</td>
	</tr>
	
	
		
	
	<tr height="40">
		<td width="120" align="center">패스워드</td>
		<td width="480" colspan="3">&nbsp;<input type="password" name="password" value="<%=bean.getPassword() %>" size="65">
		</td>
	</tr>
	
	
	<tr height="40">
		<td width="120" align="center">글내용</td>
		<td width="480" colspan="3"><textarea rows="10" cols="70" name="content" align="left"><%=bean.getContent() %></textarea>
		</td>
	</tr>
	
	<tr height="40">
		<td colspan="4" align="center">
		<input type="hidden" name="num" value="<%=bean.getNum() %>">
		<input type="submit" value="글수정">&nbsp;&nbsp;
		<input type="button" onclick="location.href='MovieMain.jsp?center=BoardList2.jsp'" value="전체글보기"></td>
	
	</tr>
</table>
</form>

</body>
</html>