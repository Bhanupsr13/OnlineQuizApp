package dao;
import model.User;
import java.sql.*;
import java.security.MessageDigest;

public class UserDAO {

    public boolean register(String username, String password) {
        String hash = hashPassword(password);
        try (Connection con = DBConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO users(username, password_hash) VALUES(?, ?)");
            ps.setString(1, username);
            ps.setString(2, hash);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Registration failed: " + e.getMessage());
            return false;
        }
    }

    public boolean login(String username, String password) {
        String hash = hashPassword(password);
        try (Connection con = DBConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE username=? AND password_hash=?");
            ps.setString(1, username);
            ps.setString(2, hash);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println("Login failed: " + e.getMessage());
            return false;
        }
    }

    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] bytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) sb.append(String.format("%02x", b));
            return sb.toString();
        } catch (Exception e) {
            return password;
        }
    }
}
