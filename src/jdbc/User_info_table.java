package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import jdbc.Item_table.item_listdata;
import jdbc.Monthly_total_consume_table.month_listdata;

public class User_info_table {

	static Connection conn = null;
	static ResultSet rs = null;
	static Statement stmt = null;
	
	public User_info_table(){
		
	}
	//데이터 베이스와 연결하는 함수
	public static void connect_DB(){
		//git에 updload 하기 때문에 id 와 pw는 삭제 합니다. 
		String url = null;
		String id = "";
		String pw = "";
		
		try
		{
			
			Class.forName("oracle.jdbc.driver.OracleDriver");			
     	//	System.out.println("driver load sucess");
		
			try{
			url = "jdbc:oracle:thin:@localhost:1521:orcl";
		//	System.out.println("db connect success");
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
	// 계정 생성시 유저의 데이터 값을 추가하기 위한 함수
	public static void insert_data(String u_id, String u_pw, String gender, int age, int m_revenue, int n_budget, int s_budget, int goal, int c_budget){
		stmt = null;
		conn = null;
		rs = null;
		connect_DB();
		//현재 재정 초기화
		try{
		stmt = conn.createStatement();
		String query = "INSERT INTO USER_INFO(USERID, USERPASSWORD, GENDER, AGE, MONTHLYREVENUE,"
				+ "NECESSARYBUDGET, SUBSIDIARYBUDGET, GOAL, CURRENTBUDGET)"
				+ "values('"+u_id+"','"+u_pw+"','"+gender+"',"+age+","+m_revenue+","
				+ n_budget+","+s_budget+","+goal+","+c_budget+")";
		stmt.executeQuery(query);
		//System.out.println("삽입이 성공적으로 이루어졌습니다.");
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			if(conn != null)try{conn.close();}catch(SQLException e){}
			if(stmt != null)try{stmt.close();}catch(SQLException e){}
		}
	}
	//유저의 정보에 대한 내용을 저장 할 수 있는 class틀 
	public static class user_listdata{
		String m_id;
		String m_pw;
		String m_gender;
		int m_age, m_mr, m_nb, m_sb, m_goal, m_cb;
		public user_listdata(){
			m_id = null; m_pw = null; m_gender = null;
			m_age = 0; m_mr = 0; m_nb = 0; m_sb = 0;
			m_goal = 0; m_cb = 0;
		}
		public String get_id(){return m_id;}
		public String get_pw(){return m_pw;}
		public String get_gender(){return m_gender;}
		public int get_age(){return m_age;}
		public int get_mr(){return m_mr;}
		public int get_nb(){return m_nb;}
		public int get_sb(){return m_sb;}
		public int get_goal(){return m_goal;}
		public int get_cb(){return m_cb;}
		public void set_id(String _id){this.m_id = _id;}
		public void set_pw(String _pw){this.m_pw = _pw;}
		public void set_gender(String _gender){this.m_gender = _gender;}
		public void set_age(int _age){this.m_age = _age;}
		public void set_mr(int _mr){this.m_mr = _mr;}
		public void set_nb(int _nb){this.m_nb = _nb;}
		public void set_sb(int _sb){this.m_sb = _sb;}
		public void set_goal(int _goal){this.m_goal = _goal;}
		public void set_cb(int _cb){this.m_cb = _cb;}
	}
	//전체 유저를 검색하는 함수 , 리턴 값으로 유저에 대한 정보를 가지고 있는 객체 배열을 가진다.
	public static ArrayList<user_listdata> select_user_list(){
		
		
		ArrayList<user_listdata> data = new ArrayList<user_listdata>();
		
		connect_DB();
		try{
		stmt = conn.createStatement();
		String query = "select * from USER_INFO ";
		rs = stmt.executeQuery(query);
		int a_count = 0;
			while(rs.next()){
				user_listdata temp = new user_listdata();
				String t_id = rs.getString("USERID");
				String t_pw= rs.getString("USERPASSWORD");
				String t_gender = rs.getString("GENDER");
				int t_age = rs.getInt("AGE");
				int t_mr = rs.getInt("MONTHLYREVENUE");
				int t_nb = rs.getInt("NECESSARYBUDGET");
				int t_sb = rs.getInt("SUBSIDIARYBUDGET");
				int t_goal = rs.getInt("GOAL");
				int t_cb = rs.getInt("CURRENTBUDGET");
				
				temp.set_id(t_id);
				temp.set_pw(t_pw);
				temp.set_gender(t_gender);
				temp.set_age(t_age);
				temp.set_mr(t_mr);
				temp.set_nb(t_nb);
				temp.set_sb(t_sb);
				temp.set_goal(t_goal);
				temp.set_cb(t_cb);
				
				data.add(a_count,temp);
				temp = null;
				a_count++;
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
public static ArrayList<user_listdata> select_user_list_byid(String _id){
		
		
		ArrayList<user_listdata> data = new ArrayList<user_listdata>();
		
		connect_DB();
		try{
		stmt = conn.createStatement();
		String query = "select * from USER_INFO WHERE USERID LIKE '"+_id+"'";
		rs = stmt.executeQuery(query);
		int a_count = 0;
			while(rs.next()){
				user_listdata temp = new user_listdata();
				String t_id = rs.getString("USERID");
				String t_pw= rs.getString("USERPASSWORD");
				String t_gender = rs.getString("GENDER");
				int t_age = rs.getInt("AGE");
				int t_mr = rs.getInt("MONTHLYREVENUE");
				int t_nb = rs.getInt("NECESSARYBUDGET");
				int t_sb = rs.getInt("SUBSIDIARYBUDGET");
				int t_goal = rs.getInt("GOAL");
				int t_cb = rs.getInt("CURRENTBUDGET");
				System.out.println(t_cb);
				temp.set_id(t_id);
				temp.set_pw(t_pw);
				temp.set_gender(t_gender);
				temp.set_age(t_age);
				temp.set_mr(t_mr);
				temp.set_nb(t_nb);
				temp.set_sb(t_sb);
				temp.set_goal(t_goal);
				temp.set_cb(t_cb);
				
				data.add(a_count,temp);
				temp = null;
				a_count++;
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
    //*
	//로그인 시 옳바른 비밀번호와 id인지 체크하는 함수, 옳바른 아이디 패스워드 일경우 true, 아닐경우 false 리턴
	public static boolean login_check(String _id, String _pw){
		boolean check = false;
		ArrayList<user_listdata> temp = new ArrayList<user_listdata>();
		temp = select_user_list();
		for(int i = 0; i < temp.size(); i++){
			if(temp.get(i).get_id().equals(_id)&&temp.get(i).get_pw().equals(_pw)){
				check = true;
				break;
			}
		}
		return check;
	}
	//*
	//계정 생성시 중복 체크를 하는 함수, 중복이 있을 경우 false, 없을 경우 true를 리턴
	public static boolean check_duplication(String _id){
		boolean check = true;
		ArrayList<user_listdata> temp = new ArrayList<user_listdata>();
		temp = select_user_list();
		//임시 저장 변수 temp에 유저 리스트를 불러 온다.
		
		//불러운 temp가 가지고 있는 배열 리스트에서 동일한 id가 있다면 check를 false로 리턴하여 중복 아이디라는 것을 알린다.
		for(int i = 0; i< temp.size(); i++){
			if(temp.get(i).get_id().equals(_id)){
				check = false;
				break;
			}
		}
		// 만약 중복 아이디 가 없다면 check = true로 리턴을 한다.
		return check;
	}
	//*
	//monthly revenue 유저 id 값을 통해 검색한다음 변경하는 함수
	public static void update_mr_userdata(String _id, int _mr){
		
		stmt = null;
		conn = null;
		rs = null;
		//데이터 베이스 연결
		connect_DB();
		try{
			stmt = conn.createStatement();
			//쿼리문 입력
			String query = "update USER_INFO set MONTHLYREVENUE = "+_mr +" WHERE USERID = '"+_id +"'";
			//쿼리문 실행
			stmt.executeQuery(query);
			
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				if(conn != null)try{conn.close();}catch(SQLException e){}
				if(stmt != null)try{stmt.close();}catch(SQLException e){}
				
			}
		
	}
public static void update_nb_userdata(String _id, int _nb){
		
		stmt = null;
		conn = null;
		rs = null;
		//데이터 베이스 연결
		connect_DB();
		try{
			stmt = conn.createStatement();
			//쿼리문 입력
			String query = "update USER_INFO set NECESSARYBUDGET = "+_nb +" WHERE USERID = '"+_id +"'";
			//쿼리문 실행
			stmt.executeQuery(query);
			
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				if(conn != null)try{conn.close();}catch(SQLException e){}
				if(stmt != null)try{stmt.close();}catch(SQLException e){}
				
			}
		
	}
	public static void update_cb_plus_mr(String _id, int mr){
		stmt = null;
		conn = null;
		rs = null;
		ArrayList<user_listdata> utemp = new ArrayList<user_listdata>();
		utemp = select_user_list_byid(_id);
		int _cb = utemp.get(0).get_cb();
		int n_cb = _cb + mr;
		//데이터 베이스 연결
		connect_DB();
		try{
			stmt = conn.createStatement();
			//쿼리문 입력
			String query = "update USER_INFO set CURRENTBUDGET = "+n_cb +" WHERE USERID = '"+_id +"'";
			//쿼리문 실행
			stmt.executeQuery(query);
			
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				if(conn != null)try{conn.close();}catch(SQLException e){}
				if(stmt != null)try{stmt.close();}catch(SQLException e){}
				
			}
	}
	public static void initial_cb_userdata(String _id, int _mr){
		stmt = null;
		conn = null;
		rs = null;
		ArrayList<user_listdata> utemp = new ArrayList<user_listdata>();
		utemp = select_user_list_byid(_id);
		int _nb = utemp.get(0).get_nb();
		int _sb = utemp.get(0).get_sb();
		int _cb = _mr-_nb-_sb;
		//데이터 베이스 연결
		connect_DB();
		try{
			stmt = conn.createStatement();
			//쿼리문 입력
			String query = "update USER_INFO set CURRENTBUDGET = "+_cb +" WHERE USERID = '"+_id +"'";
			//쿼리문 실행
			stmt.executeQuery(query);
			
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				if(conn != null)try{conn.close();}catch(SQLException e){}
				if(stmt != null)try{stmt.close();}catch(SQLException e){}
				
			}
	}
	public static void initial_nb_userdata(String _id, int _nb){
		stmt = null;
		conn = null;
		rs = null;
		ArrayList<user_listdata> utemp = new ArrayList<user_listdata>();
		
		//데이터 베이스 연결
		connect_DB();
		try{
			stmt = conn.createStatement();
			//쿼리문 입력
			String query = "update USER_INFO set NECESSARYBUDGET = "+_nb +" WHERE USERID = '"+_id +"'";
			//쿼리문 실행
			stmt.executeQuery(query);
			
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				if(conn != null)try{conn.close();}catch(SQLException e){}
				if(stmt != null)try{stmt.close();}catch(SQLException e){}
				
			}
	}
	public static void initial_sb_userdata(String _id, int _sb){
		stmt = null;
		conn = null;
		rs = null;
		ArrayList<user_listdata> utemp = new ArrayList<user_listdata>();
		
		//데이터 베이스 연결
		connect_DB();
		try{
			stmt = conn.createStatement();
			//쿼리문 입력
			String query = "update USER_INFO set SUBSIDIARY = "+_sb +" WHERE USERID = '"+_id +"'";
			//쿼리문 실행
			stmt.executeQuery(query);
			
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				if(conn != null)try{conn.close();}catch(SQLException e){}
				if(stmt != null)try{stmt.close();}catch(SQLException e){}
				
			}
	}
	//*
	public static void update_sb_userdata(String _id, int _sb){
		stmt = null;
		conn = null;
		rs = null;
		connect_DB();
		try{
			stmt = conn.createStatement();
			String query = "update USER_INFO set SUBSIDIARYBUDGET = "+_sb +" WHERE USERID = '"+_id +"'";
			stmt.executeQuery(query);
			
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				if(conn != null)try{conn.close();}catch(SQLException e){}
				if(stmt != null)try{stmt.close();}catch(SQLException e){}
				
			}
	}
	public static void update_gender_userdata(String _id, String _gender){
		stmt = null;
		conn = null;
		rs = null;
		connect_DB();
		try{
			stmt = conn.createStatement();
			String query = "update USER_INFO set GENDER = '"+_gender +"' WHERE USERID = '"+_id +"'";
			stmt.executeQuery(query);
			
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				if(conn != null)try{conn.close();}catch(SQLException e){}
				if(stmt != null)try{stmt.close();}catch(SQLException e){}
				
			}
	}
	//*
	public static void update_goal_userdata(String _id, int _goal){
		stmt = null;
		conn = null;
		rs = null;
		connect_DB();
		try{
			stmt = conn.createStatement();
			String query = "update USER_INFO set GOAL = "+_goal +" WHERE USERID = '"+_id +"'";
			stmt.executeQuery(query);
			
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				if(conn != null)try{conn.close();}catch(SQLException e){}
				if(stmt != null)try{stmt.close();}catch(SQLException e){}
				
			}
	}
	//userid를 통해 그 유저의 모든 정보를 변경 하는 함수
	public static void update_whole_userdata(String _id,String _pw, String _gender, int age, int _mr, int _nb, int _sb, int _goal){
		stmt = null;
		conn = null;
		rs = null;
		connect_DB();
		try{
			stmt = conn.createStatement();
			String query = "update ITEM set USERPASSWORD = '"+_pw +"', GENDER = '"+ _gender 
					+"', AGE = "+age +", MONTHLYREVENUE = "+_mr+", NECESSARYBUDGET = "+ _nb
					+", SUBSIDIARYBUDGET = "+_sb +"WHERE USERID = '"+_id +"'";
			stmt.executeQuery(query);
			
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				if(conn != null)try{conn.close();}catch(SQLException e){}
				if(stmt != null)try{stmt.close();}catch(SQLException e){}
				
			}
	}
	//*
	public static void update_curbudget_byconsume(String _id, int _budget){
		stmt = null;
		conn = null;
		rs = null;
		ArrayList<user_listdata> temp = new ArrayList<user_listdata>();
		//temp는 해당 월의 월간 소비를 가지고 있는 임시 객체이다.
		temp = select_user_list_byid(_id);
		//가지고 있는 값은 하나일 것임으로 0번째 항에 다가 추가할 금액을 넣는다.
		int r_budget = temp.get(0).get_cb() -_budget;
		//System.out.println(temp.get(0).get_cb());
		connect_DB();
		try{
		stmt = conn.createStatement();
		//추가된 토탈 금액으로  해당 month에 있는 total consume 값을 설정한다.
		String query = "UPDATE USER_INFO set CURRENTBUDGET =" + r_budget + "WHERE USERID LIKE '"+_id+"'";
		stmt.executeQuery(query);
		
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			if(conn != null)try{conn.close();}catch(SQLException e){}
			if(stmt != null)try{stmt.close();}catch(SQLException e){}
		}
	}
	// 유저 정보를 유저 id를 통해 검색해서 삭제하는 함수 *
	public static void delete_userdata(String _userid){
		stmt = null;
		conn = null;
		rs = null;
		connect_DB();
		try{
			stmt = conn.createStatement();
			String query = "delete from USER_INFO where USERID = '"+_userid +"'";
			int count = stmt.executeUpdate(query);
			
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				if(conn != null)try{conn.close();}catch(SQLException e){}
				if(stmt != null)try{stmt.close();}catch(SQLException e){}
				
			}
	}
}
