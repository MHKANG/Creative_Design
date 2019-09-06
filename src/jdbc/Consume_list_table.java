package jdbc;

import java.sql.*;
import java.util.*;

import jdbc.Brand_list_table.brand_listdata;
import jdbc.Consume_list_table.count_brandlist_data;

public class Consume_list_table {

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
	public static void insert_data(String u_id, String time, String _itype, String _bname, String price, String budgettype, String itemname){
		stmt = null;
		conn = null;
		rs = null;
		connect_DB();
		String [] temp ;
		String delimiter = "원";
		temp = price.split(delimiter);
		try{
		stmt = conn.createStatement();
		String query = "insert into CONSUME_LIST(USERID, TIMEINFO, ITEMTYPE, BRANDNAME, PRICE, BUDGETTYPE, ITEMNAME)"
				+ "values('"+u_id+"',to_timestamp('"+time+"','yyyy-mm-dd HH24:MI:SS'),'"+_itype+"','"+_bname+"','"+Integer.parseInt(temp[0])+"', '"+budgettype+"','"+itemname+"')";
		stmt.executeQuery(query);
		//System.out.println("삽입이 성공적으로 이루어졌습니다.");
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			if(conn != null)try{conn.close();}catch(SQLException e){}
			if(stmt != null)try{stmt.close();}catch(SQLException e){}
		}
	}
	//소비 리스트를 표현하기 위한 단위 class 생성
	public static class consume_listdata{
		String u_id; // 유저 아이디
		String t_info; // 소비 시간 표현
		String i_type; // 아이템 타입
		String b_name; // 브랜드 이름
		int price;
		String b_type; //필요 지출인지 부가 지출인지 구분
		String i_name;
		public consume_listdata(){
			u_id = null;
			t_info = null;
			i_type = null;
			b_name = null;
			price = 0;
			b_type = null;
			i_name = null;
		}
		public String get_id()
		{
			return u_id;
		}
		public String get_time()
		{
			return t_info;
		}
		public String get_type()
		{
			return i_type;
		}
		public String get_brand()
		{
			return b_name;
		}
		public int get_price()
		{
			return price;
		}
		public String get_btype()
		{
			return b_type;
		}
		public String get_iname()
		{
			return i_name;
		}
		public void set_id(String _id){
			this.u_id = _id;
		}
		public void set_time(String _info){
			this.t_info = _info;
		}
		public void set_type(String _type){
			this.i_type = _type;
		}
		public void set_brand(String _bname){
			this.b_name = _bname;
		}
		public void set_price(int _price){
			this.price = _price;
		}
		public void set_btype(String _btype){
			this.b_type = _btype;
		}
		public void set_iname(String _iname){
			this.i_name = _iname;
		}
	}
	public static class sum_price_listdata{
		String m_type;
		int m_price;
		public sum_price_listdata(){
			
		}
		public String get_type(){
			return m_type;
		}
		public int get_price(){
			return m_price;
		}
		public void set_type(String _type){
			this.m_type = _type;
		}
		public void set_price(int _price){
			this.m_price = _price; 
		}
	}
	public static class count_brandlist_data{
		String m_bname;
		String m_type;
		int m_count;
		String b_type;
		int price;
		
		public count_brandlist_data(){
			m_bname = null;
			m_type = null;
			b_type = null;
			m_count = 0;
			price = 0;
		}
		public String get_bname(){
			return m_bname;
		}
		public String get_type(){
			return m_type;
		}
		public int get_count(){
			return m_count;
		}
		public String get_btype(){
			return b_type;
		}
		public int get_price(){
			return price;
		}
		public void set_bname(String _bname){
			this.m_bname = _bname;
		}
		public void set_type(String _type){
			this.m_type = _type;
		}
		public void set_count(int _count){
			this.m_count = _count;
		}
		public void set_btype(String _btype){
			this.b_type = _btype;
		}
		public void set_price(int _price){
			this.price = _price; 
		}
	}
	/*public static ArrayList<count_brandlist_data> count_list_date(String user_id, String year, String month, String itype){
		stmt = null;
		conn = null;
		rs = null;
		connect_DB();
		
		
		ArrayList<count_brandlist_data> data = new ArrayList<count_brandlist_data>();
		try{
			stmt = conn.createStatement();
			String query = "SELECT DISTINCT BRANDNAME, ITEMTYPE, BUDGETTYPE, PRICE FROM CONSUME_LIST WHERE USERID LIKE '"+user_id+"' and ITEMTYPE LIKE '"+itype+"'";
			rs = stmt.executeQuery(query);
			int count = 0;
			while(rs.next()){
				count_brandlist_data btemp = new count_brandlist_data();
				String t_bname = rs.getString("BRANDNAME");
				String t_type = rs.getString("ITEMTYPE");
				String t_btype = rs.getString("BUDGETTYPE");
				int price;
				btemp.set_bname(t_bname);
				btemp.set_type(t_type);
				btemp.set_btype(t_btype);
				data.add(count, btemp);
				btemp = null;
				//System.out.println(t_bname + t_type);
				//System.out.println(data.get(count).get_bname() + data.get(count).get_type());
				count++;
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			if(conn != null)try{conn.close();}catch(SQLException e){}
			if(stmt != null)try{stmt.close();}catch(SQLException e){}
			if(rs != null)try{rs.close();}catch(SQLException e){}
		}
		
		stmt = null;
		conn = null;
		rs = null;
		connect_DB();
		try{
			for(int i = 0; i< data.size(); i++)
			{
				
				stmt = conn.createStatement();
				String query = "SELECT COUNT(BRANDNAME) AS COUNT_BRAND FROM CONSUME_LIST"
						+ " WHERE BRANDNAME LIKE '"+data.get(i).get_bname()+"' and TIMEINFO BETWEEN to_timestamp('"+year+"-"+month+"-01 00:00:00.000','yyyy-mm-dd HH24:MI:SS.FF3')"
								+ " and to_timestamp('"+year+"-"+month+"-30 00:00:00.000','yyyy-mm-dd HH24:MI:SS.FF3')";
				rs = stmt.executeQuery(query);
				
				while(rs.next()){
					int t_count = rs.getInt("COUNT_BRAND");
					data.get(i).set_count(t_count);
				}
				
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			if(conn != null)try{conn.close();}catch(SQLException e){}
			if(stmt != null)try{stmt.close();}catch(SQLException e){}
			if(rs != null)try{rs.close();}catch(SQLException e){}
		}
		//System.out.println(data.size());
		
		Collections.sort(data, new Comparator<count_brandlist_data>(){
			public int compare(count_brandlist_data ct1, count_brandlist_data ct2)
			{
				int result = 0;
				if(ct1.get_type().equals(ct2.get_type()))
				{
					result = (ct1.get_count() > ct2.get_count())?-1:(ct1.get_count()< ct2.get_count())?1:0;
				}
				return result;
			}
		});
		
		return data;
	}*/
	public static ArrayList<sum_price_listdata> pricesum_list_date_ness(String user_id){
		stmt = null;
		conn = null;
		rs = null;
		connect_DB();
		
		
		ArrayList<sum_price_listdata> data = new ArrayList<sum_price_listdata>();
		try{
			stmt = conn.createStatement();
			String query = "select itemtype, sum(price) as price_sum from consume_list where budgettype like 'Necessary' and userid like '"+user_id+"' group by itemtype order by price_sum desc";
			rs = stmt.executeQuery(query);
			int count = 0;
			while(rs.next()){
				sum_price_listdata temp = new sum_price_listdata();
				
				String t_type = rs.getString("ITEMTYPE");
				int t_price = rs.getInt("PRICE_SUM");
				
				temp.set_type(t_type);
				temp.set_price(t_price);
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
	public static ArrayList<sum_price_listdata> pricesum_list_date_subsidiary(String user_id){
		stmt = null;
		conn = null;
		rs = null;
		connect_DB();
		
		ArrayList<sum_price_listdata> data = new ArrayList<sum_price_listdata>();
		try{
			stmt = conn.createStatement();
			String query = "select itemtype, SUM(PRICE) AS PRICE_SUM FROM CONSUME_LIST "
					+ "WHERE BUDGETTYPE LIKE 'Subsidiary' and USERID LIKE '"+user_id+"'"
					+ "group by itemtype order by PRICE_SUM DESC";
			rs = stmt.executeQuery(query);
			int count = 0;
			while(rs.next()){
				sum_price_listdata temp = new sum_price_listdata();
				
				String t_type = rs.getString("ITEMTYPE");
				int t_price = rs.getInt("PRICE_SUM");
				
				temp.set_type(t_type);
				temp.set_price(t_price);
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
	//유저 id를 통해서 소비 리스트를 검색하는 함수
	public static ArrayList<consume_listdata> select_data_id(String _id){
		stmt = null;
		conn = null;
		rs = null;
		
		ArrayList<consume_listdata> data = new ArrayList<consume_listdata>();
		
		connect_DB();
		try{
		stmt = conn.createStatement();
		String query = "select USERID, TIMEINFO, ITEMTYPE, BRANDNAME,PRICE,ITEMNAME from CONSUME_LIST WHERE USERID LIKE '"+_id+"'";
		rs = stmt.executeQuery(query);
		int count = 0;
			while(rs.next()){
				consume_listdata temp = new consume_listdata();
				String t_id = rs.getString("USERID");
				String t_info= rs.getString("TIMEINFO");
				String t_type = rs.getString("ITEMTYPE");
				String t_bname = rs.getString("BRANDNAME");
				int t_price =rs.getInt("PRICE");
				String t_iname = rs.getString("ITEMNAME");
				
				if(t_id.equals(_id))
				{
					temp.set_id(t_id);
					temp.set_time(t_info);
					temp.set_type(t_type);
					temp.set_brand(t_bname);
					temp.set_price(t_price);
					temp.set_iname(t_iname);
					data.add(count,temp);
				}
				count++;
				temp = null;
				//System.out.println("브랜드 명 : "+data.get(a_count).get_name()+ " 타입:"+data.get(a_count).get_type());

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
	//유저 소비 리스트에서 시간으로 검색시 어떤 시간 이전의 값들만을 검색하기 위한 함수 (특정 시간 이전 소비 리스트를 검색하는 함수)
	/*public static ArrayList<consume_listdata> select_data_preTinfo(String _info){
		stmt = null;
		conn = null;
		rs = null;
		
		ArrayList<consume_listdata> data = new ArrayList<consume_listdata>();
		
		connect_DB();
		try{
		stmt = conn.createStatement();
		String query = "select USERID, TIMEINFO from CONSUME_LIST WHERE TIMEINFO < '"+_info+"'";
		rs = stmt.executeQuery(query);
		int count = 0;
			while(rs.next())
			{
				consume_listdata temp = new consume_listdata();
				String t_id = rs.getString("USERID");
				String t_info = rs.getString("TIMEINFO");
				if(t_info.equals(_info))
				{
					temp.set_id(t_id);
					temp.set_time(t_info);
					data.add(count,temp);
				}
				count++;
				temp = null;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			if(conn != null)try{conn.close();}catch(SQLException e){}
			if(stmt != null)try{stmt.close();}catch(SQLException e){}
			if(rs != null)try{rs.close();}catch(SQLException e){}
		}
		return data;
	}*/
	public static void initialize_list(String user_id, int c_year, int c_month){
		stmt = null;
		conn = null;
		rs = null;
		connect_DB();
		try{
		stmt = conn.createStatement();
		String query = "delete from consume_list where userid like '"+user_id+" and timeinfo <'"+c_year+"-"+c_month+"-01 00:00'";
		rs = stmt.executeQuery(query);
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			if(conn != null)try{conn.close();}catch(SQLException e){}
			if(stmt != null)try{stmt.close();}catch(SQLException e){}
			if(rs != null)try{rs.close();}catch(SQLException e){}
		}
	}
	//어떤 특정 시간 이후의 소비리스트를 검색하기 위한 함수
	public static ArrayList<consume_listdata> select_data_afterTinfo(String _info){
		stmt = null;
		conn = null;
		rs = null;
		
		ArrayList<consume_listdata> data = new ArrayList<consume_listdata>();
		
		connect_DB();
		try{
		stmt = conn.createStatement();
		String query = "select USERID, TIMEINFO from CONSUME_LIST WHERE TIMEINFO > '"+_info+"'";
		rs = stmt.executeQuery(query);
		int count = 0;
			while(rs.next())
			{
				consume_listdata temp = new consume_listdata();
				String t_id = rs.getString("USERID");
				String t_info = rs.getString("TIMEINFO");
				if(t_info.equals(_info))
				{
					temp.set_id(t_id);
					temp.set_time(t_info);
					data.add(count,temp);
				}
				count++;
				temp = null;
				
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
	//유저 id를 통해서 소비리스트에서 삭제하는 함수
	public static void delete_data_id(String _id, String _info){
		stmt = null;
		conn = null;
		rs = null;
		connect_DB();
		try{
			stmt = conn.createStatement();
			String query = "delete from CONSUME_LIST where USERID = '"+_id +"'AND TIMEINFO = '"+_info+"'";
			stmt.executeUpdate(query);
			
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				if(conn != null)try{conn.close();}catch(SQLException e){}
				if(stmt != null)try{stmt.close();}catch(SQLException e){}
				
			}
	}
	
	
}
