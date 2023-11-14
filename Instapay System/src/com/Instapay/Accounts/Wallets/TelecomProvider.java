package com.Instapay.Accounts.Wallets;

public abstract class TelecomProvider extends WalletApi {
    TelecomProvider(String name,int id) {
        super(name, id);
        addAccount(new WalletAccount("Ryad123","ryadA123@","01157157114", 2000000000, 1));
        // TODO add others...
    }
}
