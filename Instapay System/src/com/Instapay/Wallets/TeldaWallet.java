package com.Instapay.Wallets;

public class TeldaWallet extends BankWallet{
    public TeldaWallet(String name, int id) {
        super(name, id);
        addAccount(new WalletAccount("3ab2wy","ryadA123@","01557425211", 14.55));
        // TODO add others...
    }
}
