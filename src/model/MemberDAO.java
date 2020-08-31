package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {
	
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
	//회원  한사람에 대한 정보를 저장하는 메소드
	public void insertMember(MemberBean bean) {
		
		getCon();
		try {
			String sql="insert into member values(?,?,?,?,?,?,?,?)";
			
			//쿼리 실행할 객체 선언
			pstmt=con.prepareStatement(sql);
			//?에 삽입
			pstmt.setString(1, bean.getId());
			pstmt.setString(2, bean.getPass1());
			pstmt.setString(3, bean.getEmail());
			pstmt.setString(4, bean.getTel());
			pstmt.setString(5, bean.getHobby());
			pstmt.setString(6, bean.getJob());
			pstmt.setString(7, bean.getAge());
			pstmt.setString(8, bean.getInfo());
		
			
			pstmt.executeUpdate();
			con.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	    public int deleteMember(String id, String pass1) 
	    {
		    getCon();
		    
	 
	        String dbpw = ""; // DB상의 비밀번호를 담아둘 변수
	        int x = -1;
	 
	        try {
	            // 비밀번호 조회
	            StringBuffer query1 = new StringBuffer();
	            query1.append("SELECT pass1 from member WHERE ID=?");
	 
	            // 회원 삭제
	            StringBuffer query2 = new StringBuffer();
	            query2.append("DELETE FROM member WHERE ID=?");
	 
	 
	            // 자동 커밋을 false로 한다.
	            con.setAutoCommit(false);
	            
	            // 1. 아이디에 해당하는 비밀번호를 조회한다.
	            pstmt = con.prepareStatement(query1.toString());
	            pstmt.setString(1, id);
	            rs = pstmt.executeQuery();
	 
	            if (rs.next()) 
	            {
	                dbpw = rs.getString("pass1");
	                if (dbpw.equals(pass1)) // 입력된 비밀번호와 DB비번 비교
	                {
	                    // 같을경우 회원삭제 진행
	                    pstmt = con.prepareStatement(query2.toString());
	                    pstmt.setString(1, id);
	                    pstmt.executeUpdate();
	                    con.commit(); 
	                    x = 1; // 삭제 성공
	                } else {
	                    x = 0; // 비밀번호 비교결과 - 다름
	                }
	            }
	 
	            return x;
	 
	        } catch (Exception sqle) {
	            try {
	                con.rollback(); // 오류시 롤백
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	            throw new RuntimeException(sqle.getMessage());
	        } finally {
	            try{
	                if ( pstmt != null ){ pstmt.close(); pstmt=null; }
	                if ( con != null ){ con.close(); con=null;    }
	            }catch(Exception e){
	                throw new RuntimeException(e.getMessage());
	            }
	        }
	    } 


    

	 
		
	
	
	public void execClose(ResultSet rs, PreparedStatement pstmt, Connection conn)throws Exception{
        //자원정리
        if(rs !=null) try{rs.close();}catch(SQLException sqle){}
        if(pstmt !=null) try{pstmt.close();}catch(SQLException sqle){}
        //커넥션 풀로 반납
        if(conn !=null) try{conn.close();}catch(SQLException sqle){}
   }


	//모든 회원의 정보를 리턴하는 메소드 작성
	public Vector<MemberBean> getAllMember(){
		
		
		//리턴타입 선언
		Vector<MemberBean> v=new Vector<>();
		
		getCon();
		try {
			
			String sql="select * from member";
			//쿼리 객체 준비
			pstmt=con.prepareStatement(sql);
			
			//쿼리 실행후 결과 리턴
			rs=pstmt.executeQuery();
			//반복문을 돌면서 회원 정보를 저장
			
			while(rs.next()) {
				MemberBean bean=new MemberBean();
				bean.setId(rs.getString(1));
				bean.setPass1(rs.getString(2));
				bean.setEmail(rs.getString(3));
				bean.setTel(rs.getString(4));
				bean.setHobby(rs.getString(5));
				bean.setJob(rs.getString(6));
				bean.setAge(rs.getString(7));
				bean.setInfo(rs.getString(8));
				
				//벡터 빈클래스 저장
				
				v.add(bean);
				
			}
				con.close();
			
		}catch(Exception e) {
			
			e.printStackTrace();
		}
		return v;
		
	}
	
}
