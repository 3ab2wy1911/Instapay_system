package com.Instapay;

import com.Instapay.Banks.*;
import com.Instapay.Bills.*;
import com.Instapay.Wallets.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Database {
    private static  String [] numbers = new String[]{};
    private static    Bills [] bills = new Bills[]{};
    private static BankApi[] banks = new BankApi[]{};
    private static WalletApi[] wallets = new WalletApi[]{};
    private static List<InstapayAccount> instapayAccounts = new ArrayList<>();
    public static Scanner scanner = new Scanner (System.in);

    //------------------------------------------------------------------------------------------------------------------

    Database(){
        numbers = new String[]{"01095454010", "01557425211", "01158868614", "01550033327"};
        bills = new Bills[]{new ElectricityBill("Electricity","Faisel ST.",1,350,"01095454010"),
        new GasBill("Gas","Faisel ST.",2,150,"01095454010"),
        new WaterBill("Water","Faisel ST.",3,140,"01095454010")};
        banks = new BankApi[] {new CIB("Commercial International Bank" ,1), new QNB("Qatar National Bank", 2)};
        wallets = new WalletApi[] {new TeldaWallet("Banque du Caire Wallet",1 ), new VodafoneCash("Vodafone Cash", 2), new Fawry("Fawry Wallet", 3)};
//        String userName, String password, String mobileNumber, double balance


//        dummy accounts added in instapayAccounts list
        instapayAccounts.add(new BankAccount("mohamed ahmed riyad","reyad123456789","01157157114",60122,7));
        instapayAccounts.add(new BankAccount("ahmed shaban youssef","shaban123456789","01157157114",60122,12));
        instapayAccounts.add(new WalletAccount("mohamed ahmed abdelqawy","abqawy123456789","01157157114",60122));
        instapayAccounts.add(new WalletAccount("omar mohmaed fayek","omar123456789","01157157114",60122));

    }

    //----------------------------------------------------------------

    public static List<Bills> getBill(String number) {
        List<Bills> billsList = new ArrayList<>();
        for (Bills bill : bills) {
            if (bill.getNumber().equals(number)){
                billsList.add(bill);
            }
        }
        return billsList;
    }

    //----------------------------------------------------------------

    public static boolean verifyNumber(String number){
        for (String num : numbers){
            if (num.equals(number))
                return true;
        }
        return false;
    }

    //----------------------------------------------------------------

    public void updateInstapayAccounts(InstapayAccount account){
        instapayAccounts.add(account);
    }

    //----------------------------------------------------------------

    public static boolean verifyUserName(String userName){
        for (InstapayAccount account : instapayAccounts){
            if (account.getUserName().equals(userName))
                return false;
        }
        return true;
    }

    //----------------------------------------------------------------


}
