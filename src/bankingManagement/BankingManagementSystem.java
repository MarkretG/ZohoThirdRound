package bankingManagement;
import controller.Controller;
import persistence.DBUtil;
import java.sql.SQLException;
import java.util.HashMap;
public class  BankingManagementSystem {
    public static void main(String[] args) throws SQLException{
        Controller.getInMemoryStorageDAOHandler().initialiseHashMap();
        System.out.println("welcome to banking management system");
        System.out.println("1.New Customer\n2.Add new account for existing customer\n3.get accounts info for given customer_id\n4.exit");

        while (true)
        {
            int choice=Controller.getInputHandler().getChoice();
            switch (choice)
            {
                case 1: {
                    Customer customer = Controller.getInputHandler().getCustomerInfo();
                    Account account = Controller.getInputHandler().getAccountInfo();
                    Controller.getInMemoryStorageDAOHandler().handleNewCustomer(customer, account);
                }
                    break;
                case 2: {
                    long customer_id = Controller.getInputHandler().getId();
                    Account account = Controller.getInputHandler().getAccountInfo();
                    Controller.getInMemoryStorageDAOHandler().addNewAccountForExistingCustomer(account, customer_id);
                   }
                    break;
                case 3: {
                    long customer_id = Controller.getInputHandler().getId();
                    HashMap<Long, Account> accountInfo = Controller.getInMemoryStorageDAOHandler().getAccountsInfo(customer_id);
                    System.out.println(accountInfo.toString());
                   }
                    break;
                case 4:
                    DBUtil.closeConnection();
                    Controller.getInputHandler().closeScanner();
                    System.exit(0);

            }

        }
    }
}

