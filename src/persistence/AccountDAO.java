package persistence;
import accountManagement.Account;
import java.sql.SQLException;
import java.util.ArrayList;
public interface AccountDAO {
    //insert  account
    void addAccount(Account account)throws SQLException;
    //select account by id
    ArrayList<Account> selectAccount(long customer_id) throws SQLException;
    //select Accounts
    ArrayList<Account> selectAllAccount()throws SQLException;
}

