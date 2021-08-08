package accountManagementDao;
import accountManagement.Account;
import dbUtil.JDBCTemplate;
import java.sql.*;
import java.util.ArrayList;

public class AccountDAOImplement implements AccountDAO {
    @Override
    public void addAccount(Account account)throws SQLException {
        Connection connection= JDBCTemplate.getConnection();
        String query="insert into account_info(customer_id,account_id,balance) values(?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setLong(1,account.getCustomer_id());
            preparedStatement.setLong(2,account.getAccount_id());
            preparedStatement.setDouble(3,account.getBalance());
            preparedStatement.executeUpdate();
        }

    }

    @Override
    public ArrayList<Account> selectAllAccount()throws SQLException{
        ArrayList<Account> accounts=new ArrayList<>();
        Connection connection =JDBCTemplate.getConnection();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("select * from  account_info")) {
            while (resultSet.next()) {
                Account account=new Account();
                account.setCustomer_id(resultSet.getLong(1));
                account.setAccount_id(resultSet.getLong(2));
                account.setBalance(resultSet.getDouble(3));
                accounts.add(account);
            }
        }
        return  accounts;
    }
}

