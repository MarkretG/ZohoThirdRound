package persistence;
import bankingManagement.Account;
import java.sql.SQLException;
import java.util.ArrayList;
public interface AccountDAO {
    //insert  account
    void addAccount(Account account,long customer_id)throws SQLException;
    //select account by id
    ArrayList<Account> selectAccounts(long customer_id) throws SQLException;
    //select Accounts
    ArrayList<Account> selectAllAccounts()throws SQLException;
}