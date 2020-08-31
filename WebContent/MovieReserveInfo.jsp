<%@page import="DB.MovieListBean"%>
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
	int no=Integer.parseInt(request.getParameter("no"));
	
	//데이터베이스에 접근
	MovieDAO rdao=new MovieDAO();
	
	MovieListBean bean=rdao.getOneMovie(no);
	
	float category =bean.getCategory();
	String temp="";
	if(category==1) temp="액션";
	else if(category==2) temp="멜로";
	else if(category==3) temp="스릴러";
	
%>

<center>

<form action="MovieMain.jsp?center=MovieOptionSelect.jsp" method="post">
<table width="1000">

	<tr height="100">
		<td align="center" colspan="3">
	<font size="6" color="gray">
	
	<%=bean.getName() %>영화선택
	</font>
	</td>
	
	<tr>
		<td rowspan="6" width="500">
		<img alt="" src="img/movie/<%=bean.getImg() %>" width="450"></td>
		<td width="250" align="center"> 영화이름</td> 
		 <td width="250" align="center"><%=bean.getName() %></td>
	</tr>

	<tr>
		<td width="250" align="center">영화매수</td>
		<td width="250" align="center"><select name="qty">
										<option value="1">1</option>
										<option value="2">2</option>
										<option value="3">3</option>
									</select></td>
	</tr>
	<tr>
	<td width="250" align="center"> 영화분류</td> 
	<td width="250" align="center"><%=temp%></td>	
	</tr>
	
	<tr>
	<td width="250" align="center"> 영화가격</td> 
	<td width="250" align="center"><%=bean.getPrice() %>원</td>	
	</tr>
	
	
	<tr>
		<td align="center" colspan="2">
			<input type="hidden" name="no" value="<%=bean.getNo() %>">
			<input type="hidden" name="img" value="<%=bean.getImg() %>">
			<input type="submit" value="선택후 구매">
		</td>
	</tr>
</table>
	<br><br><Br>
	<font size="5" color="gray">영화정보보기</font>
	<p>
	<%=bean.getInfo() %>
</form>
</body>
</html>