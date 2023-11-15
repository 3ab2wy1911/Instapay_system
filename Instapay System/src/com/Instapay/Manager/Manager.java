package com.Instapay.Manager;

import com.Instapay.Accounts.InstapayAccount;

public class Manager {

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

    // ----------------------------------------------------------------

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

    // ----------------------------------------------------------------

    public void startApplication() {

        InstapayAccount instapayAccount = new InstapayAccount();
        new Database();

        System.out.println("Welcome to Instapay App!");

        while(true) {
            int answer, choice = 0;
            answer = mainMenu();
            if (answer == 1){
                instapayAccount.signIn();
            }
            else if (answer == 2){
                instapayAccount.register();
                Database.updateInstapayAccounts(instapayAccount);
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
                instapayAccount = new InstapayAccount();
            }
        }
        System.out.println("Thanks for using Instapay App!");
    }
    // ----------------------------------------------------------------
}
