package inputHandler;
import customerManagement.Customer;
import accountManagement.Account;
import java.util.Scanner;
public interface InputHandlerDAO {
    Scanner scanner=new Scanner(System.in);
    Customer getCustomersInfo();
    Account getAccountsInfo();
    void closeScanner();
}
