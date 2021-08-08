package customerManagementDao;
import customerManagement.Customer;
import dbUtil.JDBCTemplate;
import java.sql.*;
import java.util.ArrayList;
public class CustomerDAOImplement implements CustomerDAO {
    @Override
    public void addCustomer(Customer customer) throws SQLException {
        Connection con = JDBCTemplate.getConnection();
        String query="insert into customer_info(name,age,phone) values(?,?,?,?)";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
                preparedStatement.setLong(1, customer.getCustomer_id());
                preparedStatement.setString(2, customer.getName());
                preparedStatement.setInt(4, customer.getAge());
                preparedStatement.setLong(5, customer.getPhone());
                preparedStatement.executeUpdate();
             }
    }

    @Override
    public ArrayList<Customer> selectAllCustomer() throws SQLException {
        ArrayList<Customer> customers=new ArrayList<>();
        Connection connection =JDBCTemplate.getConnection();
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
