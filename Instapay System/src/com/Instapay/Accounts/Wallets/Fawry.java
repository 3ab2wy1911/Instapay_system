package com.Instapay.Accounts.Wallets;

public class Fawry extends PaymentCompanyProvider{
    public Fawry() {
        super("Fawry", 3);
        addAccount(new WalletAccount("MohamedAhmed", "MAM123!@#a", "01095454010", 500,3));
    }
}
