package persistence;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DBUtil {
    private static Connection connection=null;
    public static Connection getConnection() throws SQLException{
        if (connection != null)
        {
            return connection;
        }
        try {
            String url = "jdbc:mysql://localhost:3306/info";
            String userName = "root";
            String password = "Root@123";
            // load the Driver Class
            Class.forName("com.mysql.cj.jdbc.Driver");

            // create the connection now
            connection = DriverManager.getConnection(url, userName,password);
            throw new ClassNotFoundException();
          }
        catch (ClassNotFoundException e)
        {
            System.out.println("Connection fail please check driver class");
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