package logicalLayer;
import persistence.CustomerDAO;
import persistence.AccountDAO;
import inMemoryStorageHandling.InMemoryStorageDAO;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
public class Controller {
    static Properties properties = new Properties();
    static {
        try {
            FileReader reader = new FileReader("controller.properties");
            properties.load(reader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException i)
        {
            i.printStackTrace();
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
                e.printStackTrace();
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
                e.printStackTrace();
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
                e.printStackTrace();
            }
            return inMemoryStorageDAO;
        }
            return inMemoryStorageDAO;
    }
}