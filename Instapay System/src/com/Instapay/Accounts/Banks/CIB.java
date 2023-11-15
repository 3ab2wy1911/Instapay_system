package com.Instapay.Accounts.Banks;

public class CIB extends BankApi{
    public CIB() {
        super("CIB", 1);
        // Dummy Accounts
        addAccount(new BankAccount ("3ab2wy","mohamed123!@#$AA","01095454010",1000,1));
        addAccount(new BankAccount ("Sh3boo","sh3BaaN!@#$AA","01550021019",250,1));

    }
}
