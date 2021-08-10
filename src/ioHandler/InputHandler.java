package ioHandler;
import bankingManagement.Account;
import bankingManagement.Customer;
import java.util.Scanner;
public class InputHandler {
    private Scanner scanner=new Scanner(System.in);
    long customerId;
    int choice;
    public  Customer getCustomerInfo() {
        //System.out.println("Enter customer_id");
        //long customer_id = scanner.nextLong();
        System.out.println("enter name");
        scanner.nextLine();
        String name = scanner.nextLine();
        System.out.println("enter age");
        int age = scanner.nextInt();
        System.out.println("enter phone Number");
        long phone = scanner.nextLong();
        Customer customer = new Customer();
        //customer.setCustomer_id(customer_id);
        customer.setName(name);
        customer.setAge(age);
        customer.setPhone(phone);
        return customer;

    }
    public Account getAccountInfo() {
        //System.out.println("enter customer_id");
        //long customer_id = scanner.nextLong();
        System.out.println("enter account Id");
        long account_id = scanner.nextLong();
        System.out.println("enter balance");
        double balance = scanner.nextDouble();
        Account account = new Account();
        //account.setCustomer_id(customer_id);
        account.setAccount_id(account_id);
        account.setBalance(balance);
        return account;
    }

    public long getId() {
        System.out.println("enter customer_id");
        customerId=scanner.nextLong();
        return customerId;
    }

    public int getChoice() {
        choice=scanner.nextInt();
        return choice;
    }
    public void closeScanner() {
        scanner.close();
    }
}
