import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    Connection connection;
    public DbConnection()
    {
       System.out.println("created connection");

    }
    public Connection getConnection() {
        return connection;
    }
}
