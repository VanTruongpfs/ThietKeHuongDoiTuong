package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/cuahang";
    private static final String USER = "root";
    private static final String PASSWORD = "mysql";

    private static Connection conn;

    public static Connection getConnection() {
        if (conn == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (ClassNotFoundException e) {
                System.out.println("Không tìm thấy driver MySQL JDBC.");
                e.printStackTrace();
            } catch (SQLException e) {
                System.out.println("Lỗi kết nối đến database.");
                e.printStackTrace();
            }
        }
        return conn;
    }

    public static void closeConnection(Connection c) {
        if (c != null) {
            try {
                c.close();
                c = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void printInfo(Connection c) {
    	try {
			if (c != null) {
				System.out.println(c.getMetaData().toString());
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
