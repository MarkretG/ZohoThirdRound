package inMemomyStorageHandling;
import bankingManagement.Customer;
import bankingManagement.Account;
import java.util.ArrayList;
import java.util.HashMap;
public interface InMemoryStorageDAO {
    void storeCustomersInCustomerHashMap(ArrayList<Customer> customers);
    void storeAccountsInAccountHashMap(ArrayList<Account> accounts);
    HashMap<Long,Account> getAccountsInfo(long customer_id);

}
