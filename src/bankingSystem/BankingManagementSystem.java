package bankingSystem;
import accountManagement.Account;
import accountManagementDao.AccountDAOImplement;
import customerManagement.Customer;
import customerManagementDao.CustomerDAOImplement;
import dbUtil.JDBCTemplate;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
public class BankingManagementSystem {
    public static void main(String[] args) throws SQLException {
        //Scanner scanner=new Scanner(System.in);
        InputHandler inputHandler=new InputHandler();
        CustomerDAOImplement customerDAOImplement=new CustomerDAOImplement();
        AccountDAOImplement accountDAOImplement=new AccountDAOImplement();
        BankingDAOImplement bankingDAOImplement=new BankingDAOImplement();
        System.out.println("welcome to banking management system");
        System.out.println("1.New Customer\n2.Add new account for existing customer\n3.get account info for given customer_id\n4.exit");
        while (true)
        {
            int choice=inputHandler.scanner.nextInt();
            switch (choice)
            {
                case 1:
                    Customer customer=inputHandler.getCustomersInfo();
                    customerDAOImplement.addCustomer(customer);
                    Account account=inputHandler.getAccountsInfo();
                    accountDAOImplement.addAccount(account);
                    ArrayList<Customer> customers =customerDAOImplement.selectAllCustomer();
                    bankingDAOImplement.StoreCustomerInCustomerHashMap(customers);
                    ArrayList<Account> accounts=accountDAOImplement.selectAllAccount();
                    bankingDAOImplement.StoreAccountInAccountHashMap(accounts);
                    break;
                case 2:
                    Account account1=inputHandler.getAccountsInfo();
                    accountDAOImplement.addAccount(account1);
                    break;
                case 3:
                    System.out.println("enter customer_id for given Customer_info");
                    long customer_id=inputHandler.scanner.nextLong();
                    HashMap<Long,Account> accountInfo=bankingDAOImplement.getAccountInfo(customer_id);
                    System.out.println(accountInfo.toString());

                case 4:
                    JDBCTemplate.closeConnection();
                    System.exit(0);

            }

        }
    }
}
