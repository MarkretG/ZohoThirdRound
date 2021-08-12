package persistence;
import bankingManagement.Account;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AccountDAOImpl implements AccountDAO {
    @Override
    public  void addAccount(long customer_id, double balance)throws SQLException,ConnectionNotFoundException,SQLRelatedException{
                      Connection connection = DBUtil.getConnection();
                String query = "insert into account_info(customer_id,balance) values(?,?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setLong(1, customer_id);
                    preparedStatement.setDouble(2, balance);
                    preparedStatement.executeUpdate();
                }
                catch (SQLException e)
                {
                    throw new SQLRelatedException("Exception occur in insert query");
                }
     }

    @Override
    public ArrayList<Long> addAccounts(HashMap<Long,Account> account)throws SQLException,ConnectionNotFoundException,SQLRelatedException{
        ArrayList<Long> customer_ids=new ArrayList<>();
        Connection connection= DBUtil.getConnection();
        String query="insert into account_info(customer_id,balance) values(?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS)){
            for (Map.Entry<Long,Account> entry:account.entrySet())
            {
                preparedStatement.setLong(1,entry.getKey());
               // preparedStatement.setLong(2, account.getAccount_id());
                preparedStatement.setDouble(2,entry.getValue().getBalance());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            ResultSet resultSet=preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                customer_ids.add(resultSet.getLong(1));
            }
            resultSet.close();
        }
        catch (SQLException e)
        {
            throw new SQLRelatedException("Exception occur in insert query in account table");
        }
        return customer_ids;

    }

    @Override
    public ArrayList<Account> selectAccounts(ArrayList<Long> customer_ids) throws SQLException,ConnectionNotFoundException,SQLRelatedException{
        ArrayList<Account> accounts=new ArrayList<>();
        Connection connection = DBUtil.getConnection();
        String query="select * from  account_info where customer_id in (?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            Array array=connection.createArrayOf("BIGINT", customer_ids.toArray());
            preparedStatement.setArray(1,array);
            while (resultSet.next()) {
                Account account1=new Account();
                account1.setCustomer_id(resultSet.getLong(1));
                account1.setAccount_id(resultSet.getLong(2));
                account1.setBalance(resultSet.getDouble(3));
                accounts.add(account1);
            }

        }
        catch (SQLException e)
        {
            throw new SQLRelatedException("Exception occur in insert query in account table");
        }
        return accounts;
    }

    @Override
    public Account selectAccount(long customer_id) throws SQLException,ConnectionNotFoundException,SQLRelatedException{
        Account account=new Account();
        Connection connection = DBUtil.getConnection();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("select * from  account_info where customer_id=customer_id")) {
            while (resultSet.next()) {
                account.setCustomer_id(resultSet.getLong(1));
                account.setAccount_id(resultSet.getLong(2));
                account.setBalance(resultSet.getDouble(3));
            }
        }
        catch (SQLException e)
        {
            throw new SQLRelatedException("Exception occur in select query in account table");
        }
        return account;
    }


    @Override
    public ArrayList<Account> selectAllAccounts()throws SQLException,ConnectionNotFoundException,SQLRelatedException{
        ArrayList<Account> accounts = new ArrayList<>();
        Connection connection = DBUtil.getConnection();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("select * from  account_info")) {
            while (resultSet.next()) {
                Account account = new Account();
                account.setCustomer_id(resultSet.getLong(1));
                account.setAccount_id(resultSet.getLong(2));
                account.setBalance(resultSet.getDouble(3));
                accounts.add(account);
            }
        }
        catch (SQLException e)
        {
            throw new SQLRelatedException("Exception occur in select query in account table");
        }

        return accounts;
    }
}
