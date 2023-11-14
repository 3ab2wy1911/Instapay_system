package com.Instapay.Accounts.Wallets;

public class VodafoneCash extends TelecomProvider{
    public VodafoneCash(String name, int id) {
        super(name, id);
        addAccount(new WalletAccount("omar12","ryadA123@","01550033327", 500000000,3));
        // TODO add others...
    }
}
