package bankingSystem;
import customerManagement.Customer;
import accountManagement.Account;
import java.util.ArrayList;
import java.util.HashMap;
public interface BankingDAO {
    void StoreCustomerInCustomerHashMap(ArrayList<Customer> customers);
    void StoreAccountInAccountHashMap(ArrayList<Account> accounts);
    HashMap<Long,Account> getAccountInfo(long customer_id);

}
