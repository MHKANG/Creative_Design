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
		
		//db연결을 위한 계정 정보
		String url = null;
		String id = ""; // 계정 id
		String pw = ""; // 계쩡 비밀 번호
		
		try
		{
			
			Class.forName("oracle.jdbc.driver.OracleDriver");			
			try{
			url = "jdbc:oracle:thin:@localhost:1521:orcl"; // 현재 로컬 컴퓨터에 존재하는 포트 1521번에 orcl이라는 이름을 가진 db 주소
			conn = DriverManager.getConnection(url, id, pw);//conn 변수를 통해 db 연결에 필요한 정보 대입		
			}
			catch(SQLException e){
				System.out.println(e);
			}
		}
		catch(ClassNotFoundException e) //jdbc 드라이버를 찾을 수 없으면 에러 출력
		{
			System.out.println(e);
		
		}
	}
	//brandlist에 데이터를 삽입하기 위한 함수
	public static void insert_data(String user_id,String b_name, String ItemType, int count){
		//함수 실행시 static 변수 값들을 초기화 하여 혹시 모를 값의 오류를 방지
		stmt = null;
		conn = null;
		rs = null;
		//db 연결
		connect_DB();
		
		try{
		stmt = conn.createStatement();
		//쿼리문 작성
		String query = "insert into BRAND_LIST(USERID,BRANDNAME, ITEMTYPE, BRANDCOUNT) values('"+user_id+"','"+ b_name+"','"+ItemType+"',"+count+")";
		stmt.executeQuery(query);
		//System.out.println("삽입이 성공적으로 이루어졌습니다.");
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			if(conn != null)try{conn.close();}catch(SQLException e){}
			if(stmt != null)try{stmt.close();}catch(SQLException e){}
		}
	}
	//brand 리스트 값을 가지는 data class를 만들어 이를 arraylist의 단위로 사용한다.
	public static class brand_listdata{
		//list 데이터에 필요한 변수
		String b_name; //브랜드 이름
		String b_type; //브랜드 타입
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
		
		//검색한 값들을 저장하기 위한 배열 리스트 data를 선언
		ArrayList<brand_listdata> data = new ArrayList<brand_listdata>();
		data = select_data_byinfo(id, name ,type);
		int a_count = data.get(0).get_count()+count;
		//배열을 추가적으
		connect_DB();
		try{
		stmt = conn.createStatement();
		//쿼리문 작성
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
	//이름을 통해서 검색을 하는 함수
	public static ArrayList<brand_listdata> select_data_byinfo(String id, String _name, String type){
		stmt = null;
		conn = null;
		rs = null;
		
		//검색한 값들을 저장하기 위한 배열 리스트 data를 선언
		ArrayList<brand_listdata> data = new ArrayList<brand_listdata>();
		//배열을 추가적으
		connect_DB();
		try{
		stmt = conn.createStatement();
		//쿼리문 작성
		String query = "select * from BRAND_LIST WHERE USERID LIKE '"+id+"' and BRANDNAME LIKE '"+_name+"' and ITEMTYPE LIKE '"+type+"'";
		
		rs = stmt.executeQuery(query);
		int count = 0;
			while(rs.next()){
				//임시 변수 t_n과 t_i를 생성하여 현재 resultset에 있는 brandname과 itemtype에 있는 값들으 임시적으로 저장
				String t_n = rs.getString("BRANDNAME");
				String t_i = rs.getString("ITEMTYPE");
				String t_id = rs.getString("USERID");
				int t_c = rs.getInt("BRANDCOUNT");
				//임시적으로 brandlistdata를 저장하기 위한 temp변수를 생성
				brand_listdata temp = new brand_listdata();
				//만약 t_n 값이 찾고자하는 name과 같다면
				
					//임시 변수에 저장된 값들을 temp 임시 저장 배열에 저장한다.
				temp.set_name(t_n);
				temp.set_type(t_i);
				temp.set_id(t_id);
				temp.set_count(t_c);
				//temp에 저장 된 값들을 검색한 값들을 저장하기 위한 data 배열에 저장을 한다.
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
		//모든 검색이 완료 된 이후 데이터 배열을 반환하여 리스트를 알 수 있게 한다.
		return data;
	}
	//아이템 타입으로 검색을 하는 함수 , 변수들의 내용은 위의 함수와 모두 같으며 리턴 값 역시 arraylist배열이다.
	//여기서 arraylist란 동적 배열을 사용하기 위해서 사용된 template 타입list로 <>안에 들어 가는 것이 취급하는 데이터 타입이다.
	//<>안에는 int, string부터 시작해서 class도 들어 갈 수 있다.
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
		
		//검색한 값들을 저장하기 위한 배열 리스트 data를 선언
		ArrayList<brand_listdata> data = new ArrayList<brand_listdata>();
		//배열을 추가적으
		connect_DB();
		try{
		stmt = conn.createStatement();
		//쿼리문 작성
		String query = "select * from brand_list where userid like '"+id+"' and itemtype like '"+type+"' order by BRANDCOUNT desc";
		rs = stmt.executeQuery(query);
		int count = 0;
			while(rs.next()){
				//임시 변수 t_n과 t_i를 생성하여 현재 resultset에 있는 brandname과 itemtype에 있는 값들으 임시적으로 저장
				String t_n = rs.getString("BRANDNAME");
				String t_i = rs.getString("ITEMTYPE");
				String t_id = rs.getString("USERID");
				int t_c = rs.getInt("BRANDCOUNT");
				//임시적으로 brandlistdata를 저장하기 위한 temp변수를 생성
				brand_listdata temp = new brand_listdata();
				
				//임시 변수에 저장된 값들을 temp 임시 저장 배열에 저장한다.
				temp.set_name(t_n);
				temp.set_type(t_i);
				temp.set_id(t_id);
				temp.set_count(t_c);
				//temp에 저장 된 값들을 검색한 값들을 저장하기 위한 data 배열에 저장을 한다.
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
		//모든 검색이 완료 된 이후 데이터 배열을 반환하여 리스트를 알 수 있게 한다.
		return data;
	}
	
	public static ArrayList<brand_listdata> select_data(){
		stmt = null;
		conn = null;
		rs = null;
		
		//검색한 값들을 저장하기 위한 배열 리스트 data를 선언
		ArrayList<brand_listdata> data = new ArrayList<brand_listdata>();
		//배열을 추가적으
		connect_DB();
		try{
		stmt = conn.createStatement();
		//쿼리문 작성
		String query = "select * from brand_list";
		rs = stmt.executeQuery(query);
		int count = 0;
			while(rs.next()){
				//임시 변수 t_n과 t_i를 생성하여 현재 resultset에 있는 brandname과 itemtype에 있는 값들으 임시적으로 저장
				String t_n = rs.getString("BRANDNAME");
				String t_i = rs.getString("ITEMTYPE");
				String t_id = rs.getString("USERID");
				int t_c = rs.getInt("BRANDCOUNT");
				//임시적으로 brandlistdata를 저장하기 위한 temp변수를 생성
				brand_listdata temp = new brand_listdata();
				//임시 변수에 저장된 값들을 temp 임시 저장 배열에 저장한다.
				temp.set_name(t_n);
				temp.set_type(t_i);
				temp.set_id(t_id);
				temp.set_count(t_c);
				//temp에 저장 된 값들을 검색한 값들을 저장하기 위한 data 배열에 저장을 한다.
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
		//모든 검색이 완료 된 이후 데이터 배열을 반환하여 리스트를 알 수 있게 한다.
		return data;
	}
	
	//이름을 통해서 삭제하는 함수
	public static void delete_data_byname(String b_name){
		stmt = null;
		conn = null;
		rs = null;
		//db연결
		connect_DB();
		try{
			stmt = conn.createStatement();
			String query = "delete from BRAND_LIST where BRANDNAME = '"+b_name +"'";
			int count = stmt.executeUpdate(query);
			System.out.println("성공적으로 지웠습니다." + count);
			
			
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				if(conn != null)try{conn.close();}catch(SQLException e){}
				if(stmt != null)try{stmt.close();}catch(SQLException e){}
				
			}
	}
	//아이템 타입을 통해서 데이터를 삭제하는 함수
	public static void delete_data_byItype(String Itype){
		stmt = null;
		conn = null;
		rs = null;
		connect_DB();
		try{
			stmt = conn.createStatement();
			String query = "delete from BRAND_LIST where ITEMTYPE = '"+Itype +"'";
			stmt.executeQuery(query);
			System.out.println("성공적으로 지웠습니다.");
			
			
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				if(conn != null)try{conn.close();}catch(SQLException e){}
				if(stmt != null)try{stmt.close();}catch(SQLException e){}
				
			}
	}
	//브랜드 이름을 통해서 아이템 타입을 변경하는 함수 
	
	

}
