package inMemoryStorageHandling;
import bankingManagement.Customer;
import bankingManagement.Account;
import java.util.ArrayList;
import java.util.HashMap;
public interface InMemoryStorageDAO {
    void storeCustomersInCustomerHashMap(ArrayList<Customer> customers);

    void storeAccountsInAccountHashMap(ArrayList<Account> accounts);
    void storeCustomerInCustomerHashMap(Customer customer,long customer_id);

    void storeAccountInAccountHashMap(Account account,long customer_id);

    HashMap<Long,Account> getAccountsInfo(long customer_id);

}