<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="MemberLoginProc.jsp" method="post">
	<table width="300" border="1" bordercolor="skyblue">
	<tr height="100">
	<td align="center" colspan="2">
		<font size="6" color="skyblue">로그인</font></td>
	</tr>
	
	<tr height="40">
		<td width="120" align="center">아이디</td>
		<td width="180">
		<input type="text" name="id" size="15"></td>
	</tr>
	
	
	<tr height="40">
		<td width="120" align="center">패스워드</td>
		<td width="180"><input type="password" name="pass" size="15"></td>
	</tr>
	
	
	<tr height="40">
	
	<td align="center" colspan="2">
	<input type="submit" value="로그인">
	<input type="reset"  value="다시입력" ></td>
	</tr>
	</table>
	</form>
</body>
</html>