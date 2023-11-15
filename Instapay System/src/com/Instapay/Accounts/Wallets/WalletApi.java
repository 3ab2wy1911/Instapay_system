package com.Instapay.Accounts.Wallets;

import com.Instapay.Accounts.AccountType;

import java.util.ArrayList;
import java.util.List;

public abstract class WalletApi {
    private String name;
    private int id;
    private List<WalletAccount> accounts;

    //------------------------------------------------------------------------------------------------------------------

    // Constructor

    WalletApi(String name, int id){

        this.name = name;
        this.id = id;
        accounts = new ArrayList<>(){};
    }

    // Getters
    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAccounts(List<WalletAccount> accounts) {
        this.accounts = accounts;
    }

    //----------------------------------------------------------------

    // Setters
    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public List<WalletAccount> getAccounts() {
        return accounts;
    }

    //-----------------------------------------------------------------
    public WalletAccount getAccount(String userName){
        for (WalletAccount account : accounts){
            if (account.getUserName().equals(userName)){
                return account;
            }
        }
        return null;
    }

    //------------------------------------
    public void addAccount(WalletAccount account){
        accounts.add(account);
    }

    //------------------------------------

    public AccountType getAccountUsingMobileNumber(String number) {
        for (WalletAccount account : accounts){
            if (account.getMobileNumber().equals(number)){
                return account;
            }
        }
        return null;
    }
}
