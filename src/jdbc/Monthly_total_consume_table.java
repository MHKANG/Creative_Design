package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import jdbc.Consume_list_table.consume_listdata;

public class Monthly_total_consume_table {

	static Connection conn = null;
	static ResultSet rs = null;
	static Statement stmt = null;
	
	public Monthly_total_consume_table(){
		
	}
	//데이터 베이스 연결 함수
	public static void connect_DB(){
		
		String url = null;
		String id = "";
		String pw = "";
		
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");			
			//System.out.println("driver load success");
		
			try{
			url = "jdbc:oracle:thin:@localhost:1521:orcl";
			//System.out.println("db connect success");
			conn = DriverManager.getConnection(url, id, pw);		
			}
			catch(SQLException e){
				System.out.println(e);
			
			}
		}
		catch(ClassNotFoundException e)
		{
			System.out.println(e);
		
		}
	}
	//*
	//월간 소비에 추가 금액을 추가 하는 함수
	public static void add_data(String _id, int year, int month, int add_consume){
		stmt = null;
		conn = null;
		rs = null;
		ArrayList<month_listdata> temp = new ArrayList<month_listdata>();
		//temp는 해당 월의 월간 소비를 가지고 있는 임시 객체이다.
		temp = select_data_month(_id,year, month);
		System.out.println(temp.get(0).get_tconsume()+" "+ add_consume);
		//가지고 있는 값은 하나일 것임으로 0번째 항에 다가 추가할 금액을 넣는다.
		int total_consume = 0 ;
		total_consume = temp.get(0).get_tconsume()+ add_consume;
		
		//System.out.println(temp.size());
		//System.out.println(total_consume);
		connect_DB();
		try{
		stmt = conn.createStatement();
		
		//추가된 토탈 금액으로  해당 month에 있는 total consume 값을 설정한다.
		String query = "UPDATE MONTHLY_TOTAL_CONSUME SET TOTALCONSUME = "
				+total_consume +"WHERE MONTH = "+month+" and USERID like '"+_id+"' and YEAR = "+year+"";
		stmt.executeQuery(query);
		
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			if(conn != null)try{conn.close();}catch(SQLException e){}
			if(stmt != null)try{stmt.close();}catch(SQLException e){}
		}
	}
	public static class month_listdata{
		String m_id;
		int m_month;
		int m_tconsume;
		int m_year;
		int m_nb;
		int m_sb;
		public month_listdata(){
			m_id = null;
			m_month = 0;
			m_tconsume = 0;
			m_year = 0;
			m_sb = 0;
			m_nb = 0;
		}
		public String get_id(){
			return m_id;
		}
		public int get_month()
		{
			return m_month;
		}
		public int get_year(){
			return m_year;
		}
		public int get_tconsume()
		{
			return m_tconsume;
		}
		public int get_nb()
		{
			return m_nb;
		}
		public int get_sb(){
			return m_sb;
		}
		public void set_id(String _id){
			this.m_id = _id;
		}
		public void set_month(int _month){
			this.m_month = _month;
		}
		public void set_year(int _year){
			this.m_year = _year;
		}
		public void set_tconsume(int _tconsume){
			this.m_tconsume = _tconsume;
		}
		public void set_nb(int _nb){
			this.m_nb = _nb;
		}
		public void set_sb(int _sb){
			this.m_sb = _sb;
		}
	}
	//*
	public static void create_month_data(String u_id, int _year, int _month){
		stmt = null;
		conn = null;
		rs = null;
		connect_DB();
		try{
			stmt = conn.createStatement();
			String query = "insert into MONTHLY_TOTAL_CONSUME(USERID, MONTH, TOTALCONSUME, YEAR, NECESSARYBUDGET, SUBSIDIARYBUDGET)"
					+ "values('"+u_id+"',"+_month+",0,'"+_year+"',0,0)";
			stmt.executeQuery(query);
			System.out.println("삽입이 성공적으로 이루어졌습니다.");
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				if(conn != null)try{conn.close();}catch(SQLException e){}
				if(stmt != null)try{stmt.close();}catch(SQLException e){}
			}
		
	}
	public static ArrayList<month_listdata> select_data_byidPlusyear(String _id, int year){
		stmt = null;
		conn = null;
		rs = null;
		
		ArrayList<month_listdata> data = new ArrayList<month_listdata>();
		
		connect_DB();
		try{
		stmt = conn.createStatement();
		String query = "select * from MONTHLY_TOTAL_CONSUME WHERE USERID LIKE '"+_id+"' and year <="+year
				+"order by year desc, month desc";
		rs = stmt.executeQuery(query);
		int count = 0;
			while(rs.next()){
				month_listdata temp = new month_listdata();
				
				int t_month = rs.getInt("MONTH");
				int t_tconsume= rs.getInt("TOTALCONSUME");
				String t_id = rs.getString("USERID");
				int t_year = rs.getInt("YEAR");
				int t_nb = rs.getInt("NECESSARYBUDGET");
				int t_sb = rs.getInt("SUBSIDIARYBUDGET");
				
				temp.set_id(t_id);
				temp.set_month(t_month);
				temp.set_tconsume(t_tconsume);
				temp.set_year(t_year);
				temp.set_nb(t_nb);
				temp.set_sb(t_sb);
				data.add(count,temp);
				
				temp = null;
				count++;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			if(conn != null)try{conn.close();}catch(SQLException e){}
			if(stmt != null)try{stmt.close();}catch(SQLException e){}
			if(rs != null)try{rs.close();}catch(SQLException e){}
		}
		return data;
	}
	public static ArrayList<month_listdata> select_data_byid(String _id){
		stmt = null;
		conn = null;
		rs = null;
		
		ArrayList<month_listdata> data = new ArrayList<month_listdata>();
		
		connect_DB();
		try{
		stmt = conn.createStatement();
		String query = "select * from MONTHLY_TOTAL_CONSUME WHERE USERID LIKE '"+_id+"'";
		rs = stmt.executeQuery(query);
		int count = 0;
			while(rs.next()){
				month_listdata temp = new month_listdata();
				
				int t_month = rs.getInt("MONTH");
				int t_tconsume= rs.getInt("TOTALCONSUME");
				String t_id = rs.getString("USERID");
				int t_year = rs.getInt("YEAR");
				int t_nb = rs.getInt("NECESSARYBUDGET");
				int t_sb = rs.getInt("SUBSIDIARYBUDGET");
				
				temp.set_id(t_id);
				temp.set_month(t_month);
				temp.set_tconsume(t_tconsume);
				temp.set_year(t_year);
				temp.set_nb(t_nb);
				temp.set_sb(t_sb);
				data.add(count,temp);
				
				temp = null;
				count++;
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			if(conn != null)try{conn.close();}catch(SQLException e){}
			if(stmt != null)try{stmt.close();}catch(SQLException e){}
			if(rs != null)try{rs.close();}catch(SQLException e){}
		}
		return data;
	}
	
	public static int cal_avg_totalconsume(String _id, int year){
		int result = 0;
		
		stmt = null;
		conn = null;
		rs = null;
		
		ArrayList<month_listdata> data = new ArrayList<month_listdata>();
		
		connect_DB();
		try{
		stmt = conn.createStatement();
		String query = "select TRUNC(AVG(TOTALCONSUME),2) AS AVG_TOTAL from MONTHLY_TOTAL_CONSUME WHERE USERID LIKE '"+_id+"' and YEAR = "+year+" GROUP BY USERID, YEAR";
		rs = stmt.executeQuery(query);
		int count = 0;
			while(rs.next()){
				result = rs.getInt("AVG_TOTAL");
				
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			if(conn != null)try{conn.close();}catch(SQLException e){}
			if(stmt != null)try{stmt.close();}catch(SQLException e){}
			if(rs != null)try{rs.close();}catch(SQLException e){}
		}
		return result;
	}
	public static int cal_avg_necessary(String _id){
		int result = 0;
		
		stmt = null;
		conn = null;
		rs = null;
		
		ArrayList<month_listdata> data = new ArrayList<month_listdata>();
		
		connect_DB();
		try{
		stmt = conn.createStatement();
		String query = "select TRUNC(AVG(NECESSARYBUDGET),0) AS AVG_TOTAL from MONTHLY_TOTAL_CONSUME WHERE USERID LIKE '"+_id+"'GROUP BY USERID, YEAR";
		rs = stmt.executeQuery(query);
		int count = 0;
			while(rs.next()){
				result = rs.getInt("AVG_TOTAL");
				
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			if(conn != null)try{conn.close();}catch(SQLException e){}
			if(stmt != null)try{stmt.close();}catch(SQLException e){}
			if(rs != null)try{rs.close();}catch(SQLException e){}
		}
		return result;
	}
	public static int cal_avg_subsidiary(String _id){
		int result = 0;
		
		stmt = null;
		conn = null;
		rs = null;
		
		ArrayList<month_listdata> data = new ArrayList<month_listdata>();
		
		connect_DB();
		try{
		stmt = conn.createStatement();
		String query = "select TRUNC(AVG(SUBSIDIARYBUDGET),0) AS AVG_TOTAL from MONTHLY_TOTAL_CONSUME WHERE USERID LIKE '"+_id+"' GROUP BY USERID, YEAR";
		rs = stmt.executeQuery(query);
		int count = 0;
			while(rs.next()){
				result = rs.getInt("AVG_TOTAL");
				
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			if(conn != null)try{conn.close();}catch(SQLException e){}
			if(stmt != null)try{stmt.close();}catch(SQLException e){}
			if(rs != null)try{rs.close();}catch(SQLException e){}
		}
		return result;
	}
	//*
	//월간 데이터 정보를 월 별로 검색 하는 함수 이다.
	public static ArrayList<month_listdata> select_data_month(String _id, int _year, int _month){
		stmt = null;
		conn = null;
		rs = null;
		
		ArrayList<month_listdata> data = new ArrayList<month_listdata>();
		
		connect_DB();
		try{
		stmt = conn.createStatement();
		String query = "select * from MONTHLY_TOTAL_CONSUME WHERE MONTH = "+_month+" and USERID LIKE '"+_id+"' and YEAR = "+_year+"";
		rs = stmt.executeQuery(query);
		int count = 0;
			while(rs.next()){
				month_listdata temp = new month_listdata();
				
				int t_month = rs.getInt("MONTH");
				int t_tconsume= rs.getInt("TOTALCONSUME");
				String t_id = rs.getString("USERID");
				int t_year = rs.getInt("YEAR");
				int t_nb = rs.getInt("NECESSARYBUDGET");
				int t_sb = rs.getInt("SUBSIDIARYBUDGET");
				
				System.out.println("ok7");
				temp.set_id(t_id);
				temp.set_month(t_month);
				temp.set_tconsume(t_tconsume);
				temp.set_year(t_year);
				temp.set_nb(t_nb);
				temp.set_sb(t_sb);
				data.add(count,temp);
				
				temp = null;
				count++;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			if(conn != null)try{conn.close();}catch(SQLException e){}
			if(stmt != null)try{stmt.close();}catch(SQLException e){}
			if(rs != null)try{rs.close();}catch(SQLException e){}
		}
		return data;
	}
	//월간 소비 금액을 변경하는 함수이다.
	public static void update_month_necessary(String _id,int _year, int _month, int _necessary){
		stmt = null;
		conn = null;
		rs = null;
		connect_DB();
		try{
			stmt = conn.createStatement();
			String query = "update MONTHLY_TOTAL_CONSUME set NECESSARYBUDGET = '"+_necessary +"' WHERE MONTH = "+_month +" and USERID LIKE '"+_id+"' and YEAR = "+_year+"";
			stmt.executeQuery(query);
			
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				if(conn != null)try{conn.close();}catch(SQLException e){}
				if(stmt != null)try{stmt.close();}catch(SQLException e){}
				
			}
		
	}
	public static void update_month_subsidiary(String _id,int _year, int _month, int _subsidiary){
		stmt = null;
		conn = null;
		rs = null;
		connect_DB();
		try{
			stmt = conn.createStatement();
			String query = "update MONTHLY_TOTAL_CONSUME set SUBSIDIARYBUDGET = "+_subsidiary +" WHERE MONTH = "+_month +" and USERID LIKE '"+_id+"' and YEAR = "+_year+"";
			stmt.executeQuery(query);
			
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				if(conn != null)try{conn.close();}catch(SQLException e){}
				if(stmt != null)try{stmt.close();}catch(SQLException e){}
				
			}
		
	}
	
}
