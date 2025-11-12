package dao;
import java.sql.*;
import java.util.*;

public class QuizDAO {
    public List<Integer> getAllQuizIds() {
        List<Integer> list = new ArrayList<>();
        try (Connection con = DBConnection.getConnection()) {
            ResultSet rs = con.createStatement().executeQuery("SELECT id FROM quizzes");
            while (rs.next()) list.add(rs.getInt("id"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public String getQuizTitle(int quizId) {
        try (Connection con = DBConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT title FROM quizzes WHERE id=?");
            ps.setInt(1, quizId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getString("title");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
