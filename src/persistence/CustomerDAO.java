package persistence;
import customerManagement.Customer;
import java.sql.SQLException;
import java.util.ArrayList;
public interface CustomerDAO {
    //insert  account
    void addCustomer(Customer customer)throws SQLException;
    ArrayList<Customer> selectCustomer(long customer_id) throws SQLException;
    //select Accounts
    ArrayList<Customer> selectAllCustomer()throws SQLException;
}
