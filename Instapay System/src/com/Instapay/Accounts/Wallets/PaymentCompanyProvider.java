package com.Instapay.Accounts.Wallets;

public abstract class PaymentCompanyProvider extends WalletApi {
    PaymentCompanyProvider(String name, int id) {
        super(name, id);
    }
}
