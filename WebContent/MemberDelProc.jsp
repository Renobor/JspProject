<%@page import="model.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<html>
<head>
    <title>ȸ�� ���� ó��</title>
</head>
<body>
    <%
        String id= (String)session.getAttribute("id"); 
        String pass1 =request.getParameter("pass1");
        
        // ���ǿ��� ���̵�, DeleteForm.jsp���� �Է¹��� ��й�ȣ�� �����´�.
        // ������ ����� ������ ȸ�������� �����Ѵ�. - ��������� ��ȯ
        MemberDAO dao =new MemberDAO();
        
        int check = dao.deleteMember(id, pass1);
        
        if(check == 1){
            session.invalidate(); // �����ߴٸ� ���������� �����Ѵ�.
    %>
        <br><br>
        <b><font size="4" color="gray">ȸ�������� �����Ǿ����ϴ�.</font></b>
        <br><br><br>
    
        <input type="button" value="Ȯ��" onclick="javascript:window.location='MovieMain.jsp?center=MemberlistCon.do'">
    
    <%    
         // ��й�ȣ�� Ʋ����� - ������ �ȵǾ��� ���
        }else{
    %>
        <script>
            alert("��й�ȣ�� ���� �ʽ��ϴ�.");
            history.go(-1);
        </script>
    <%
        } 
    %>
</body>
</html>

