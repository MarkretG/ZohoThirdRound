package persistence;
import bankingManagement.Customer;
import java.sql.SQLException;
import java.util.ArrayList;
public interface CustomerDAO {
    //insert  customers
    ArrayList<Long> addCustomer(ArrayList<Customer> customers)throws SQLException, PersistenceException;

    //select customers
    ArrayList<Customer> selectCustomers(ArrayList<Long> customer_ids) throws SQLException, PersistenceException;
    ArrayList<Customer> selectAllCustomers()throws SQLException, PersistenceException;
}