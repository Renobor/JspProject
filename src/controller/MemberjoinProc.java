package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.MemberBean;
import model.MemberDAO;

/**
 * Servlet implementation class MemberjoinProc
 */
@WebServlet("/proc.do")
public class MemberjoinProc extends HttpServlet {
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		reqPro(request,response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		reqPro(request,response);
	}
	
	protected void reqPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		MemberBean bean =new MemberBean();
		bean.setId(request.getParameter("id"));
		String pass1=request.getParameter("pass1");
		String pass2=request.getParameter("pass2");
		
		bean.setPass1(pass1);
		bean.setPass2(pass2);
		bean.setEmail(request.getParameter("email"));
		bean.setTel(request.getParameter("tel"));
		
		String [] arr=request.getParameterValues("hobby");
		String data="";
		for(String string:arr) {
			data+=string+" ";
		}
		bean.setHobby(data);
		bean.setJob(request.getParameter("job"));
		bean.setAge(request.getParameter("age"));
		bean.setInfo(request.getParameter("info"));
		
		
			MemberDAO mdao=new MemberDAO();
			mdao.insertMember(bean);
			//컨트롤러에서 또 다른 컨트롤러를 호출해주어야 합니다.
			RequestDispatcher dis=request.getRequestDispatcher("/MemberlistCon.do");
			dis.forward(request, response);
			
		
		
	}
}
