package logicalLayer;
import bankingManagement.Account;
import bankingManagement.Customer;
import inMemoryStorageHandling.InMemoryStorageDAO;
import persistence.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class LogicalHandler{
    private CustomerDAO customerDAO=Controller.getCustomerPersistenceDAOHandler();
      private AccountDAO accountDAO=Controller.getAccountPersistenceDAOHandler();
      private InMemoryStorageDAO inMemoryStorageDAO=Controller.getInMemoryStorageDAOHandler();

      private static LogicalHandler logicalHandler=null;
      public  static LogicalHandler getInstance()
      {
          if(logicalHandler==null)
          {
              logicalHandler=new LogicalHandler();
          }
          return logicalHandler;
      }

    public void initialiseHashMap() throws SQLException{
        //initially store customer table and account table in hashmap
            try {
                inMemoryStorageDAO.storeCustomersInCustomerHashMap(customerDAO.selectAllCustomers());
                inMemoryStorageDAO.storeAccountsInAccountHashMap(accountDAO.selectAllAccounts());
            }
            catch (PersistenceException e)
            {
                System.out.println(e.getMessage());
            }

    }
    public void handleNewCustomer(ArrayList<Customer> customers, ArrayList<Account> accounts) throws SQLException{
            try {
                ArrayList<Long> customer_ids = customerDAO.addCustomer(customers);
                HashMap<Long, Account> account = getAccounts(customer_ids, accounts);

                ArrayList<Long> customer_id = accountDAO.addAccounts(account);
                inMemoryStorageDAO.storeCustomersInCustomerHashMap(customerDAO.selectCustomers(customer_ids));
                inMemoryStorageDAO.storeAccountsInAccountHashMap(accountDAO.selectAccounts(customer_id));
            }
            catch (PersistenceException e)
            {
                System.out.println(e.getMessage());
            }
      }
    public void addNewAccountForExistingCustomer(long customer_id,double balance) throws SQLException {
             try {
                 accountDAO.addAccount(customer_id,balance);
                 inMemoryStorageDAO.storeAccountInAccountHashMap(accountDAO.selectAccount(customer_id));
             }
             catch (PersistenceException e)
             {
                 System.out.println(e.getMessage());
             }

    }
    public Customer getCustomerObject(String name,int age,long phone)
    {
        Customer customer = new Customer();
        //customer.setCustomer_id(customer_id);
        customer.setName(name);
        customer.setAge(age);
        customer.setPhone(phone);
        return customer;
    }
    public Account getAccountObject(double balance)
    {
        Account account=new Account();
        account.setBalance(balance);
        return account;
    }
    public HashMap<Long,Account> getAccounts(ArrayList<Long> customer_ids,ArrayList<Account> accounts)
    {
        HashMap<Long,Account> account = new HashMap<>();

        for (int i = 0; i < customer_ids.size(); i++) {
            account.put(customer_ids.get(i), accounts.get(i));
        }
        return account;

    }

}
