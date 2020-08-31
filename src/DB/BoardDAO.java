package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardDAO {
	
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public void getCon() {
		
		try {
			Context initctx=new InitialContext();
			Context envctx=(Context) initctx.lookup("java:comp/env");
			DataSource ds=(DataSource)envctx.lookup("jdbc/pool");
			//DataSource
			con=ds.getConnection();
		}catch(Exception e) {
			
				e.printStackTrace();
		
		}
	
	}
	
	public void insertBoard(BoardBean bean) {
		
		getCon();
		
		int ref=0; //글 그룹을 의미 = 쿼리를 실행시켜서 가장큰 ref값을  
		int re_step=1;
		int re_level=1;
		
		
		try {
			String refsql="select max(ref) from board";
			
			pstmt=con.prepareStatement(refsql);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				
				ref=rs.getInt(1)+1; //최대값에 +1을 더해서 글그룹을 설정
				
			}
			
			
			String sql="insert into board values(board_seq.nextval,?,?,?,?,sysdate,?,?,?,0,?)";
					 
			
			pstmt=con.prepareStatement(sql);
			
			
			pstmt.setString(1, bean.getWriter());
			pstmt.setString(2, bean.getEmail());
			pstmt.setString(3, bean.getSubject());
			pstmt.setString(4, bean.getPassword());
			pstmt.setInt(5, ref);
			pstmt.setInt(6, re_step);
			pstmt.setInt(7, re_level);
			pstmt.setString(8, bean.getContent());
			
			pstmt.executeUpdate();
			
		
			
			con.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	
	  public Vector<BoardBean> getAllBoard(int start,int end){
	  
	  Vector<BoardBean> v=new Vector<>();
	  
	  getCon();
	  
	  
	  
	  try {
	  
	  String
	  sqls="select * from (select A.*,Rownum Rnum from (select * from board order by ref desc,re_step asc)A) "
	  + "where Rnum>=? and Rnum<=?"; // 먼저쓴글 순서 re_step은 계급순서?
	  
	  pstmt=con.prepareStatement(sqls); 
	  pstmt.setInt(1, start); 
	  pstmt.setInt(2,end); //10개를 맞춰주기위해
	  
	  rs=pstmt.executeQuery();
	  
	  while(rs.next()) { 
		  
	BoardBean bean=new BoardBean();
	  
	  bean.setNum(rs.getInt(1)); 
	  bean.setWriter(rs.getString(2));
	  bean.setEmail(rs.getString(3)); 
	  bean.setSubject(rs.getString(4));
	  bean.setPassword(rs.getString(5));
	  bean.setReg_date(rs.getDate(6).toString()); 
	  bean.setRef(rs.getInt(7));
	  bean.setRe_step(rs.getInt(8)); 
	  bean.setRe_level(rs.getInt(9));
	  bean.setReadcount(rs.getInt(10)); 
	  bean.setContent(rs.getString(11));
	  
	  v.add(bean); 
	  } con.close();
	  
	  }catch(Exception e) {
		 e.printStackTrace(); 
	  }
	  
	  return v;
	  
	  }
	 
	 
	
	//이건 하나의 정보를 나타내주는거니까 여러값을 담을수있는 Vector을 안쓴다.
	
	public BoardBean getOneBoard(int num)
	{
		
		BoardBean bean=new BoardBean();
		getCon();
		
		try {
			
			
			//조회수 증가 쿼리
			
//			String readsql="update board2 set readcount=readcount+1 where num=?";
//			
//			pstmt=con.prepareStatement(readsql);
//			
//			pstmt.setInt(1, num);
//			pstmt.executeUpdate();
			
			//쿼리준비 
			String sql="select * from board where num=?";
			//
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);			
			rs=pstmt.executeQuery();		
			
			if(rs.next()) {
				
				bean.setNum(rs.getInt(1));
				bean.setWriter(rs.getString(2));
				bean.setEmail(rs.getString(3));
				bean.setSubject(rs.getString(4));
				bean.setPassword(rs.getString(5));
				bean.setReg_date(rs.getDate(6).toString());
				bean.setRef(rs.getInt(7));
				bean.setRe_step(rs.getInt(8));
				bean.setRe_level(rs.getInt(9));
				bean.setReadcount(rs.getInt(10));
				bean.setContent(rs.getString(11));
				
				
				
			}
			con.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return bean;
		
	}
	
	//답변글이 저장되는 메소드
	public void reWriteBoard(BoardBean bean) {
		//부모글 그룹과 글레벨 글스텝을 읽어드림
		int ref=bean.getRef();
		int re_step=bean.getRe_step();
		int re_level=bean.getRe_level();
		
		getCon();
		
		try {
			
		///////핵심 코드
		//부모 글보다 큰 re_level의 값을 전부 1씩 증가시켜줌.
		String levelsql="update board set re_level=re_level+1 where ref=? and re_level>?";
											//ref랑 같은그룹이고 re_level보다 큰 부모들
		pstmt=con.prepareStatement(levelsql);
		pstmt.setInt(1, ref);
		pstmt.setInt(2, re_level);
		
		pstmt.executeUpdate();
		//답변글 데이터를 저장
			
		String sql="insert into board values(board_seq.nextval,?,?,?,?,sysdate,?,?,?,0,?)";
			  
			  pstmt=con.prepareStatement(sql); 
			  pstmt.setString(1, bean.getWriter());
			  pstmt.setString(2, bean.getEmail()); 
			  pstmt.setString(3, bean.getSubject());
			  pstmt.setString(4, bean.getPassword()); 
			  pstmt.setInt(5, ref); 
			  pstmt.setInt(6, re_step+1); 
			  pstmt.setInt(7, re_level+1); 
			  pstmt.setString(8,bean.getContent()); 
			  
		pstmt.executeUpdate();
		
		con.close();
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public BoardBean getOneUpdateBoard(int num)
	{
		
		BoardBean bean=new BoardBean();
		getCon();
		
		try {
			
			
			//조회수 증가 쿼리
			
			String readsql="update board set readcount=readcount+1 where num=?";
			
			pstmt=con.prepareStatement(readsql);
			
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
			
			//쿼리준비 
			String sql="select * from board where num=?";
			//
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);			
			rs=pstmt.executeQuery();		
			
			if(rs.next()) {
				
				bean.setNum(rs.getInt(1));
				bean.setWriter(rs.getString(2));
				bean.setEmail(rs.getString(3));
				bean.setSubject(rs.getString(4));
				bean.setPassword(rs.getString(5));
				bean.setReg_date(rs.getDate(6).toString());
				bean.setRef(rs.getInt(7));
				bean.setRe_step(rs.getInt(8));
				bean.setRe_level(rs.getInt(9));
				bean.setReadcount(rs.getInt(10));
				bean.setContent(rs.getString(11));
		
				
			}
			con.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return bean;
		
	}
	
	//그냥 원하는 패스워드를 들고오기
	public String getPass(int num) {
		String pass="";
		getCon();
		try {
			String sql="select password from board where num=?";
			//쿼리실행할 객체 선언
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs=pstmt.executeQuery();
			//
			
			if(rs.next()) {
				pass=rs.getString(1);
			}
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	
		return pass;
	}
	
	//수정하기 위한 DAO
	public void updateBoard(BoardBean bean) {
		getCon();
		try {
			
			String sql="update board set subject=?,content=? where num=?";
			pstmt=con.prepareStatement(sql);
			
			pstmt.setString(1, bean.getSubject());
			pstmt.setString(2, bean.getContent());
			pstmt.setInt(3, bean.getNum());
			pstmt.executeLargeUpdate();
			con.close();    
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public void deleteBoard(int num) {
		getCon();
		
		try {
			String sql="delete from board where num=?";
			pstmt=con.prepareStatement(sql);
			
			pstmt.setInt(1, num);
			
			pstmt.executeUpdate();
			
			con.close();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	public int getAllCount() {
		getCon();
		
		int count=0;
		try {
			
			String sql="select count(*) from board";
			
			pstmt=con.prepareStatement(sql);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				count=rs.getInt(1);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return count;
	}
	
	
}
