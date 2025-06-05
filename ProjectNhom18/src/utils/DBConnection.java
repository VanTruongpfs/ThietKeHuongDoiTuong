package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
    private static final String URL = "jdbc:mysql://localhost:3306/cuahang";
    private static final String USER = "root";
    private static final String PASSWORD = "mysql";
    
    private static Connection conn;
    
	private static DBConnection instance;
	private DBConnection() throws SQLException {
		 if (conn == null) {
	            try {
	                Class.forName("com.mysql.cj.jdbc.Driver");
	                conn = DriverManager.getConnection(URL, USER, PASSWORD);
	            } catch (ClassNotFoundException e) {
	                System.out.println("Không tìm thấy driver MySQL JDBC.");
	                e.printStackTrace();
	            }
	        }
	}
    public static synchronized DBConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }
    public static Connection getConnection() {
        
        return conn;
    }    
    
}
