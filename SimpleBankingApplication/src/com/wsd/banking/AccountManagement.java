package com.wsd.banking;

import java.util.ArrayList;
import java.util.Scanner;

public class AccountManagement {

	private static ArrayList<BankAccount> accounts = new ArrayList<>();
	private static Scanner scanner = new Scanner(System.in);

	protected static void createAccount() {
		System.out.println("\n===== Create a new account =====");

		// Choose the type of account
		System.out.println(
				"Choose account type:-\n" + "1. Current Account\n" + "2. Savings Account\n" + "3. Salary Account\n");
		System.out.print("Enter your choice:");

		int accountTypeChoice = scanner.nextInt();
		scanner.nextLine();
		// Determine the account type based on the user's choice
		AccountType accountType;
		switch (accountTypeChoice) {
		case 1:
			accountType = AccountType.CURRENT;
			break;
		case 2:
			accountType = AccountType.SAVINGS;
			break;
		case 3:
			accountType = AccountType.SALARY;
			break;
		default:
			accountType = AccountType.DEFAULT;
			break;
		}

		// account holder's name
		System.out.print("Enter account holder's name: ");
		String name = scanner.nextLine();
		while (!ValidationHelper.isValidName(name)) {
			System.out.print("Enter account holder's name: ");
			name = scanner.nextLine();
		}

		// account number
		System.out.print("Enter account number: ");
		int number = scanner.nextInt();
		while (!ValidationHelper.isValidNumber(number)) {
			System.out.print("Enter account number: ");
			number = scanner.nextInt();
		}
		scanner.nextLine();

		// account creation date
		System.out.print("Enter creation date [yyyy-MM-dd]:");
		String creationDate = scanner.nextLine();
		while (!ValidationHelper.isValidCreationDate(creationDate)) {
			System.out.println("Invalid creation date. Please enter a valid date.");
			System.out.print("Enter creation date: ");
			creationDate = scanner.nextLine();
		}
		
		// contact number
		System.out.print("Enter contact number: ");
		int contactNumber = scanner.nextInt();
		while (!ValidationHelper.isValidNumber(contactNumber)) {
			System.out.print("Enter contact number: ");
			number = scanner.nextInt();
		}
		scanner.nextLine();

		// account initial balance
		System.out.print("Enter initial balance: ");
		double initialBalance = scanner.nextDouble();
		while (!ValidationHelper.isValidInitialBalance(initialBalance, accountType)) {
			System.out.println("Invalid initial balance. Minimum balance required: "
					+ ValidationHelper.getMinimumBalanceForAccountType(accountType));
			System.out.print("Enter initial balance: ");
			initialBalance = scanner.nextDouble();
		}

		BankAccount newAccount;
		newAccount = new BankAccount(accountType, name, number, creationDate,contactNumber, initialBalance);
		accounts.add(newAccount);
		System.out.println("\n==========================================");
		System.out.println("\tNew Account created successfully.");
		System.out.println("\n==========================================");
	}

	protected static void displayAllAccounts() {
		System.out.println("\n===== Displaying all accounts =====");
		if (accounts.isEmpty()) {
			System.out.println("No account found.");
		} else {
			String header = String.format("| %-12s | %-20s | %-8s | %-15s | %-15s | %-10s |", "Account Type", "Account Holder",
					"Number", "Creation Date","Contact Number", "Balance");

			System.out.println("+--------------+----------------------+----------+-----------------+-----------------+------------+");
			System.out.println(header);
			System.out.println("+--------------+----------------------+----------+-----------------+-----------------+------------+");

			for (BankAccount account : accounts) {
				String accountInfo = String.format("| %-12s | %-20s | %-8s | %-15s | %-15s | %-10.2f |",
						account.getAccountType(), account.getName(), account.getNumber(), account.getCreationDate(),account.getContactNumber(),
						account.getBalance());
				System.out.println(accountInfo);
			}
			System.out.println("+--------------+----------------------+----------+-----------------+-----------------+------------+");
		}
	}

	protected static void updateAccount() {
		System.out.println("\n===== Update an account =====");
		System.out.print("Enter account number to update: ");
		int accountNumber = scanner.nextInt();
		scanner.nextLine();

		BankAccount accountToUpdate = findAccountByNumber(accountNumber);
		if (accountToUpdate != null) {
			System.out.print("Enter new account holder's name: ");
			String newName = scanner.nextLine();
			accountToUpdate.setName(newName);
			System.out.println("\nAccount updated successfully.");
		} else {
			System.out.println("\nAccount not found.");
		}
	}

	protected static void deleteAccount() {
		System.out.println("\n===== Delete an account =====");
		System.out.print("Enter the account number to delete: ");
		int accountNumber = scanner.nextInt();
		scanner.nextLine();

		BankAccount accountToDelete = findAccountByNumber(accountNumber);
		if (accountToDelete != null) {
			accounts.remove(accountToDelete);
			System.out.println("------------------------------------------------");
			System.out.println("\nAccount deleted successfully.");
		} else {
			System.out.println("Account not found.");
		}
	}

	protected static void searchAccount() {
		System.out.println("\n===== Search for an account =====");
		System.out.print("Enter account number to search: ");
		int accountNumber = scanner.nextInt();
		scanner.nextLine();
		BankAccount searchedAccount = findAccountByNumber(accountNumber);
		if (searchedAccount != null) {
			System.out.println("\nAccount details:");
			String header = String.format("| %-12s | %-20s | %-8s | %-15s | %-15s | %-10s |", "Account Type", "Account Holder",
					"Number", "Creation Date","Contact Number", "Balance");

			System.out.println("+--------------+----------------------+----------+-----------------+-----------------+------------+");
			System.out.println(header);
			System.out.println("+--------------+----------------------+----------+-----------------+------------+");
			String accountInfo = String.format("| %-12s | %-20s | %-8s | %-15s | %-15s | %-10.2f |",
					searchedAccount.getAccountType(), searchedAccount.getName(), searchedAccount.getNumber(),
					searchedAccount.getCreationDate(),searchedAccount.getContactNumber(), searchedAccount.getBalance());
			System.out.println(accountInfo);
			System.out.println("+--------------+----------------------+----------+-----------------+------------+");

		} else {
			System.out.println("Account not found.");
		}
	}

	protected static BankAccount findAccountByNumber(int accountNumber) {
		for (BankAccount account : accounts) {
			if (account.getNumber() == accountNumber) {
				return account;
			}
		}
		return null;
	}

	public static boolean duplicateAccountByNumber(int accountNumber) {
		for (BankAccount account : accounts) {
			if (account.getNumber() == accountNumber) {
				return true;
			}
		}
		return false;
	}

}
