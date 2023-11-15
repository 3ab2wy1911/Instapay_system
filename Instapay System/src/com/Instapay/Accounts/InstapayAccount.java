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

    public List<Bills> getBills() {
        return bills;
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

        if (Database.verifyMobileNumber(mobileNumber)){
            System.out.println("There is already an account with this mobile number !!!");
            return;
        }
        System.out.print("Enter a user name : ");
        userName = scanner.next();
        while (!Database.verifyUserName(userName)){
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

    public void transferToInstapayAcc(String mobileNumber, String userName){
        double amount;
        Scanner input = new Scanner(System.in);
        InstapayAccount acc;
        // if mobile number is not null then we should search by mobileNumber
        if (mobileNumber.length() != 0){
            acc = Database.getInstaPayAccountByMobNumber(mobileNumber);
            if (acc != null){
                if(!acc.getUserName().equals(this.userName)){
                    while(true){
                        System.out.println("Enter Amount:-");
                        input.useLocale(Locale.US);
                        amount = input.nextDouble();
                        input.nextLine();
                        if (amount > this.balance){
                            System.out.println("Insufficient Balance!");
                        }

                        else if (amount <= -2 || amount == 0){
                            System.out.println("Enter a valid amount!");
                        }
                        else if (amount == -1){
                            exit(0);
                        }
                        else break;
                    }
                    InstapayAccount insacc = Database.getInstaPayAccountByMobNumber(mobileNumber); // get the instapay account by mob number and update it
                    insacc.balance += amount;
                    this.balance -= amount;
                    System.out.println("Transferred Successfully");
                }
                else {
                    System.out.println("Mobile Number not found");
                }
            }
            else {
                System.out.println("Mobile Number not found");
            }
        }
        else {
            acc = Database.getInstaPayAccountByUserName(userName);
            if (acc != null){
                if(!acc.getUserName().equals(this.userName)){
                    while(true){
                        System.out.println("Enter Amount:-");
                        input.useLocale(Locale.US);
                        amount = input.nextDouble();
                        input.nextLine();
                        if (amount > this.balance){
                            System.out.println("Insufficient Balance!");
                        }

                        else if (amount <= -2 || amount == 0){
                            System.out.println("Enter a valid amount!");
                        }
                        else if (amount == -1){
                            exit(0);
                        }
                        else break;
                    }
                    InstapayAccount insacc = Database.getInstaPayAccountByUserName(userName);
                    insacc.balance += amount;
                    this.balance -= amount;
                    System.out.println("Transferred Successfully");
                }
                else {
                    System.out.println("User Name not found");
                }
            }
            else {
                System.out.println("User Name not found");
            }
        }

    }

    //----------------------------------------------------------------

    public void transfer() {
        int choice;
        System.out.println("(1) Transfer to a wallet\n(2) Transfer to Instapay Account\n(3) Transfer to Bank Account\n(4) Exit");
        Scanner input = new Scanner(System.in);
        choice = input.nextInt();
        input.nextLine();
        if (choice == 1) {
            System.out.println("Please enter mobile number");
            String mobileNumber;
            mobileNumber = input.nextLine();
            WalletAccount wacc = Database.getAccountWithNumber(mobileNumber);
            if (wacc != null){
                if (!this.getMobileNumber().equals(mobileNumber)) {
                    double amount;
                    while(true){
                        System.out.println("Enter Amount: ");
                        input.useLocale(Locale.US);
                        amount = input.nextDouble();
                        if (amount <= this.getBalance() && amount > 0) {
                            wacc.setBalance(wacc.getBalance() + amount);
                            Database.updateWalletAccount(wacc);
                            this.updateBalance(this.getBalance() - amount);
                            System.out.println("Transferred Successfully");
                            return;
                        } else if (this.getBalance() < amount){
                            System.out.println("Insufficient Balance!");
                        }
                        else if (amount == -1) return;
                        else {
                            System.out.println("Enter a valid amount");
                        }
                    }


                } else {
                    System.out.println("Mobile Number not found!");
                }
            }
            else {
                System.out.println("Mobile Number not found!");
            }

        }
        else if (choice == 2){
            String mobileNumber;
            String userName;
            int tempChoice;
            System.out.println("How do you want to send money:-\n(1) mobile number \n(2) username");
            tempChoice = input.nextInt();
            input.nextLine();
            if (tempChoice == 1){
                System.out.println("Enter Mobile Number");
                mobileNumber = input.nextLine();
                transferToInstapayAcc(mobileNumber, "");
            }
            else if (tempChoice == 2){
                System.out.println("Enter User Name");
                userName = input.nextLine();
                transferToInstapayAcc("", userName);
            }
            else {
                System.out.println("invalid choice");
            }
        }
        else if (choice == 3){
            if (this.type.equals("Bank")){
                System.out.println("Enter the number of receiver:- ");
                String mobileNumber;
                mobileNumber = input.nextLine();
                BankAccount bacc;
                if(Database.searchBankAccount(mobileNumber)){
                    bacc = Database.getBankAccount(mobileNumber);
                    double amount;
                    while(true){
                        System.out.println("Enter Amount: ");
                        input.useLocale(Locale.US);
                        amount = input.nextDouble();
                        if (amount <= this.getBalance() && amount > 0) {
                            bacc.setBalance(bacc.getBalance() + amount);
                            Database.updateBankAccount(bacc);
                            this.updateBalance(this.getBalance() - amount);
                            System.out.println("Transferred Successfully");
                            return;
                        } else if (this.getBalance() < amount){
                            System.out.println("Insufficient Balance!");
                        }
                        else if (amount == -1) return;
                        else {
                            System.out.println("Enter a valid amount");
                        }
                    }
                }
                else {
                    System.out.println("Bank account not found");
                    return;
                }

            }
            else {
                System.out.println("You are not registered with a bank account");
                return;
            }
        }
        else return;
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
