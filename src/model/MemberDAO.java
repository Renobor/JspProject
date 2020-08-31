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
	//ȸ��  �ѻ���� ���� ������ �����ϴ� �޼ҵ�
	public void insertMember(MemberBean bean) {
		
		getCon();
		try {
			String sql="insert into member values(?,?,?,?,?,?,?,?)";
			
			//���� ������ ��ü ����
			pstmt=con.prepareStatement(sql);
			//?�� ����
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
		    
	 
	        String dbpw = ""; // DB���� ��й�ȣ�� ��Ƶ� ����
	        int x = -1;
	 
	        try {
	            // ��й�ȣ ��ȸ
	            StringBuffer query1 = new StringBuffer();
	            query1.append("SELECT pass1 from member WHERE ID=?");
	 
	            // ȸ�� ����
	            StringBuffer query2 = new StringBuffer();
	            query2.append("DELETE FROM member WHERE ID=?");
	 
	 
	            // �ڵ� Ŀ���� false�� �Ѵ�.
	            con.setAutoCommit(false);
	            
	            // 1. ���̵� �ش��ϴ� ��й�ȣ�� ��ȸ�Ѵ�.
	            pstmt = con.prepareStatement(query1.toString());
	            pstmt.setString(1, id);
	            rs = pstmt.executeQuery();
	 
	            if (rs.next()) 
	            {
	                dbpw = rs.getString("pass1");
	                if (dbpw.equals(pass1)) // �Էµ� ��й�ȣ�� DB��� ��
	                {
	                    // ������� ȸ������ ����
	                    pstmt = con.prepareStatement(query2.toString());
	                    pstmt.setString(1, id);
	                    pstmt.executeUpdate();
	                    con.commit(); 
	                    x = 1; // ���� ����
	                } else {
	                    x = 0; // ��й�ȣ �񱳰�� - �ٸ�
	                }
	            }
	 
	            return x;
	 
	        } catch (Exception sqle) {
	            try {
	                con.rollback(); // ������ �ѹ�
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
        //�ڿ�����
        if(rs !=null) try{rs.close();}catch(SQLException sqle){}
        if(pstmt !=null) try{pstmt.close();}catch(SQLException sqle){}
        //Ŀ�ؼ� Ǯ�� �ݳ�
        if(conn !=null) try{conn.close();}catch(SQLException sqle){}
   }


	//��� ȸ���� ������ �����ϴ� �޼ҵ� �ۼ�
	public Vector<MemberBean> getAllMember(){
		
		
		//����Ÿ�� ����
		Vector<MemberBean> v=new Vector<>();
		
		getCon();
		try {
			
			String sql="select * from member";
			//���� ��ü �غ�
			pstmt=con.prepareStatement(sql);
			
			//���� ������ ��� ����
			rs=pstmt.executeQuery();
			//�ݺ����� ���鼭 ȸ�� ������ ����
			
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
				
				//���� ��Ŭ���� ����
				
				v.add(bean);
				
			}
				con.close();
			
		}catch(Exception e) {
			
			e.printStackTrace();
		}
		return v;
		
	}
	
}
