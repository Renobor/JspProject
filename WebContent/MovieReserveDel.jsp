<%@page import="DB.MovieDAO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
 
<% 
    //차량 예약을 삭제하는 페이지
    //쿼리문을 사용하기위한 pk(기본키 값으로 사용하기 위해) id와 대여일을 파라미터로 받는다.
    
    String id= (String)session.getAttribute("id"); 
    String rday = (String)request.getParameter("rday");
    
    System.out.println("아이디:"+id);
    
    MovieDAO rdao = new MovieDAO();
    //예약삭제 메소드 호출
    rdao.MovieRemoveReserve(id,rday);
    System.out.println("영화예약날짜:"+rday);
    if(rdao!=null){
    %>
	   <script type="text/javascript">
	   
	        alert("회원정보가 삭제되었습니다.")
	        location.href="MovieMain.jsp?center=MovieReserveView.jsp"
	   </script>
     
              
 <%    
 }
%>

</body>
</html>
