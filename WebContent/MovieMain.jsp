<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<!-- 1.HTML로 화면을 작성하고 center 파라미터를 들고와서 center에 Center.jsp로 들고온후에
중앙에 Center.jsp화면을 뿌려주고 Top.jsp를 상단에 Botton.jsp를 하단에 뿌려줍니다. -->

<!-- 일단 서버에 한번 실행을 해줘야 Context에 연결이 링크가되니까 실행부터한다. -->



<%
	String center=request.getParameter("center");
	
	if(center==null){
		
		center="Center.jsp";
		
	}
	
%>

<table width="1000" align="center">
	<tr height="120" align="center">         
	<!-- top에 tr에 70 50 있으니 140정도 잡아줌 -->
	<!-- top부분 -->
	<td align="center"> <jsp:include page="Top.jsp"></jsp:include></td>
	</tr>
		
	<tr  align="center">
		<td align="center" width="1000"><jsp:include page="<%=center %>"></jsp:include></td>
	</tr>
	
	<tr height="100" align="center">
		<td align="center" width="1000"><jsp:include page="Bottom.jsp"></jsp:include>
	</td>
	</tr>
</table>


</body>
</html>