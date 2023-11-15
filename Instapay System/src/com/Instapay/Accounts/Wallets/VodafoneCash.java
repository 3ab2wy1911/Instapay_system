package com.Instapay.Accounts.Wallets;

public class VodafoneCash extends TelecomProvider{
    public VodafoneCash() {
        super("Vodafone Cash", 1);

        // Dummy Accounts
        addAccount(new WalletAccount("omar12","ryadA123@ABC","01050033327", 500000000,3));
        addAccount(new WalletAccount("Reyad55","ryadA123@ABC","0100033327", 10000,3));
        addAccount(new WalletAccount("mo123","ryadA123@ABC","01000033328", 50,3));
    }
}
