package com.wsd.banking;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ValidationHelper {
	// Constants for minimum initial balance based on account type
    public static final double MIN_INITIAL_BALANCE_DEFAULT = 100.0;
    public static final double MIN_INITIAL_BALANCE_CURRENT = 500.0;
    public static final double MIN_INITIAL_BALANCE_SAVINGS = 1000.0;
    public static final double MIN_INITIAL_BALANCE_SALARY = 0.0;

    public static boolean isValidName(String name) {
        try {
            if (name == null || name.trim().length() < 3) {
                throw new IllegalArgumentException("Name must not be null and should have at least 3 characters.");
            }

            if (containsNumberOrSpecialCharacter(name)) {
                throw new IllegalArgumentException("Name cannot contain digits or special characters.");
            }

            return true; // Validation passed
        } catch (Exception e) {
            System.out.println("Error validating name: " + e.getMessage());
            return false;
        }
    }

    private static boolean containsNumberOrSpecialCharacter(String input) {
        // Check if the input contains any digits or special characters
        return input.matches(".*[\\d\\W].*");
    }

    public static boolean isValidNumber(int number) {
        try {
            if (number <= 0) {
                throw new IllegalArgumentException("Account number must be greater than 0.");
            }

            String numberStr = String.valueOf(number);
            if (numberStr.length() <= 3) {
                throw new IllegalArgumentException("Account number must have more than three digits.");
            }

            if (AccountManagement.duplicateAccountByNumber(number)) {
                throw new IllegalArgumentException("Account number is already in use.");
            }

            return true; // Validation passed
        } catch (Exception e) {
            System.out.println("Error validating account number: " + e.getMessage());
            return false;
        }
    }


    public static boolean isValidCreationDate(String creationDate) {
        if (creationDate == null || creationDate.trim().isEmpty()) {
            return false;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false); // Strict date parsing

        try {
            //throw an exception if the format is invalid
            Date parsedDate = dateFormat.parse(creationDate);

            // ensure the date is not in the future
            Date currentDate = new Date();
            return parsedDate.before(currentDate);

        } catch (ParseException e) {
            return false;
        }
    }

    public static boolean isValidInitialBalance(double initialBalance, AccountType accountType) {
        // check a minimum initial balance for each account type
        return initialBalance >= getMinimumBalanceForAccountType(accountType);
    }

    protected static double getMinimumBalanceForAccountType(AccountType accountType) {
        switch (accountType) {
            case CURRENT:
                return MIN_INITIAL_BALANCE_CURRENT;
            case SAVINGS:
                return MIN_INITIAL_BALANCE_SAVINGS;
            case SALARY:
                return MIN_INITIAL_BALANCE_SALARY;
            case DEFAULT:
            default:
                return MIN_INITIAL_BALANCE_DEFAULT;
        }
    }
}
