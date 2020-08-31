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
	String id = (String) session.getAttribute("id");
	
	if(id==null){
		id="GUEST";
	}

%>



<table width="1000" bgcolor="white" align="center">
	<tr height="70">
		<td colspan="4">
		<a href="MovieMain.jsp" style="text-decoration:none">
			<img alt="" src="img/Ocnlogo.jpg" height="100"></a>
		</td>
		<td align="center" width="200">
		<%=id %> 님 반갑습니다.
		<p>
		
		<%
			if(id.equals("GUEST")){ %>
								
				<button onclick="location.href='MovieMain.jsp?center=MemberLogin.jsp'">로그인</button>
			<!-- 로그인이 되지 않았을경우 로그인 화면으로 이동. -->
			
		<% 	}else{%>
			     <button onclick="location.href='MemberLogout.jsp'">로그아웃</button>
			<!-- 로그아웃 버튼시 로그아웃처리 -->
		<% 
			}
		 %>
		
		<button onclick="location.href='MovieMain.jsp?center=MemberJoin.jsp'">회원가입</button>
		
		
		
		</td>
	</tr>
	
	<tr height="50">
		<td align="center" width="200" bgcolor="skyblue">
			<font color="white" size="5"><a href="MovieMain.jsp?center=MovieReserveMain.jsp" style="text-decoration:none">영화 예약하기</a></font>	
												<!-- RentcarMAin.jsp 에서 center값만 바뀌게하기 위해서  RentcarMain.jsp?center를 한것이고 그center값이 carReserveMain이라는뜻-->
		</td>
		
		<td align="center" width="200" bgcolor="skyblue">
			<font color="white" size="5"><a href="MovieMain.jsp?center=MovieReserveView.jsp" style="text-decoration:none">영화 예약확인</a></font>	
		</td>
		
		<td align="center" width="200" bgcolor="skyblue">
			<font color="white" size="5"><a href="MovieMain.jsp?center=BoardList.jsp" style="text-decoration:none">게시판(Model1)</a></font>	
		</td>
		
		<td align="center" width="200" bgcolor="skyblue">
			<font color="white" size="5"><a href="MovieMain.jsp?center=BoardListCon.do" style="text-decoration:none">게시판(Model2)</a></font>	
		</td>
		
		<td align="center" width="200" bgcolor="skyblue">
			<font color="white" size="5"><a href="MovieMain.jsp?center=MemberlistCon.do" style="text-decoration:none">회원목록</a></font>	
		</td>
		
	</tr>
</table>

</body>
</html>