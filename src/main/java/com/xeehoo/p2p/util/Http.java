package com.xeehoo.p2p.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Http {

	private static List<String> methodPa(String strURL){  
        String temp;  
        List<String> list = new ArrayList<String>();
        try{  
            URL url = new URL(strURL);  
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setConnectTimeout(5000);
			conn.setReadTimeout(5000);
			conn.connect();

			InputStreamReader isr = new InputStreamReader(conn.getInputStream());
            BufferedReader br = new BufferedReader(isr);  
            int i = 0;
            while((temp = br.readLine()) != null){ 
            	if((HtmlUtil.getTextFromHtml(temp).equals("") || HtmlUtil.getTextFromHtml(temp).equals(" ")) && i!=1){
            		continue;
            	}
            	if(HtmlUtil.getTextFromHtml(temp).equals("鍥戒骇鑽搧")){
            		continue;
            	}
            	if(HtmlUtil.getTextFromHtml(temp).equals("批准文号")){
            		i=1;
            	}
            	if(HtmlUtil.getTextFromHtml(temp).equals("药品本位码备注")){
            		break;
            	}
				if(HtmlUtil.getTextFromHtml(temp).equals("没有相关信息")){
					break;
				}
            	list.add(HtmlUtil.getTextFromHtml(temp));
            }     
            br.close();  
            isr.close();
			if (conn != null)
				conn.disconnect();

        }catch(Exception e){  
            e.printStackTrace();
			return null;
        }  
        return list;
    } 
	
    
    // 结果处理
    private static  List<String>  convertResult(List<String> list){
    	List<String> new_list = new ArrayList<String>();
    	if(list!=null && list.size() > 0){
    		for(int i = 0; i<list.size(); i++){
    			if(i==2|| i==3 ||
    			   i==6|| i==7 ||
    			   i==10|| i==11 ||
    			   i==14|| i==15 ||
    			   i==18|| i==19 ||
    					   i==22||
    			   i==23|| i==24 ||
    			   i==26|| i==27 ||
    			   i==29|| i==30 ||
    			   i==33|| i==34 ||
    			   i==37|| i==38 ||
    			   i==41|| i==42 ||
    			   i==45|| i==46 ||
    			   i==49|| i==50
    					){
    			}else{
    				String ss = list.get(i); 
    				if(ss.equals("") || ss.equals(" ")){
    					ss = "null";
    				}
    				new_list.add(ss);
    			}
    		}
    	}
    	List<String> c_list = new ArrayList<String>(); 
    	if(new_list!=null && new_list.size() > 0){
    		for(int i=0 ; i<new_list.size(); i++){
    			if(i+1 < new_list.size()){
//    				c_list.add(new_list.get(i)+"," + new_list.get(i+1));
					c_list.add(new_list.get(i+1));
    				i++;
    			}
    		}
    	}
    	
    	return c_list;
    }
    
	// 写文件
	private static void writerTxt(String i, File file) {
		FileWriter fw = null;
		BufferedWriter bw = null;
		try {
//			File file = new File(path);
			fw = new FileWriter(file, true);
			bw = new BufferedWriter(fw);
//			for(String ss : list){
				bw.write(i);
				bw.newLine();
				bw.flush(); 
//			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fw != null) {
				try {
					bw.close();
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}

		}
	}

	private static Connection getConn() {
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://192.168.10.10:3306/fesco?useUnicode=true&characterEncoding=UTF-8";
		String username = "neowave";
		String password = "neowave";
		Connection conn = null;
		try {
			Class.forName(driver); //classLoader,加载对应驱动
			conn = (Connection) DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	private static int insert(Connection conn, Drug drug) {
//		Connection conn = getConn();
		int i = 0;
		String sql = "insert into drug(id, pzwh,cpmc,ywmc,spm,jx,gg,scdw,scdz,cplb,ypzwh,pzrq,ypbwm) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt;
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.setInt(1, drug.getId());
			pstmt.setString(2, drug.getPzwh());
			pstmt.setString(3, drug.getCpmc());
			pstmt.setString(4, drug.getYwmc());
			pstmt.setString(5, drug.getSpm());
			pstmt.setString(6, drug.getJx());
			pstmt.setString(7, drug.getGg());
			pstmt.setString(8, drug.getScdw());
			pstmt.setString(9, drug.getScdz());
			pstmt.setString(10, drug.getCplb());
			pstmt.setString(11, drug.getYpbwm());
			pstmt.setString(12, drug.getPzrq().length()>10 ? drug.getPzrq().substring(0,10): drug.getPzrq());
			pstmt.setString(13, drug.getYpbwm());

			i = pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

    public static void main(String[] args) {
    	String path = "D:/a.txt";
		File file = new File(path);
		Connection con = getConn(); //196040 -- 197000
    	for(int i=173028 ; i<180000 ; i++){
    		System.out.println("process - " + i);
//    		 int  i = 2000;
//	    	String url = "http://app1.sfda.gov.cn/datasearch/face3/content.jsp?tableId=25&tableName=TABLE25&tableView=国产药品&Id="+i ;
    		//这是正确的连接
	    	String url = "http://app1.sfda.gov.cn/datasearch/face3/content.jsp?tableId=25&tableName=TABLE25&tableView=国产药品&Id=" + i;
	    	
	    	//错误连接处理
//	    	String url = "http://app1.sfda.gov.cn/datasearch/face3/content.jsp?tableId=25&tableName=TABLE25&tableView=国产药品&Id=20000" ;
	    	List<String> list = methodPa(url);

			int cnt = 0;
			while (list == null && cnt < 3){
				try {
					Thread.sleep(1000*5L);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				list = methodPa(url);
				cnt++;
			}
			if (list == null){
				System.out.println("Error： " + i);
				writerTxt(new Integer(i).toString(), file);
				continue;
			}

	    	list = convertResult(list);
			if (list.size() <= 0){
				System.out.println("Empty： " + i);
				continue;
			}

			Drug drug = new Drug();
			drug.setId(i);
			for(int j = 0; j < list.size(); j++){
				switch(j){
					case 0:
						drug.setPzwh(list.get(j));break;
					case 1:
						drug.setCpmc(list.get(j));break;
					case 2:
						drug.setYwmc(list.get(j));break;
					case 3:
						drug.setSpm(list.get(j));break;
					case 4:
						drug.setJx(list.get(j));break;
					case 5:
						drug.setGg(list.get(j));break;
					case 6:
						drug.setScdw(list.get(j));break;
					case 7:
						drug.setScdz(list.get(j));break;
					case 8:
						drug.setCplb(list.get(j));break;
					case 9:
						drug.setYpzwh(list.get(j));break;
					case 10:
						drug.setPzrq(list.get(j));break;
					case 11:
						drug.setYpbwm(list.get(j));break;
				}
			}
			insert(con, drug);
			try {
				Thread.sleep(1000 * 5L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

//	    	writerTxt(list, path);
    	}

		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("complete.");
	}
}
