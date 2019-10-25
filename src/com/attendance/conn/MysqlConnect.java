package com.attendance.conn;
import java.sql.*;

public class MysqlConnect {
	    Connection conn = null;  
	    PreparedStatement pstmt = null;  
	    ResultSet rs = null; 
	    //数据库连接方法
		public Connection connect() throws ClassNotFoundException, SQLException{
	    	Connection conn=null; 
		    Class.forName("com.mysql.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/kaoqin?useUnicode=true&characterEncoding=utf-8"; 
		    String user="root"; 
			String password="123456"; 
			conn=DriverManager.getConnection(url,user,password); 
			return conn;
		}
		//关闭数据库资源
		public void close(Statement stat,Connection conn) throws SQLException{
			if(stat!=null){
		    	   stat.close();
		    }
		    if(conn!=null){
		    	   conn.close();
		    }
		}
	    public void getConnection() {  
	        try {  
	            Class.forName("com.mysql.jdbc.Driver");  
	            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/kaoqin?useUnicode=true&characterEncoding=utf-8", "root", "123456");  
	        } catch (ClassNotFoundException e) {  
	            e.printStackTrace();  
	        } catch (SQLException e) {  
	            e.printStackTrace();  
	        }  
	    }  
	    public int executeUpdate(String sql, Object... obj) {  
	        int num = 0;  
	        getConnection();  
	        try {  
	            pstmt = conn.prepareStatement(sql);  
	            for (int i = 0; i < obj.length; i++) {  
	                pstmt.setObject(i + 1, obj[i]);  
	            }  
	            num = pstmt.executeUpdate();  
	        } catch (SQLException e) {  
	            e.printStackTrace();  
	        } finally {  
	            closeAll();  
	        }  
	        return num;  
	    }  
	    public ResultSet executeQuery(String sql){
	    	rs=null;
	    	try {
				Statement stmt=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
				rs=stmt.executeQuery(sql);
			} catch (Exception e) {
				// TODO: handle exception
			}
	    	return rs;
	    }
	    
	    public ResultSet executeQuery(String sql, Object... obj) {  
	        getConnection();  
	        try {  
	            pstmt = conn.prepareStatement(sql);  
	            for (int i = 0; i < obj.length; i++) {  
	                pstmt.setObject(i + 1, obj[i]);  
	            }  
	            rs = pstmt.executeQuery();  
	        } catch (SQLException e) {  
	            e.printStackTrace();  
	        }  
	        return rs;  
	    }  
	    public void closeAll() {  
	    	try {
		        try {  
		            rs.close();  
		        } catch (SQLException e) {  
		            e.printStackTrace();  
		        }  
		        try {  
		            pstmt.close();  
		        } catch (SQLException e) {  
		            e.printStackTrace();  
		        }  
		        try {  
		            conn.close();  
		        } catch (SQLException e) {  
		            e.printStackTrace();  
		        }  
	    	} catch (Exception e) {
				// TODO: handle exception
	    		e.printStackTrace();
			}
	    }  
}
