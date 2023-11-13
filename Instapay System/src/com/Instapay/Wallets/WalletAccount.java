package com.Instapay.Wallets;

import com.Instapay.InstapayAccount;

public class WalletAccount extends InstapayAccount {
    public String provider; // TODO
    WalletAccount(String userName, String password, String mobileNumber, double balance) {
        super(userName, password, mobileNumber, balance);
    }

}
