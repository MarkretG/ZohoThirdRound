package customerManagementDao;
import customerManagement.Customer;
import java.sql.SQLException;
import java.util.ArrayList;
public interface CustomerDAO {
    //insert  account
    void addCustomer(Customer customer)throws SQLException;
    //select  account by id
    // Account selectAccount(int id);
    //select Accounts
    ArrayList<Customer> selectAllCustomer()throws SQLException;
}
