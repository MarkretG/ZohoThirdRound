package inputHandler;
import bankingManagement.Customer;
import bankingManagement.Account;
import java.util.Scanner;
public interface InputHandlerDAO {
    Scanner scanner=new Scanner(System.in);
    Customer getCustomerInfo();
    Account getAccountInfo();
    long getNextLongFromUser();
    void closeScanner();
}
