package logicalLayer;
import bankingManagement.Account;
import bankingManagement.Customer;
import persistence.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class LogicalHandler {

      private static LogicalHandler logicalHandler=null;
      public  static LogicalHandler getInstance()
      {
          if(logicalHandler==null)
          {
              logicalHandler=new LogicalHandler();
          }
          return logicalHandler;
      }

    public void initialiseHashMap() throws SQLException,LogicalException{
        //initially store customer table and account table in hashmap
            try {
                //get all customers and store in customer HashMap
                ArrayList<Customer> customers=Controller.getCustomerDAOHandler().selectAllCustomers();
                Controller.getInMemoryStorageDAOHandler().storeCustomersInCustomerHashMap(customers);

                //get all Accounts and store Hashmap account HashMap
                ArrayList<Account> accounts=Controller.getAccountDAOHandler().selectAllAccounts();
                Controller.getInMemoryStorageDAOHandler().storeAccountsInAccountHashMap(accounts);
            }
            catch (PersistenceException e)
            {
                System.out.println(e.getErrorCode()+":"+e.getMessage());
            }

    }
    public void handleNewCustomer(ArrayList<Customer> customers, ArrayList<Account> accounts) throws SQLException,LogicalException{
            try {
                ArrayList<Long> customer_ids = Controller.getCustomerDAOHandler().addCustomer(customers);
                HashMap<Long, Account> account = getAccounts(customer_ids, accounts);

                ArrayList<Long> customer_id = Controller.getAccountDAOHandler().addAccounts(account);
                Controller.getInMemoryStorageDAOHandler().storeCustomersInCustomerHashMap(Controller.getCustomerDAOHandler().selectCustomers(customer_ids));
                Controller.getInMemoryStorageDAOHandler().storeAccountsInAccountHashMap(Controller.getAccountDAOHandler().selectAccounts(customer_id));
            }
            catch (PersistenceException e)
            {
                System.out.println(e.getErrorCode()+":"+e.getMessage());
            }
      }
    public void addNewAccountForExistingCustomer(long customer_id,double balance) throws SQLException,LogicalException {
             try {
                 Controller.getAccountDAOHandler().addAccount(customer_id,balance);
                 Controller.getInMemoryStorageDAOHandler().storeAccountInAccountHashMap(Controller.getAccountDAOHandler().selectAccount(customer_id));
             }
             catch (PersistenceException e)
             {
                 System.out.println(e.getErrorCode()+":"+e.getMessage());
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
