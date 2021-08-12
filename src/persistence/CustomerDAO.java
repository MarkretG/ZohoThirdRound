package persistence;
import bankingManagement.Customer;
import java.sql.SQLException;
import java.util.ArrayList;
public interface CustomerDAO {
    //insert  account
    ArrayList<Long> addCustomer(ArrayList<Customer> customers)throws SQLException,ConnectionNotFoundException,SQLRelatedException;
    ArrayList<Customer> selectCustomers(ArrayList<Long> customer_ids) throws SQLException,ConnectionNotFoundException,SQLRelatedException;
    //select Accounts
    ArrayList<Customer> selectAllCustomers()throws SQLException,ConnectionNotFoundException,SQLRelatedException;
}