package logicalLayer;
import bankingManagement.Account;
import bankingManagement.Customer;
import inMemoryStorageHandling.InMemoryStorageDAO;
import persistence.AccountDAO;
import persistence.CustomerDAO;
import java.sql.SQLException;
public class LogicalHandling {
      CustomerDAO customerDAO=Controller.getCustomerPersistenceDAOHandler();
      AccountDAO accountDAO=Controller.getAccountPersistenceDAOHandler();
      InMemoryStorageDAO inMemoryStorageDAO=Controller.getInMemoryStorageDAOHandler();

    public void initialiseHashMap()throws SQLException {
        //initially store customer table and account table in hashmap
        inMemoryStorageDAO.storeCustomersInCustomerHashMap(customerDAO.selectAllCustomers());
        inMemoryStorageDAO.storeAccountsInAccountHashMap(accountDAO.selectAllAccounts());

    }
    public void handleNewCustomer(Customer customer, Account account) throws SQLException{
        long customer_id=customerDAO.addCustomer(customer);
        accountDAO.addAccount(account,customer_id);

        inMemoryStorageDAO.storeCustomerInCustomerHashMap(customer,customer_id);
        inMemoryStorageDAO.storeAccountInAccountHashMap(account,customer_id);

    }
    public void addNewAccountForExistingCustomer(Account account,long customer_id) throws SQLException{
        accountDAO.addAccount(account,customer_id);
        inMemoryStorageDAO.storeAccountInAccountHashMap(account,customer_id);
    }

}
