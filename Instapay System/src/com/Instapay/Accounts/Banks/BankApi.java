package com.Instapay.Accounts.Banks;

import java.util.ArrayList;
import java.util.List;

public abstract class BankApi {
    // common Attributes
    private String name;

    private int id;
    private static List<BankAccount> accounts;
    //------------------------------------------------------------------------------------------------------------------

    public BankApi(String name, int id ){
        this.name = name;
        this.id = id;
        accounts  = new ArrayList<BankAccount>(){};
    }
    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAccounts(List<BankAccount> accounts) {
        this.accounts = accounts;
    }

    //------------------------------------

    // Getters
    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public List<BankAccount> getAccounts() {
        return accounts;
    }

    //------------------------------------

    public BankAccount getAccount(String userName){
        for (BankAccount account : accounts){
            if (account.getUserName().equals(userName)){
                return account;
            }
        }
        return null;
    }

    //------------------------------------

    public void addAccount(BankAccount account){
        accounts.add(account);
    }
}
