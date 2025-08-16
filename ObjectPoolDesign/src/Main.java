import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        DbConnectionManager dbConnectionManager = DbConnectionManager.getInstance(3,5);

        Connection c1  = dbConnectionManager.getConnection();
        Connection c2 = dbConnectionManager.getConnection();
        Connection c3 = dbConnectionManager.getConnection() ;
        Connection c4 = dbConnectionManager.getConnection();
        Connection c5 = dbConnectionManager.getConnection() ;
        Connection c6 = dbConnectionManager.getConnection();
        dbConnectionManager.releaseConnection(c1);
        c6 = dbConnectionManager.getConnection();

    }
}
