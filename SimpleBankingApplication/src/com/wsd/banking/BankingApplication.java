package com.wsd.banking;

import java.util.Scanner;

public class BankingApplication {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;

        do {
            displayMenu();
            System.out.print("Enter your choice (1-8): ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                	AccountManagement.createAccount();
                    break;
                case 2:
                	AccountManagement.displayAllAccounts();
                    break;
                case 3:
                	AccountManagement.updateAccount();
                    break;
                case 4:
                	AccountManagement.deleteAccount();
                    break;
                case 5:
                	//AccountTransaction.deposit();
                    break;
                case 6:
                	//AccountTransaction.withdraw();
                    break;
                case 7:
                	AccountManagement.searchAccount();
                    break;
                case 8:
                    System.out.println("Exiting the application. Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 8.");
            }
        } while (choice != 8);

        scanner.close();
    }
    private static void displayMenu() {
        System.out.println("\n===== Banking Application Menu =====\n" +
                "1. Create a new account\n" +
                "2. Display all accounts\n" +
                "3. Update an account\n" +
                "4. Delete an account\n" +
                "5. Deposit an amount into your account\n" +
                "6. Withdraw an amount from your account\n" +
                "7. Search for an account\n" +
                "8. Exit");
    }
}
