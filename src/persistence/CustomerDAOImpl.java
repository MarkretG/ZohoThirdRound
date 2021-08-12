package persistence;
import bankingManagement.Customer;
import java.sql.*;
import java.util.ArrayList;
public class CustomerDAOImpl implements CustomerDAO {
    private final int  errorCodeForSqlInsertQuery=402;
    private final int  errorCodeForSqlSelectQuery=403;
    @Override
    public ArrayList<Long> addCustomer(ArrayList<Customer> customers)throws SQLException, PersistenceException {
        ArrayList<Long> customer_ids = new ArrayList<>();
        Connection connection = DBUtil.getConnection();
        String query = "insert into customer_info(name,age,phone) values(?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            //preparedStatement.setLong(1, customer.getCustomer_id());
            for (Customer customer : customers) {
                preparedStatement.setString(1, customer.getName());
                preparedStatement.setInt(2, customer.getAge());
                preparedStatement.setLong(3, customer.getPhone());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                customer_ids.add(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new PersistenceException("Exception occur in insert query in customer table", errorCodeForSqlInsertQuery);
        }
        return customer_ids;
    }

    @Override
    public ArrayList<Customer> selectCustomers(ArrayList<Long> customer_ids) throws SQLException, PersistenceException {
        ArrayList<Customer> customers=new ArrayList<>();
        Connection connection = DBUtil.getConnection();
        String query="select customer_id,name from customer_info where customer_id in (?)";
        try ( PreparedStatement preparedStatement = connection.prepareStatement(query);
              ResultSet resultSet = preparedStatement.executeQuery()) {
            Array array = connection.createArrayOf("BIGINT", customer_ids.toArray());
            preparedStatement.setArray(1, array);
            while (resultSet.next()) {
                Customer customerList=new Customer();
                customerList.setCustomer_id(resultSet.getLong(1));
                customerList.setName(resultSet.getString(2));
                customers.add(customerList);
            }
        }
        catch (SQLException e)
        {
            throw new PersistenceException("Exception occur in select query for customer table",errorCodeForSqlSelectQuery);
        }
        return customers;

    }


    @Override
    public ArrayList<Customer> selectAllCustomers() throws SQLException, PersistenceException {
        ArrayList<Customer> customers = new ArrayList<>();
        Connection connection = DBUtil.getConnection();
        String query = "select customer_id,name from customer_info";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Customer customerList = new Customer();
                customerList.setCustomer_id(resultSet.getLong(1));
                customerList.setName(resultSet.getString(2));
                customers.add(customerList);
            }
        } catch (SQLException e) {
            throw new PersistenceException("Exception occur in select query for customer table", errorCodeForSqlSelectQuery);
        }
        return customers;

    }
}