package jdbc;
		
import java.sql.*;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.Executors;

import jdbc.Brand_list_table.brand_listdata;
import jdbc.Consume_list_table.consume_listdata;
import jdbc.Consume_list_table.count_brandlist_data;
import jdbc.Consume_list_table.sum_price_listdata;
import jdbc.Item_table.item_listdata;
import jdbc.Monthly_total_consume_table.month_listdata;
import jdbc.Preference_brand_table.p_brandinfo;
import jdbc.User_info_table.user_listdata;

import java.net.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.concurrent.*;

public class JDBC_MAIN {

	public static void main(String[] args)throws Exception{
		User_info_table uit = new User_info_table();
		Consume_list_table clt = new Consume_list_table();
		Monthly_total_consume_table mtct = new Monthly_total_consume_table();
		Preference_brand_table plt = new Preference_brand_table();
		Brand_list_table blt = new Brand_list_table();
		ArrayList<count_brandlist_data> ctemp = new ArrayList<count_brandlist_data>();
		ArrayList<count_brandlist_data> ptemp = new ArrayList<count_brandlist_data>();
		ArrayList<month_listdata> mtemp = new ArrayList<month_listdata>();
		ArrayList<brand_listdata> btemp = new ArrayList<brand_listdata>();
		Item_table it = new Item_table();
		ArrayList<item_listdata> itemp = new ArrayList<item_listdata>();
		//String temp = it.reutrn_subtype_bytexten("BLUEJEAN");
		//System.out.println(temp);
		//itemp = it.select_data_bybrandtype("NIKE", "�Ź�", 200000);
		/*for(int i = 0; i < itemp.size(); i++){
			System.out.println(itemp.get(i).get_iname() +" "+itemp.get(i).get_price());
		}
		//blt.update_brandcount("maestrok","GUCCI" , "����", 2);
		
		/*btemp = blt.select_data_byinfo("maestrok","GUCCI" ,"�ð�");
		for(int i = 0; i < btemp.size(); i++){
			System.out.println(btemp.get(i).get_name()+" "+btemp.get(i).get_count());
		}*
		//blt.insert_data("maestrok","GUCCI" , "����", 2);
		/*blt.insert_data("maestrok","CHANEL" , "����", 3);
		blt.insert_data("maestrok","PRADA" , "����", 7);
		blt.insert_data("maestrok","MIU MIU" , "����", 10);
		blt.insert_data("maestrok","COLOMBO" , "����", 4);
		blt.insert_data("maestrok","HERMES" , "����", 9);
		blt.insert_data("maestrok","DELVAUX" , "����", 13);
		blt.insert_data("maestrok","BOTTEGA VENETA" , "����", 1);*/
		
		//ArrayList<sum_price_listdata> ne = new ArrayList<sum_price_listdata>();
		//ne = clt.pricesum_list_date_ness("maestrok");
		/*for(int i = 0; i < ne.size(); i++){
			System.out.println(ne.get(i).get_price());
		}*/
		//ArrayList<month_listdata> ktemp = new ArrayList<month_listdata>();
		/*for(int i = 1; i <6; i++){
			mtct.create_month_data("maestrok", 2016, i);
		}*/
		//System.out.println(ktemp.size());
		//uit.update_curbudget_byconsume("maestrok", 0);
		//ArrayList<user_listdata> utemp = new ArrayList<user_listdata>();
		//utemp = uit.select_user_list_byid("maestrok");
		//uit.update_cb_plus_mr("maestrok", 40000);
		//System.out.println(utemp.get(0).get_cb());
		/*clt.insert_data("maestrok","2015-05-04 17:51", "�Ƿ�", "reebok", "24000��", "Necessary","��û����");
		clt.insert_data("maestrok", "2016-06-11 17:52", "������", "�ƻ꺴��", "25000��", "Subsidiary","�ƻ꺴��������");
		clt.insert_data("maestrok", "2013-06-13 17:41", "�ĺ�", "�ƿ���", "50000��", "Necessary","������ũ");
		clt.insert_data("maestrok", "2013-07-24 17:52", "�Ź�", "nike", "32000��", "Necessary","����Ű �Ź�");
		clt.insert_data("maestrok", "2012-06-23 17:52", "�Ź�", "reebok", "23000��", "Necessary","���ϽŹ�");
		clt.insert_data("maestrok", "2010-06-30 20:53", "����", "�ƽþƳ��װ�", "700000��", "Subsidiary","�ƽþƳ�����");
		clt.insert_data("maestrok", "2010-03-12 17:53", "�ð�", "casio", "230000��", "Subsidiary","ī�ÿ��ð�");
		clt.insert_data("maestrok", "2014-06-03 17:41", "�ڵ��", "GUCCI", "3100000��", "Subsidiary","�����ڵ��");
		clt.insert_data("maestrok", "2016-06-09 18:54", "�ð�", "casio", "1200000��", "Subsidiary","ī�ÿ��ð�2");
		clt.insert_data("maestrok", "2016-06-01 15:37", "�����", "������", "2700��", "Necessary","��������");
		clt.insert_data("maestrok", "2016-06-02 14:52", "���⼼", "�ѱ�����", "20000��", "Necessary","�ѱ����⼼��");
		clt.insert_data("maestrok", "2016-06-04 17:43", "�Ƿ�", "addidas", "52000��", "Necessary","�Ƶ�ٽ�����");
		clt.insert_data("maestrok", "2016-06-07 18:21", "��", "¥����", "24000��", "Subsidiary","����2��");
		*/
		//ArrayList<user_listdata> utemp = new ArrayList<user_listdata>();
		//utemp =uit.select_user_list_byid("maestro");
		//System.out.println(utemp.get(0).get_mr());
		
		//Calendar c = Calendar.getInstance();
		//String ntime = new String();
		//ntime = String.valueOf(c.get(Calendar.YEAR));
		
		//System.out.println(result);
		//mtct.create_month_data("mhkang", "2016");
		
		//System.out.println(mtemp.get(0).get_tconsume());
		/*for(int i = 0; i < ptemp.size(); i++){
			System.out.println(ptemp.get(i).get_bname() +" "+ ptemp.get(i).get_count());
		}
		*/
		
		
		
		ExecutorService pool = Executors.newFixedThreadPool(50);
		System.out.println("����");
		//ServerSocket server = null;
		/*try (ServerSocket server = new ServerSocket(46000)) {
			
            while (true) {
                try {
                	 
                    Socket connection = server.accept();
                   
                    Callable<Void> task = new SocketClient(connection);
                    pool.submit(task);
                } catch (IOException ex) {}
            }
        } catch (IOException ex) {
            System.err.println("Could't start server");
           // server.close();
        }*/
     
	}
	//check�� true �� ��� ���� ���� �Ȱ�
	public static boolean check_change_month(){
		boolean check = false;
		Calendar c = Calendar.getInstance();
		String ntime = new String();
		String ntime2 = new String();
		ntime = String.valueOf(c.get(Calendar.YEAR));
		ntime2 =String.valueOf(c.get(Calendar.MONTH)+1);
		Calendar cal = new GregorianCalendar();
		cal.add(Calendar.DATE, -1);
		
		if(Integer.parseInt(ntime)-(cal.get(Calendar.YEAR)) == 1 ||Integer.parseInt(ntime2)-(cal.get(Calendar.MONTH)+1) == 1 )
		{
			check = true;
		}
		return check;
		
	}
	private static class SocketClient implements Callable<Void> 
	   {
	      
	      private Socket connection;
	      private boolean connect = false;
	      String message;
	      User_info_table uit = new User_info_table();
	      Consume_list_table clt = new Consume_list_table();
	      Monthly_total_consume_table mtct = new Monthly_total_consume_table();
	      Brand_list_table blt = new Brand_list_table();
	      Preference_brand_table pbt = new Preference_brand_table();
	      Monthly_total_consume_table mctc = new Monthly_total_consume_table();
	      
	      /*
	      ObjectOutputStream outputStream = new ~~
	      outputStream.writeObject(String)
	      outputStream.flush();
	      */
	      
	      SocketClient(Socket connection)
	      {
	         this.connection = connection;
	      }
	      //�޼����� �������� �������� ���������� ��� ���� �ֵ����Ѵ�.
	      @Override
	      public Void call() throws Exception
	      {
	         connect = true;
	         String p_m[];
	         String delimiter = "%";
	         
	         Calendar c = Calendar.getInstance();
		     String ntime = new String();
		     String ntime2 = new String();
			 ntime = String.valueOf(c.get(Calendar.YEAR));
			 ntime2 =String.valueOf(c.get(Calendar.MONTH)+1);
	         System.out.println(connection.getInetAddress() + " Start!");
	         
	         while(connect)
	         {
	             try
	             {
	            	Calendar cal = new GregorianCalendar();
	         		cal.add(Calendar.DATE, -1);
	         		
	                ObjectInputStream inputStream = new ObjectInputStream(connection.getInputStream());
	                message = (inputStream.readObject()).toString();
	               p_m = message.split(delimiter);
	              
	               ArrayList<user_listdata> temp = new ArrayList<user_listdata>(); 
	               temp = uit.select_user_list_byid(p_m[1]);
	               int flag = Integer.parseInt(p_m[0]);
	               
	               if(check_change_month() == true){
	            	   uit.update_cb_plus_mr(p_m[1], temp.get(0).get_mr());
	            	   clt.initialize_list(p_m[1], (cal.get(Calendar.YEAR)), (cal.get(Calendar.MONTH)+1));
	            	   mtct.create_month_data(p_m[1], Integer.parseInt(ntime), Integer.parseInt(ntime2));
	            	   
	            	 //  uit.update_cb_plus_mr(_id, mr);
	               }
	               
	               int _nb = mtct.cal_avg_necessary(p_m[1]);
	               int _sb = mtct.cal_avg_subsidiary(p_m[1]);
	               uit.update_nb_userdata(p_m[1],_nb);
	               uit.update_sb_userdata(p_m[1],_sb);
	               System.out.println(message);
	               
	               ObjectOutputStream outputStream = new ObjectOutputStream(connection.getOutputStream());
	               //flag�� ��쿡 ���� ���� �Լ��� ������.
	               switch(flag)
	               {
	               //�α���
	               	case 0: 
	               	{
	               		//�ǹٸ� id�� pw���� üũ �Ѵ�.
	            	  if(uit.login_check(p_m[1], p_m[2]) == true){
	            		  outputStream.writeObject("Accept");
	            		  outputStream.flush();
	            	  }
	            	  else{
	            		  outputStream.writeObject("Reject");
	            		  outputStream.flush();
	            	  }
	            	  break;
	               	}
	               	//���̵� ����
	               	case 1:
	               	 {
	               		//�⵵�� ����Ʈ ������ ���� �⵵�� ���� �ִ´�.
	               		 //id ������ �ߺ� üũ
	               	   if(uit.check_duplication(p_m[1])== true){
	               		   uit.insert_data(p_m[1], p_m[2], p_m[6], 0, Integer.parseInt(p_m[3]), 0, 0, Integer.parseInt(p_m[4]),Integer.parseInt(p_m[5]));
	               		   mtct.create_month_data(p_m[1], Integer.parseInt(ntime),Integer.parseInt(ntime2));
	               		   
	               		   outputStream.writeObject("Accept");
	               		   outputStream.flush();
	               	   }
	               	   else{
	               		   outputStream.writeObject("Reject");
	               		   outputStream.flush();
	               	   }
	               	   break;
	                 }
	               	 //����
	               	case 2:
	               	{
	               	  ArrayList<month_listdata> mtemp = new ArrayList<month_listdata>();
	               	  mtemp = mtct.select_data_byidPlusyear(p_m[1], Integer.parseInt(ntime));
	               	  String data;
	               	  if(temp.size()== 0){
	               		  data = null;
	               	  }
	               	  else
	               	  {
			              data = Integer.toString(mtemp.get(36).get_tconsume()/10000);
			              if(mtemp.size()>= 36){
			            	  for(int i = 1; i < 37; i++){
				               		 data += "%"+Integer.toString(mtemp.get(37-i).get_tconsume()/10000);
				              }
			              }else if(mtemp.size()<36)
			              {
				              for(int i = 1; i < mtemp.size(); i++){
				               		 data += "%"+Integer.toString(mtemp.get(mtemp.size()-i).get_tconsume()/10000);
				              }
			              }
			              outputStream.writeObject(data);
	              		   outputStream.flush();
		               	}
	               	}
	               	break;
	               	 //�Һ� ��� �ҷ�����
	               	case 3 :
	               	{
	               	  ArrayList<consume_listdata> ctemp = new ArrayList<consume_listdata>();
	               	 //id�� ���ؼ� db�� �ִ� id�� �´� �Һ� ����� �����´�.
	               	 
	               	 ctemp = clt.select_data_id(p_m[1]);
	               	 String data = ctemp.get(0).get_time()+"%"+ctemp.get(0).get_price()+"%"+ctemp.get(0).get_brand()+"%"+ctemp.get(0).get_iname();
	               	  for(int i = 1; i < ctemp.size(); i++)
	               	  {
	               		  data+= "%" +ctemp.get(i).get_time()+"%"+ctemp.get(i).get_price()+"%" + ctemp.get(i).get_brand()+"%"+ctemp.get(i).get_iname();
	               		  
	               	  }
	               	 outputStream.writeObject(data);
             		 outputStream.flush();
	               	}
	               	break;
	            	
	          
	               	//���� ���� ����
	               	case 4 :
	               	{
	               	 //System.out.println(message);
	               	  ArrayList<user_listdata> utemp = new ArrayList<user_listdata>();	    
	               	 
	               	  utemp = uit.select_user_list_byid(p_m[1]);
	               	  
	               	  String data = utemp.get(0).get_mr()+"%"+utemp.get(0).get_goal()+"%"+utemp.get(0).get_gender();
	               	 
	               	  outputStream.writeObject(data);
	               	  outputStream.flush();
	               	   
	               	}
	               	break;
	               	//message : flag + id + text(������� �ؽ�Ʈ)
	               	case 5:
	               	{
	               		String result = null;
	               		ArrayList<item_listdata> result_item = new ArrayList<item_listdata>();
	               		
	               		ArrayList<user_listdata> utemp = new ArrayList<user_listdata>();
	               		ArrayList<brand_listdata> s_table = new ArrayList<brand_listdata>();
	               		ArrayList<p_brandinfo> ptemp = new ArrayList<p_brandinfo>();
	               		Brand_list_table blt = new Brand_list_table();
	               		User_info_table uit = new User_info_table();
	               		int median = 0;
	               		int cur_b = 0;
	               		int d_ten_b = cur_b/10;
	               		Item_table it = new Item_table();
	               		Preference_brand_table pbt = new Preference_brand_table();
	               		String sub_type = it.return_subtype_bytexten(p_m[2]);
	               		System.out.println("���� ��Ʈ�� ����");
	               		if(sub_type.equals("error")){
	               			result = "Reject";
	               		}
	               		else
	               		{
		               		String item_type = it.reutrn_type_by_subtype(sub_type); 
		               		System.out.println("������ Ÿ�� ��Ʈ�� ����");
		               		s_table = blt.select_data_countsort(p_m[1], item_type);
		               		System.out.println("�귣�� Ÿ�� �������� �� ����");
		               		pbt.insert_pdata(p_m[1], s_table.get(0).get_name(), s_table.get(1).get_name(), s_table.get(2).get_name(),s_table.get(0).get_type());
		               		System.out.println("�켱 ���� ����Ʈ�� ������ ���� ����");
		               		median = it.cal_median_of_three_brand(s_table.get(0).get_name(), s_table.get(1).get_name(), s_table.get(2).get_name(), sub_type);
		               		System.out.println("�̵�� ������ �� ���ϱ� ����");
		               		utemp = uit.select_user_list_byid(p_m[1]);
		               		System.out.println("���� ������ �� ���ϱ� ����");
		               		cur_b = utemp.get(0).get_cb();
		               		System.out.println("���� �ܾ� ���� �� ���ϱ� ����");
		               		if(d_ten_b > median){
		               			result_item = it.select_data_Info_high_preference(s_table.get(0).get_name(), s_table.get(1).get_name(), s_table.get(2).get_name(), sub_type, d_ten_b);
		               			System.out.println("���ǿ� �´� ������ ����Ʈ ���� ����");
		               			result = result_item.get(0).get_iname()+"%"+result_item.get(0).get_price()+"%"
		               		   +result_item.get(1).get_iname()+"%"+result_item.get(1).get_price()+"%"
		               		   +result_item.get(2).get_iname()+"%"+result_item.get(2).get_price();
		               			
		               		}else if(median > d_ten_b){
		               			result_item = it.select_data_Info_high_preference(s_table.get(0).get_name(), s_table.get(1).get_name(), s_table.get(2).get_name(), sub_type, d_ten_b);
		               			System.out.println("���ǿ� �´� ������ ����Ʈ ���� ����");
		               			result = result_item.get(0).get_iname()+"%"+result_item.get(0).get_price()+"%"
		    	               			+result_item.get(1).get_iname()+"%"+result_item.get(1).get_price()+"%"
		    	               			+result_item.get(2).get_iname()+"%"+result_item.get(2).get_price();
		               		}else{
		               			result_item = it.select_data_Info_high_preference(s_table.get(0).get_name(), s_table.get(1).get_name(), s_table.get(2).get_name(), sub_type, d_ten_b);
		               			System.out.println("���ǿ� �´� ������ ����Ʈ ���� ����");
		               			result = result_item.get(0).get_iname()+"%"+result_item.get(0).get_price()+"%"
		    	               	+result_item.get(1).get_iname()+"%"+result_item.get(1).get_price()+"%"
		    	               	+result_item.get(2).get_iname()+"%"+result_item.get(2).get_price();
		               		}
	               		}
	               		System.out.println("��ȣ���� �´� �˻� ����");
	               		outputStream.writeObject(result);
	               		outputStream.flush();
	               	}
	               	case 6 :
	               	{
	               		ArrayList<sum_price_listdata> ne = new ArrayList<sum_price_listdata>();
	               		ArrayList<sum_price_listdata> sub = new ArrayList<sum_price_listdata>();
	               		ArrayList<user_listdata> uutemp = new ArrayList<user_listdata>();
	               		ArrayList<month_listdata> mmtemp = new ArrayList<month_listdata>();
	               		String n_Str = null;
	               		String s_Str = null;
	               		String n_temp = null;
	               		String temp1 = null;
	               		String temp2 = null;
	               		int sum_p =0;
	               		int sum_p2 = 0;
	               		String result = null;
	               		ne = clt.pricesum_list_date_ness(p_m[1]);
	               		sub = clt.pricesum_list_date_subsidiary(p_m[1]);
	               		if(ne.size()<3){
	               			if(ne.size() == 0)
	               				temp1 = n_Str;
	               			else{
		               			n_Str = ne.get(0).get_type() + "%" + ne.get(0).get_price();
		               			for(int i = 1; i < ne.size(); i++){
		               				n_Str +="%"+ ne.get(i).get_type() +"%"+ ne.get(i).get_price();
		               			}
	               			}
	               			temp1 = n_Str;
	               		}
	               		else if(ne.size() >=3){
	               			n_Str = ne.get(0).get_type() + "%" + ne.get(0).get_price();
		               		for(int i = 1; i < 3; i++){
		               		
		               			n_Str += "%"+ne.get(i).get_type() +"%" + ne.get(i).get_price();
		               		}
		               		for(int i = 3; i <ne.size(); i++){
		               			sum_p += ne.get(i).get_price();
		               		}
		               		temp1 = n_Str +"%"+"��Ÿ"+"%"+Integer.toString(sum_p);
	               		}
	               		if(sub.size()<3){
	               			if(sub.size() == 0)
	               				temp2 = s_Str;
	               			else{
		               			s_Str = sub.get(0).get_type() + "%" + sub.get(0).get_price();
		               			for(int i = 1; i < ne.size(); i++){
		               				s_Str += "%"+sub.get(i).get_type() +"%"+ sub.get(i).get_price();
		               			}
	               			}
	               			temp2 = s_Str;
	               		}
	               		if(sub.size() >=3){
	               			s_Str = sub.get(0).get_type() + "%" + sub.get(0).get_price();
		               		for(int i = 1; i < 3; i++){
		               			s_Str += "%"+ sub.get(i).get_type() +"%" + sub.get(i).get_price();
		               		}
		               		for(int i = 3; i <sub.size(); i++){
		               			sum_p2 += sub.get(i).get_price();
		               		}
		               		temp2 = s_Str +"%"+"��Ÿ"+"%"+Integer.toString(sum_p2);
	               		}
	               		if(ne.size()>=4 && sub.size()>=4){
	               			result = temp1 +"%"+ temp2+"%" +"4"+"%"+"4";
	               		}
	               		else if(ne.size()<4 && sub.size()<4){
	               			result = temp1 +"%"+ temp2+"%" + Integer.toString(ne.size())+"%"+Integer.toString(sub.size());
	               		}
	               		else if(ne.size()>=4 && sub.size()<4){
	               			result = temp1 +"%"+ temp2+"%" +"4"+"%"+Integer.toString(sub.size());
	               		}
	               		else if(ne.size()<4 && sub.size()>=4){
	               			result = temp1 +"%"+ temp2+"%" +Integer.toString(ne.size())+"%"+"4";
	               		}
	               		uutemp = uit.select_user_list_byid(p_m[1]);
	               		mmtemp = mtct.select_data_byidPlusyear(p_m[1], Integer.parseInt(ntime));
	               		String data = result +"%"+ uutemp.get(0).get_nb() +"%" +mmtemp.get(0).get_nb()+"%"
	               			+ uutemp.get(0).get_sb()+"%"+mmtemp.get(0).get_sb()+"%"+uutemp.get(0).get_cb()+"%"+uutemp.get(0).get_goal();
	               		//System.out.print(result);
	               		outputStream.writeObject(data);
	               		outputStream.flush();
	               		
	               	}
	               	break;
	               	//SMS ���� ���� �� ACCEPT : ����
	               	case 7:
	               	{
	               		String[] p_price;
	               		String del = "��";
	               		p_price = p_m[4].split(del);
	            		String[] s_time;
	            		String del2 ="-";
	            		s_time = p_m[7].split(del2);
	            		clt.insert_data(p_m[1], p_m[7], p_m[5], p_m[3], p_m[4], p_m[6],p_m[2]);
	            		uit.update_curbudget_byconsume(p_m[1], Integer.parseInt(p_price[0]));
	            		
	            		if(p_m[6].equals("Necessary")){
	            			mtct.update_month_necessary(p_m[1],Integer.parseInt(s_time[0]) ,Integer.parseInt(s_time[1]), Integer.parseInt(p_price[0]));
	            			
	            		}else if(p_m[6].equals("Subsidiary")){
	            			mtct.update_month_subsidiary(p_m[1],Integer.parseInt(s_time[0]) ,Integer.parseInt(s_time[1]), Integer.parseInt(p_price[0]));
	            			
	            		}
	               		mtct.add_data(p_m[1], Integer.parseInt(s_time[0]), Integer.parseInt(s_time[1]), Integer.parseInt(p_price[0]));
	               		;
	               		outputStream.writeObject("Accept");
	               		outputStream.flush();
	               		
	               		break;
	               	}
	               	
	               	case 8:
	               	{
	               		ArrayList<user_listdata> utemp = new ArrayList<user_listdata>();	    
		               	uit.update_mr_userdata(p_m[1], Integer.parseInt(p_m[2]));
		               	uit.update_goal_userdata(p_m[1],Integer.parseInt(p_m[3]));
		               	uit.update_gender_userdata(p_m[1], p_m[4]);
		               //	uit.update_cb_userdata(p_m[1],Integer.parseInt(p_m[2]));
		               	
		               	outputStream.writeObject("Accept");
	               		outputStream.flush();
	               	}
	               	break;
	               	//�Ϲ� ���
	               	case 9:
	               	{
	               		String result = null;
	               		ArrayList<item_listdata> result_item = new ArrayList<item_listdata>();
	               		
	               		ArrayList<user_listdata> utemp = new ArrayList<user_listdata>();
	               		ArrayList<brand_listdata> s_table = new ArrayList<brand_listdata>();
	               		ArrayList<p_brandinfo> ptemp = new ArrayList<p_brandinfo>();
	               		Brand_list_table blt = new Brand_list_table();
	               		User_info_table uit = new User_info_table();
	               		int median = 0;
	               		int cur_b = 0;
	               		int d_ten_b = cur_b/10;
	               		Item_table it = new Item_table();
	               		//Preference_brand_table pbt = new Preference_brand_table();
	               		String sub_type = it.return_subtype_bytexten(p_m[2]);
	               		System.out.println("���� ��Ʈ�� ����");
	               		if(sub_type.equals("error")){
	               			result = "Reject";
	               		}
	               		else
	               		{
		               		String item_type = it.reutrn_type_by_subtype(sub_type); 
		               		System.out.println("������ Ÿ�� ��Ʈ�� ����");
		               		s_table = blt.select_data_countsort(p_m[1], item_type);
		               		System.out.println("�귣�� Ÿ�� �������� �� ����")
		               		median = it.cal_median_of_all_brand(sub_type);
		               		System.out.println("�̵�� ������ �� ���ϱ� ����");
		               		utemp = uit.select_user_list_byid(p_m[1]);
		               		System.out.println("���� ������ �� ���ϱ� ����");
		               		cur_b = utemp.get(0).get_cb();
		               		System.out.println("���� �ܾ� ���� �� ���ϱ� ����");
		               		if(d_ten_b > median){
		               			result_item = it.select_data_Info_high_preference(s_table.get(0).get_name(), s_table.get(1).get_name(), s_table.get(2).get_name(), sub_type, d_ten_b);
		               			System.out.println("���ǿ� �´� ������ ����Ʈ ���� ����");
		               			result = result_item.get(0).get_iname()+"%"+result_item.get(0).get_price()+"%"
		               		   +result_item.get(1).get_iname()+"%"+result_item.get(1).get_price()+"%"
		               		   +result_item.get(2).get_iname()+"%"+result_item.get(2).get_price();
		               			
		               		}else if(median > d_ten_b){
		               			result_item = it.select_data_Info_high_preference(s_table.get(0).get_name(), s_table.get(1).get_name(), s_table.get(2).get_name(), sub_type, d_ten_b);
		               			System.out.println("���ǿ� �´� ������ ����Ʈ ���� ����");
		               			result = result_item.get(0).get_iname()+"%"+result_item.get(0).get_price()+"%"
		    	               			+result_item.get(1).get_iname()+"%"+result_item.get(1).get_price()+"%"
		    	               			+result_item.get(2).get_iname()+"%"+result_item.get(2).get_price();
		               		}else{
		               			result_item = it.select_data_Info_high_preference(s_table.get(0).get_name(), s_table.get(1).get_name(), s_table.get(2).get_name(), sub_type, d_ten_b);
		               			System.out.println("���ǿ� �´� ������ ����Ʈ ���� ����");
		               			result = result_item.get(0).get_iname()+"%"+result_item.get(0).get_price()+"%"
		    	               	+result_item.get(1).get_iname()+"%"+result_item.get(1).get_price()+"%"
		    	               	+result_item.get(2).get_iname()+"%"+result_item.get(2).get_price();
		               		}
	               		}
	               		System.out.println("��ȣ���� �´� �˻� ����");
	               		outputStream.writeObject(result);
	               		outputStream.flush();
	               	}
	               	break;
	               }
	              
	              // outputStream.flush();
	               } 
	             catch (IOException ex) 
	             { 
	                System.err.println(ex);
	                connection.close();
	                connect = false;
	               } 
	         }
	         
	         return null;
	      }
	      
	   }

	
}
