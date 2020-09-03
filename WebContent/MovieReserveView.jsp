<%@page import="DB.MovieDAO"%>
<%@page import="DB.MovieViewBean"%>
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

<%
	String id=(String)session.getAttribute("id");
	if(id==null){
%>		
		<script>
		alert("로그인 되어있지 않습니다");	
		location.href='MovieMain.jsp?center=MemberLogin.jsp';
		</script>	
	
		
<% 		
	}
	//로그인 되어있는 아이디를 읽어 옴
	
	MovieDAO rdao=new MovieDAO();
	Vector<MovieViewBean> v = rdao.getAllReserve(id);
	
	
	
%>
<center>
<table width="1000" >
	<tr height="100">
	<td align="center" colspan="11">
	<font size="6" color="gray">영화 예약 화면 </font></td>
	
	</tr>
	
	
	<tr height="40">
	
		<td width="150" align="center">이미지</td>
		<td width="150" align="center">이름</td>
		<td width="150" align="center">대여일</td>
		<td width="100" align="center">총금액</td>
		<td width="60" align="center">수량</td>
		<td width="60" align="center">팝콘</td>
		<td width="60" align="center">콜라</td>
		<td width="60" align="center">치킨</td>
		<td width="60" align="center">커플셋트</td>
		
	</tr>
	
<%
	for(int i=0;i<v.size();i++){
		
		MovieViewBean bean=v.get(i);
	    
		int pop=0;
		if(bean.getPop()==1)pop=5000;
		int chiken=0;
		if(bean.getPop()==1)chiken=7000;
		int cola=0;
		if(bean.getPop()==1)cola=3000;
		int couple=0;
		if(bean.getPop()==1)couple=10000;
		int total=pop+cola+chiken+couple+bean.getPrice();
		
%>
		<tr height="70">
		<td width="150" align="center"><img alt="" src="img/movie/<%=bean.getImg() %>" width="120" height="70"></td>
		<td width="150" align="center"><%=bean.getName()%></td>
		<td width="150" align="center"><%=bean.getRday() %></td>
		<td width="100" align="center"><%=total%>원</td>
		<td width="60" align="center"><%=bean.getQty()%></td>
		<td width="60" align="center"><%=bean.getPop() %></td>
		<td width="60" align="center"><%=bean.getCola() %></td>
		<td width="60" align="center"><%=bean.getChiken() %></td>
		<td width="60" align="center"><%=bean.getCoupleset() %></td>
	    <td width="90" align="center">
      	
      	 <input type="button" value="예약삭제" onclick="javascript:window.location='MovieReserveDel.jsp?rday=<%=bean.getRday()%>'">
		</td>
		
		
	</tr>
<% 		
	}

%>

</table>
</body>
</html>