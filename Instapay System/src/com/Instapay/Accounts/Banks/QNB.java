package com.Instapay.Accounts.Banks;

public class QNB extends BankApi{
    public QNB(String name, int id) {
        super(name, id);
        addAccount(new BankAccount ("Ahmed Shabaan","Shabaan123!@#$AA","01158868614",1000,2));
    // TODO add other dummy accounts.
    }
}
