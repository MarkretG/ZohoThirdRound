package ioHandler;
import bankingManagement.Account;
import bankingManagement.Admin;
import bankingManagement.Customer;
import inMemoryStorageHandling.AccountNotFoundException;
import inMemoryStorageHandling.InMemoryStorageDAO;
import logicalLayer.Controller;
import logicalLayer.LogicalException;
import logicalLayer.LogicalHandler;
import persistence.DBUtil;
import persistence.PersistenceException;
import java.util.ArrayList;
import java.util.HashMap;
public class  BankingManagementSystem {
    public static void main(String[] args) throws LogicalException, PersistenceException, AccountNotFoundException {

        LogicalHandler.getInstance().initialiseHashMap();
        System.out.println("welcome to banking management system");
        while (true) {
            System.out.println("1.admin login\n 2.Customer login");
            int choice = InputHandler.getInstance().getChoice();
            switch (choice) {
                case 1:
                    AdminLogin();
                    break;
                case 2:
                    customerLogin();
                    break;

            }

        }
    }

    public static void AdminLogin() throws LogicalException, PersistenceException, AccountNotFoundException {
        Admin admin = LogicalHandler.getInstance().initialiseAdmin();
        InputHandler inputHandler = InputHandler.getInstance();
        Admin adminLogin = inputHandler.getAdminInfo();
        if (LogicalHandler.getInstance().verifyAdmin(admin, adminLogin)) {
            boolean end = true;
            while (end) {
                System.out.println("1.New Customers\n2.Add new account for existing customer\n3.get accounts info for given customer_id\n4.delete account5.exit");
                int ch = inputHandler.getChoice();
                switch (ch) {
                    case 1: {
                        ArrayList<Customer> customers = inputHandler.getCustomerInfo();
                        ArrayList<Account> accounts = inputHandler.getAccountInfo(customers.size());
                        LogicalHandler.getInstance().handleNewCustomer(customers, accounts);
                    }
                    break;
                    case 2: {
                        long customer_id = inputHandler.getCustomerId();
                        System.out.println("enter balance");
                        double balance = inputHandler.getBalance();
                        LogicalHandler.getInstance().addNewAccountForExistingCustomer(customer_id, balance);
                    }
                    break;
                    case 3: {
                        long customer_id = inputHandler.getCustomerId();
                        try {
                            HashMap<Long, Account> accountInfo = Controller.getInMemoryStorageDAOHandler().getAccountsInfo(customer_id);
                            System.out.println(accountInfo);
                        } catch (AccountNotFoundException e) {
                            System.out.println("Customer_id:" + e.getCustomer_id() + "this customer_id not available in account table");
                        }
                    }
                    break;
                    case 4:
                    {
                        long customerId= inputHandler.getCustomerId();
                        long accountId=inputHandler.getAccountId();
                        LogicalHandler.getInstance().deleteAccount(customerId,accountId);
                    }
                    break;
                    case 5: {
                        end = false;
                    }
                    break;
                }

            }
        } else {
            System.out.println("invalid admin name or password\n press 1 for login");
        }

    }

    public  static void customerLogin() throws LogicalException, AccountNotFoundException {
        Customer customer = InputHandler.getInstance().getCustomerLoginInfo();
        if (LogicalHandler.getInstance().verifyCustomer(customer)) {
            boolean end=true;
            while (end)
            {
                System.out.println("1.withdraw\n2.deposit\n3.transaction details\n4.transfer money5.exit");
                int choice=InputHandler.getInstance().getChoice();
                switch (choice)
                {
                    case 1:
                        HashMap<Long,Account> accountHashMap=Controller.getInMemoryStorageDAOHandler().getAccountsInfo(customer.getCustomer_id());
                        System.out.println("which account you want to withdraw amount");
                        long account_id=InputHandler.getInstance().getAccountId();

                }
            }

        }
    }
}




