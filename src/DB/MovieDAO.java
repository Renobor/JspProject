package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MovieDAO {
	
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	//Ŀ�ؼ� Ǯ�� �̿��� �����ͺ��̽� ����
	
	
	
	
	public void getCon() {
		//server.xml�� ����� jdbc/pool�� ���;��ϱ� ������ 
		//NamingException �� ����Ѵ�. jdbc/pool�� ���������ִٴ°�
		//����ϱ⶧��.. name="jdbc/pool" 
		
		try {
			Context initctx=new InitialContext();
			Context envctx=(Context) initctx.lookup("java:comp/env");
			DataSource ds=(DataSource)envctx.lookup("jdbc/pool");
			//DataSource
			con=ds.getConnection();
			//NamingException�� ����Ұ�� ���� Ŀ�ؼǵ� Ʈ������ġ�� �ʿ��ϱ⿡
			//NamingException�� ����� exception�� ����Ѵ�.
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	//�ֽż� 3���� �ڵ����� �����ϴ� �޼ҵ�
	
	public Vector<MovieListBean> getSelectMovie(){
		
		//����Ÿ���� �����ϵ����Ѵ�. VectorŸ��
		Vector<MovieListBean> v=new Vector<>();
		getCon();
		
		try{
			String sql="select * from movie order by no desc";
			pstmt=con.prepareStatement(sql);
			
			rs=pstmt.executeQuery();
			
			int count=0;
			
			while(rs.next()) {
				
				MovieListBean bean = new MovieListBean();
				bean.setNo(rs.getInt(1));
				bean.setName(rs.getString(2));
				bean.setCategory(rs.getInt(3));
				bean.setPrice(rs.getInt(4));
				bean.setUsepeople(rs.getInt(5));
				bean.setImg(rs.getString(6));
				bean.setInfo(rs.getString(7));
				
				v.add(bean);
				count++;
				if(count >2)
				//4�ɶ� ���������ϱ� =���� 
				//3���� ���Ϳ�����
					
					break;
				
			}
			
			con.close();
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return v;
	}

	//ī�װ��� �ش��ϴ� ��������� �����ϴ� �޼ҵ�
	public Vector<MovieListBean> getCategory(int cate){
		
		Vector<MovieListBean> v = new Vector<>();
		
		MovieListBean bean=null;
		getCon();
		
		try {
			String sql="select * from movie where category=?";
			
			pstmt=con.prepareStatement(sql);
			
			pstmt.setInt(1, cate);
			
			rs=pstmt.executeQuery();
			
			
			
		while(rs.next()) {
			 bean=new MovieListBean();
			 //bean�� CarListBean���� ����
			 
				bean.setNo(rs.getInt(1));
				bean.setName(rs.getString(2));
				bean.setCategory(rs.getInt(3));
				bean.setPrice(rs.getInt(4));
				bean.setUsepeople(rs.getInt(5));
				bean.setImg(rs.getString(6));
				bean.setInfo(rs.getString(7));
				
				v.add(bean);
			 
			 
		}
		con.close();
		}catch (Exception e) {
			e.printStackTrace();// TODO: handle exception
		}
		return v;
	}
	
	//��������� �����ϴ� �޼ҵ�
	public Vector<MovieListBean> getAllMovie(){
		Vector<MovieListBean> v = new Vector<>();
		
		MovieListBean bean=null;
		getCon();
		
		try {
			String sql="select * from movie";
			pstmt=con.prepareStatement(sql);
				
			
//			pstmt.setInt(1, cate); //���� ���ǹ��� �����ϱ� �Ķ���� �ȹ޾ƿ͵� �Ǵ� 
									//�̷��� �Ⱦ���.
			
			rs=pstmt.executeQuery();
			
			
			
		while(rs.next()) {
			 	bean=new MovieListBean();
			 	//bean�� CarListBean���� ����
			 
				bean.setNo(rs.getInt(1));
				bean.setName(rs.getString(2));
				bean.setCategory(rs.getInt(3));
				bean.setPrice(rs.getInt(4));
				bean.setUsepeople(rs.getInt(5));
				bean.setImg(rs.getString(6));
				bean.setInfo(rs.getString(7));
				
				v.add(bean);
			 
			 
		}
		con.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return v;
		
		
		
	}
	
	//�̰� �ϳ��� ������ �����ϴ� �޼ҵ�� �׷��� vector�� �Ƚᵵ�ȴ�.
	public MovieListBean getOneMovie(int no) {
		
		MovieListBean bean=new MovieListBean();
		getCon();
		
		try {
			String sql="select * from movie where no=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			rs=pstmt.executeQuery();
			
		if(rs.next()) {
			 
				bean.setNo(rs.getInt(1));
				bean.setName(rs.getString(2));
				bean.setCategory(rs.getInt(3));
				bean.setPrice(rs.getInt(4));
				bean.setUsepeople(rs.getInt(5));
				bean.setImg(rs.getString(6));
				bean.setInfo(rs.getString(7));
				
		}
			con.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	
		return bean;
	}
	
	
	
	
	public int getMember(String id,String pass1) {
		
		int result=0;
		
		getCon();
		
		try {
			
			String sql="select count(*) from member where id=? and pass1=?";
			
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pass1);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				result=rs.getInt(1);
			}
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
		
		
	}
	
	
	//�ϳ��� ���� ������ �����ϴ� �޼ҵ�
	public void setReserveMovie(MovieReserveBean bean) {
		
		
		getCon();
		try {
			
			String sql="insert into Moviereserve values(reserve_seq.NEXTVAL,?,?,?,?,?,?,?,?,?)";
			pstmt =con.prepareStatement(sql);
			pstmt.setInt(1,bean.getNo());
			pstmt.setString(2, bean.getId());
			pstmt.setInt(3, bean.getQty());
			pstmt.setInt(4,bean.getSel());
			pstmt.setString(5, bean.getRday());
			pstmt.setInt(6, bean.getPop());
			pstmt.setInt(7, bean.getChiken());
			pstmt.setInt(8, bean.getCola());
			pstmt.setInt(9, bean.getCouple());
			
			
			pstmt.executeUpdate();
			
			con.close();
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	 public void MovieRemoveReserve(String id,String rday)
	 
	 {
	        getCon();
	 
	        try {
	        	
	      String sql = "delete from Moviereserve where id=? and rday=?";
	      pstmt = con.prepareStatement(sql);
	      // ?�� ���� �ֱ�
	            
	      pstmt.setString(1, id);
	      pstmt.setString(2, rday);
	      System.out.println("�����Ϸ�");
	      System.out.println(id);
	      System.out.println(rday);
	      pstmt.executeUpdate();
	 
	      con.close();
	 
	      } catch (Exception e) {
	      e.printStackTrace();
	        }
	 
	    }
	 
	 
	
	
	
	
	public Vector<MovieViewBean> getAllReserve(String id) {
		
		Vector<MovieViewBean> v = new Vector<>();
		MovieViewBean bean=null;
		
		
		getCon();
		
		try {
			
			String sql="select * from movie natural join MovieReserve"
					+ " where sysdate < to_date(rday,'YYYY-MM-DD')and id=?";
					
			pstmt=con.prepareStatement(sql);		
			//?
			pstmt.setString(1, id);
			//��� ����
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				bean=new MovieViewBean();
				bean.setName(rs.getString(2));
				bean.setPrice(rs.getInt(4));
				bean.setImg(rs.getString(6));
				bean.setQty(rs.getInt(10));
				bean.setRday(rs.getString(12));
				bean.setPop(rs.getInt(13));
				bean.setCola(rs.getInt(14));
				bean.setChiken(rs.getInt(15));
				bean.setCoupleset(rs.getInt(16));
				
				//��Ŭ������ ���Ϳ� �����Ѵ�.
				v.add(bean);
				
			}
			con.close();
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return v;
	}
	
}
