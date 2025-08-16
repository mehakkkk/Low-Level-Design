import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBLogTarget implements ILogTarget{
    String url;
    String userName;
    String password;

    public DBLogTarget(String url, String userName, String password) {
        this.url = url;
        this.userName = userName;
        this.password = password;
    }

    @Override
    public void writeLog(Message message) {
        try(Connection connection = DriverManager.getConnection(url,userName,password))
        {
            PreparedStatement ps = connection.prepareStatement("insert into logs(level,message,timestamp) values (?,?,?)");
            ps.setInt(1,message.getLogLevel().ordinal());
            ps.setString(2,message.getText());
            ps.setLong(2,message.getTimestamp());
            ps.executeUpdate();
        }
        catch(SQLException ex)
        {
            System.out.println("Oops encountered an issue while logging, please find the info below\n"+ex.getMessage());
        }

    }
}
