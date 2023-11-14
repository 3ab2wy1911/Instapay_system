package com.Instapay.Accounts.Banks;

public class CIB extends BankApi{
    public CIB() {
        super("CIB", 1);
        addAccount(new BankAccount ("Mohamed","mohamed123!@#$AA","01095454010",0,1));
    // TODO add other dummy accounts
    }
}
