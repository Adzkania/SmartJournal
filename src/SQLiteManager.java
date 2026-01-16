

import java.sql.*;

public class SQLiteManager {

    private static final String DB_URL = "jdbc:sqlite:smartjournal.db";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    public static void initialize() {
        String usersTable = """
            CREATE TABLE IF NOT EXISTS users (
                email TEXT PRIMARY KEY,
                display_name TEXT,
                password_hash TEXT
            );
        """;

        String journalsTable = """
            CREATE TABLE IF NOT EXISTS journals (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                user_email TEXT,
                date TEXT,
                content TEXT,
                weather TEXT,
                mood TEXT
            );
        """;

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {

            stmt.execute(usersTable);
            stmt.execute(journalsTable);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

