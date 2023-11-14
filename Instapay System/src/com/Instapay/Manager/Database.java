package com.Instapay.Manager;

import com.Instapay.Accounts.Banks.*;
import com.Instapay.Bills.*;
import com.Instapay.Accounts.Wallets.*;
import com.Instapay.Accounts.InstapayAccount;

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

    public Database(){

        numbers = new String[]{"01095454010", "01557425211", "01158868614", "01550033327"};


        bills = new Bills[]{new ElectricityBill("Electricity","Faisel ST.",1,350,"01095454010"),
        new GasBill("Gas","Faisel ST.",2,150,"01095454010"),
        new WaterBill("Water","Faisel ST.",3,140,"01095454010")};


        banks = new BankApi[] {new CIB(), new QNB()};
        wallets = new WalletApi[] {new TeldaWallet("Banque du Caire Wallet",1 ), new VodafoneCash("Vodafone Cash", 2), new Fawry("Fawry Wallet", 3)};

//        dummy accounts added in instapayAccounts list
        instapayAccounts.add(new InstapayAccount("mohamedahmedriyad","reyad123456789","01157157114",60122));
        instapayAccounts.add(new InstapayAccount("ahmedshaban","shaban123456789","01157157114",60122));
        instapayAccounts.add(new InstapayAccount("aq","abqawy123456789","01557425211",60122));
        instapayAccounts.add(new InstapayAccount("omarmohmaedfayek","omar123456789","01157157114",60122));
        instapayAccounts.add(new InstapayAccount("3ab2wy","omar123456789","01095454010",1000.0));

    }

    //----------------------------------------------------------------


    public static List<InstapayAccount> getInstapayAccounts() {
        return instapayAccounts;
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

    public boolean verifyNumber(String number){
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

    public boolean verifyUserName(String userName){
        for (InstapayAccount account : instapayAccounts){
            if (account.getUserName().equals(userName))
                return true;
        }
        return false;
    }

    //----------------------------------------------------------------

    public static BankApi getBank(int id){
        for (BankApi bank : banks){
            if (bank.getId() == id){
                return bank;
            }
        }
        return null;
    }

    //----------------------------------------------------------------

    public static WalletApi getWallets(int id){
        for (WalletApi wallet : wallets){
            if (wallet.getId() == id){
                return wallet;
            }
        }
        return null;
    }

    //----------------------------------------------------------------

    public InstapayAccount getInstaPayAccountByMobNumber(String mobileNumber){
        for (InstapayAccount account : instapayAccounts){
            if (account.getMobileNumber().equals(mobileNumber))
                return account;
        }
        return null;
    }

    //----------------------------------------------------------------

    public InstapayAccount getInstaPayAccountByUserName(String userName){
        for (InstapayAccount account : instapayAccounts){
            if (account.getUserName().equals(userName))
                return account;
        }
        return null;
    }

    //----------------------------------------------------------------

    public void printInstas(String mobileNumber){
        for (InstapayAccount account : instapayAccounts){
            if (account.getMobileNumber().equals(mobileNumber)){
                System.out.println(account.getMobileNumber());
                System.out.println(account.getBalance());
                break;
            }

        }
    }

    //----------------------------------------------------------------
    public void displayApis(int id){
        if (id == 1){
            for (BankApi bank: banks){
                System.out.println("Bank Name : " + bank.getName()+"\tID : "+bank.getId());
            }
        }
        else if (id ==2){
            for (WalletApi wallet: wallets){
                System.out.println("Wallet Name : " + wallet.getName()+"\tID : "+wallet.getId());
            }
        }
    }
    //----------------------------------------------------------------
    public BankApi selectBank(int id){
        for (BankApi bank : banks){
            if (bank.getId() == id){
                return bank;
            }
        }
        return null;
    }
    //----------------------------------------------------------------

    public WalletApi selectWallet(int id){
        for (WalletApi wallet : wallets){
            if (wallet.getId() == id){
                return wallet;
            }
        }
        return null;
    }

    //----------------------------------------------------------------

}
