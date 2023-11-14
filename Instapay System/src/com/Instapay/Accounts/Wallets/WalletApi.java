package com.Instapay.Accounts.Wallets;

import com.Instapay.Bills.Bills;

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
    public WalletAccount getAccountWithNumber(String number){
        for (WalletAccount account : accounts){
            if (account.getMobileNumber().equals(number)){
                return account;
            }
        }
        return null;
    }
    public boolean searchForNumber(String number, int id){ // method to search for a mobile number using wallet api
        for (WalletAccount acc : accounts){
            if (acc.getMobileNumber().equals(number) && acc.provider.id == id){
                return true;
            }
        }
        return false;
    }
    //------------------------------------
    public void addAccount(WalletAccount account){
        accounts.add(account);
    }
    public void printAccounts(){
        for (WalletAccount acc : accounts){
            System.out.println("--------------------------------Account Info--------------------------------");
            System.out.println("UserName : " + acc.getUserName());
            System.out.println("Mobile Number : " + acc.getMobileNumber());
            System.out.println("Balance : " + acc.getBalance());
            System.out.println("--------------------------------  com.Instapay.Bills.Bills --------------------------------");
            System.out.println("No bills available");

        }
    }
}
