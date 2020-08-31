<%@page import="DB.MovieListBean"%>
<%@page import="DB.MovieDAO"%>
<%@page import="DB.MovieListBean"%>
<%@page import="java.util.Vector"%>
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
	int category =Integer.parseInt(request.getParameter("category"));
	String temp="";
	if(category==1){
		temp="소형";
	} else if(category==2) {
		temp="중형";
	} else if(category==3){
		temp="대형";
	}
	
%>

<center>
<table width="1000">
	<tr height="100">
	<td align="center" colspan="3">
	<font size="5" color="gray">렌트카 종류</font></td>
	</tr>
<%
	
	
	MovieDAO rdao=new MovieDAO();
	
	Vector<MovieListBean> v=rdao.getCategory(category);
	//tr을 3개씩 보여주고 다시 tr을 실행할수 있도록 하는변수선언.
	
	int j=0;
	for(int i=0;i<v.size();i++){
		
		
		//벡터에 저장되어있는 빈클래스를 추출
		MovieListBean bean=v.get(i);
		if(j%3==0){
%>
		
	<tr height="220">	
<% }%>
		<td width="333" align="center">
		<a href="MovieMain.jsp?center=MovieReserveInfo.jsp?no=<%=bean.getNo() %>">
				<!-- center에 CarReserveInfo(하나에대한정보)를 no기준으로  bean에 no값을 얻어온다.  -->
		<img alt="" src="img/movie/<%=bean.getImg() %>" width="300" height="200">
		</a><p>
		<font size="3" color="gray"><b>차량명: <%=bean.getName() %></b></font></td>
<%		
	j=j+1; //j값을 증가하여 총 3개의 정보를 보여주기위함.
	}

%>
</table>
</body>
</html>