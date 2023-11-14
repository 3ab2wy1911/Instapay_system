import com.Instapay.Banks.BankAccount;
import com.Instapay.Database;
import com.Instapay.InstapayAccount;
import com.Instapay.Wallets.*;

public class Main {
    public static void main(String[] args) {
        Database d= new Database();    // just for creating my dummy account list
                                        // (waiting for register function to add our list )

        InstapayAccount bankAccount = new BankAccount();
        bankAccount.showAccounts();

        bankAccount.signIn();


    }

}