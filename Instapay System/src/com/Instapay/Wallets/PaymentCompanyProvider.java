package com.Instapay.Wallets;

import com.Instapay.Wallets.WalletApi;

public abstract class PaymentCompanyProvider extends WalletApi {
    PaymentCompanyProvider(String name, int id) {
        super(name, id);
    }
}
