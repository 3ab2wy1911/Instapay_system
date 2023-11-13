import com.Instapay.Banks.QNB;

public class Main {
    public static void main(String[] args) {

        QNB bank1 = new QNB ("CIB Cairo", 1);
        System.out.println(bank1.getAccount("01095454010").getUserName());

    }

}