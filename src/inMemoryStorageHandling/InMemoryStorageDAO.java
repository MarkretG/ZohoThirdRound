package inMemoryStorageHandling;
import bankingManagement.Customer;
import bankingManagement.Account;
import java.util.ArrayList;
import java.util.HashMap;

public interface InMemoryStorageDAO {
    //store all customers in customer hashmap
    void storeCustomersInCustomerHashMap(ArrayList<Customer> customers);

    //store all accounts in account hashmap
    void storeAccountsInAccountHashMap(ArrayList<Account> accounts);

    //store account in account hashmap
    void storeAccountInAccountHashMap(Account account);

    //return hashmap particular customer_id
    HashMap<Long,Account> getAccountsInfo(long customer_id)throws AccountNotFoundException;

}