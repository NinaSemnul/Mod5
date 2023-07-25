import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabasePopulateService {

    public static void main(String[] args) throws SQLException, IOException {
        Connection connection = Database.getInstance().getConnection();
        stuffTables(connection);
        Database.getInstance().closeConnection();
    }

    private static void stuffTables(Connection connection) throws SQLException, IOException {
        String query  = Files.readString(Path.of("sql/populate_db.sql"));

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Successfully stuff tables");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("False, no stuff tables");
        }
    }

}


