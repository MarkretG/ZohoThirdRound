package persistence;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DBUtil {
    private static Connection connection=null;
    public static Connection getConnection() throws SQLException, PersistenceException {
        if (connection != null)
        {
            return connection;
        }

        try {
            String url = "jdbc:mysql://localhost:3306/info";
            String userName = "root";
            String password = "Root@123";

            Class.forName("com.mysql.cj.jdbc.Driver"); // load the Driver Class

            connection = DriverManager.getConnection(url, userName, password);// create the connection now
        } catch (ClassNotFoundException e) {
            int errorCode = 202;
            throw new PersistenceException("Exception in get connection", errorCode);
        }

      return connection;
    }
    public static void closeConnection(){
        if (connection!=null)
        {
            try {
                connection.close();
            }
            catch (SQLException e)
            {
            }
        }
    }

}