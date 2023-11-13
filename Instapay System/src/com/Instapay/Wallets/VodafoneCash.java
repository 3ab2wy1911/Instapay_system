package com.Instapay.Wallets;

public class VodafoneCash extends TelecomProvider{
    public VodafoneCash(String name, int id) {
        super(name, id);
        addAccount(new WalletAccount("omar12","ryadA123@","01550033327", 500000000));
        // TODO add others...
    }
}
