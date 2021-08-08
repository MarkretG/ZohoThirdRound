package bankingSystem;
import persistence.CustomerDAO;
import persistence.AccountDAO;
import inputHandler.InputHandlerDAO;
import accountManagement.Account;
import customerManagement.Customer;
import controller.Controller;
import persistence.JDBCTemplate;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
public class  BankingManagementSystem {
    public static void main(String[] args) throws SQLException {
        CustomerDAO customerDAO=Controller.getCustomerPersistenceDaoHandler();
        AccountDAO accountDAO=Controller.getAccountPersistenceDaoHandler();
        InputHandlerDAO inputHandlerDAO=Controller.getInputHandler();
        InMemoryStorageDAO inMemoryStorageDAO =Controller.getBankingDaoHandler();
        //initially store customer table and account table in hashmap
        inMemoryStorageDAO.storeCustomerInCustomerHashMap(customerDAO.selectAllCustomer());
        inMemoryStorageDAO.storeAccountInAccountHashMap(accountDAO.selectAllAccount());
        System.out.println("welcome to banking management system");
        System.out.println("1.New Customer\n2.Add new account for existing customer\n3.get account info for given customer_id\n4.exit");
        while (true)
        {
            int choice=inputHandlerDAO.scanner.nextInt();
            switch (choice)
            {
                case 1:
                    Customer customer=inputHandlerDAO.getCustomersInfo();
                    customerDAO.addCustomer(customer);
                    Account account=inputHandlerDAO.getAccountsInfo();
                    accountDAO.addAccount(account);
                    ArrayList<Customer> customers =customerDAO.selectCustomer(customer.getCustomer_id());
                    inMemoryStorageDAO.storeCustomerInCustomerHashMap(customers);
                    ArrayList<Account> accounts=accountDAO.selectAccount(account.getCustomer_id());
                    inMemoryStorageDAO.storeAccountInAccountHashMap(accounts);
                    break;
                case 2:
                    Account account1=inputHandlerDAO.getAccountsInfo();
                    accountDAO.addAccount(account1);
                    ArrayList<Account> accounts1=accountDAO.selectAccount(account1.getCustomer_id());
                    inMemoryStorageDAO.storeAccountInAccountHashMap(accounts1);
                    break;
                case 3:
                    System.out.println("enter customer_id for given Customer_info");
                    long customer_id=inputHandlerDAO.scanner.nextLong();
                    HashMap<Long,Account> accountInfo= inMemoryStorageDAO.getAccountInfo(customer_id);
                    System.out.println(accountInfo.toString());

                case 4:
                    JDBCTemplate.closeConnection();
                    inputHandlerDAO.closeScanner();
                    System.exit(0);

            }

        }
    }
}
