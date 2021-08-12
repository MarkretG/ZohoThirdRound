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

    private static final int errorCodeForFile=502;
    private static final int errorCodeForClass=503;

    private static CustomerDAO customerDAO=null;
    private static AccountDAO accountDAO=null;
    private static InMemoryStorageDAO inMemoryStorageDAO=null;

    static Properties properties = new Properties();

    public static Properties getProperties() throws LogicalException
    {
        if (!properties.isEmpty()) {
            return properties;
        }

        String path = System.getProperty("user.dir") + File.separator + "src" + File.separator;
        try (FileReader reader = new FileReader(path + "controller.properties")) {
            properties.load(reader);
        } catch (FileNotFoundException e) {
            throw new LogicalException("file not found please check your file path", errorCodeForFile);
        } catch (IOException i) {
            throw new LogicalException("you are try to read file that does not exist", errorCodeForFile);
        }
        return properties;
    }

    public static synchronized CustomerDAO getCustomerDAOHandler()throws LogicalException{

        if (customerDAO != null) {
            return customerDAO;
        }
        try {
            String className = (String) getProperties().get("customerDAO");
            customerDAO = (CustomerDAO) Class.forName(className).newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            throw new LogicalException("class not found in properties file", errorCodeForClass);
        } catch (LogicalException e) {
            System.out.println(e.getMessage());
        }
        return customerDAO;
    }
    public static synchronized AccountDAO getAccountDAOHandler() throws LogicalException {
        if (accountDAO != null) {
            return accountDAO;
        }
        try {
            String className = (String) getProperties().get("accountDAO");
            accountDAO = (AccountDAO) Class.forName(className).newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            throw new LogicalException("class not found in properties file", errorCodeForClass);
        } catch (LogicalException e) {
            System.out.println(e.getMessage());
        }
        return accountDAO;
    }

    public static synchronized InMemoryStorageDAO getInMemoryStorageDAOHandler()throws LogicalException{
        if (inMemoryStorageDAO != null) {
            return inMemoryStorageDAO;
        }
        try {
            String className = (String) getProperties().get("inMemoryStorageDAO");
            inMemoryStorageDAO = (InMemoryStorageDAO) Class.forName(className).newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            throw new LogicalException("class not found in properties file", errorCodeForClass);
        } catch (LogicalException e) {
            System.out.println(e.getMessage());
        }
        return inMemoryStorageDAO;
    }
}