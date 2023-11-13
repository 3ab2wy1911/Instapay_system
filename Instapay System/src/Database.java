import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Database {
    private static  String [] numbers = new String[]{};
    private static  Bills [] bills = new Bills[]{};
    private static List<InstapayAccount> instapayAccounts = new ArrayList<InstapayAccount>();
    public static Scanner scanner = new Scanner (System.in);

    //------------------------------------------------------------------------------------------------------------------

    Database(){
        numbers = new String[]{"01095454010", "01557425211"};
        bills = new Bills[]{new Bills("Electricity","Faisel ST.",1,350,"01095454010"),
        new Bills("Gas","Faisel ST.",2,150,"01095454010"),
        new Bills("Internet","Faisel ST.",3,140,"01095454010")};
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
