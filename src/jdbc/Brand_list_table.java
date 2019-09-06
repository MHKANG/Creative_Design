package jdbc;

import java.sql.*;
import java.util.*;

public class Brand_list_table {

	static Connection conn = null;
	static ResultSet rs = null;
	static Statement stmt = null;
	public Brand_list_table(){
		
	}
	
	public static void connect_DB(){
		
		//db������ ���� ���� ����
		String url = null;
		String id = ""; // ���� id
		String pw = ""; // ���� ��� ��ȣ
		
		try
		{
			
			Class.forName("oracle.jdbc.driver.OracleDriver");			
			try{
			url = "jdbc:oracle:thin:@localhost:1521:orcl"; // ���� ���� ��ǻ�Ϳ� �����ϴ� ��Ʈ 1521���� orcl�̶�� �̸��� ���� db �ּ�
			conn = DriverManager.getConnection(url, id, pw);//conn ������ ���� db ���ῡ �ʿ��� ���� ����		
			}
			catch(SQLException e){
				System.out.println(e);
			}
		}
		catch(ClassNotFoundException e) //jdbc ����̹��� ã�� �� ������ ���� ���
		{
			System.out.println(e);
		
		}
	}
	//brandlist�� �����͸� �����ϱ� ���� �Լ�
	public static void insert_data(String user_id,String b_name, String ItemType, int count){
		//�Լ� ����� static ���� ������ �ʱ�ȭ �Ͽ� Ȥ�� �� ���� ������ ����
		stmt = null;
		conn = null;
		rs = null;
		//db ����
		connect_DB();
		
		try{
		stmt = conn.createStatement();
		//������ �ۼ�
		String query = "insert into BRAND_LIST(USERID,BRANDNAME, ITEMTYPE, BRANDCOUNT) values('"+user_id+"','"+ b_name+"','"+ItemType+"',"+count+")";
		stmt.executeQuery(query);
		//System.out.println("������ ���������� �̷�������ϴ�.");
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			if(conn != null)try{conn.close();}catch(SQLException e){}
			if(stmt != null)try{stmt.close();}catch(SQLException e){}
		}
	}
	//brand ����Ʈ ���� ������ data class�� ����� �̸� arraylist�� ������ ����Ѵ�.
	public static class brand_listdata{
		//list �����Ϳ� �ʿ��� ����
		String b_name; //�귣�� �̸�
		String b_type; //�귣�� Ÿ��
		String u_id;
		int count;
		public brand_listdata(){
			b_name = null;
			b_type = null;
		}
		public String get_name()
		{
			return b_name;
		}
		public String get_type()
		{
			return b_type;
		}
		public String get_id()
		{
			return u_id;
		}
		public int get_count()
		{
			return count;
		}
		public void set_name(String _name){
			this.b_name = _name;
		}
		public void set_type(String _type){
			this.b_type = _type;
		}
		public void set_id(String _id){
			this.u_id =_id;
		}
		public void set_count(int _count){
			this.count =_count;
		}
	}
	public static void update_brandcount(String id, String name, String type, int count){
		stmt = null;
		conn = null;
		rs = null;
		
		//�˻��� ������ �����ϱ� ���� �迭 ����Ʈ data�� ����
		ArrayList<brand_listdata> data = new ArrayList<brand_listdata>();
		data = select_data_byinfo(id, name ,type);
		int a_count = data.get(0).get_count()+count;
		//�迭�� �߰�����
		connect_DB();
		try{
		stmt = conn.createStatement();
		//������ �ۼ�
		String query = "update brand_list set brandcount = "+a_count+"where userid like '"+id+"' and itemtype like '"+type+"'and brandname like'"+name+"'";
		
		stmt.executeQuery(query);
		
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			if(conn != null)try{conn.close();}catch(SQLException e){}
			if(stmt != null)try{stmt.close();}catch(SQLException e){}
			if(rs != null)try{rs.close();}catch(SQLException e){}
		}
	}
	//�̸��� ���ؼ� �˻��� �ϴ� �Լ�
	public static ArrayList<brand_listdata> select_data_byinfo(String id, String _name, String type){
		stmt = null;
		conn = null;
		rs = null;
		
		//�˻��� ������ �����ϱ� ���� �迭 ����Ʈ data�� ����
		ArrayList<brand_listdata> data = new ArrayList<brand_listdata>();
		//�迭�� �߰�����
		connect_DB();
		try{
		stmt = conn.createStatement();
		//������ �ۼ�
		String query = "select * from BRAND_LIST WHERE USERID LIKE '"+id+"' and BRANDNAME LIKE '"+_name+"' and ITEMTYPE LIKE '"+type+"'";
		
		rs = stmt.executeQuery(query);
		int count = 0;
			while(rs.next()){
				//�ӽ� ���� t_n�� t_i�� �����Ͽ� ���� resultset�� �ִ� brandname�� itemtype�� �ִ� ������ �ӽ������� ����
				String t_n = rs.getString("BRANDNAME");
				String t_i = rs.getString("ITEMTYPE");
				String t_id = rs.getString("USERID");
				int t_c = rs.getInt("BRANDCOUNT");
				//�ӽ������� brandlistdata�� �����ϱ� ���� temp������ ����
				brand_listdata temp = new brand_listdata();
				//���� t_n ���� ã�����ϴ� name�� ���ٸ�
				
					//�ӽ� ������ ����� ������ temp �ӽ� ���� �迭�� �����Ѵ�.
				temp.set_name(t_n);
				temp.set_type(t_i);
				temp.set_id(t_id);
				temp.set_count(t_c);
				//temp�� ���� �� ������ �˻��� ������ �����ϱ� ���� data �迭�� ������ �Ѵ�.
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
		//��� �˻��� �Ϸ� �� ���� ������ �迭�� ��ȯ�Ͽ� ����Ʈ�� �� �� �ְ� �Ѵ�.
		return data;
	}
	//������ Ÿ������ �˻��� �ϴ� �Լ� , �������� ������ ���� �Լ��� ��� ������ ���� �� ���� arraylist�迭�̴�.
	//���⼭ arraylist�� ���� �迭�� ����ϱ� ���ؼ� ���� template Ÿ��list�� <>�ȿ� ��� ���� ���� ����ϴ� ������ Ÿ���̴�.
	//<>�ȿ��� int, string���� �����ؼ� class�� ��� �� �� �ִ�.
	public static ArrayList<brand_listdata> select_data_nametype(String _bname, String _type){
		stmt = null;
		conn = null;
		rs = null;
		
		ArrayList<brand_listdata> data = new ArrayList<brand_listdata>();
		connect_DB();
		try{
		stmt = conn.createStatement();
		String query = "select BRANDNAME, ITEMTYPE from BRAND_LIST WHERE ITEMTYPE LIKE '"+_type+"' and BRANDNAME LKIE '"+_bname+"'";
		rs = stmt.executeQuery(query);
		int count = 0;
			while(rs.next())
			{
				brand_listdata temp = new brand_listdata();
				String t_n = rs.getString("BRANDNAME");
				String t_i = rs.getString("ITEMTYPE");
				if(t_i.equals(_type))
				{
					temp.set_name(t_n);
					temp.set_type(t_i);
					data.add(count, temp);
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
	
	public static ArrayList<brand_listdata> select_data_countsort(String id, String type){
		stmt = null;
		conn = null;
		rs = null;
		
		//�˻��� ������ �����ϱ� ���� �迭 ����Ʈ data�� ����
		ArrayList<brand_listdata> data = new ArrayList<brand_listdata>();
		//�迭�� �߰�����
		connect_DB();
		try{
		stmt = conn.createStatement();
		//������ �ۼ�
		String query = "select * from brand_list where userid like '"+id+"' and itemtype like '"+type+"' order by BRANDCOUNT desc";
		rs = stmt.executeQuery(query);
		int count = 0;
			while(rs.next()){
				//�ӽ� ���� t_n�� t_i�� �����Ͽ� ���� resultset�� �ִ� brandname�� itemtype�� �ִ� ������ �ӽ������� ����
				String t_n = rs.getString("BRANDNAME");
				String t_i = rs.getString("ITEMTYPE");
				String t_id = rs.getString("USERID");
				int t_c = rs.getInt("BRANDCOUNT");
				//�ӽ������� brandlistdata�� �����ϱ� ���� temp������ ����
				brand_listdata temp = new brand_listdata();
				
				//�ӽ� ������ ����� ������ temp �ӽ� ���� �迭�� �����Ѵ�.
				temp.set_name(t_n);
				temp.set_type(t_i);
				temp.set_id(t_id);
				temp.set_count(t_c);
				//temp�� ���� �� ������ �˻��� ������ �����ϱ� ���� data �迭�� ������ �Ѵ�.
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
		//��� �˻��� �Ϸ� �� ���� ������ �迭�� ��ȯ�Ͽ� ����Ʈ�� �� �� �ְ� �Ѵ�.
		return data;
	}
	
	public static ArrayList<brand_listdata> select_data(){
		stmt = null;
		conn = null;
		rs = null;
		
		//�˻��� ������ �����ϱ� ���� �迭 ����Ʈ data�� ����
		ArrayList<brand_listdata> data = new ArrayList<brand_listdata>();
		//�迭�� �߰�����
		connect_DB();
		try{
		stmt = conn.createStatement();
		//������ �ۼ�
		String query = "select * from brand_list";
		rs = stmt.executeQuery(query);
		int count = 0;
			while(rs.next()){
				//�ӽ� ���� t_n�� t_i�� �����Ͽ� ���� resultset�� �ִ� brandname�� itemtype�� �ִ� ������ �ӽ������� ����
				String t_n = rs.getString("BRANDNAME");
				String t_i = rs.getString("ITEMTYPE");
				String t_id = rs.getString("USERID");
				int t_c = rs.getInt("BRANDCOUNT");
				//�ӽ������� brandlistdata�� �����ϱ� ���� temp������ ����
				brand_listdata temp = new brand_listdata();
				//�ӽ� ������ ����� ������ temp �ӽ� ���� �迭�� �����Ѵ�.
				temp.set_name(t_n);
				temp.set_type(t_i);
				temp.set_id(t_id);
				temp.set_count(t_c);
				//temp�� ���� �� ������ �˻��� ������ �����ϱ� ���� data �迭�� ������ �Ѵ�.
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
		//��� �˻��� �Ϸ� �� ���� ������ �迭�� ��ȯ�Ͽ� ����Ʈ�� �� �� �ְ� �Ѵ�.
		return data;
	}
	
	//�̸��� ���ؼ� �����ϴ� �Լ�
	public static void delete_data_byname(String b_name){
		stmt = null;
		conn = null;
		rs = null;
		//db����
		connect_DB();
		try{
			stmt = conn.createStatement();
			String query = "delete from BRAND_LIST where BRANDNAME = '"+b_name +"'";
			int count = stmt.executeUpdate(query);
			System.out.println("���������� �������ϴ�." + count);
			
			
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				if(conn != null)try{conn.close();}catch(SQLException e){}
				if(stmt != null)try{stmt.close();}catch(SQLException e){}
				
			}
	}
	//������ Ÿ���� ���ؼ� �����͸� �����ϴ� �Լ�
	public static void delete_data_byItype(String Itype){
		stmt = null;
		conn = null;
		rs = null;
		connect_DB();
		try{
			stmt = conn.createStatement();
			String query = "delete from BRAND_LIST where ITEMTYPE = '"+Itype +"'";
			stmt.executeQuery(query);
			System.out.println("���������� �������ϴ�.");
			
			
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				if(conn != null)try{conn.close();}catch(SQLException e){}
				if(stmt != null)try{stmt.close();}catch(SQLException e){}
				
			}
	}
	//�귣�� �̸��� ���ؼ� ������ Ÿ���� �����ϴ� �Լ� 
	
	

}
