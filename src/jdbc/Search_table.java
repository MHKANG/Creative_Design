package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import jdbc.Consume_list_table.count_brandlist_data;
import jdbc.Item_table.item_listdata;

public class Search_table {

	static Connection conn = null;
	static ResultSet rs = null;
	static Statement stmt = null;
	
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
	public static void insert_data(String u_id, String itype){
		stmt = null;
		conn = null;
		rs = null;
		connect_DB();
		
		try{
		stmt = conn.createStatement();
		String query = "insert into SEARCH(USERID, ITEMTYPE) values('"+ u_id+"','"+itype+"')";
		stmt.executeQuery(query);
		//System.out.println("삽입이 성공적으로 이루어졌습니다.");
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			if(conn != null)try{conn.close();}catch(SQLException e){}
			if(stmt != null)try{stmt.close();}catch(SQLException e){}
		}
	}
	public static class S_brandlistdata{
		String m_id;
		String m_iname;
		String m_itype;
		int m_price;
		public S_brandlistdata(){
			m_id = null;
			m_itype = null;
		}
		public String get_id()
		{
			return m_id;
		}
		public String get_itype()
		{
			return m_itype;
		}
		public void set_id(String _id){
			this.m_id = _id;
		}
		public void set_itype(String _itype){
			this.m_itype = _itype;
		}
	}
	public static ArrayList<item_listdata> highest_three(int _cb){
		ArrayList<item_listdata> data = new ArrayList<item_listdata>();
		Item_table it = new Item_table();
		ArrayList<item_listdata> itemp = new ArrayList<item_listdata>();
		ArrayList<count_brandlist_data> ptemp = new ArrayList<count_brandlist_data>();
		itemp = it.select_data_downPriceInfo_high(_cb);
	
		for(int i = 0; i < 3; i++)
		{
			data.add(i, itemp.get(i));
		}
		return data;
	}
	public static ArrayList<item_listdata> lowest_three(int _cb){
		ArrayList<item_listdata> data = new ArrayList<item_listdata>();
		Item_table it = new Item_table();
		ArrayList<item_listdata> itemp = new ArrayList<item_listdata>();
		ArrayList<count_brandlist_data> ptemp = new ArrayList<count_brandlist_data>();
		itemp = it.select_data_downPriceInfo_low(_cb);
		for(int i = 0; i < 3; i++)
		{
			data.add(i, itemp.get(i));
		}
		return data;
	}
}
