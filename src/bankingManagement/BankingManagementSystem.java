package bankingManagement;
import persistence.CustomerDAO;
import persistence.AccountDAO;
import inputHandler.InputHandlerDAO;
import controller.Controller;
import persistence.JDBCTemplate;
import java.sql.SQLException;
import java.util.HashMap;
import inMemoryStorageHandling.InMemoryStorageDAO;
public class  BankingManagementSystem {
    public static void main(String[] args) throws SQLException {
        CustomerDAO customerDAO=Controller.getCustomerPersistenceDaoHandler();
        AccountDAO accountDAO=Controller.getAccountPersistenceDaoHandler();

        InputHandlerDAO inputHandlerDAO=Controller.getInputHandler();
        InMemoryStorageDAO inMemoryStorageDAO =Controller.getInMemoryStorageDAOHandler();

        //initially store customer table and account table in hashmap
        inMemoryStorageDAO.storeCustomersInCustomerHashMap(customerDAO.selectAllCustomers());
        inMemoryStorageDAO.storeAccountsInAccountHashMap(accountDAO.selectAllAccounts());

        System.out.println("welcome to banking management system");
        System.out.println("1.New Customer\n2.Add new account for existing customer\n3.get accounts info for given customer_id\n4.exit");

        while (true)
        {
            int choice=inputHandlerDAO.getNextIntFromUser();
            switch (choice)
            {
                case 1:
                   {
                    Customer customer = inputHandlerDAO.getCustomerInfo();
                    customerDAO.addCustomer(customer);
                    Account account = inputHandlerDAO.getAccountInfo();
                    accountDAO.addAccount(account);
                    inMemoryStorageDAO.storeCustomersInCustomerHashMap(customerDAO.selectCustomers(customer.getCustomer_id()));
                    inMemoryStorageDAO.storeAccountsInAccountHashMap(accountDAO.selectAccounts(account.getCustomer_id()));
                    }
                    break;
                case 2: {
                    Account account = inputHandlerDAO.getAccountInfo();
                    accountDAO.addAccount(account);
                    inMemoryStorageDAO.storeAccountsInAccountHashMap(accountDAO.selectAccounts(account.getCustomer_id()));
                }
                    break;
                case 3: {
                    System.out.println("enter customer_id for given Customer_info");
                    long customer_id = inputHandlerDAO.getNextLongFromUser();
                    HashMap<Long, Account> accountInfo = inMemoryStorageDAO.getAccountsInfo(customer_id);
                    System.out.println(accountInfo.toString());
                }
                break;

                case 4:
                    JDBCTemplate.closeConnection();
                    inputHandlerDAO.closeScanner();
                    System.exit(0);

            }

        }
    }
}

