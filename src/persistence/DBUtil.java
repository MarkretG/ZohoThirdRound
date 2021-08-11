package persistence;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DBUtil {
    private static Connection connection=null;
    public static Connection getConnection()throws  ClassNotFoundException {
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
            throw new ClassNotFoundException("Driver class not found");
        }
        catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
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