package persistence;
import bankingManagement.Customer;
import java.sql.SQLException;
import java.util.ArrayList;
public interface CustomerDAO {
    //insert  account
    long addCustomer(Customer customer)throws SQLException;
    ArrayList<Customer> selectCustomers(long customer_id) throws SQLException;
    //select Accounts
    ArrayList<Customer> selectAllCustomers()throws SQLException;
}