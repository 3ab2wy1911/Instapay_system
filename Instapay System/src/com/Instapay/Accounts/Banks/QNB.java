package com.Instapay.Accounts.Banks;

public class QNB extends BankApi{
    public QNB() {
        super("QNB",2);
        addAccount(new BankAccount ("AhmedShabaan","Shabaan123!@#$AA","01158868614",1000,2));
    // TODO add other dummy accounts.
    }
}
