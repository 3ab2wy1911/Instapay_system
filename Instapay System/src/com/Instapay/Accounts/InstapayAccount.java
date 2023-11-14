package com.Instapay.Accounts;

import com.Instapay.Accounts.Banks.BankAccount;
import com.Instapay.Accounts.Banks.BankApi;
import com.Instapay.Accounts.Wallets.WalletAccount;
import com.Instapay.Accounts.Wallets.WalletApi;
import com.Instapay.Bills.Bills;
import com.Instapay.Manager.Database;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import static com.Instapay.Manager.Database.*;

public class InstapayAccount {

    // Common Attributes
    private String userName;
    private String password;
    private String mobileNumber;
    private double balance;
    private AccountType accountType;
    private String type;
    private List<Bills> bills;
    private WalletApi walletapi;

    private Database db; // to be deleted after the creation of menu display
    //------------------------------------------------------------------------------------------------------------------

    // Constructor

    public  InstapayAccount(){
        db = new Database();
    }
    public InstapayAccount(String userName, String password, String mobileNumber, double balance){
        this.userName = userName;
        this.password = password;
        this.mobileNumber = mobileNumber;
        this.balance = balance;
        setBills(mobileNumber);
    }

    //----------------------------------------------------------------

    // Setters
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setBills(String number) {
        this.bills = Database.getBill(number);
    }
    public void setApi(WalletApi wapi){
        this.walletapi = wapi;
    }
    public void setDataBase(Database db){
        this.db = db;
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
    public void displayMenu(){

    }

    //----------------------------------------------------------------

    public void register() {

        System.out.println("--------------------------------Register--------------------------------");

        // Select the type of the account.
        int choice = 3;
        while (choice > 2 || choice < 0) {
            System.out.println("Please Choose the type of your account :\n1.Bank Account.\n2.Wallet Account\n0.Exit");
            choice = scanner.nextInt();
        }

        db.displayApis(choice); // Display the banks or the wallets.

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
            BankApi api = db.selectBank(answer);
            if (api == null) {
                System.out.println("Bank not found ! Exiting the Registration Process...");
                return;
            }

            this.accountType = api.getAccount(userName);
            if (accountType == null) {
                System.out.println("Account not found ! Exiting the Registration Process...");
                return;
            }
        }
        else {
            WalletApi api = db.selectWallet(answer);
            if (api == null) {
                System.out.println("Wallet not found ! Exiting the Registration Process...");
                return;
            }
            this.accountType = api.getAccount(userName);
            if (accountType == null) {
                System.out.println("Account not found ! Exiting the Registration Process...");
                return;
            }
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
        if (db.verifyNumber(mobileNumber)){
            System.out.println("Mobile Verification Succeed :)");
        }
        else{
            System.out.println("Mobile Verification Failed :(");
            return;
        }

        if (db.verifyMobileNumber(mobileNumber)){
            System.out.println("There is already an account with this mobile number !!!");
            return;
        }
        System.out.print("Enter a user name : ");
        userName = scanner.next();
        while (!db.verifyUserName(userName)){
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

        db.updateInstapayAccounts(this);

    }

    //----------------------------------------------------------------

    public void signIn(){
        System.out.println("--------------------------------Login--------------------------------");
        System.out.println("Enter user name:");
//        scanner.nextLine();
        String userName = scanner.next();

        for (InstapayAccount account : getInstapayAccounts()) {
            if (userName.equals(account.getUserName())) {
                System.out.println("UserName found.");

                System.out.println("Enter user Password:");
                scanner.nextLine();
                String password = scanner.nextLine();

                if (password.equals(account.getPassword())) {
                    this.userName = account.getUserName();
                    this.password = account.getPassword();
                    this.balance = account.getBalance();
                    this.mobileNumber = account.getMobileNumber();
                    this.bills = account.getBills();
                    System.out.println("Password matches user name. Welcome "+this.userName+ ":)");
                    return;
                }

                else {
                    System.out.println("Incorrect password :(");
                    return;
                }

            }
        }
        System.out.println("UserName not found :(");
    }


    //----------------------------------------------------------------

//    public void showAccounts() {
//        System.out.println("accounts exist are : \n");
//        for (InstapayAccount account : getInstapayAccounts()) {
//        System.out.println("username : "+account.userName);
//        System.out.println("Password : "+account.password);
//        System.out.println();
//    }
//}


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
    public void transferToPayment(String mobileNumber){
        int choice;
        Scanner input = new Scanner(System.in);
        System.out.println("(1) Fawry");
        choice = input.nextInt();
        if (choice > 1 || choice <= 0) {
            System.out.println("invalid choice");
            return;
        }
        if (choice == 1) {
            // id 3 for fawry
            if (walletapi.searchForNumber(mobileNumber, 3)) {
                double amount;
                System.out.println("Enter Amount: ");
                amount = input.nextDouble();
                if (amount <= getBalance()) {
                    balance -= amount; // edit the sender balance
                    WalletAccount wacc = walletapi.getAccountWithNumber(mobileNumber); // get the account of the receiver through api
                    wacc.setBalance(wacc.getBalance() + amount); // to update the receiver balance
                } else {
                    System.out.println("Insufficient Balance!");
                    return;
                }
            } else {
                // mobile number is not found
                System.out.println("Mobile Number not found!");
                return;
            }
        }

    }

    //----------------------------------------------------------------

    public void transferToTelecomComp(String mobileNumber){
        int choice;
        Scanner input = new Scanner(System.in);
        System.out.println("(1) Vodafone Cash");
        choice = input.nextInt();
        if (choice > 1 || choice <= 0) {
            System.out.println("invalid choice");
            return;
        }
        if (choice == 1) {
            // id 2 for vodafone cash
            if (walletapi.searchForNumber(mobileNumber, 2)) {
                double amount;
                System.out.println("Enter Amount: ");
                amount = input.nextDouble();
                if (amount <= getBalance()) {
                    balance -= amount; // edit the sender balance
                    WalletAccount wacc = walletapi.getAccountWithNumber(mobileNumber); // get the account of the receiver through api
                    wacc.setBalance(wacc.getBalance() + amount); // to update the receiver balance
                } else {
                    System.out.println("Insufficient Balance!");
                    return;
                }
            } else {
                // mobile number is not found
                System.out.println("Mobile Number not found!");
                return;
            }
        }
    }

    //----------------------------------------------------------------

    public void transferToBankWallet(String mobileNumber){
        int choice;
        Scanner input = new Scanner(System.in);
        System.out.println("(1) Telda");
        choice = input.nextInt();
        if (choice > 1 || choice <= 0) {
            System.out.println("invalid choice");
            return;
        }
        if (choice == 1) {
            // id 1 for Telda
            if (walletapi.searchForNumber(mobileNumber, 1)) {
                Double amount;
                System.out.println("Enter Amount: ");
                amount = input.nextDouble();
                if (amount <= getBalance()) {
                    balance -= amount; // edit the sender balance
                    WalletAccount wacc = walletapi.getAccountWithNumber(mobileNumber); // get the account of the receiver through api
                    wacc.setBalance(wacc.getBalance() + amount); // to update the receiver balance
                } else {
                    System.out.println("Insufficient Balance!");
                    return;
                }
            } else {
                // mobile number is not found
                System.out.println("Mobile Number not found!");
                return;
            }
        }
    }

    //----------------------------------------------------------------

    public void transferToInstapayAcc(String mobileNumber, String userName){
        double amount;
        Scanner input = new Scanner(System.in);
        // if mobile number is not null then we should search by mobileNumber
        if (mobileNumber.length() != 0){
            if(db.verifyUserName(mobileNumber)){    // wrong use here !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                System.out.println("Enter Amount:-");
                amount = input.nextDouble();
                if (amount > this.balance){
                    System.out.println("Insufficient Balance!");
                }
                else {
                    InstapayAccount insacc = db.getInstaPayAccountByMobNumber(mobileNumber); // get the instapay account by mob number and update it
                    insacc.balance += amount;
                    this.balance -= amount;
                    System.out.println("Transferred Successfully");
                }
            }
            else {
                System.out.println("Mobile Number not found");
            }
        }
        else {
            if(!db.verifyUserName(userName)){   // update it as i updated the function in db !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                System.out.println("Enter Amount:-");
                amount = input.nextDouble();
                if (amount > this.balance){
                    System.out.println("Insufficient Balance!");
                }
                else {
                    InstapayAccount insacc = db.getInstaPayAccountByUserName(userName); // get the instapay account by mob number and update it
                    insacc.balance += amount;
                    this.balance -= amount;
                    System.out.println("Transferred Successfully");
                }
            }
        }
    }

    //----------------------------------------------------------------

    public void transfer() {
        int choice;
        System.out.println("(1) Transfer to a wallet\n(2) Transfer to Instapay Account");
        Scanner input = new Scanner(System.in);
        choice = input.nextInt();
        // if choice to send to a wallet whether it was a payment companies or bank
        // and to send you have to enter the wallet number and check if it has a wallet
        if (choice == 1) {
            String mobileNumber;
            System.out.println("Please Select Wallet Providers:- ");
            System.out.println("(1) Electronic Payment Companies ie: Fawry");
            System.out.println("(2) Telecommunication Companies ie:Vodafone Cash");
            System.out.println("(3) Bank Wallets");
            choice = input.nextInt();
            if (choice > 3 || choice <= 0) {
                System.out.println("invalid choice");
                return;
            }
            System.out.println("Please enter mobile number");
            mobileNumber = input.next();
            if (choice == 1) {
                transferToPayment(mobileNumber);
            } else if (choice == 2) {
                transferToTelecomComp(mobileNumber);
            } else if (choice == 3) {
                transferToBankWallet(mobileNumber);
            }

        }
        else if (choice == 2){
            String mobileNumber;
            String userName;
            int tempChoice;
            double amount;
            System.out.println("How do you want to send money:-\n(1) mobile number \n(2) username");
            tempChoice = input.nextInt();
            if (tempChoice == 1){
                System.out.println("Enter Mobile Number");
                mobileNumber = input.next();
                transferToInstapayAcc(mobileNumber, "");
            }
            if (tempChoice == 2){
                System.out.println("Enter User Name");
                userName = input.next();
                transferToInstapayAcc("", userName);
            }
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
