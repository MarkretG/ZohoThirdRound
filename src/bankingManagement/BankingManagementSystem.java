package bankingManagement;
import controller.Controller;
import inMemoryStorageHandling.InMemoryStorageDAO;
import persistence.DBUtil;
import java.sql.SQLException;
import java.util.HashMap;
public class  BankingManagementSystem {
    public static void main(String[] args) throws SQLException,ClassNotFoundException{
        InMemoryStorageDAO inMemoryStorageDAO=Controller.getInMemoryStorageDAOHandler();
        inMemoryStorageDAO.initialiseHashMap();
        System.out.println("welcome to banking management system");
        System.out.println("1.New Customer\n2.Add new account for existing customer\n3.get accounts info for given customer_id\n4.exit");

        while (true)
        {
            int choice=Controller.getInputHandler().getNextIntFromUser();
            switch (choice)
            {
                case 1:
                    Controller.getInMemoryStorageDAOHandler().handleNewCustomer();
                    break;
                case 2:
                    Controller.getInMemoryStorageDAOHandler().AddNewAccountForExistingCustomer();
                    break;
                case 3:
                    HashMap<Long, Account> accountInfo = Controller.getInMemoryStorageDAOHandler().getAccountsInfo();
                    System.out.println(accountInfo.toString());
                    break;
                case 4:
                    DBUtil.closeConnection();
                    Controller.getInputHandler().closeScanner();
                    System.exit(0);

            }

        }
    }
}

