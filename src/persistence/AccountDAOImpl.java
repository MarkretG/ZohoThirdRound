package persistence;
import bankingManagement.Account;
import java.sql.*;
import java.util.ArrayList;
public class AccountDAOImpl implements AccountDAO {
    @Override
    public void addAccount(Account account,long customer_id)throws SQLException{
        Connection connection= DBUtil.getConnection();
        String query="insert into account_info(customer_id,account_id,balance) values(?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setLong(1,customer_id);
            preparedStatement.setLong(2,account.getAccount_id());
            preparedStatement.setDouble(3,account.getBalance());
            preparedStatement.executeUpdate();
        }

    }

    @Override
    public ArrayList<Account> selectAccounts(long customer_id) throws SQLException{
        ArrayList<Account> accounts=new ArrayList<>();
        Connection connection = DBUtil.getConnection();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("select * from  account_info where customer_id=customer_id")) {
            while (resultSet.next()) {
                Account account1=new Account();
                account1.setCustomer_id(resultSet.getLong(1));
                account1.setAccount_id(resultSet.getLong(2));
                account1.setBalance(resultSet.getDouble(3));
                accounts.add(account1);
            }

        }
        return accounts;
    }

    @Override
    public ArrayList<Account> selectAllAccounts()throws SQLException{
        ArrayList<Account> accounts=new ArrayList<>();
        Connection connection = DBUtil.getConnection();
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