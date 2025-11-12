package dao;
import model.Question;
import java.sql.*;
import java.util.*;

public class QuestionDAO {
    public List<Question> getQuestionsByQuizId(int quizId) {
        List<Question> list = new ArrayList<>();
        try (Connection con = DBConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM questions WHERE quiz_id=?");
            ps.setInt(1, quizId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Question(
                        rs.getInt("id"),
                        rs.getInt("quiz_id"),
                        rs.getString("question_text"),
                        rs.getString("option_a"),
                        rs.getString("option_b"),
                        rs.getString("option_c"),
                        rs.getString("option_d"),
                        rs.getString("correct_option").charAt(0)
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
