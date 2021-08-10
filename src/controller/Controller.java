package controller;
import persistence.CustomerDAO;
import persistence.AccountDAO;
import inputHandler.InputHandlerDAO;
import persistence.CustomerDAOImpl;
import persistence.AccountDAOImpl;
import inputHandler.InputHandlerDAOImpl;
import inMemoryStorageHandling.InMemoryStorageDAO;
import inMemoryStorageHandling.InMemoryStorageDAOImpl;
public class Controller {
    private static CustomerDAO customerDAO=null;
    private static AccountDAO accountDAO=null;
    private static InputHandlerDAO inputHandlerDAO=null;
    private static InMemoryStorageDAO inMemoryStorageDAO=null;
    public static synchronized CustomerDAO getCustomerPersistenceDaoHandler()
    {
        if (customerDAO==null) {
            customerDAO= new CustomerDAOImpl();
        }
        return customerDAO;
    }
    public static synchronized AccountDAO getAccountPersistenceDaoHandler()
    {
        if (accountDAO==null) {
            accountDAO= new AccountDAOImpl();
        }
        return accountDAO;
    }
    public static synchronized InputHandlerDAO getInputHandler()
    {
        if (inputHandlerDAO==null) {
            inputHandlerDAO= new InputHandlerDAOImpl();
        }
        return inputHandlerDAO;
    }
    public static synchronized InMemoryStorageDAO getInMemoryStorageDAOHandler()
    {
        if (inMemoryStorageDAO==null) {
            inMemoryStorageDAO=new InMemoryStorageDAOImpl();
        }
        return inMemoryStorageDAO;
    }
}