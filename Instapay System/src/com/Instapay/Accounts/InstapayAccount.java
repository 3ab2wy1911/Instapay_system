package com.Instapay.Accounts;

import com.Instapay.Accounts.Banks.BankAccount;
import com.Instapay.Accounts.Banks.BankApi;
import com.Instapay.Accounts.Wallets.WalletAccount;
import com.Instapay.Accounts.Wallets.WalletApi;
import com.Instapay.Bills.Bills;
import com.Instapay.Manager.Database;

import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import static com.Instapay.Manager.Database.*;
import static java.lang.System.exit;

public class InstapayAccount {

    // Common Attributes
    private String userName;
    private String password;
    private String mobileNumber;
    private double balance;
    private AccountType accountType;

    private String type;
    private List<Bills> bills;

    //------------------------------------------------------------------------------------------------------------------

    // Constructors

    public  InstapayAccount(){
    }

    //----------------------------------------------------------------

    public InstapayAccount(String userName, String password, String mobileNumber, double balance, String type){
        this.userName = userName;
        this.password = password;
        this.mobileNumber = mobileNumber;
        this.balance = balance;
        this.type = type;
        setBills(mobileNumber);
    }

    //----------------------------------------------------------------

    // Setters

    public void setBills(String number) {
        this.bills = Database.getBill(number);
    }

    //----------------------------------------------------------------

    // Getters
    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public double getBalance() {
        return balance;
    }

    public String getType() {
        return type;
    }

    public List<Bills> getBills() {
        return bills;
    }
    private String getUsername() {
        return userName;
    }

    //----------------------------------------------------------------

    public void register() {
        String type;
        System.out.println("--------------------------------Register--------------------------------");

        // Select the type of the account.
        int choice = 3;
        while (choice > 2 || choice < 0) {
            System.out.println("Please Choose the type of your account :\n1.Bank Account.\n2.Wallet Account\n0.Exit");
            choice = scanner.nextInt();
        }
        if (choice == 1){
            type = "bank";
        }
        else {
            type = "wallet";
        }
        Database.displayApis(choice); // Display the banks or the wallets.

        // Select the name of the bank or wallet.
        System.out.println("Enter the id of your Bank or Wallet : ");
        int answer = scanner.nextInt();



        System.out.print("Enter UserName of your Bank or Wallet account: ");
        String userName = scanner.next();
        System.out.print("Enter Password of your Bank or Wallet account: ");
        scanner.nextLine();
        String password = scanner.nextLine();
        System.out.print("Enter Mobile number of your Bank or Wallet account: ");
        String mobileNumber = scanner.next();

        if(choice == 1) {
            BankApi api = Database.selectBank(answer);
            if (api == null) {
                System.out.println("Bank not found ! Exiting the Registration Process...");
                return;
            }

            this.accountType = api.getAccount(userName);
        }
        else {
            WalletApi api = Database.selectWallet(answer);
            if (api == null) {
                System.out.println("Wallet not found ! Exiting the Registration Process...");
                return;
            }
            this.accountType = api.getAccount(userName);
        }

        if (accountType == null) {
            System.out.println("Account not found ! Exiting the Registration Process...");
            return;
        }

        String accountPassword = accountType.getPassword(), accountMobileNumber = accountType.getMobileNumber();

        if (accountPassword.equals(password) && accountMobileNumber.equals(mobileNumber)) {
            System.out.println("Account was Found :)");
        }

        else {
            System.out.println("Invalid Data :(");
            return;
        }

        System.out.println("Verifying mobile Number....");
        // Verify that the number is found.
        if (Database.verifyNumber(mobileNumber)){
            System.out.println("Mobile Verification Succeed :)");
        }
        else{
            System.out.println("Mobile Verification Failed :(");
            return;
        }

        if (Database.verifyInstaMobileNumber(mobileNumber, type)){
            System.out.println("There is already an account with this mobile number !!!");
            return;
        }
        System.out.print("Enter a user name : ");
        userName = scanner.next();
        while (Database.verifyUserName(userName)){
            System.out.print("This User Name is unavailable please enter another one : ");
            userName = scanner.next();
        }

        System.out.print("Enter a password : ");
        scanner.nextLine();
        password = scanner.nextLine();
        String strongPasswordRegex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";  // Regex of strong password.
        while (!password.matches((strongPasswordRegex))){
            System.out.println("Please Enter a strong password : ");
            password = scanner.nextLine();
        }

        this.balance = accountType.getBalance();
        this.userName = userName;
        this.password = password;
        this.mobileNumber = mobileNumber;
        this.type = type;
    }

    //----------------------------------------------------------------

    public void signIn(){
        System.out.println("--------------------------------Login--------------------------------");
        System.out.print("Enter user name: ");
        String userName = scanner.next();

        for (InstapayAccount account : getInstapayAccounts()) {

            if (userName.equals(account.getUserName())) {
                System.out.print("Enter user Password: ");
                scanner.nextLine();
                String password = scanner.nextLine();

                if (password.equals(account.getPassword())) {
                    this.userName = account.getUserName();
                    this.password = account.getPassword();
                    this.balance = account.getBalance();
                    this.mobileNumber = account.getMobileNumber();
                    this.bills = account.getBills();
                    this.type = account.type;
                    System.out.println("Password matches user name. Welcome "+this.userName+ ":)");
                }

                else {
                    System.out.println("Incorrect password :(");
                }
                return;

            }
        }
        System.out.println("UserName not found :(");
    }

    //----------------------------------------------------------------

    public void updateBalance(double newBalance) {
        this.balance = newBalance;
    }

    //----------------------------------------------------------------

    public void inquireBalance() {
        System.out.println("--------------------------------Account Info--------------------------------");
        System.out.println("UserName : " + this.userName);
        System.out.println("Mobile Number : " + this.mobileNumber);
        System.out.println("Balance : " + this.balance);
    }

    //----------------------------------------------------------------

    public void transfer(){
        int choice = 0;
        String number, user;
        while (choice < 1 || choice > 4){
            System.out.println("(1)Transfer to Bank Account \n(2) Transfer to Instapay Account\n(3) Transfer to a wallet \n(4) Exit");
            choice = scanner.nextInt();
        }
        if (choice == 4){
            return;
        }
        else if (choice == 3){

            while (choice < 1 || choice > 2){
                System.out.println("How to you want to transfer?\n1.By username\n2.By Number\n");
                choice = scanner.nextInt();
            }
            InstapayAccount instance;
            if (choice == 1){
                System.out.println("Enter username of Instapay Account : ");
                user = scanner.next();
                instance = Database.getInstaPayAccountByUserName(user);

            }
            else {
                System.out.println("Enter number of Instapay Account : ");
                number = scanner.next();
                instance = Database.getInstaPayAccountByMobNumber(number);
            }

            if (instance == null){
                System.out.println("Account not found!!!");
                return;
            }
            System.out.println("Account found with username : " + instance.getUsername());
            System.out.print("Enter the amount of money you want to transfer : ");
            double amount = scanner.nextDouble();
            if (amount > this.balance){
                System.out.println("Insufficient balance !!");
                return;
            }
            this.balance -= amount;
            instance.updateBalance(amount);
        }
        else {
            Database.displayApis(choice);

            AccountType account;
            if (choice  == 1){
                if(!this.type.equals("bank")) {
                    System.out.println("Sorry, You can't transfer to a bank account unless your Instapay account is registered with a bank account");
                    return;
                }
                System.out.print("Enter Bank Id : ");
                int id = scanner.nextInt();
                BankApi bank = Database.selectBank(id);
                if (bank == null) {
                    System.out.println("Bank not found !!!");
                    return;
                }
                number = scanner.next();
                account = bank.getAccountUsingMobileNumber(number);
            }

            else {
                System.out.print("Enter Wallet Id : ");
                int id = scanner.nextInt();
                WalletApi wallet = Database.selectWallet(id);

                if (wallet == null) {
                    System.out.println("Wallet not found!!!");
                    return;
                }
                number = scanner.next();
                account = wallet.getAccountUsingMobileNumber(number);
            }
            if (account == null) {
                System.out.println("Account not found!!!");
                return;
            }

            System.out.println("Account Found with username : " + account.getUsername());
            System.out.print("Enter the amount of money you want to transfer : ");
            double amount = scanner.nextDouble();
            if (amount > this.balance){
                System.out.println("Insufficient balance !!");
                return;
            }
            this.balance -= amount;
            account.updateBalance(amount);
        }

    }


    //----------------------------------------------------------------

    public void payBill() {
        System.out.println("--------------------------------Pay Bills--------------------------------");
        this.setBills(this.getMobileNumber());
        boolean flag = true;
        while (flag)                                   // to reuse this function
        {
            if (bills == null || bills.isEmpty()) {                      //to check if there is no bill to pay
                System.out.println("No available bills");
                return;
            }

            System.out.println("Available Bills : ");
            System.out.println("---------------------------------------------------------------------");

            for (Bills bill : bills) {               //to show all the user bills
                bill.print();
            }

            System.out.print("Enter the ID of the bill you want to pay : ");

            int choice = scanner.nextInt();
            if (choice>bills.size())
            {
                System.out.println("Unknown ID please try again");
                continue;
            }

            Iterator<Bills> iterator = bills.iterator();   // looping by iterator to reach our selected bill
            while (iterator.hasNext()) {
                Bills bill = iterator.next();
                if (bill.getId() == choice) {              // catching user selected bill
                    if (bill.getAmount() > this.getBalance()) {       // checking balance validation
                        System.out.println("Cannot pay this bill because your balance is less than the bill amount");
                    } else {
                        this.updateBalance(this.getBalance() - bill.getAmount()); // updating balance after paying
                        iterator.remove(); // Remove the bill from the list
                        Database.updateBills(choice);
                        System.out.println(bill.getName() +" Bill has been paid successfully. Your current balance: " + this.getBalance());
                    }
                }
            }
            for (Bills bill : bills) {         // Print the remaining bills after payment
                bill.print();
            }
            System.out.println("1. Pay another bill.\n2.Exit");
            int again = scanner.nextInt();

             if (again  == 2) {
                flag = false;

                System.out.println("Exiting the payBill function. Goodbye!");           // Exit the function
            }
            else {
                flag = false;
                System.out.println("Invalid choice. Exiting PayBill function");         // Handle invalid input
            }
        }


    }


    //----------------------------------------------------------------


}
