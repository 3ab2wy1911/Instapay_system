import com.Instapay.Accounts.AccountType;
import com.Instapay.Accounts.Wallets.Fawry;
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
        WalletApi wapi = new Fawry("Fawry", 1);
        wapi.addAccount(new WalletAccount("sh3boo", "123465", "01222222222", 2000.0, 3));
        wapi.addAccount(new WalletAccount("mohamed", "123465", "01111111111", 1000.0, 3));
        wapi.addAccount(new WalletAccount("reyad", "123465", "03333333333", 1000.0, 2));
        InstapayAccount insacc = new InstapayAccount("omar", "123465", "01550033327", 1000.0);
        insacc.setApi(wapi);
        insacc.transfer();
        insacc.inquireBalance();
        wapi.printAccounts();
    }

}