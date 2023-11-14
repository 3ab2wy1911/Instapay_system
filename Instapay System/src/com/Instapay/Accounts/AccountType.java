package com.Instapay.Accounts;

public abstract class AccountType {
    // Common Attributes
    private String userName;
    private String password;
    private String mobileNumber;
    private double balance;

//----------------------------------------------------------------------------------------------------------------------
    protected AccountType(String userName, String password, String mobileNumber, double balance){
        this.userName = userName;
        this.password = password;
        this.mobileNumber = mobileNumber;
        this.balance = balance;
    }
//----------------------------------------------------------------------------------------------------------------------
    protected void updateBalance(double balance){
        this.balance = balance;
    }
//----------------------------------------------------------------------------------------------------------------------
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
}
