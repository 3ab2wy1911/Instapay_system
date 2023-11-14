package com.Instapay.Manager;

import com.Instapay.Accounts.InstapayAccount;

public class Manager {
    private Database db;
    private InstapayAccount instapayAccount;

    //------------------------------------------------------------------------------------------------------------------

    public int mainMenu(){

        System.out.println("1.Login\n2.Register\n3.Exit");
        System.out.print("Enter your choice : ");
        int choice = Database.scanner.nextInt();
        while(choice >3 || choice <1){
            System.out.println("1.Login\n2.Register\n3.Exit");
            System.out.print("Invalid choice !!! Enter a valid choice : ");
            choice = Database.scanner.nextInt();
        }
        return choice;
    }
    //------------------------------------------------------------------------------------------------------------------

    public int operations(){

        System.out.println("1.Transfer\n2.Pay a Bill\n3.Inquire about the balance\n4.Log out");
        System.out.print("Enter your choice : ");
        int choice = Database.scanner.nextInt();
        while(choice >4 || choice <1){
            System.out.println("1.Transfer\n2.Pay a Bill\n3.Inquire about the balance\n4.Exit");
            System.out.print("Invalid choice !!! Enter a valid choice : ");
            choice = Database.scanner.nextInt();
        }
        return choice;
    }

    //------------------------------------------------------------------------------------------------------------------
    public void startApplication() {

        db = new Database();
        instapayAccount = new InstapayAccount();

        System.out.println("Welcome to Instapay App!");

        int answer = 0,choice = 0;
        while(true) {
            answer = mainMenu();
            if (answer == 1){
                instapayAccount.signIn();
            }
            else if (answer == 2){
                instapayAccount.register();
            }
            else {
                break;
            }

            if (instapayAccount.getUserName()!=null){
                while(choice != 4){
                    choice = operations();
                    if (choice == 1){
                        instapayAccount.transfer();
                    }
                    else if (choice == 2){
                        instapayAccount.payBill();
                    }
                    else if (choice == 3){
                        instapayAccount.inquireBalance();
                    }
                }
            }
        }
        System.out.println("Thanks for using Instapay App!");
    }

}
