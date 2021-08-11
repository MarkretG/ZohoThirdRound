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
    private static CustomerDAO customerDAO=null;
    private static AccountDAO accountDAO=null;
    private static InMemoryStorageDAO inMemoryStorageDAO=null;
    static Properties properties = new Properties();
    static {
        String path = System.getProperty("user.dir") + File.separator + "src" + File.separator;
        try (FileReader reader = new FileReader(path + "controller.properties")) {
            properties.load(reader);
        }
        catch (FileNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
        catch (IOException i)
        {
            System.out.println(i.getMessage());
        }
    }

    public static synchronized CustomerDAO getCustomerPersistenceDAOHandler()  {
        if (customerDAO!=null) {
            return customerDAO;
        }
        String className=(String)properties.get("customerDAO");
        try {
            customerDAO=(CustomerDAO)Class.forName(className).newInstance();
        } catch (InstantiationException | IllegalAccessException |ClassNotFoundException  e) {
            System.out.println(e.getMessage());
        }

        return customerDAO;
    }
    public static synchronized AccountDAO getAccountPersistenceDAOHandler() {
        if (accountDAO!=null) {
            return accountDAO;
        }
           String className = (String) properties.get("accountDAO");
        try {
            accountDAO = (AccountDAO) Class.forName(className).newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            System.out.println(e.getMessage());

        }

        return accountDAO;
    }
    public static synchronized InMemoryStorageDAO getInMemoryStorageDAOHandler()  {
        if (inMemoryStorageDAO!=null)
        {
            return inMemoryStorageDAO;
        }

        String className = (String) properties.get("inMemoryStorageDAO");
        try {
            inMemoryStorageDAO = (InMemoryStorageDAO) Class.forName(className).newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return inMemoryStorageDAO;
    }
}