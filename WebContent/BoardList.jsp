<%@page import="DB.BoardBean"%>
<%@page import="java.util.Vector"%>
<%@page import="DB.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<center>
<h2>전체글보기</h2>

<%
	//화면에 보여질 게시글의 개수를 지정
	int pageSize=10;

// 	//현재 카운터를 클릭한 번호갑을 얻어옴
	String pageNum=request.getParameter("pageNum");
	
// 	//만약 처음 boardlist.jsp를 클릭하거나 수정 삭제 등 다른게시글에서 
// 	//이전페이지로 넘어오면 pageNum값이 없기에 null 처리를 해줌
	
	if(pageNum==null){
		pageNum="1";
	}
	
	int count=0; //전체 글의 갯수를 저장하는 변수
	
	int number=0;//페이지 넘버링 변수
	
	//현재 보고자하는 페이지 숫자를 저장.
	int currentPage=Integer.parseInt(pageNum);
	
	
	
	BoardDAO bdao =new BoardDAO();
	
	count=bdao.getAllCount();
	
	//현재 페이지에 보여줄 시작 번호를 설정 =데이터 베이스에서 불러올 시작번호
			
	int startRow=(currentPage-1)*pageSize+1;
	int endRow=currentPage * pageSize;
	
	//최신글 10개를 기준으로 게시글을 리턴 받아주는 메소드 호출
	Vector<BoardBean> vec =bdao.getAllBoard(startRow,endRow);
	
	//테이블에 표시할 번호를 지정 
	number = count-(currentPage-1) * pageSize; //공식
	
	
%>
	
<table width="700" border="1" bgcolor="skyblue">
	<tr height="40">
		<td align="right" colspan="5">
		<input type="button" value="글쓰기" onclick="location.href='MovieMain.jsp?center=BoardWriteForm.jsp'">
		
		</td>
	</tr>

	<tr height="40">
		<td width="50" align="center"> 번호</td>
		<td width="320" align="center">제목</td>
		<td width="100" align="center">작성자</td>
		<td width="150" align="center">작성열</td>
		<td width="80"  algin="center">조회수</td>
	</tr>



<%

	for(int i=0;i<vec.size();i++){
		
	BoardBean bean=vec.get(i);
	
		
	
%>

	<tr height="40">
		<td width="50" align="center"> <%=number-- %></td>
		<td width="320" align="center">
		<a href="MovieMain.jsp?center=BoardInfo.jsp?num=<%=bean.getNum()%>" style="text-decoration:none">
		
		<%
			if(bean.getRe_step()>1){  //Re_step값이 존재한다면 
				for(int j=0;j<(bean.getRe_step()-1)*5;j++){ //5칸띄워라
			%>&nbsp;&nbsp; <!-- 공백나타주기 -->		
			<%	}
			}
		%>
		
		
		<%=bean.getSubject()%>
		</a></td>
		<td width="100" align="center"><%=bean.getWriter() %></td>
		<td width="150" align="center"><%=bean.getReg_date() %></td>
		<td width="80" align="center"><%=bean.getReadcount() %></td>
		</tr>
		
<%} %>

</table>
<p>

<!-- 페이지 카운터링 소스를  -->
<% 
if(count>0){
	int pageCount=count/pageSize  + (count%pageSize==0? 0:1); 
	//카운터링 숫자를 얼마까지 보여줄건지 결정한다.
	
	//시작페이지를 설정
	
	int startPage = 1;
	
	if(currentPage % 10 !=0){
		startPage=(int)(currentPage/10) * 10+1;
		
	}else{
		startPage=((int)(currentPage/10)-1) * 10+1;
			
		
	}
		int pageBlock=10; //카운터링 처리숫자
		int endPage=startPage+pageBlock-1; //화면에 보여질 페이지의 마지막 숫자.
		if(endPage > pageCount) endPage=pageCount;
		
		if(startPage>10){
%>			
			<a href="MovieMain.jsp?center=BoardList.jsp?PageNum= <%=startPage-10%>"></a>
<% 		
		}
		for(int i=startPage;i<=endPage;i++){
%>
		<a href="MovieMain.jsp?center=BoardList.jsp?pageNum=<%=i%>">[<%=i %>]</a>	
<% 		
		}
		
		if(endPage<pageCount){
%>
		<a href="MovieMain.jsp?center=BoardList.jsp?pageNum=<%=startPage+10 %>">[다음]</a>
<% 
	
		}
	}

%>
</body>
</html>