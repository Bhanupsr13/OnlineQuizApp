package dao;
import java.sql.*;

public class DBConnection {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/quiz_app";
    private static final String USER = "root";
    private static final String PASSWORD = "root"; // change this

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Database connection failed!");
            e.printStackTrace();
            return null;
        }
    }
}
