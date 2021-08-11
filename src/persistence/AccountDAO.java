package persistence;
import bankingManagement.Account;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public interface AccountDAO {

    void addAccount(long customer_id,double balance) throws SQLException;
    //insert  account
    ArrayList<Long> addAccounts(HashMap<Long,Account> account)throws SQLException;
    //select accounts by id
    ArrayList<Account> selectAccounts(ArrayList<Long> customer_ids) throws SQLException;
    Account selectAccount(long customer_id) throws SQLException;
    //select Accounts
    ArrayList<Account> selectAllAccounts()throws SQLException;
}