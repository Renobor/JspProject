<%@page import="DB.MovieListBean"%>
<%@page import="DB.MovieDAO"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
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

	<jsp:useBean id="rbean" class="DB.MovieReserveBean">
		<jsp:setProperty name="rbean" property="*"/>
	</jsp:useBean>

<%
	String id=(String)session.getAttribute("id");
	if(id==null){
		
	

%>
	<script>
		alert("로그인후 이용 가능합니다");
		location.href='MovieMain.jsp?center=MemberLogin.jsp';
	</script>

<%	
	}
	
	//날짜 비교
	Date d1=new Date();
	Date d2=new Date();
	
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-mm-dd");
	
	d1=sdf.parse(rbean.getRday());
	d2=sdf.parse(sdf.format(d2));
	
	//날짜 비교 메소드를 사용할수 있다.
	
	int compare=d1.compareTo(d2);
	//예약하려는 날짜보다 현재날짜가 크다면 -1
	//예약하려는 날짜와 현재날짜가 같다면 0
	//예약하려는 날짜가 더 크다면 1을 리턴
	
	if(compare<0){//오늘날짜보다 이전 날짜 선택시.
		
	%>
		<script>
			alert("현재 날짜보다 이전 날짜는 선택불가");
			history.go(-1);
		</script>
		
	<%	
	}
	
	//결과적으로 아무런 문제가 없다면 데이터 저장후 결과 페이지 보여주기
	//아이디 값이 빈클래스에 없기에
	String id1=(String)session.getAttribute("id");
	
	rbean.setId(id1);
	
	//데이터 베이스에 빈클래스를 저장
	MovieDAO rdao=new MovieDAO();
	rdao.setReserveMovie(rbean);
	
	
	//날짜를 
	
	MovieListBean cbean=rdao.getOneMovie(rbean.getNo());
	//차량 총 금액
   float totalmovie=cbean.getPrice()*rbean.getQty()*rbean.getSel();
	float pop=0;
	if(rbean.getPop()==1)pop=5000;
	float chiken=0;
	if(rbean.getPop()==1)chiken=7000;
	float couple=0;
	if(rbean.getPop()==1)couple=10000;
	
	float totaloption=(rbean.getQty()*rbean.getSel())*(pop+chiken+couple);

	
%>	
	
	
<center>
	<table width="1000">

	<tr height="100">
	<td align="center" >
	<font size="6" color="gray">차량 예약 완료 화면</font></td>
	</tr>
	
	
	
	<tr height="100">
	<td align="center">
	<img alt="" src="img/<%=cbean.getImg() %>" width="470"></td>
	</tr>
	<tr height="50">
	<td align="center">
	<font size="5" color="red">차량 총예약 금액 <%=totalmovie%>원</font></td>
	</tr>
	<tr height="50">
	<td align="center">
		<font size="5" color="red">차량 총옵션 금액<%=totaloption%>원</font></td>
	</tr>
	<tr height="50">
		<td align="center">
		<font size="5" color="red">차량 총금액 <%=totaloption+totalmovie%>원</font>
	</td>
	</tr>
</table>	
	
</body>
</html>