package dao;
import java.sql.*;

public class ResultDAO {
    public void saveResult(int userId, int quizId, int score) {
        try (Connection con = DBConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO results(user_id, quiz_id, score) VALUES(?,?,?)");
            ps.setInt(1, userId);
            ps.setInt(2, quizId);
            ps.setInt(3, score);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
