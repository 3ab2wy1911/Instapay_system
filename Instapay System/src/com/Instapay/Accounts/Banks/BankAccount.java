package com.Instapay.Accounts.Banks;
import com.Instapay.*;
import com.Instapay.Accounts.AccountType;

public class BankAccount extends AccountType {
    BankApi bank;

    public BankApi getBank(int id ) {
        return bank;
    }

    public void setBank(BankApi bank) {
        this.bank = bank;
    }

    public BankAccount(String userName, String password, String mobileNumber, double balance,int id) {
        super(userName, password, mobileNumber, balance);
        this.bank = Database.getBank(id);
    }


    public void updateBankAccount(String userName){ // If we don't use it then just fuckin delete it.
        BankAccount account = bank.getAccount(userName);
        setUserName(userName);
        setPassword(account.getPassword());
        setBalance(account.getBalance());
        setMobileNumber(account.getMobileNumber());
    }
}
