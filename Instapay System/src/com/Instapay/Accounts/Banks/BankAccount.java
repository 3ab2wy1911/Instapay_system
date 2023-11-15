package com.Instapay.Accounts.Banks;
import com.Instapay.Accounts.AccountType;
import com.Instapay.Manager.Database;

public class BankAccount extends AccountType {
    BankApi bank;

    public BankAccount(String userName, String password, String mobileNumber, double balance,int id) {
        super(userName, password, mobileNumber, balance);
        this.bank = Database.getBank(id);
    }

}
