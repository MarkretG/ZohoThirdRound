package persistence;
import bankingManagement.Account;
import bankingManagement.Customer;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public interface PersistenceDAO{
    //insert  customers
    ArrayList<Long> addCustomer(ArrayList<Customer> customers)throws SQLException, PersistenceException;

    //select customers
    ArrayList<Customer> selectCustomers(ArrayList<Long> customer_ids) throws SQLException, PersistenceException;
    ArrayList<Customer> selectAllCustomers()throws SQLException, PersistenceException;

    //insert  account
    void addAccount(long customer_id,double balance) throws SQLException, PersistenceException;
    ArrayList<Long> addAccounts(HashMap<Long, Account> account)throws SQLException, PersistenceException;

    //select accounts by id
    ArrayList<Account> selectAccounts(ArrayList<Long> customer_ids) throws SQLException, PersistenceException;
    Account selectAccount(long customer_id) throws SQLException, PersistenceException;

    //select All Accounts
    ArrayList<Account> selectAllAccounts()throws SQLException, PersistenceException;
}
