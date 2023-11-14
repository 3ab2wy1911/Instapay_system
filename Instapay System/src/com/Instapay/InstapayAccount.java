package com.Instapay;

import com.Instapay.Accounts.AccountType;
import com.Instapay.Accounts.Wallets.WalletAccount;
import com.Instapay.Accounts.Wallets.WalletApi;
import com.Instapay.Bills.Bills;

import javax.xml.crypto.Data;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import static com.Instapay.Database.getInstapayAccounts;
import static com.Instapay.Database.scanner;

public class InstapayAccount {

    // Common Attributes
    private String userName;
    private String password;
    private String mobileNumber;
    private double balance;
    private AccountType account;
    private List<Bills> bills;
    private WalletApi walletapi;

    private Database db; // to be deleted after the creation of menu display
    //------------------------------------------------------------------------------------------------------------------

    // Constructor
    public InstapayAccount(String userName, String password, String mobileNumber, double balance){
        this.userName = userName;
        this.password = password;
        this.mobileNumber = mobileNumber;
        this.balance = balance;
        setBills(mobileNumber);
    }

    public InstapayAccount(){}

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

    public void register(){
        System.out.print("Enter UserName : ");
        String userName = Database.scanner.next();
        while (!Database.verifyUserName(userName)){
            System.out.println("UserName not available !!!");
            userName = Database.scanner.next();
        }
        System.out.println("Enter Password : ");
        String password = Database.scanner.next();
        // to be completed.
    }

    //----------------------------------------------------------------

    public void signIn(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter user name:");
        String userName = scanner.nextLine();

        for (InstapayAccount account : getInstapayAccounts()) {
            if (userName.equals(account.getUserName())) {
                System.out.println("UserName found.");

                System.out.println("Enter user Password:");
                String password = scanner.nextLine();

                if (password.equals(account.getPassword())) {
                    System.out.println("Password matches user name. Welcome :)");
                    return;
                } else {
                    System.out.println("Incorrect password.");
                    return;
                }
            }
        }

        System.out.println("UserName not found.");
    }


    //----------------------------------------------------------------

    public void showAccounts() {
        System.out.println("accounts exist are : \n");
        for (InstapayAccount account : getInstapayAccounts()) {
            System.out.println("username : "+account.userName);
            System.out.println("Password : "+account.password);
            System.out.println();
        }
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
        System.out.println("--------------------------------  com.Instapay.Bills.Bills --------------------------------");
        if (bills!=null){
            for (Bills bill : bills){
                bill.print();
                System.out.println("----------------------------------------------------");
            }
        }
        else {
            System.out.println("No bills available");
        }
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

    public void transferToInstapayAcc(String mobileNumber, String userName){
        double amount;
        Scanner input = new Scanner(System.in);
        // if mobile number is not null then we should search by mobileNumber
        if (mobileNumber.length() != 0){
            if(db.verifyUserName(mobileNumber)){
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
            if(!db.verifyUserName(userName)){
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
        this.setBills(this.getMobileNumber());
        boolean flag = true;
        while (flag)                                   // to reuse this function
        {
            if (bills == null || bills.isEmpty()) {                      //to check if there is no bill to pay
                System.out.println("No available bills");
                return;
            }
            System.out.println("Here are your Bills");

            for (Bills bill : bills) {               //to show all the user bills
                bill.print();
            }
            System.out.println("Enter the id of the bill you want to pay?");
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
            System.out.println("Press 1 to pay another bill or press 2 to exit:");
            int again = scanner.nextInt();

            if (again == 1) {
                flag= true;
            }
            else if (again  == 2) {
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
