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

public class Item_table {

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
	public static void insert_data(String i_id, String i_name, int i_price, String i_type, String b_name){
		stmt = null;
		conn = null;
		rs = null;
		connect_DB();
		
		try{
		stmt = conn.createStatement();
		String query = "insert in ITEM(USERID, ITEMNAME, ITEMPRICE, ITEMTYPE, BRANDNAME)"
				+ "+ values('"+ i_id+"','"+i_name+"',"+i_price+",'"+i_type+"','"+b_name+"')";
		stmt.executeQuery(query);
		//System.out.println("������ ���������� �̷�������ϴ�.");
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			if(conn != null)try{conn.close();}catch(SQLException e){}
			if(stmt != null)try{stmt.close();}catch(SQLException e){}
		}
	}
	//item ���̺��� ������ �ִ� ������ �����ϱ� ���� class
	public static class item_listdata{
		String i_id;
		String i_name;
		int i_price;
		String i_type;
		String b_name;
		String s_type;
		
		public item_listdata(){
			i_id = null;i_name = null;
			i_price = 0;i_type = null; b_name = null;
		}
		public String get_id()
		{
			return i_id;
		}
		public String get_iname()
		{
			return i_name;
		}
		public int get_price()
		{
			return i_price;
		}
		public String get_type()
		{
			return i_type;
		}
		public String get_bname()
		{
			return b_name;
		}
		public String get_subtype()
		{
			return s_type;
		}
		public void set_id(String _id){
			this.i_id = _id;
		}
		public void set_iname(String _iname){
			this.i_name = _iname;
		}
		public void set_price(int _price){
			this.i_price = _price;
		}
		public void set_itype(String _type){
			this.i_type = _type;
		}
		public void set_bname(String _bname){
			this.b_name = _bname;
		}
		public void set_stype(String _stype){
			this.s_type = _stype;
		}
	}
	public static ArrayList<item_listdata> select_data_bybrandtype(String bname, String type, int price){
		stmt = null;
		conn = null;
		rs = null;
		
		ArrayList<item_listdata> data = new ArrayList<item_listdata>();
		connect_DB();
		try{
		stmt = conn.createStatement();
		String query = "select * "
				+ " from ITEM WHERE BRANDNAME LIKE '"+bname+"' and ITEMTYPE LIKE '"+type+"'and ITEMPRICE <"+price+"order by ITEMPRICE DESC" ;
		rs = stmt.executeQuery(query);
		int count = 0;
			while(rs.next()){
				item_listdata temp = new item_listdata();
				String t_id = rs.getString("ITEMID");
				String t_iname= rs.getString("ITEMNAME");
				int t_price= rs.getInt("ITEMPRICE");
				String t_type= rs.getString("ITEMTYPE");
				String t_bname= rs.getString("BRANDNAME");
				String t_stype = rs.getString("SUBTYPE");
				
				temp.set_id(t_id);
				temp.set_iname(t_iname);
				temp.set_price(t_price);
				temp.set_itype(t_type);
				temp.set_bname(t_bname);
				temp.set_stype(t_stype);
				data.add(count,temp);
				
				temp = null;
				count++;
				//System.out.println("�귣�� �� : "+data.get(a_count).get_name()+ " Ÿ��:"+data.get(a_count).get_type());
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
	public static int cal_median_of_three_brand(String bname1, String bname2, String bname3, String stype){
		
		stmt = null;
		conn = null;
		rs = null;
		int result = 0;
		ArrayList<item_listdata> data = new ArrayList<item_listdata>();
		connect_DB();
		try{
		stmt = conn.createStatement();
		String query = "SELECT TRUNC(MEDIAN(ITEMPRICE),0) AS MIDDLE FROM ITEM WHERE BRANDNAME LIKE '"+bname1+
				"' OR BRANDNAME LIKE '"+bname2+"' OR BRANDNAME LIKE '"+bname3+"' AND SUBTYPE LIKE '"+stype+"'";
		rs = stmt.executeQuery(query);
			while(rs.next()){
				result = rs.getInt("MIDDLE");
				//System.out.println("�귣�� �� : "+data.get(a_count).get_name()+ " Ÿ��:"+data.get(a_count).get_type());
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
	public static int cal_median_of_all_brand(String stype){
		
		stmt = null;
		conn = null;
		rs = null;
		int result = 0;
		ArrayList<item_listdata> data = new ArrayList<item_listdata>();
		connect_DB();
		try{
		stmt = conn.createStatement();
		String query = "SELECT TRUNC(MEDIAN(ITEMPRICE),0) AS MIDDLE FROM ITEM WHERE SUBTYPE LIKE '"+stype+"'";
		rs = stmt.executeQuery(query);
			while(rs.next()){
				result = rs.getInt("MIDDLE");
				//System.out.println("�귣�� �� : "+data.get(a_count).get_name()+ " Ÿ��:"+data.get(a_count).get_type());
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
	public static String reutrn_type_by_subtype(String subtype){
		String type = null;
		if(subtype.equals("û����")||subtype.equals("ûġ��")||subtype.equals("�����")||subtype.equals("Ƽ����")||subtype.equals("�巹��")){
			type = "�Ƿ�";
		}else if(subtype.equals("�ȭ")||subtype.equals("����")||subtype.equals("������")||subtype.equals("����")||subtype.equals("����")){
			type = "�Ź�";
		}else if(subtype.equals("�����")||subtype.equals("��Ʈ��")||subtype.equals("ũ�ν���")||subtype.equals("Ŭ��ġ��")||subtype.equals("����")){
			type = "����";
		}else if(subtype.equals("��Ż�ð�")||subtype.equals("���׽ð�")){
			type = "�ð�";
		}
		return type;
	}
	public static String return_subtype_bytexten(String txt){
		String s_type = null;
		if(txt.equals("jean")||txt.equals("bluejean")||txt.equals("JEAN")||txt.equals("BULEJEAN")||txt.equals("BLUE JEAN")||txt.equals("blue jean")||txt.equals("û��")||txt.equals("û����")){
			return s_type = "û����";
		}else if(txt.equals("cotton")||txt.equals("cotton pants")||txt.equals("cottonpants")||txt.equals("��")||txt.equals("COTTON")||txt.equals("COTTON PANTS")||txt.equals("COTTONPANTS")||txt.equals("�����")||txt.equals("���")){
			return s_type = "�����";
		}else if(txt.equals("blueskirt")||txt.equals("ûġ��")||txt.equals("ûġ")||txt.equals("ġ��")||txt.equals("BLUESKIRT")||txt.equals("BLUE SKIRT")||txt.equals("SKIRT")||txt.equals("skirt")){
			return s_type = "ûġ��";
		}else if(txt.equals("DRESS")||txt.equals("dress")||txt.equals("�巹��")){
			return s_type = "�巹��";
		}else if(txt.equals("Ƽ����")||txt.equals("t-shirts")||txt.equals("T����")||txt.equals("T-Shirts")||txt.equals("t-shirts")||txt.equals("shirts")||txt.equals("����")){
			return s_type = "Ƽ����";
		}else if(txt.equals("���׽ð�")||txt.equals("����")||txt.equals("leather")){
			return s_type = "���׽ð�";
		}else if(txt.equals("��Ż")||txt.equals("��Ż�ð�")||txt.equals("metal")||txt.equals("metal watch")||txt.equals("metalwatch")){
			return s_type = "��Ż�ð�";
		}else if(txt.equals("���")||txt.equals("�����")||txt.equals("dress")){
			return s_type = "�����";
		}else if(txt.equals("JEAN")){
			return s_type = "��Ʈ��";
		}else if(txt.equals("JEAN")){
			return s_type = "ũ�ν���";
		}else if(txt.equals("JEAN")){
			return s_type = "Ŭ��ġ��";
		}else if(txt.equals("JEAN")){
			return s_type = "����";
		}else if(txt.equals("JEAN")){
			return s_type = "����";
		}else if(txt.equals("JEAN")){
			return s_type = "����";
		}else if(txt.equals("JEAN")){
			return s_type = "������";
		}else if(txt.equals("JEAN")){
			return s_type = "�ȭ";
		}else if(txt.equals("JEAN")){
			return s_type = "����";
		}else
		{
			s_type = "error";
		}
		
		return s_type;
	}
	/*public static String return_subtype_bytextlike(String text){
		
	}*/
	//id�� ���ؼ� ������ ����Ʈ�� �˻��ϴ� �Լ�
	public static ArrayList<item_listdata> select_data_id(String _id){
		stmt = null;
		conn = null;
		rs = null;
		
		ArrayList<item_listdata> data = new ArrayList<item_listdata>();
		connect_DB();
		try{
		stmt = conn.createStatement();
		String query = "select ITEMID, ITEMNAME, ITEMPRICE, ITEMTYPE, BRANDNAME"
				+ " from ITEM WHERE USERID LIKE '"+_id+"'";
		rs = stmt.executeQuery(query);
		int count = 0;
			while(rs.next()){
				item_listdata temp = new item_listdata();
				String t_id = rs.getString("ITEMID");
				String t_iname= rs.getString("ITEMNAME");
				int t_price= rs.getInt("ITEMPRICE");
				String t_type= rs.getString("ITEMTYPE");
				String t_bname= rs.getString("BRANDNAME");
				
				if(t_id.equals(_id))
				{
					temp.set_id(t_id);
					temp.set_iname(t_iname);
					temp.set_price(t_price);
					temp.set_itype(t_type);
					temp.set_bname(t_bname);
					data.add(count,temp);
				}
				temp = null;
				count++;
				//System.out.println("�귣�� �� : "+data.get(a_count).get_name()+ " Ÿ��:"+data.get(a_count).get_type());
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
	//�������� �̸��� ���ؼ� �˻��ϴ� �Լ�
	public static ArrayList<item_listdata> select_data_iname(String _iname){
		stmt = null;
		conn = null;
		rs = null;
		
		ArrayList<item_listdata> data = new ArrayList<item_listdata>();
		connect_DB();
		try{
		stmt = conn.createStatement();
		String query = "select ITEMID, ITEMNAME, ITEMPRICE, ITEMTYPE, BRANDNAME"
				+ " from ITEM WHERE ITEMNAME LIKE '"+_iname+"'";
		int count = 0;
		rs = stmt.executeQuery(query);
			while(rs.next()){
				item_listdata temp = new item_listdata();
				String t_id = rs.getString("ITEMID");
				String t_iname= rs.getString("ITEMNAME");
				int t_price= rs.getInt("ITEMPRICE");
				String t_type= rs.getString("ITEMTYPE");
				String t_bname= rs.getString("BRANDNAME");
				
				if(t_iname.equals(_iname))
				{
					temp.set_id(t_id);
					temp.set_iname(t_iname);
					temp.set_price(t_price);
					temp.set_itype(t_type);
					temp.set_bname(t_bname);
					data.add(count, temp);
				}
				temp = null;
				count++;
				//System.out.println("�귣�� �� : "+data.get(a_count).get_name()+ " Ÿ��:"+data.get(a_count).get_type());
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
	//Ư�� ���� �̻��� �������� �˻��� �� �ִ� �Լ� ���� �� arraylist �迭
	public static ArrayList<item_listdata> select_data_upPriceInfo(int _price){
		stmt = null;
		conn = null;
		rs = null;
		
		ArrayList<item_listdata> data = new ArrayList<item_listdata>();
		
		connect_DB();
		try{
		stmt = conn.createStatement();
		String query = "select ITEMID, ITEMNAME, ITEMPRICE, ITEMTYPE, BRANDNAME"
				+ " from ITEM WHERE ITEMPRICE > '"+_price+"'";
		rs = stmt.executeQuery(query);
		int count = 0;
			while(rs.next())
			{
				item_listdata temp = new item_listdata();
				String t_id = rs.getString("ITEMID");
				String t_iname= rs.getString("ITEMNAME");
				int t_price= rs.getInt("ITEMPRICE");
				String t_type= rs.getString("ITEMTYPE");
				String t_bname= rs.getString("BRANDNAME");
				
				temp.set_id(t_id);
				temp.set_iname(t_iname);
				temp.set_price(t_price);
				temp.set_itype(t_type);
				temp.set_bname(t_bname);
				data.add(count,temp);	
				temp = null;
				count++;
				//System.out.println("�귣�� �� : "+data.get(a_count).get_name()+ " Ÿ��:"+data.get(a_count).get_type());
				
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
	//Ư�� ���� ������ ���� �˻� �� �� �ִ� �Լ� ���� �� �迭  �˻� �迭 ����Ʈ
	public static ArrayList<item_listdata> select_data_downPriceInfo_high(int _price){
		stmt = null;
		conn = null;
		rs = null;
		
		ArrayList<item_listdata> data = new ArrayList<item_listdata>();
		
		connect_DB();
		try{
		stmt = conn.createStatement();
		String query = "select ITEMID, ITEMNAME, ITEMPRICE, ITEMTYPE, BRANDNAME"
				+ " from ITEM WHERE ITEMPRICE < '"+_price+"' ORDER BY ITEMPRICE DESC";
		rs = stmt.executeQuery(query);
		int count = 0;
			while(rs.next())
			{
				item_listdata temp = new item_listdata();
				String t_id = rs.getString("ITEMID");
				String t_iname= rs.getString("ITEMNAME");
				int t_price= rs.getInt("ITEMPRICE");
				String t_type= rs.getString("ITEMTYPE");
				String t_bname= rs.getString("BRANDNAME");
				
				temp.set_id(t_id);
				temp.set_iname(t_iname);
				temp.set_price(t_price);
				temp.set_itype(t_type);
				temp.set_bname(t_bname);
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
	public static ArrayList<item_listdata> select_data_Info_high_preference(String bname1, String bname2, String bname3, String s_type,int _price){
		stmt = null;
		conn = null;
		rs = null;
		
		ArrayList<item_listdata> data = new ArrayList<item_listdata>();
		
		connect_DB();
		try{
		stmt = conn.createStatement();
		String query = "SELECT * FROM ITEM WHERE ITEMPRICE <"+_price+" "
				+ "AND SUBTYPE LIKE '"+s_type+"'"
				+ " AND BRANDNAME IN(SELECT BRANDNAME FROM ITEM "
				+ "WHERE BRANDNAME LIKE '"+bname1+"' OR BRANDNAME LIKE '"+bname2+"' OR BRANDNAME LIKE '"+bname3+"')"
				+ "ORDER BY ITEMPRCE DESC";
		rs = stmt.executeQuery(query);
		int count = 0;
			while(rs.next())
			{
				item_listdata temp = new item_listdata();
				String t_id = rs.getString("ITEMID");
				String t_iname= rs.getString("ITEMNAME");
				int t_price= rs.getInt("ITEMPRICE");
				String t_type= rs.getString("ITEMTYPE");
				String t_bname= rs.getString("BRANDNAME");
				String t_stype = rs.getString("SUBTYPE");
				
				temp.set_id(t_id);
				temp.set_iname(t_iname);
				temp.set_price(t_price);
				temp.set_itype(t_type);
				temp.set_bname(t_bname);
				temp.set_stype(t_type);
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
	
	public static ArrayList<item_listdata> select_data_downPriceInfo_low(int _price){
		stmt = null;
		conn = null;
		rs = null;
		
		ArrayList<item_listdata> data = new ArrayList<item_listdata>();
		
		connect_DB();
		try{
		stmt = conn.createStatement();
		String query = "select ITEMID, ITEMNAME, ITEMPRICE, ITEMTYPE, BRANDNAME"
				+ " from ITEM WHERE ITEMPRICE < '"+_price+"'ORDER BY ITEMPRICE ASC";
		rs = stmt.executeQuery(query);
		int count = 0;
			while(rs.next())
			{
				item_listdata temp = new item_listdata();
				String t_id = rs.getString("ITEMID");
				String t_iname= rs.getString("ITEMNAME");
				int t_price= rs.getInt("ITEMPRICE");
				String t_type= rs.getString("ITEMTYPE");
				String t_bname= rs.getString("BRANDNAME");
				
				temp.set_id(t_id);
				temp.set_iname(t_iname);
				temp.set_price(t_price);
				temp.set_itype(t_type);
				temp.set_bname(t_bname);
				data.add(count,temp);	
				temp = null;
				count++;
				//System.out.println("�귣�� �� : "+data.get(a_count).get_name()+ " Ÿ��:"+data.get(a_count).get_type());
				
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			if(conn != null)try{conn.close();}catch(SQLException e){}
			if(stmt != null)try{stmt.close();}catch(SQLException e){}
			if(rs != null)try{rs.close();}catch(SQLException e){}
		}
		/*Collections.sort(data, new Comparator<item_listdata>(){
			public int compare(item_listdata ct1, item_listdata ct2)
			{
				int result = 0;
				if(ct1.get_type().equals(ct2.get_type()))
				{
					result = (ct1.get_price() < ct2.get_price())?-1:(ct1.get_price()> ct2.get_price())?1:0;
				}
				return result;
			}
		});*/
		return data;
	}
	//������ ����Ʈ�� ��� ������ ����ϴ� �Լ� , ��� ������ ������ �ϴ� �Լ�
	public static ArrayList<item_listdata> select_data_Info(){
		stmt = null;
		conn = null;
		rs = null;
		
		ArrayList<item_listdata> data = new ArrayList<item_listdata>();
	
		connect_DB();
		try{
		stmt = conn.createStatement();
		String query = "select ITEMID, ITEMNAME, ITEMPRICE, ITEMTYPE, BRANDNAME"
				+ " from ITEM";
		rs = stmt.executeQuery(query);
		int count = 0;
			while(rs.next())
			{
				item_listdata temp = new item_listdata();
				String t_id = rs.getString("ITEMID");
				String t_iname= rs.getString("ITEMNAME");
				int t_price= rs.getInt("ITEMPRICE");
				String t_type= rs.getString("ITEMTYPE");
				String t_bname= rs.getString("BRANDNAME");
				
				temp.set_id(t_id);
				temp.set_iname(t_iname);
				temp.set_price(t_price);
				temp.set_itype(t_type);
				temp.set_bname(t_bname);
				data.add(count,temp);	
				temp = null;
				count++;
				//System.out.println("�귣�� �� : "+data.get(a_count).get_name()+ " Ÿ��:"+data.get(a_count).get_type());
				
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
	public static ArrayList<item_listdata> select_data_Brand_high(String _bname,int _cbudget ){
		stmt = null;
		conn = null;
		rs = null;
		
		ArrayList<item_listdata> data = new ArrayList<item_listdata>();
	
		connect_DB();
		try{
		stmt = conn.createStatement();
		String query = "select ITEMID, ITEMNAME, ITEMPRICE, ITEMTYPE, BRANDNAME WHERE BRANDNAME LIKE '"+_bname+"' and ITEMPRICE < "+_cbudget
				+ "' from ITEM ORDER BY ITEMPRICE DESC";
		rs = stmt.executeQuery(query);
		int count = 0;
			while(rs.next())
			{
				item_listdata temp = new item_listdata();
				String t_id = rs.getString("ITEMID");
				String t_iname= rs.getString("ITEMNAME");
				int t_price= rs.getInt("ITEMPRICE");
				String t_type= rs.getString("ITEMTYPE");
				String t_bname= rs.getString("BRANDNAME");
				
				temp.set_id(t_id);
				temp.set_iname(t_iname);
				temp.set_price(t_price);
				temp.set_itype(t_type);
				temp.set_bname(t_bname);
				data.add(count,temp);	
				temp = null;
				count++;
				//System.out.println("�귣�� �� : "+data.get(a_count).get_name()+ " Ÿ��:"+data.get(a_count).get_type());
				
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
	public static ArrayList<item_listdata> select_data_Brand_low(String _bname,int _cbudget ){
		stmt = null;
		conn = null;
		rs = null;
		
		ArrayList<item_listdata> data = new ArrayList<item_listdata>();
	
		connect_DB();
		try{
		stmt = conn.createStatement();
		String query = "select ITEMID, ITEMNAME, ITEMPRICE, ITEMTYPE, BRANDNAME WHERE BRANDNAME LIKE '"+_bname+"'and ITEMPRICE <"+_cbudget+"from ITEM ORDER BY ITEMPRICE ASC";
		rs = stmt.executeQuery(query);
		int count = 0;
			while(rs.next())
			{
				item_listdata temp = new item_listdata();
				String t_id = rs.getString("ITEMID");
				String t_iname= rs.getString("ITEMNAME");
				int t_price= rs.getInt("ITEMPRICE");
				String t_type= rs.getString("ITEMTYPE");
				String t_bname= rs.getString("BRANDNAME");
				
				temp.set_id(t_id);
				temp.set_iname(t_iname);
				temp.set_price(t_price);
				temp.set_itype(t_type);
				temp.set_bname(t_bname);
				data.add(count,temp);	
				temp = null;
				count++;
				//System.out.println("�귣�� �� : "+data.get(a_count).get_name()+ " Ÿ��:"+data.get(a_count).get_type());
				
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
	//�������� id�� ���ؼ� ������ �����ϴ� �Լ�
	public static void delete_data_id(String i_id){
		stmt = null;
		conn = null;
		rs = null;
		connect_DB();
		try{
			stmt = conn.createStatement();
			String query = "delete from ITEM WHERE ITEMID = '"+i_id +"'";
			int count = stmt.executeUpdate(query);
			
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				if(conn != null)try{conn.close();}catch(SQLException e){}
				if(stmt != null)try{stmt.close();}catch(SQLException e){}
				
			}
	}
	//�������� id�� ���ؼ� �̸��� �����ϴ� �Լ�
	public static void update_data_iname(String i_iname, String i_id){
		stmt = null;
		conn = null;
		rs = null;
		connect_DB();
		try{
			stmt = conn.createStatement();
			String query = "update ITEM set ITEMNAME = '"+i_iname +"' where ITEMID = '"+i_id +"'";
			stmt.executeQuery(query);
			
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				if(conn != null)try{conn.close();}catch(SQLException e){}
				if(stmt != null)try{stmt.close();}catch(SQLException e){}
				
			}
		
	}
	//�������� id�� ���ؼ� ������ �����ϴ� �Լ�
	public static void update_data_price(String i_id,int i_price){
		stmt = null;
		conn = null;
		rs = null;
		connect_DB();
		try{
			stmt = conn.createStatement();
			String query = "update ITEM set ITEMPRICE = '"+i_price +"' where ITEMID = '"+i_id +"'";
			stmt.executeQuery(query);
			
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				if(conn != null)try{conn.close();}catch(SQLException e){}
				if(stmt != null)try{stmt.close();}catch(SQLException e){}
				
			}
		
	}
	//�������� id�� ���ؼ� brand�̸��� �����ϴ� �Լ�
	public static void update_data_bname(String i_id, int i_bname){
		stmt = null;
		conn = null;
		rs = null;
		connect_DB();
		try{
			stmt = conn.createStatement();
			String query = "update ITEM set BRANDNAME = '"+i_bname +"' where ITEMID = '"+i_id +"'";
			stmt.executeQuery(query);
			
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				if(conn != null)try{conn.close();}catch(SQLException e){}
				if(stmt != null)try{stmt.close();}catch(SQLException e){}
				
			}
		
	}
	/*
	public static ArrayList<count_brandlist_data> select_perferece_item_bybname(String _bname, String c_budget ){
		stmt = null;
		conn = null;
		rs = null;
		Consume_list_table clt = new Consume_list_table();
		ArrayList<count_brandlist_data> ctemp = new ArrayList<count_brandlist_data>();
		ArrayList<count_brandlist_data> ptemp = new ArrayList<count_brandlist_data>();
		ctemp = clt.count_list_date(_id, _year, _month, _itype);
		
		for(int i = 0; i < 3; i++)
		{
			ptemp.add(i, ctemp.get(i));
		}
		return ptemp;
		
	}*/
	//�������� id�� ���ؼ� type�� �����ϴ� �Լ�
	public static void update_data_itype(String i_id,int i_type){
		stmt = null;
		conn = null;
		rs = null;
		connect_DB();
		try{
			stmt = conn.createStatement();
			String query = "update ITEM set ITEMTYPE = '"+i_type +"' where ITEMID = '"+i_id +"'";
			stmt.executeQuery(query);
			
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				if(conn != null)try{conn.close();}catch(SQLException e){}
				if(stmt != null)try{stmt.close();}catch(SQLException e){}
				
			}
		
	}
	//�������� ��� ������ id�� ���ؼ� �����ϴ� �Լ�
	public static void update_whole_data(String i_iname, int i_price, String i_type, String i_bname, String i_id){
		stmt = null;
		conn = null;
		rs = null;
		connect_DB();
		try{
			stmt = conn.createStatement();
			String query = "update ITEM set ITEMNAME = '"+i_iname +"', ITEMPRICE = '"+ i_price 
					+"', ITEMTYPE = '"+i_type +"', BRANDNAME = '"+i_bname+"' WHERE ITEMID = '"+i_id +"'";
			stmt.executeQuery(query);
			
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				if(conn != null)try{conn.close();}catch(SQLException e){}
				if(stmt != null)try{stmt.close();}catch(SQLException e){}
				
			}
		
	}
	
}
