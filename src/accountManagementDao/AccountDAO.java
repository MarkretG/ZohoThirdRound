package accountManagementDao;
import accountManagement.Account;
import java.sql.SQLException;
import java.util.ArrayList;
public interface AccountDAO {
    //insert  account
    void addAccount(Account account)throws SQLException;
    //select  account by id
   // Account selectAccount(int id);
    //select Accounts
    ArrayList<Account> selectAllAccount()throws SQLException;
}

