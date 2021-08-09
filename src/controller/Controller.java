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
    public static CustomerDAO getCustomerPersistenceDaoHandler()
    {
        return new CustomerDAOImplement();
    }
    public static AccountDAO getAccountPersistenceDaoHandler()
    {
        return  new AccountDAOImplement();
    }
    public static InputHandlerDAO getInputHandler()
    {
        return new InputHandlerDAOIml();
    }
    public static InMemoryStorageDAO getInMemoryStorageDAOHandler()
    {
        return new InMemoryStorageDAOImplement();
    }
}
