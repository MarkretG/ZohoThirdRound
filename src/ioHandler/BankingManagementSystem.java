package ioHandler;
import bankingManagement.Account;
import bankingManagement.Customer;
import inMemoryStorageHandling.AccountNotFoundException;
import logicalLayer.Controller;
import logicalLayer.LogicalException;
import logicalLayer.LogicalHandler;
import persistence.DBUtil;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
public class  BankingManagementSystem {
    public static void main(String[] args) throws SQLException, LogicalException {
        InputHandler inputHandler=new InputHandler();
        LogicalHandler.getInstance().initialiseHashMap();
        System.out.println("welcome to banking management system");
        System.out.println("1.New Customer\n2.Add new account for existing customer\n3.get accounts info for given customer_id\n4.exit");

        while (true)
        {
            int choice=inputHandler.getChoice();
            switch (choice)
            {
                case 1: {
                    ArrayList<Customer> customers = inputHandler.getCustomerInfo();
                    ArrayList<Account> accounts = inputHandler.getAccountInfo(customers.size());
                    LogicalHandler.getInstance().handleNewCustomer(customers, accounts);
                    }
                break;
                case 2: {
                    System.out.println("enter customer id");
                    long customer_id = inputHandler.getCustomerId();
                    System.out.println("enter balance");
                    double balance = inputHandler.getBalance();
                    LogicalHandler.getInstance().addNewAccountForExistingCustomer(customer_id,balance);
                   }
                break;
                case 3:
                    System.out.println("enter customer id");
                    long customer_id = inputHandler.getCustomerId();
                    try {
                        HashMap<Long, Account> accountInfo = Controller.getInMemoryStorageDAOHandler().getAccountsInfo(customer_id);
                        System.out.println(accountInfo);
                    }
                    catch (AccountNotFoundException e)
                    {
                        System.out.println("Customer_id:"+e.getCustomer_id()+"this customer_id not available in account table");
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

