package com.Instapay.Accounts.Banks;

public class CIB extends BankApi{
    public CIB(String name, int id) {
        super(name, id);
        addAccount(new BankAccount ("Mohamed Ahmed","mohamed123!@#$AA","01095454010",0,1));
    // TODO add other dummy accounts
    }
}
