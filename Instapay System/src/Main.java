
import com.Instapay.Accounts.Wallets.WalletAccount;
import com.Instapay.Accounts.Wallets.WalletApi;
import com.Instapay.Database;
import com.Instapay.InstapayAccount;

public class Main {
    public static void main(String[] args) {
        Database d= new Database();    // just for creating my dummy account list
                                        // (waiting for register function to add our list )

    //        InstapayAccount bankAccount = new BankAccount();
    //        bankAccount.showAccounts();
    //
    //        bankAccount.signIn();


    //        InstapayAccount n = new WalletAccount("username", "password", "01095454010", 1000.0);

    //        n.payBill();

        WalletApi wapiFawry = d.getWallets(1); // gives me the account list of the api belongs to fawry
        wapiFawry.addAccount(new WalletAccount("sh3boo", "123465", "01222222222", 2000.0, 3));
        wapiFawry.addAccount(new WalletAccount("mohamed", "123465", "01111111111", 1000.0, 3));

        WalletApi wapiVodafone = d.getWallets(2); // gives me the account list of the api belongs to vodafone cash
        wapiVodafone.addAccount(new WalletAccount("reyad", "123465", "03333333333", 1000.0, 2));
        wapiVodafone.addAccount(new WalletAccount("elramly", "123465", "04444444444", 1000.0, 2));

        WalletApi wapiTelda = d.getWallets(2); // gives me the account list of the api belongs to telda
        wapiTelda.addAccount(new WalletAccount("elramly", "123465", "05555555555", 1000.0, 1));
        InstapayAccount insacc = new InstapayAccount("omar", "123465", "01550033327", 1000.0);
        // will be updated in the menu display
        // when a user wants to transfer to a fawry you will set the api to wapiFawry into insta account to load all the accounts belongs to fawry
        // same for other services
        insacc.setApi(wapiFawry);
//        insacc.setApi(wapiVodafone);
//        insacc.setApi(wapiTelda);
        insacc.setDataBase(d);

        insacc.transfer();
        insacc.inquireBalance();
        d.printInstas("0121212");
        //wapiFawry.printAccounts();
    }

}