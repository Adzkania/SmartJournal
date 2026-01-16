import java.sql.*;

public class SQLiteUserDAO {

    public static void insertUser(User user) {
        String sql = """
            INSERT OR IGNORE INTO users (email, display_name, password_hash)
            VALUES (?, ?, ?)
        """;

        try (Connection conn = SQLiteManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, user.getEmail());
            pstmt.setString(2, user.getDisplayName());
            pstmt.setString(3, user.getPassword());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
