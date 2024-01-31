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
        return name != null && name.trim().length() >=3 && !containsNumberOrSpecialCharacter(name);
    }

    private static boolean containsNumberOrSpecialCharacter(String input) {
        // Check if the input contains any digits or special characters
        return input.matches(".*[\\d\\W].*");
    }

    public static boolean isValidNumber(int number) {
        return number > 0  && String.valueOf(number).length() > 3 && !AccountManagement.duplicateAccountByNumber(number);
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
