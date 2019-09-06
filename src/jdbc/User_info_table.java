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
	//������ ���̽��� �����ϴ� �Լ�
	public static void connect_DB(){
		//git�� updload �ϱ� ������ id �� pw�� ���� �մϴ�. 
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
	// ���� ������ ������ ������ ���� �߰��ϱ� ���� �Լ�
	public static void insert_data(String u_id, String u_pw, String gender, int age, int m_revenue, int n_budget, int s_budget, int goal, int c_budget){
		stmt = null;
		conn = null;
		rs = null;
		connect_DB();
		//���� ���� �ʱ�ȭ
		try{
		stmt = conn.createStatement();
		String query = "INSERT INTO USER_INFO(USERID, USERPASSWORD, GENDER, AGE, MONTHLYREVENUE,"
				+ "NECESSARYBUDGET, SUBSIDIARYBUDGET, GOAL, CURRENTBUDGET)"
				+ "values('"+u_id+"','"+u_pw+"','"+gender+"',"+age+","+m_revenue+","
				+ n_budget+","+s_budget+","+goal+","+c_budget+")";
		stmt.executeQuery(query);
		//System.out.println("������ ���������� �̷�������ϴ�.");
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			if(conn != null)try{conn.close();}catch(SQLException e){}
			if(stmt != null)try{stmt.close();}catch(SQLException e){}
		}
	}
	//������ ������ ���� ������ ���� �� �� �ִ� classƲ 
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
	//��ü ������ �˻��ϴ� �Լ� , ���� ������ ������ ���� ������ ������ �ִ� ��ü �迭�� ������.
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
	//�α��� �� �ǹٸ� ��й�ȣ�� id���� üũ�ϴ� �Լ�, �ǹٸ� ���̵� �н����� �ϰ�� true, �ƴҰ�� false ����
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
	//���� ������ �ߺ� üũ�� �ϴ� �Լ�, �ߺ��� ���� ��� false, ���� ��� true�� ����
	public static boolean check_duplication(String _id){
		boolean check = true;
		ArrayList<user_listdata> temp = new ArrayList<user_listdata>();
		temp = select_user_list();
		//�ӽ� ���� ���� temp�� ���� ����Ʈ�� �ҷ� �´�.
		
		//�ҷ��� temp�� ������ �ִ� �迭 ����Ʈ���� ������ id�� �ִٸ� check�� false�� �����Ͽ� �ߺ� ���̵��� ���� �˸���.
		for(int i = 0; i< temp.size(); i++){
			if(temp.get(i).get_id().equals(_id)){
				check = false;
				break;
			}
		}
		// ���� �ߺ� ���̵� �� ���ٸ� check = true�� ������ �Ѵ�.
		return check;
	}
	//*
	//monthly revenue ���� id ���� ���� �˻��Ѵ��� �����ϴ� �Լ�
	public static void update_mr_userdata(String _id, int _mr){
		
		stmt = null;
		conn = null;
		rs = null;
		//������ ���̽� ����
		connect_DB();
		try{
			stmt = conn.createStatement();
			//������ �Է�
			String query = "update USER_INFO set MONTHLYREVENUE = "+_mr +" WHERE USERID = '"+_id +"'";
			//������ ����
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
		//������ ���̽� ����
		connect_DB();
		try{
			stmt = conn.createStatement();
			//������ �Է�
			String query = "update USER_INFO set NECESSARYBUDGET = "+_nb +" WHERE USERID = '"+_id +"'";
			//������ ����
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
		//������ ���̽� ����
		connect_DB();
		try{
			stmt = conn.createStatement();
			//������ �Է�
			String query = "update USER_INFO set CURRENTBUDGET = "+n_cb +" WHERE USERID = '"+_id +"'";
			//������ ����
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
		//������ ���̽� ����
		connect_DB();
		try{
			stmt = conn.createStatement();
			//������ �Է�
			String query = "update USER_INFO set CURRENTBUDGET = "+_cb +" WHERE USERID = '"+_id +"'";
			//������ ����
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
		
		//������ ���̽� ����
		connect_DB();
		try{
			stmt = conn.createStatement();
			//������ �Է�
			String query = "update USER_INFO set NECESSARYBUDGET = "+_nb +" WHERE USERID = '"+_id +"'";
			//������ ����
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
		
		//������ ���̽� ����
		connect_DB();
		try{
			stmt = conn.createStatement();
			//������ �Է�
			String query = "update USER_INFO set SUBSIDIARY = "+_sb +" WHERE USERID = '"+_id +"'";
			//������ ����
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
	//userid�� ���� �� ������ ��� ������ ���� �ϴ� �Լ�
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
		//temp�� �ش� ���� ���� �Һ� ������ �ִ� �ӽ� ��ü�̴�.
		temp = select_user_list_byid(_id);
		//������ �ִ� ���� �ϳ��� �������� 0��° �׿� �ٰ� �߰��� �ݾ��� �ִ´�.
		int r_budget = temp.get(0).get_cb() -_budget;
		//System.out.println(temp.get(0).get_cb());
		connect_DB();
		try{
		stmt = conn.createStatement();
		//�߰��� ��Ż �ݾ�����  �ش� month�� �ִ� total consume ���� �����Ѵ�.
		String query = "UPDATE USER_INFO set CURRENTBUDGET =" + r_budget + "WHERE USERID LIKE '"+_id+"'";
		stmt.executeQuery(query);
		
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			if(conn != null)try{conn.close();}catch(SQLException e){}
			if(stmt != null)try{stmt.close();}catch(SQLException e){}
		}
	}
	// ���� ������ ���� id�� ���� �˻��ؼ� �����ϴ� �Լ� *
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
