package bankingSystem;
import accountManagement.Account;
import customerManagement.Customer;
import java.util.ArrayList;
import java.util.HashMap;
public class BankingDAOImplement implements BankingDAO{
    HashMap<Long,String> customerHashMap=new HashMap<>();
    HashMap<Long,HashMap<Long,Account>> accountInfoHashMap=new HashMap<>();
    @Override
    public void StoreCustomerInCustomerHashMap(ArrayList<Customer> customers) {
        for (Customer customer:customers)
        {
            customerHashMap.put(customer.getCustomer_id(),customer.getName());
        }

    }

    @Override
    public void StoreAccountInAccountHashMap(ArrayList<Account> accounts) {
        for (Account account:accounts)
        {
            HashMap accountHashMap = accountInfoHashMap.get(account.getCustomer_id());
            if (accountHashMap == null) {
                accountHashMap = new HashMap<Long, Account>();
            }
            accountHashMap.put(account.getAccount_id(), account);
            accountInfoHashMap.put(account.getCustomer_id(), accountHashMap);
        }

    }

    @Override
    public HashMap<Long, Account> getAccountInfo(long customer_id) {
        return accountInfoHashMap.get(customer_id);
    }
}
