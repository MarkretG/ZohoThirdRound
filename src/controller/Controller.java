package controller;
import persistence.CustomerDAO;
import persistence.AccountDAO;
import inputHandler.InputHandlerDAO;
import persistence.CustomerDAOImplement;
import persistence.AccountDAOImplement;
import inputHandler.InputHandlerDAOIml;
import inMemoryStorageHandling.InMemoryStorageDAO;
import inMemoryStorageHandling.InMemoryStorageDAOImplement;
public class Controller {
    private static CustomerDAO customerDAO=null;
    private static AccountDAO accountDAO=null;
    private static InputHandlerDAO inputHandlerDAO=null;
    private static InMemoryStorageDAO inMemoryStorageDAO=null;
    public static synchronized CustomerDAO getCustomerPersistenceDaoHandler()
    {
        if (customerDAO==null) {
            customerDAO= new CustomerDAOImplement();
        }
        return customerDAO;
    }
    public static synchronized AccountDAO getAccountPersistenceDaoHandler()
    {
        if (accountDAO==null) {
            accountDAO= new AccountDAOImplement();
        }
        return accountDAO;
    }
    public static synchronized InputHandlerDAO getInputHandler()
    {
        if (inputHandlerDAO==null) {
            inputHandlerDAO= new InputHandlerDAOIml();
        }
        return inputHandlerDAO;
    }
    public static synchronized InMemoryStorageDAO getInMemoryStorageDAOHandler()
    {
        if (inMemoryStorageDAO==null) {
            inMemoryStorageDAO=new InMemoryStorageDAOImplement();
        }
        return inMemoryStorageDAO;
    }
}