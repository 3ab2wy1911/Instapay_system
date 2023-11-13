package com.Instapay.Banks;

import com.Instapay.InstapayAccount;

public class BankAccount extends InstapayAccount {
    BankApi bank;

    public BankApi getBank(int id ) {
        return bank;
    }

    public void setBank(BankApi bank) {
        this.bank = bank;
    }

    public BankAccount(String userName, String password, String mobileNumber, double balance, int id) {
        super(userName, password, mobileNumber, balance);
    }

    public BankAccount() {
        super();

    }

    public void updateBankAccount(String userName){ // used in sign in.
        BankAccount account = bank.getAccount(userName);
        setUserName(userName);
        setPassword(account.getPassword());
        setBalance(account.getBalance());
        setMobileNumber(account.getMobileNumber());
    }
}
