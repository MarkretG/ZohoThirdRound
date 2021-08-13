package logicalLayer;
import bankingManagement.Account;
import bankingManagement.Customer;
import persistence.*;
import java.util.ArrayList;
import java.util.HashMap;

public class LogicalHandler {

    private static LogicalHandler logicalHandler = null;

    public static LogicalHandler getInstance() {
        if (logicalHandler == null) {
            logicalHandler = new LogicalHandler();
        }
        return logicalHandler;
    }

    public void initialiseHashMap() throws LogicalException{
        //initially store customer table and account table in hashmap
        try {
            //get all customers and store in customer HashMap
            ArrayList<Customer> customers = Controller.getPersistenceDAOHandler().selectAllCustomers();
            Controller.getInMemoryStorageDAOHandler().storeCustomersInCustomerHashMap(customers);

            //get all Accounts and store in Hashmap account HashMap
            ArrayList<Account> accounts = Controller.getPersistenceDAOHandler().selectAllAccounts();
            Controller.getInMemoryStorageDAOHandler().storeAccountsInAccountHashMap(accounts);
        } catch (PersistenceException  e) {
            System.out.println("ERROR CODE:"+e.getErrorCode() + " " + e.getMessage());
        }

    }
    public void handleNewCustomer(ArrayList<Customer> customers, ArrayList<Account> accounts) throws LogicalException{
        try {
            //insert customers in customer table and get generated customer ids
            ArrayList<Long> customer_ids = Controller.getPersistenceDAOHandler().addCustomers(customers);

            //map the inserted customer id and matching account info
            HashMap<Long, Account> account = getAccounts(customer_ids, accounts);

            //insert accounts in account table and get inserted customer ids
            ArrayList<Long> customer_id = Controller.getPersistenceDAOHandler().addAccounts(account);

            //get all inserted customers
            ArrayList<Customer> customers1 = Controller.getPersistenceDAOHandler().selectCustomers(customer_ids);

            //store in customer HashMap
            Controller.getInMemoryStorageDAOHandler().storeCustomersInCustomerHashMap(customers1);

            // get all inserted Accounts
            ArrayList<Account> accounts1 = Controller.getPersistenceDAOHandler().selectAccounts(customer_id);

            //store in account HashMap
            Controller.getInMemoryStorageDAOHandler().storeAccountsInAccountHashMap(accounts1);
        } catch (PersistenceException e) {
            System.out.println("ERROR CODE:"+e.getErrorCode() + ":" + e.getMessage());
        }
      }
    public void addNewAccountForExistingCustomer(long customer_id,double balance) throws LogicalException {
        try {
            //add new account for existing customer
            Controller.getPersistenceDAOHandler().addAccount(customer_id, balance);

            //get inserted account
            Account account = Controller.getPersistenceDAOHandler().selectAccount(customer_id);

            //store in account hashMap
            Controller.getInMemoryStorageDAOHandler().storeAccountInAccountHashMap(account);
        }
        catch (PersistenceException e) {
            System.out.println("ERROR CODE:"+e.getErrorCode() + " " + e.getMessage());
        }

    }
    public Customer getCustomerObject(String name,int age,long phone)
    {
        //create customer object
        Customer customer = new Customer();
        //customer.setCustomer_id(customer_id);
        customer.setName(name);
        customer.setAge(age);
        customer.setPhone(phone);
        return customer;
    }
    public Account getAccountObject(double balance)
    {
        //create account object
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
