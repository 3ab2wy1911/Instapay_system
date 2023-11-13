package com.Instapay.Wallets;

import com.Instapay.Wallets.WalletApi;

public abstract class BankWallet extends WalletApi {

    BankWallet(String name, int id) {
        super(name,id);
    }
}
