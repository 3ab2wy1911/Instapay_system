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

        numbers = new String[]{"01095454010", "01557425211", "01158868614", "01550033327","01000033328"};


        bills = new Bills[]{new ElectricityBill("Electricity","Faisel ST.",1,350,"01095454010"),
        new GasBill("Gas","Faisel ST.",2,150,"01095454010"),
        new WaterBill("Water","Faisel ST.",3,140,"01095454010")};


        banks = new BankApi[] {new CIB(), new QNB()};
        wallets = new WalletApi[]{new VodafoneCash(),new TeldaWallet(), new Fawry()};

//        dummy accounts added in instapayAccounts list
        instapayAccounts.add(new InstapayAccount("mohamedahmedriyad","reyad123456789","01157157114",60122, "Bank"));
        instapayAccounts.add(new InstapayAccount("ahmedshaban","shaban123456789","01157157114",60122, "Wallet"));
        instapayAccounts.add(new InstapayAccount("aq","abqawy123456789","01557425211",60122,"Bank"));
        instapayAccounts.add(new InstapayAccount("omarmohmaedfayek","omar123456789","01550033327",60122, "Wallet"));
        instapayAccounts.add(new InstapayAccount("3ab2wy","omar123456789","01095454010",1000.0, "Bank"));

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

    public static boolean verifyNumber(String number){
        for (String num : numbers){
            if (num.equals(number))
                return true;
        }
        return false;
    }

    //----------------------------------------------------------------

    public static void updateInstapayAccounts(InstapayAccount account){
        instapayAccounts.add(account);
    }

    //----------------------------------------------------------------

    public static boolean verifyUserName(String userName){
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

    public static InstapayAccount getInstaPayAccountByMobNumber(String mobileNumber){
        for (InstapayAccount account : instapayAccounts){
            if (account.getMobileNumber().equals(mobileNumber))
                return account;
        }
        return null;
    }

    //----------------------------------------------------------------

    public static InstapayAccount getInstaPayAccountByUserName(String userName){
        for (InstapayAccount account : instapayAccounts){
            if (account.getUserName().equals(userName))
                return account;
        }
        return null;
    }

    //----------------------------------------------------------------

    public static void displayApis(int id){
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
    public static BankApi selectBank(int id){
        for (BankApi bank : banks){
            if (bank.getId() == id){
                return bank;
            }
        }
        return null;
    }
    //----------------------------------------------------------------

    public static WalletApi selectWallet(int id){
        for (WalletApi wallet : wallets){
            if (wallet.getId() == id){
                return wallet;
            }
        }
        return null;
    }

    //----------------------------------------------------------------

    public static boolean verifyInstaMobileNumber(String mobileNumber, String type){
        for (InstapayAccount account : instapayAccounts){
            if(account.getMobileNumber().equals(mobileNumber) && (account.getType().equals(type) || type.equals("any"))){
                return true;
            }
        }
        return false;
    }
    //----------------------------------------------------------------
    public static WalletAccount getAccountWithNumber(String mobileNumber){
        for (int i = 0; i < 3; i++){
            for (WalletAccount wacc : wallets[i].getAccounts()){
                if (wacc.getMobileNumber().equals(mobileNumber)){
                    return wacc;
                }
            }
        }
        return null;
    }
    //---------------------------------------------------------------
    public static void updateWalletAccount(WalletAccount acc){
        for (int i = 0; i < 3; i++){
            for (WalletAccount wacc : wallets[i].getAccounts()){
                if (wacc.getMobileNumber().equals(acc.getMobileNumber())){
                    wacc = acc;
                }
            }
        }
    }

    public static boolean searchBankAccount(String mobileNuber){
        for (int i = 0; i < 2; i++){
            for (BankAccount wacc : banks[i].getAccounts()){
                if (wacc.getMobileNumber().equals(mobileNuber)){
                    return true;
                }
            }
        }
        return false;
    }
    public static void updateBankAccount(BankAccount acc){
        for (int i = 0; i < 2; i++){
            for (BankAccount wacc : banks[i].getAccounts()){
                if (wacc.getMobileNumber().equals(acc.getMobileNumber())){
                    wacc = acc;
                }
            }
        }
    }
    public static BankAccount getBankAccount(String mobileNumber){
        for (int i = 0; i < 2; i++){
            for (BankAccount wacc : banks[i].getAccounts()){
                if (wacc.getMobileNumber().equals(mobileNumber)){
                    return wacc;
                }
            }
        }
        return null;
    }

    //----------------------------------------------------------------

    public static void updateBills(int id){
        for (Bills bill : bills){
            if (bill.getId() == id){
                bill.setNumber("*");
            }
        }
    }
}
