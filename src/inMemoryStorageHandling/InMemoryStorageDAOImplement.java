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
public class InMemoryStorageDAOImplement implements InMemoryStorageDAO {
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
            HashMap accountHashMap = accountInfoHashMap.getOrDefault(account.getCustomer_id(), new HashMap<>());
            accountHashMap.put(account.getAccount_id(), account);
            accountInfoHashMap.put(account.getCustomer_id(), accountHashMap);
        }

    }

    @Override
    public void handleNewCustomer() throws SQLException{
        Customer customer = inputHandlerDAO.getCustomerInfo();
        Account account = inputHandlerDAO.getAccountInfo();

        long customer_id=customerDAO.addCustomer(customer);
        accountDAO.addAccount(account,customer_id);

        storeCustomersInCustomerHashMap(customerDAO.selectCustomers(customer_id));
        storeAccountsInAccountHashMap(accountDAO.selectAccounts(customer_id));

    }

    @Override
    public void AddNewAccountForExistingCustomer() throws SQLException{
        long customer_id=inputHandlerDAO.getNextLongFromUser();
        Account account=inputHandlerDAO.getAccountInfo();
        accountDAO.addAccount(account,customer_id);
        storeAccountsInAccountHashMap(accountDAO.selectAccounts(customer_id));
    }

    @Override
    public HashMap<Long, Account> getAccountsInfo() {
        long customer_id = inputHandlerDAO.getNextLongFromUser();
        HashMap<Long, Account> accountInfo = accountInfoHashMap.get(customer_id);
        return  accountInfo;
    }

}
