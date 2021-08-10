package ioHandler;
import bankingManagement.Account;
import bankingManagement.Customer;
import logicalLayer.Controller;
import logicalLayer.LogicalHandling;
import persistence.DBUtil;
import java.sql.SQLException;
import java.util.HashMap;
public class  BankingManagementSystem {
    public static void main(String[] args) throws SQLException{
        InputHandler inputHandler=new InputHandler();
        LogicalHandling logicalHandling=new LogicalHandling();
        logicalHandling.initialiseHashMap();
        System.out.println("welcome to banking management system");
        System.out.println("1.New Customer\n2.Add new account for existing customer\n3.get accounts info for given customer_id\n4.exit");

        while (true)
        {
            int choice=inputHandler.getChoice();
            switch (choice)
            {
                case 1: {
                    Customer customer = inputHandler.getCustomerInfo();
                    Account account = inputHandler.getAccountInfo();
                    logicalHandling.handleNewCustomer(customer, account);
                }
                break;
                case 2: {
                    long customer_id = inputHandler.getId();
                    Account account =  inputHandler.getAccountInfo();
                    logicalHandling.addNewAccountForExistingCustomer(account, customer_id);
                }
                break;
                case 3: {
                    long customer_id = inputHandler.getId();
                    HashMap<Long, Account> accountInfo = Controller.getInMemoryStorageDAOHandler().getAccountsInfo(customer_id);
                    System.out.println(accountInfo);
                }
                break;
                case 4:
                    DBUtil.closeConnection();
                    inputHandler.closeScanner();
                    System.exit(0);
            }

        }
    }
}
