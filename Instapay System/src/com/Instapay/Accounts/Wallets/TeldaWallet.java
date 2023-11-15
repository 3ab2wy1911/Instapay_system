package com.Instapay.Accounts.Wallets;

public class TeldaWallet extends BankWallet{
    public TeldaWallet() {
        super("Telda", 2);
        addAccount(new WalletAccount("3ab2wy","ryadA123@","01557425211", 14.55, 1));
    }
}
