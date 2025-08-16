import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class DbConnectionManager {

    //this class can only be accessed by the outer class
    //the client won't be able to make explicit object of this class
    private class DbConnection {
        Connection connection;
        public DbConnection()
        {
            try {
                connection = DriverManager.getConnection("jdbc:url", "user", "password");
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        public Connection getConnection() {
            return connection;
        }
    }

    private int POOL_SIZE;
    private int MAX_POOL_SIZE;
    private List<DbConnection> freeConnections;
    private List<DbConnection> usedConnextions;

    //ensuring singleton
    private static DbConnectionManager dbConnectionManager;

    private DbConnectionManager(int POOL_SIZE,int MAX_POOL_SIZE)
    {
        this.POOL_SIZE = POOL_SIZE;
        this.MAX_POOL_SIZE = MAX_POOL_SIZE;

        //create free connections
        for(int i =0;i<this.POOL_SIZE;i++)
        {
            freeConnections.add(new DbConnection());
        }
    }

    public static DbConnectionManager getInstance(int POOL_SIZE,int MAX_POOL_SIZE)
    {
        //ensure thread safety
        if(dbConnectionManager == null)
        {
            synchronized (DbConnectionManager.class)
            {
                if(dbConnectionManager == null)
                    dbConnectionManager = new DbConnectionManager(POOL_SIZE,MAX_POOL_SIZE);
            }
        }
        return dbConnectionManager;
    }

    public synchronized Connection getConnection()
    {
        if(!freeConnections.isEmpty() && usedConnextions.size()<MAX_POOL_SIZE)
        {
            freeConnections.add(new DbConnection());
            System.out.println("New connection added to the pool");
        }
        else if(freeConnections.isEmpty() && usedConnextions.size()>=MAX_POOL_SIZE)
        {
            System.out.println("All the connections are currently in use");
            return null;
        }
        DbConnection dbConnection = freeConnections.remove(freeConnections.size()-1);
        usedConnextions.add(dbConnection);
        return dbConnection.getConnection();
    }

    public synchronized void releaseConnection(Connection dbConnection)
    {
        if(dbConnection != null)
        {
            usedConnextions.stream()
                    .filter((con)->dbConnection.equals(con.getConnection()))
                    .findFirst()
                    .ifPresent((con)->{
                        freeConnections.add(con);
                        usedConnextions.remove(con);
                    });

        }
    }

}
