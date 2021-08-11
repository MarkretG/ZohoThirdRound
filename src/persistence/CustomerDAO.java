package persistence;
import bankingManagement.Customer;
import java.sql.SQLException;
import java.util.ArrayList;
public interface CustomerDAO {
    //insert  account
    ArrayList<Long> addCustomer(ArrayList<Customer> customers)throws SQLException,ClassNotFoundException;
    ArrayList<Customer> selectCustomers(ArrayList<Long> customer_ids) throws SQLException,ClassNotFoundException;
    //select Accounts
    ArrayList<Customer> selectAllCustomers()throws SQLException,ClassNotFoundException;
}