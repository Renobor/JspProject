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
	//수량
	int qty=Integer.parseInt(request.getParameter("qty"));
	
	String img=request.getParameter("img");
	
%>
<center>
<form action="MovieMain.jsp?center=MovieReserveResult.jsp" method="post">
<table width="1000">

	
  
		
		
  	
	
  <tr>
  
		<td rowspan="7" width="500" align="center">
		<img alt="" src="img/movie/<%=img%>" width="450"></td>
		<td width="250" align="center"> 영화매수</td> 
		 <td width="250" align="center">
		 <select name="sel">
		 <option value="1">1매</option>
		 <option value="2">2매</option>
		 <option value="3">3매</option>
		 <option value="4">4매</option>
		 <option value="5">5매</option>
		 <option value="6">6매</option>
		 <option value="7">7매</option>
		 
		 </select>
		 </td>	 
	</tr>
  	<tr>
  		<td width="250" align="center">예약일</td>
  		<td width="250" align="center">
  		<input type="date(now)" name="rday" size="15"></td>
  	</tr>	
	
	<tr>
		<td width="250" align="center">팝콘</td>
		<td width="250" align="center">
		<select name="pop">
			<option value="1">3000원</option>
			<option value="2">비적용 </option>
			
		</select></td>
		</tr>
	<tr>
		<td width="250" align="center">치킨</td>
		<td width="250" align="center"><select name="chiken">
			<option value="1">6000원</option>
			<option value="2">비적용</option>
		</select></td>
	</tr>
	
	
			
	<tr>
		<td width="250" align="center">콜라</td>
		<td width="250" align="center"><select name="cola">
						<option value="1">적용(무료)</option>
						<option value="2">비적용</option>
						
		</select></td>
	</tr>
	<tr>
		<td width="250" align="center">커플용</td>
		<td width="250" align="center">
		<select name="couple">
			<option value="1">1만2천원</option>
			<option value="2">비적용</option>
		</select></td>
	</tr>	
	<tr>
		<td align="center" colspan="2">
		<input type="hidden" name="no" value="<%=no%>">
		<input type="hidden" name="qty" value="<%=qty%>">
		<input type="submit" value="영화 예약하기">
		</td>
	
	<tr>

	<!-- 이걸 한꺼번에 받아줄수있는 bean클래스를 만든다 CarReserveBean을 만든다-->
</table>	
</form>	
</body>
</html>