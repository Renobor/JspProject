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
//�� ���� url ����
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
      
 
        // �Խñ� ���� ����
        int pageSize = 10;
 
        
        String pageNum = request.getParameter("pageNum");
 
        
        if (pageNum == null) {
            pageNum = "1";
        }
       
        int count = 0;
        
        // ������ ���� �ѹ��� ���ڰ� ����
        int number = 0;
 
        // ���� �������� �ִ� ������ ���ڸ� ���ڷ� ��ȯ
        int currentPage = Integer.parseInt(pageNum);
        // ��ü �Խñ��� ������ �����;� �ϱ⿡ �����ͺ��̽� ��ü ����
        BoardDAO bdao = new BoardDAO();
        count = bdao.getAllCount();
        
        // ���� ������ ������ ���� ��ȣ�� ����
        int startRow = (currentPage - 1) * pageSize + 1;
        int endRow = currentPage * pageSize;
 
        // �ֽű� 10���� �������� �Խñ��� ���� �޾��ִ� �޼ҵ� ȣ��
        Vector<BoardBean> v = bdao.getAllBoard(startRow, endRow);
        number = count - (currentPage - 1) * pageSize;
 
        // BoardList.jsp ������ request ��ü�� ��Ƽ� �Ѱ��ش�.
        request.setAttribute("v", v);
        request.setAttribute("number", number);
        request.setAttribute("pageSize", pageSize);
        request.setAttribute("count", count);
        request.setAttribute("currentPage", currentPage);
 
        // BoardList�� ���� forward�� ��Ų��.
        RequestDispatcher dis = request.getRequestDispatcher("MovieMain.jsp?center=BoardList.jsp");
        dis.forward(request, response);
 
    }
}