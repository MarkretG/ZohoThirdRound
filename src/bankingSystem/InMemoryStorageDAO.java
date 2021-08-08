package bankingSystem;
import customerManagement.Customer;
import accountManagement.Account;
import java.util.ArrayList;
import java.util.HashMap;
public interface InMemoryStorageDAO {
    void storeCustomerInCustomerHashMap(ArrayList<Customer> customers);
    void storeAccountInAccountHashMap(ArrayList<Account> accounts);
    HashMap<Long,Account> getAccountInfo(long customer_id);

}

