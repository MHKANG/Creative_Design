package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import jdbc.Consume_list_table.consume_listdata;

import jdbc.Consume_list_table.count_brandlist_data;
import jdbc.Item_table.item_listdata;
import jdbc.Monthly_total_consume_table.month_listdata;

public class Preference_brand_table {

	static Connection conn = null;
	static ResultSet rs = null;
	static Statement stmt = null;
	
	public Preference_brand_table(){
		
	}
	public static void connect_DB(){
		
		
		String url = null;
		String id = "";
		String pw = "";
		
		try
		{
			
			Class.forName("oracle.jdbc.driver.OracleDriver");			
			//System.out.println("driver load sucess");
		
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
	//선호 테이블에 데이터를 삽입하는 함수 이다.
	public static void insert_data(String month, String itype){
		stmt = null;
		conn = null;
		rs = null;
		connect_DB();
		
		try{
		stmt = conn.createStatement();
		String query = "insert into PREFERENCE_BRAND(MONTH, ITEMTYPE) values('"+ month+"','"+itype+"')";
		stmt.executeQuery(query);
		//System.out.println("삽입이 성공적으로 이루어졌습니다.");
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			if(conn != null)try{conn.close();}catch(SQLException e){}
			if(stmt != null)try{stmt.close();}catch(SQLException e){}
		}
	}
	public static class p_brandinfo{
		String m_id;
		String m_bname1;
		String m_bname2;
		String m_bname3;
		String m_btype;
		
		public p_brandinfo(){
			m_id = null;
			m_bname1 = null;
			m_bname2 = null;
			m_bname3 = null;
			m_btype = null;
			
			
		}
		public String get_id(){
			return m_id;
		}
		public String get_bname1()
		{
			return m_bname1;
		}
		public String get_bname2()
		{
			return m_bname2;
		}
		public String get_bname3()
		{
			return m_bname3;
		}
		public String get_btype()
		{
			return m_btype;
		}
		
		public void set_id(String _id){
			this.m_id = _id;
		}
		public void set_bname1(String _name1){
			this.m_bname1 = _name1;
		}
		public void set_bname2(String _name2){
			this.m_bname2 = _name2;
		}
		public void set_bname3(String _name3){
			this.m_bname3 = _name3;
		}
		public void set_btype(String _type){
			this.m_btype = _type;
		}
		
		
	}
	public static class p_brandlistdata{
		String m_user_id;
		String m_month;
		String m_itype;
		public p_brandlistdata(){
			m_month = null;
			m_itype = null;
		}
		public String get_month()
		{
			return m_month;
		}
		public String get_itype()
		{
			return m_itype;
		}
		public void set_month(String _month){
			this.m_month = _month;
		}
		public void set_itype(String _itype){
			this.m_itype = _itype;
		}
	}
	public static void insert_pdata(String id, String bname1, String bname2,  String bname3, String itype){
		stmt = null;
		conn = null;
		rs = null;
		connect_DB();
		try{
			stmt = conn.createStatement();
			String query = "insert into prference_brand(USERID, BRANDNAME1, BRANDNAME2, BRANDNAME3, BRANDTYPE) VALUES('"+id+"','"+bname1+"','"+bname2+"','"+bname3+"','"+itype+"'";;
			rs = stmt.executeQuery(query);
			
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				if(conn != null)try{conn.close();}catch(SQLException e){}
				if(stmt != null)try{stmt.close();}catch(SQLException e){}
				if(rs != null)try{rs.close();}catch(SQLException e){}
			}
	}
	//원 정보를 통해 데이터를 검색하는 함수 이다.
	public static ArrayList<p_brandlistdata> select_pdata_month(String _month){
		stmt = null;
		conn = null;
		rs = null;
		
		
		ArrayList<p_brandlistdata> data = new ArrayList<p_brandlistdata>();
		
		connect_DB();
		try{
		stmt = conn.createStatement();
		String query = "select MONTH, ITEMTYPE from PREFERENCE_BRAND WHERE MONTH LIKE '"+_month+"'";
		rs = stmt.executeQuery(query);
		int count = 0;
			while(rs.next()){
				p_brandlistdata temp = new p_brandlistdata();
				String t_month = rs.getString("MONTH");
				String t_itype= rs.getString("ITEMTYPE");
				
				if(t_month.equals(_month))
				{
					temp.set_month(t_month);
					temp.set_itype(t_itype);
					data.add(count,temp);
				}
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
	public static ArrayList<item_listdata> select_perferece_item_high(String _bname, int _cbudget ){
		stmt = null;
		conn = null;
		rs = null;
		Item_table it = new Item_table();
		ArrayList<item_listdata> itemp = new ArrayList<item_listdata>();
		ArrayList<item_listdata> ptemp = new ArrayList<item_listdata>();
		itemp = it.select_data_Brand_high(_bname, _cbudget);
		
		for(int i = 0; i < 3; i++)
		{
			ptemp.add(i, itemp.get(i));
		}
		return ptemp;
		
	}
	public static ArrayList<item_listdata> select_perferece_item_low(String _bname, int _cbudget ){
		stmt = null;
		conn = null;
		rs = null;
		Item_table it = new Item_table();
		ArrayList<item_listdata> itemp = new ArrayList<item_listdata>();
		ArrayList<item_listdata> ptemp = new ArrayList<item_listdata>();
		itemp = it.select_data_Brand_high(_bname, _cbudget);
		
		for(int i = 0; i < 3; i++)
		{
			ptemp.add(i, itemp.get(i));
		}
		return ptemp;
		
	}
	
	
	
	//월 정보를 통해 아이템의 타입을 변경하는 함수이다.
	public static void update_pmonth_data(String _itype, String _month){
		stmt = null;
		conn = null;
		rs = null;
		connect_DB();
		try{
			stmt = conn.createStatement();
			String query = "update PREFERENCE_BRAND set ITEMTYPE = '"+_itype +"' WHERE MONTH = '"+_month +"'";
			stmt.executeQuery(query);
			
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				if(conn != null)try{conn.close();}catch(SQLException e){}
				if(stmt != null)try{stmt.close();}catch(SQLException e){}
				
			}
		
	}

}
