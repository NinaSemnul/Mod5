import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

public class DatabaseInitService {
    public static void main(String[] args) throws SQLException, IOException {

        Connection connection = Database.getInstance().getConnection();
        createTables(connection);
        Database.getInstance().closeConnection();
    }

    private static void createTables(Connection connection) throws SQLException, IOException {
        String query  = Files.readString(Path.of("sql/init_db.sql"));

        try {
        Statement statement = connection.createStatement();
        statement.executeUpdate(query);
         System.out.println("Successfully create tables");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("False, no create tables");
        }
    }
}
