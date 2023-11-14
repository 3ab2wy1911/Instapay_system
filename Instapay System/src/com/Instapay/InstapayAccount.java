package com.Instapay;

import com.Instapay.Banks.BankAccount;
import com.Instapay.Bills.Bills;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import static com.Instapay.Database.getInstapayAccounts;
import static com.Instapay.Database.scanner;

public abstract class InstapayAccount {

    // Common Attributes
    private String userName;
    private String password;
    private String mobileNumber;
    private double balance;
    private List<Bills> bills;

    //------------------------------------------------------------------------------------------------------------------

    // Constructor
    protected InstapayAccount(String userName, String password, String mobileNumber, double balance){
        this.userName = userName;
        this.password = password;
        this.mobileNumber = mobileNumber;
        this.balance = balance;
        setBills(mobileNumber);
    }

    protected InstapayAccount(){}

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

        System.out.println("Enter user Password:");
        String password = scanner.nextLine();

        for (InstapayAccount account : getInstapayAccounts()) {
            if (userName.equals(account.userName)){
                System.out.println("user name found ");
                if (password.equals(account.password)){
                    System.out.println("password matches user name, welcome:)");
                    return;
                }

                else {
                    System.out.println("password is not correct");
                    return;
                }
            }
            else {
                System.out.println("UserName not found");
                return;
            }
        }
    }


    //----------------------------------------------------------------

    public void showAccounts() {
        System.out.println("accounts exist are : ");
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

    public void transfer(){
        // TODO
    }

    //----------------------------------------------------------------

    public void payBill() {
        Database fdf = new Database();
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
            } else if (again  == 2) {
                flag = false;

                System.out.println("Exiting the payBill function. Goodbye!");           // Exit the function
            } else {
                flag = false;
                System.out.println("Invalid choice. Exiting PayBill function");         // Handle invalid input
            }
        }



    }


    //----------------------------------------------------------------


}
