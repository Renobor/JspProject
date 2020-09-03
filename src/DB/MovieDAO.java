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
	
	//커넥션 풀을 이용한 데이터베이스 연결
	
	
	
	
	public void getCon() {
		//server.xml에 등록한 jdbc/pool을 들고와야하기 때문에 
		//NamingException 을 사용한다. jdbc/pool이 없을수도있다는걸
		//명시하기때문.. name="jdbc/pool" 
		
		try {
			Context initctx=new InitialContext();
			Context envctx=(Context) initctx.lookup("java:comp/env");
			DataSource ds=(DataSource)envctx.lookup("jdbc/pool");
			//DataSource
			con=ds.getConnection();
			//NamingException을 사용할경우 위에 커넥션도 트라이케치가 필요하기에
			//NamingException을 지우고 exception을 사용한다.
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	//최신순 3대의 자동차를 리턴하는 메소드
	
	public Vector<MovieListBean> getSelectMovie(){
		
		//리턴타입을 설정하도록한다. Vector타입
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
				//4될때 빠져나가니까 =뺀다 
				//3개만 벡터에저장
					
					break;
				
			}
			
			con.close();
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return v;
	}

	//카테고리에 해당하는 모든정보를 리턴하는 메소드
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
			 //bean을 CarListBean으로 정의
			 
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
	
	//모든정보를 리턴하는 메소드
	public Vector<MovieListBean> getAllMovie(){
		Vector<MovieListBean> v = new Vector<>();
		
		MovieListBean bean=null;
		getCon();
		
		try {
			String sql="select * from movie";
			pstmt=con.prepareStatement(sql);
				
			
//			pstmt.setInt(1, cate); //여긴 조건문이 없으니까 파라미터 안받아와도 되니 
									//이런건 안쓴다.
			
			rs=pstmt.executeQuery();
			
			
			
		while(rs.next()) {
			 	bean=new MovieListBean();
			 	//bean을 CarListBean으로 정의
			 
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
	
	//이건 하나의 정보를 리턴하는 메소드다 그래서 vector를 안써도된다.
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
	
	
	//하나의 예약 정보를 저장하는 메소드
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
	      // ?에 값을 넣기
	            
	      pstmt.setString(1, id);
	      pstmt.setString(2, rday);
	      System.out.println("삭제완료");
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
			//결과 리턴
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
				
				//빈클래스를 벡터에 저장한다.
				v.add(bean);
				
			}
			con.close();
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return v;
	}
	
}
