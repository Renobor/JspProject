<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<html>
<head>
    <title>탈퇴 화면</title>
    
    <style type="text/css">
        table{
            margin-left:auto; 
            margin-right:auto;
            border:3px solid skyblue;
        }
        
        td{
            border:1px solid skyblue
        }
        
        #title{
            background-color:skyblue
        }
    </style>
    
    <script type="text/javascript">
        // 비밀번호 미입력시 경고창
        function checkValue(){
            if(!document.deleteform.password.value){
                alert("비밀번호를 입력하지 않았습니다.");
                return false;
            }
        }
    </script>
    
</head>
<body>
<%
	String id=request.getParameter("id");
%>
    <form name="deleteform" method="post" action="MovieMain.jsp?center=MemberDelProc.jsp"
        onsubmit="return checkValue()">
 
        <table>
        
       <tr height="50" height="center">
		<td width="150" align="center">아이디</td>
		<td width="350" align="center">
		<input type="text" name="id" size="40"  value=<%=id %>  placeholder="id를 넣으세요">
		</td>
		</tr>
	   <tr>
       <td width="150" align="center">비밀번호</td>
       <td width="350" align="center">
       <input type="password" name="pass1" size="40" placeholder="비밀번호는 영문과 숫자만 넣어주세요">
       
     
       <input type="button" value="취소" onclick="javascript:window.location='MovieMain.jsp'">
        <input type="submit" value="탈퇴" /> 
       </tr>
        
        
    </table>
    </form>
</body>
</html>
