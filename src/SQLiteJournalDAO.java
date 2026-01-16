import java.sql.*;

public class SQLiteJournalDAO {

    public static void saveJournal(String email, JournalEntry entry) {
        String sql = """
            INSERT INTO journals (user_email, date, content, weather, mood)
            VALUES (?, ?, ?, ?, ?)
        """;

        try (Connection conn = SQLiteManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, email);
            pstmt.setString(2, entry.getDate());
            pstmt.setString(3, entry.getContent());
            pstmt.setString(4, entry.getWeather());
            pstmt.setString(5, entry.getMood());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateJournal(String email, String date, String content, String mood) {
    String sql = """
        UPDATE journals
        SET content = ?, mood = ?
        WHERE user_email = ? AND date = ?
    """;

    try (Connection conn = SQLiteManager.connect();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setString(1, content);
        pstmt.setString(2, mood);
        pstmt.setString(3, email);
        pstmt.setString(4, date);
        pstmt.executeUpdate();

    } catch (SQLException e) {
        e.printStackTrace();
    }
}

}
