package persistence;
import bankingManagement.Customer;
import java.sql.*;
import java.util.ArrayList;
public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public long addCustomer(Customer customer) throws SQLException{
        long key=-1L;
        Connection connection = DBUtil.getConnection();
        String query="insert into customer_info(name,age,phone) values(?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS)) {
            //preparedStatement.setLong(1, customer.getCustomer_id());
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setInt(2, customer.getAge());
            preparedStatement.setLong(3, customer.getPhone());
            preparedStatement.execute();
            ResultSet resultSet=preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                key = resultSet.getLong(1);
            }
        }
        return key;
    }

    @Override
    public ArrayList<Customer> selectCustomers(long customer_id) throws SQLException{
        ArrayList<Customer> customers=new ArrayList<>();
        Connection connection = DBUtil.getConnection();
        String query="select customer_id,name from customer_info where customer_id=customer_id";
        try ( PreparedStatement preparedStatement = connection.prepareStatement(query);
              ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Customer customerList=new Customer();
                customerList.setCustomer_id(resultSet.getLong(1));
                customerList.setName(resultSet.getString(2));
                customers.add(customerList);
            }
        }
        return customers;

    }


    @Override
    public ArrayList<Customer> selectAllCustomers() throws SQLException{
        ArrayList<Customer> customers=new ArrayList<>();
        Connection connection = DBUtil.getConnection();
        String query="select customer_id,name from customer_info";
        try ( PreparedStatement preparedStatement = connection.prepareStatement(query);
              ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Customer customerList=new Customer();
                customerList.setCustomer_id(resultSet.getLong(1));
                customerList.setName(resultSet.getString(2));
                customers.add(customerList);
            }
        }
        return customers;

    }
}