package com.Instapay;

import com.Instapay.Bills.Bills;

import java.util.List;

public abstract class InstapayAccount {

    // Common Attributes
    private String userName;
    private String password;
    private String mobileNumber;
    private float balance;
    private List<Bills> bills;

    //------------------------------------------------------------------------------------------------------------------

    // Constructor
    protected InstapayAccount(String userName, String password, String mobileNumber, float balance){
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

    public void setBalance(float balance) {
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

    public float getBalance() {
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
