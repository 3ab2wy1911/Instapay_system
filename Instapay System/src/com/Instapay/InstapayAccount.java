package com.Instapay;

import com.Instapay.Banks.BankAccount;
import com.Instapay.Bills.Bills;

import java.util.List;
import java.util.Scanner;

import static com.Instapay.Database.getInstapayAccounts;

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

    public void updateBalance(float newBalance) {
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

    public void payBill(){
        if (bills == null){
            System.out.println("No available bills");
            return;
        }

        for (Bills bill : bills){
            bill.print();
        }

        System.out.println("Enter the id of the bill you want to pay?");
//        int choice = scanner.nextInt();
//        System.out.println();
    }

    //----------------------------------------------------------------


}
