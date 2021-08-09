package inMemoryStorageHandling;
import bankingManagement.Customer;
import bankingManagement.Account;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
public interface InMemoryStorageDAO {
    void storeCustomersInCustomerHashMap(ArrayList<Customer> customers);

    void storeAccountsInAccountHashMap(ArrayList<Account> accounts);

    void handleNewCustomer() throws SQLException;

    void AddNewAccountForExistingCustomer() throws SQLException;

    HashMap<Long,Account> getAccountsInfo();

    void initialiseHashMap()throws SQLException;

}