package inMemoryStorageHandling;
import bankingManagement.Account;
import bankingManagement.Customer;
import controller.Controller;
import inputHandler.InputHandlerDAO;
import persistence.AccountDAO;
import persistence.CustomerDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
public class InMemoryStorageDAOImpl implements InMemoryStorageDAO {
    CustomerDAO customerDAO=Controller.getCustomerPersistenceDaoHandler();
    AccountDAO accountDAO=Controller.getAccountPersistenceDaoHandler();
    InputHandlerDAO inputHandlerDAO=Controller.getInputHandler();


    private HashMap<Long,String> customerHashMap=new HashMap<>();
    private HashMap<Long,HashMap<Long,Account>> accountInfoHashMap=new HashMap<>();

    @Override
    public void initialiseHashMap()throws SQLException{
        //initially store customer table and account table in hashmap
        storeCustomersInCustomerHashMap(customerDAO.selectAllCustomers());
        storeAccountsInAccountHashMap(accountDAO.selectAllAccounts());

    }

    @Override
    public void storeCustomersInCustomerHashMap(ArrayList<Customer> customers) {
        for (Customer customer:customers)
        {
            customerHashMap.put(customer.getCustomer_id(),customer.getName());
        }
    }

    @Override
    public void storeAccountsInAccountHashMap(ArrayList<Account> accounts) {
        for (Account account:accounts)
        {
            HashMap<Long,Account> accountHashMap = accountInfoHashMap.getOrDefault(account.getCustomer_id(), new HashMap<>());
            accountHashMap.put(account.getAccount_id(), account);
            accountInfoHashMap.put(account.getCustomer_id(), accountHashMap);
        }

    }

    @Override
    public void storeCustomerInCustomerHashMap(Customer customer,long customer_id) {
        customerHashMap.put(customer_id,customer.getName());
    }

    @Override
    public void storeAccountInAccountHashMap(Account account,long customer_id) {
        HashMap<Long,Account> accountHashMap = accountInfoHashMap.getOrDefault(account.getCustomer_id(), new HashMap<>());
        accountHashMap.put(account.getAccount_id(), account);
        accountInfoHashMap.put(customer_id, accountHashMap);
    }


    @Override
    public void handleNewCustomer(Customer customer,Account account) throws SQLException{
        long customer_id=customerDAO.addCustomer(customer);
        accountDAO.addAccount(account,customer_id);

        storeCustomerInCustomerHashMap(customer,customer_id);
        storeAccountInAccountHashMap(account,customer_id);

    }

    @Override
    public void addNewAccountForExistingCustomer(Account account,long customer_id) throws SQLException{
        accountDAO.addAccount(account,customer_id);
        storeAccountInAccountHashMap(account,customer_id);
    }

    @Override
    public HashMap<Long, Account> getAccountsInfo(long customer_id) {
        return accountInfoHashMap.get(customer_id);
    }

}
