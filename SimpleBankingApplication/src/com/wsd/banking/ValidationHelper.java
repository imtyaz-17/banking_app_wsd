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
    	
    }

    private static boolean containsNumberOrSpecialCharacter(String input) {
        
    }

    public static boolean isValidNumber(int number) {
        return number > 0  && String.valueOf(number).length() > 3 && !AccountManagement.duplicateAccountByNumber(number);
    }

    public static boolean isValidCreationDate(String creationDate) {
        
    }

    public static boolean isValidInitialBalance(double initialBalance, AccountType accountType) {
            }

    protected static double getMinimumBalanceForAccountType(AccountType accountType) {
}

}
