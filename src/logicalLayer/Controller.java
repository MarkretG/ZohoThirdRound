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
    public static synchronized CustomerDAO getCustomerPersistenceDAOHandler() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        if (customerDAO==null) {
            String className=(String) properties.get(customerDAO);
            customerDAO=(CustomerDAO)Class.forName(className).newInstance();
            return customerDAO;
        }
        return customerDAO;
    }
    public static synchronized AccountDAO getAccountPersistenceDAOHandler() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        if (accountDAO==null) {
            String className=(String) properties.get(accountDAO);
            accountDAO=(AccountDAO)Class.forName(className).newInstance();
            return accountDAO;
        }
        return accountDAO;
    }
    public static synchronized InMemoryStorageDAO getInMemoryStorageDAOHandler() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        if (inMemoryStorageDAO==null) {
            String className=(String) properties.get(inMemoryStorageDAO);
            inMemoryStorageDAO=(InMemoryStorageDAO) Class.forName(className).newInstance();
            return inMemoryStorageDAO;
        }
        return inMemoryStorageDAO;
    }
}