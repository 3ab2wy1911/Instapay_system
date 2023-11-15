package com.Instapay.Accounts.Banks;

public class CIB extends BankApi{
    public CIB() {
        super("CIB", 1);
        // Dummy Accounts
        addAccount(new BankAccount ("AhmedShabaan","Shabaan123!@#$AA","01158868614",1000,2));
        addAccount(new BankAccount ("MohamedAhmed","3ab2wy1234A!@#$AA","01095454010",1000,2));

    }
}
