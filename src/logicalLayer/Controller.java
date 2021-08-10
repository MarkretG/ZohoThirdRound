package logicalLayer;
import persistence.CustomerDAO;
import persistence.AccountDAO;
import inMemoryStorageHandling.InMemoryStorageDAO;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
public class Controller {
    static Properties properties = new Properties();
    static {
        try {
            String path=System.getProperty("user.dir");
            FileReader reader = new FileReader(path+File.separator+"src"+File.separator+"controller.properties");
            properties.load(reader);
        } catch (FileNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
        catch (IOException i)
        {
            System.out.println(i.getMessage());
        }
    }



    private static CustomerDAO customerDAO=null;
    private static AccountDAO accountDAO=null;
    private static InMemoryStorageDAO inMemoryStorageDAO=null;
    public static synchronized CustomerDAO getCustomerPersistenceDAOHandler()  {
        if (customerDAO==null) {
            String className=(String)properties.get("customerDAO");
            try {
                customerDAO=(CustomerDAO)Class.forName(className).newInstance();
            } catch (InstantiationException | IllegalAccessException |ClassNotFoundException  e) {
                System.out.println(e.getMessage());
            }

            return customerDAO;
        }
        return customerDAO;
    }
    public static synchronized AccountDAO getAccountPersistenceDAOHandler() {
        if (accountDAO==null) {
           String className=(String) properties.get("accountDAO");
            try {
                accountDAO=(AccountDAO)Class.forName(className).newInstance();
            }
            catch (InstantiationException |IllegalAccessException |ClassNotFoundException  e) {
                System.out.println(e.getMessage());

            }

            return accountDAO;
        }
        return accountDAO;
    }
    public static synchronized InMemoryStorageDAO getInMemoryStorageDAOHandler()  {
        if (inMemoryStorageDAO==null) {
            String className=(String) properties.get("inMemoryStorageDAO");
            try {
                inMemoryStorageDAO=(InMemoryStorageDAO) Class.forName(className).newInstance();
            }
            catch (InstantiationException |IllegalAccessException |ClassNotFoundException  e) {
                System.out.println(e.getMessage());
            }
            return inMemoryStorageDAO;
        }
            return inMemoryStorageDAO;
    }
}