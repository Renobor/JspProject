<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<html>
<head>
    <title>Ż�� ȭ��</title>
    
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
        // ��й�ȣ ���Է½� ���â
        function checkValue(){
            if(!document.deleteform.password.value){
                alert("��й�ȣ�� �Է����� �ʾҽ��ϴ�.");
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
		<td width="150" align="center">���̵�</td>
		<td width="350" align="center">
		<input type="text" name="id" size="40"  value=<%=id %>  placeholder="id�� ��������">
		</td>
		</tr>
	   <tr>
       <td width="150" align="center">��й�ȣ</td>
       <td width="350" align="center">
       <input type="password" name="pass1" size="40" placeholder="��й�ȣ�� ������ ���ڸ� �־��ּ���">
       
     
       <input type="button" value="���" onclick="javascript:window.location='MovieMain.jsp'">
        <input type="submit" value="Ż��" /> 
       </tr>
        
        
    </table>
    </form>
</body>
</html>
