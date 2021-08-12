package ioHandler;
import bankingManagement.Account;
import bankingManagement.Customer;
import logicalLayer.Controller;
import logicalLayer.LogicalHandler;
import persistence.AccountNotFoundException;
import persistence.ConnectionNotFoundException;
import persistence.DBUtil;
import persistence.SQLRelatedException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
public class  BankingManagementSystem {
    public static void main(String[] args) throws SQLException, ConnectionNotFoundException, SQLRelatedException {
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
                    long customer_id = inputHandler.getCustomerId();
                    double balance = inputHandler.getBalance();
                    LogicalHandler.getInstance().addNewAccountForExistingCustomer(customer_id,balance);
                }
                break;
                case 3: {
                    long customer_id = inputHandler.getCustomerId();
                    try {
                        HashMap<Long, Account> accountInfo = Controller.getInMemoryStorageDAOHandler().getAccountsInfo(customer_id);
                        System.out.println(accountInfo);
                       }
                     catch (AccountNotFoundException e)
                     {
                        System.out.println(e.getCustomer_id()+"this id not available in account table");
                     }

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

