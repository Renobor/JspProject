package controller;
 
import java.io.IOException;
import java.util.Vector;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import model.BoardBean;
import model.BoardDAO;
 
/**
 * Servlet implementation class BoardListCon
 */
//웹 서블릿 url 설정
@WebServlet("/BoardListCon.do")
public class BoardListCon extends HttpServlet {
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        reqPro(request, response);
    }
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        reqPro(request, response);
    }
 
    protected void reqPro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
 
        // 게시글 갯수 지정
        int pageSize = 10;
 
        
        String pageNum = request.getParameter("pageNum");
 
        
        if (pageNum == null) {
            pageNum = "1";
        }
       
        int count = 0;
        
        // 페이지 내의 넘버링 숫자값 선언
        int number = 0;
 
        // 현재 보여지고 있는 페이지 문자를 숫자로 변환
        int currentPage = Integer.parseInt(pageNum);
        // 전체 게시글의 갯수를 가져와야 하기에 데이터베이스 객체 생성
        BoardDAO bdao = new BoardDAO();
        count = bdao.getAllCount();
        
        // 현재 보여질 페이지 시작 번호를 설정
        int startRow = (currentPage - 1) * pageSize + 1;
        int endRow = currentPage * pageSize;
 
        // 최신글 10개를 기준으로 게시글을 리턴 받아주는 메소드 호출
        Vector<BoardBean> v = bdao.getAllBoard(startRow, endRow);
        number = count - (currentPage - 1) * pageSize;
 
        // BoardList.jsp 쪽으로 request 객체에 담아서 넘겨준다.
        request.setAttribute("v", v);
        request.setAttribute("number", number);
        request.setAttribute("pageSize", pageSize);
        request.setAttribute("count", count);
        request.setAttribute("currentPage", currentPage);
 
        // BoardList를 통해 forward를 시킨다.
        RequestDispatcher dis = request.getRequestDispatcher("MovieMain.jsp?center=BoardList.jsp");
        dis.forward(request, response);
 
    }
}