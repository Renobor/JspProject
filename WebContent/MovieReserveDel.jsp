<%@page import="DB.MovieDAO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
 
<% 
    //���� ������ �����ϴ� ������
    //�������� ����ϱ����� pk(�⺻Ű ������ ����ϱ� ����) id�� �뿩���� �Ķ���ͷ� �޴´�.
    
    String id= (String)session.getAttribute("id"); 
    String rday = (String)request.getParameter("rday");
    
    System.out.println("���̵�:"+id);
    
    MovieDAO rdao = new MovieDAO();
    //������� �޼ҵ� ȣ��
    rdao.MovieRemoveReserve(id,rday);
    System.out.println("��ȭ���೯¥:"+rday);
    if(rdao!=null){
    %>
	   <script type="text/javascript">
	   
	        alert("ȸ�������� �����Ǿ����ϴ�.")
	        location.href="MovieMain.jsp?center=MovieReserveView.jsp"
	   </script>
     
              
 <%    
 }
%>

</body>
</html>
