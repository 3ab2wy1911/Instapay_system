package com.Instapay.Accounts.Wallets;

import com.Instapay.Accounts.AccountType;
import com.Instapay.Database;

public class WalletAccount extends AccountType {
    public WalletApi provider;
    public WalletAccount(String userName, String password, String mobileNumber, double balance, int id) {
        super(userName, password, mobileNumber, balance);
        this.provider = Database.getWallets(id);
    }

}
