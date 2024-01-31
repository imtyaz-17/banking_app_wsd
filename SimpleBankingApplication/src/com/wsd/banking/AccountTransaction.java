package com.wsd.banking;

import java.util.Scanner;

public class AccountTransaction {
	private static Scanner scanner = new Scanner(System.in);
	protected static void deposit() {
	        System.out.println("\n===== Deposit into an account =====");
	        System.out.print("Enter account number to deposit into: ");
	        int accountNumber = scanner.nextInt();
	        scanner.nextLine();  // Consume the newline character

	        BankAccount accountToDeposit = AccountManagement.findAccountByNumber(accountNumber);
	        if (accountToDeposit != null) {
	            System.out.print("Enter amount to deposit: ");
	            double amount = scanner.nextDouble();
	            accountToDeposit.setBalance(accountToDeposit.getBalance() + amount);
	            System.out.println("Deposit successful. Updated balance: " + accountToDeposit.getBalance());
	        } else {
	            System.out.println("Account not found.");
	        }
	    }

	protected static void withdraw() {
	        System.out.println("\n===== Withdraw from an account =====");
	        System.out.print("Enter account number to withdraw from: ");
	        int accountNumber = scanner.nextInt();
	        scanner.nextLine();  // Consume the newline character

	        BankAccount accountToWithdraw = AccountManagement.findAccountByNumber(accountNumber);
	        if (accountToWithdraw != null) {
	            System.out.print("Enter amount to withdraw: ");
	            double amount = scanner.nextDouble();
	            while (!accountToWithdraw.canWithdraw(amount)) {
	                System.out.println("Invalid withdrawal amount. Minimum balance should be maintained.");
	                System.out.print("Enter amount to withdraw: ");
	                amount = scanner.nextDouble();
	            }
	            accountToWithdraw.setBalance(accountToWithdraw.getBalance() - amount);
	            System.out.println("Withdrawal successful. Remaining balance: " + accountToWithdraw.getBalance());
	        } else {
	            System.out.println("Account not found.");
	        }
	    }
}
