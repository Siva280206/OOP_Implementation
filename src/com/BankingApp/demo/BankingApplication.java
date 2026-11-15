package com.BankingApp.demo;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class BankingApplication {
    static Scanner sc = new Scanner(System.in);
    static List<Customer> customers = new ArrayList<>();
    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            System.out.println("\nBank");
            System.out.println("1 New Customer");
            System.out.println("2 Existing Customer Login");
            System.out.println("3 Exit");
			System.out.println();
            System.out.print("\nEnter choice: ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    registerCustomer();
                    break;
                case 2:
                    loginCustomer();
                    break;
                case 3:
                    running = false;
                    System.out.println("\nBye");
                    break;
                default:
                    System.out.println("\nInvalid choice!");
            }
        }
    }
	//New Customer
    static void registerCustomer() {

        System.out.println("\nNew Customer");
        System.out.print("Customer ID: ");
        int id = sc.nextInt();
		sc.nextLine(); 
        if (findCustomer(id) != null) {
            System.out.println("Customer ID already exists!");
            return;
        }

        System.out.print("Customer Name: ");
        String name = sc.nextLine();
        System.out.print("\nEmail: ");
        String email = sc.nextLine();
        Customer customer = new Customer(id, name, email);
        customers.add(customer);
        System.out.println("Registration successful!");
        System.out.println("Open an account?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        int choice = sc.nextInt();
        if (choice == 1) {
            openAccount(customer);
        }
    }
	//Log in Customer
    static void loginCustomer() {
        System.out.print("\nEnter your Customer ID: ");
        int id = sc.nextInt();
        Customer customer = findCustomer(id);
        if (customer == null) {
            System.out.println("No customer INVALID !");
            return;
        }
        System.out.println("Welcome, " + customer.getCname());
        customerDashboard(customer);
    }
	//Check if customer exists in List
    static Customer findCustomer(int id) {
        for (Customer customer : customers) {
            if (customer.getCid() == id) {
                return customer;
            }
        }
        return null;
    }
	//After login Dashboard
    static void customerDashboard(Customer customer) {
        boolean active = true;
        while (active) {
            System.out.println("Customer Dashboard");
            System.out.println("1 View Accounts");
            System.out.println("2 New Account");
            System.out.println("3 Deposit");
            System.out.println("4 Withdraw ");
            System.out.println("5 Payh");
            System.out.println("6 Calculate Interest");
            System.out.println("7 Logout");

            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    viewAccounts(customer);
                    break;
                case 2:
                    openAccount(customer);
                    break;
                case 3:
                    pDeposit(customer);
                    break;
                case 4:
                    pWithdraw(customer);
                    break;
                case 5:
                    makePayment(customer);
                    break;
                case 6:
                    calculateInterest(customer);
                    break;
                case 7:
                    active = false;
                    System.out.println("Logged out");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
    // New Account
    static void openAccount(Customer customer) {
        System.out.println("NEW ACCOUNT CREATION");
        System.out.println("1. Savings Account");
        System.out.println("2. Current Account");
        int choice = sc.nextInt();

        System.out.print("Account ID: ");
        int accID = sc.nextInt();

        System.out.print("Initial Deposit: ");
        double balance = sc.nextDouble();

        if (choice == 1) {
            System.out.print("Interest Rate: ");
            int rate = sc.nextInt();
            BankAccount Saccount = new SavingsAccount(
                accID, balance, customer, rate
            );
			customer.addAccount(Saccount);
        } 
		else if (choice == 2) {
            System.out.print("Minimum Balance: ");
            double minimum = sc.nextDouble();
            BankAccount Caccount = new CurrentAccount(
                accID, balance, customer, minimum
            );
			customer.addAccount(Caccount); 

        } else {
            System.out.println("Invalid account type!");
            return;
        }
        System.out.println("Account created successfully!");
    }

    // viewing accounts 
    static void viewAccounts(Customer customer) {
        System.out.println("Myaccounts");
        List<BankAccount> accounts = customer.showAcc();
        if (accounts.isEmpty()) {
            System.out.println("No accounts available.");
            return;
        }

        for (BankAccount account : accounts) {
            System.out.println("Account ID: " + account.getAccNo() + ", Balance: " + account.getBalance());
        }
    }

    //selecting account
    static BankAccount selectAccount(Customer customer) {

        List<BankAccount> accounts = customer.showAcc();
        if (accounts.isEmpty()) {
            System.out.println("No accounts available!");
            return null;
        }
        System.out.println("Select Account:");
		int i=0;
        for(BankAccount a:accounts){
			System.out.println((++i)+"Account ID: "+a.getAccNo()+"Balance: "+a.getBalance());
		}
        int choice = sc.nextInt();
        if (choice < 1 || choice > accounts.size()) {
            System.out.println("Invalid selection!");
            return null;
        }
        return accounts.get(choice - 1);
    }
    // deposit
    static void pDeposit(Customer customer) {
        BankAccount account = selectAccount(customer);
        if (account == null) return;
        System.out.print("Enter deposit amount: ");
        double amount = sc.nextDouble();
        account.deposit(amount);
    }

    // Withdraw
    static void pWithdraw(Customer customer) {
        BankAccount account = selectAccount(customer);
        if (account == null) return;
        System.out.print("Enter withdrawal amount: ");
        double amount = sc.nextDouble();
        account.withDraw(amount);
    }

    // pay
    static void makePayment(Customer customer) {
        BankAccount account = selectAccount(customer);
        if (account == null) return;
        System.out.println("\n1. UPI Payment");
        System.out.println("2. Card Payment");
        int choice = sc.nextInt();
        System.out.print("Enter payment amount: ");
        double amount = sc.nextDouble();
        Payment paying;
        if (choice == 1) {
            System.out.print("Enter UPI ID: ");
            String upi = sc.nextLine();
            paying = new UPIPayment(upi);
        } else if (choice == 2) {
            System.out.print("Enter card number: ");
            int num = sc.nextInt();
            paying = new CardPayment(num);
        } else {
            System.out.println("Invalid payment method!");
            return;
        }
        paying.pay(account, amount);
    }
    //interest
    static void calculateInterest(Customer customer) {
        BankAccount account = selectAccount(customer);
        if (account == null) return;
        account.calculateInterest();
    }
}
