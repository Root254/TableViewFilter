package populatingdatafromdatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author user
 */
public class DBConnector {
    public static Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:derby://localhost:1527/sample", "app", "app");
        
        return connection;
    }
}
