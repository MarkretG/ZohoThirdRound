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
    public static CustomerDAO getCustomerPersistenceDaoHandler()
    {
        if (customerDAO==null) {
            return new CustomerDAOImplement();
        }
        return customerDAO;
    }
    public static AccountDAO getAccountPersistenceDaoHandler()
    {
        if (accountDAO==null) {
            return new AccountDAOImplement();
        }
        return accountDAO;
    }
    public static InputHandlerDAO getInputHandler()
    {
        if (inputHandlerDAO==null) {
            return new InputHandlerDAOIml();
        }
        return inputHandlerDAO;
    }
    public static InMemoryStorageDAO getInMemoryStorageDAOHandler()
    {
        if (inMemoryStorageDAO==null) {
            return new InMemoryStorageDAOImplement();
        }
        return inMemoryStorageDAO;
    }
}