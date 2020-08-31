<%@page import="DB.MovieListBean"%>
<%@page import="DB.MovieDAO"%>
<%@page import="java.util.Vector"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<center>
<!-- 데이터베이스에 연결하여 최신순 자동차 3대만 뿌려주는 데이터를 가져옴 -->


<%	
	
	MovieDAO rdao=new MovieDAO();
	
	
	Vector<MovieListBean> v=rdao.getSelectMovie();
	
	
%>

<table width="1000" align="center">
	<tr height="100">
	<td align="center" colspan="3">
	<font size="5" color="black">영화 장르</font></td>
	</tr>
	
<% for(int i=0;i<v.size(); i++) {
	
		MovieListBean bean=v.get(i);	
		//bean에다가 담아서 화면에 뿌려주기위해 v 크기를 bean에다가 담는다.
		
%>
		<td width="333" align="center">
		<a href="MovieMain.jsp?center=MovieReserveInfo.jsp?no=<%=bean.getNo() %>">
		<img alt="" src="img/movie/<%=bean.getImg()%>" width="300" height="220">
		<!-- 이미지 폴더경로 -->
		
		</a>
		<p>
		영화명: <%=bean.getName() %>
		</td>
<%
	}
%>		


</table>

	<form action="MovieMain.jsp?center=CartegoryList.jsp" method="post">
	
	<!-- 카테고리 리스트 -->
	
	<font size="3" color="black"><b>영화 장르 검색 하기 </b></font>&nbsp;&nbsp;
	<select name="category">
	 
		<option value="1">액션</option>
		<option value="2">멜로</option>
		<option value="3">스릴러</option>
	</select> 
	
	<input type="submit" value="검색"> &nbsp;
	</form><br><br>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<button onclick="location.href='MovieMain.jsp?center=MovieAllList.jsp'">전체 검색</button>
	

</body>
</html>