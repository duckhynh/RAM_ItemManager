/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tool;

import java.time.YearMonth;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 *
 * @author Hung
 */
public class GetInput {

    private static Scanner scanner = new Scanner(System.in);

    // Get input as a String
    public static String getString(String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();
        while (input.isEmpty()) {
            System.out.println("Input cannot be empty. Please enter again.");
            System.out.print(prompt);
            input = scanner.nextLine().trim();
        }
        return input;
    }

    // Get input as an int with valid range check
    public static int getInt(String prompt, int min, int max) {
        int value;
        while (true) {
            System.out.print(prompt);
            try {
                value = Integer.parseInt(scanner.nextLine());
                if (value >= min && value <= max) {
                    return value;
                } else {
                    System.out.println("Please enter a number between " + min + " and " + max + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer.");
            }
        }
    }

    // Get input as a double with valid range check
    public static double getDouble(String prompt, double min, double max) {
        double value;
        while (true) {
            System.out.print(prompt);
            try {
                value = Double.parseDouble(scanner.nextLine());
                if (value >= min && value <= max) {
                    return value;
                } else {
                    System.out.println("Please enter a number between " + min + " and " + max + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid decimal number.");
            }
        }
    }

    // Get new int value or keep the current value if left blank
    public static String getNewString(String prompt, String currentValue) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();
        return input.isEmpty() ? currentValue : input;
    }

    // Get new int value or keep the current value if left blank
    public static int getNewInt(String prompt, int min, int max, int currentValue) {
        System.out.print(prompt + " (Current: " + currentValue + "): ");
        String input = scanner.nextLine().trim();
        if (input.isEmpty()) {
            return currentValue;
        }
        try {
            int value = Integer.parseInt(input);
            if (value >= min && value <= max) {
                return value;
            } else {
                System.out.println("Please enter a number between " + min + " and " + max + ".");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid integer.");
        }
        return currentValue;
    }

    // Get new double value or keep the current value if left blank
    public static double getNewDouble(String prompt, double min, double max, double currentValue) {
        System.out.print(prompt + " (Current: " + currentValue + "): ");
        String input = scanner.nextLine().trim();
        if (input.isEmpty()) {
            return currentValue;
        }
        try {
            double value = Double.parseDouble(input);
            if (value >= min && value <= max) {
                return value;
            } else {
                System.out.println("Please enter a number between " + min + " and " + max + ".");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid decimal number.");
        }
        return currentValue;
    }

    // Get input as a boolean (Yes/No)
    public static boolean getBoolean(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("yes") || input.equals("y")) {
                return true;
            } else if (input.equals("no") || input.equals("n")) {
                return false;
            } else {
                System.out.println("Please enter 'yes' or 'no'.");
            }
        }
    }

    public static YearMonth getYearMonth(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return YearMonth.parse(input); // Thử parse chuỗi đầu vào thành YearMonth
            } catch (DateTimeParseException e) {
                System.out.println("Invalid format. Please enter a date in the format YYYY-MM.");
            }
        }
    }

    // Validate ID with regex
    final static String idRegex = "^[A-Z]\\d{4}$";

    public static String getValidId(String prompt) {
        return getValidInput(prompt, idRegex, "Invalid ID format. Please enter a valid ID with PXXXX.");
    }

    // General method for input validation
    private static String getValidInput(String prompt, String regex, String errorMessage) {
        while (true) {
            String input = getString(prompt);
            if (input.matches(regex)) {
                return input;
            } else {
                System.out.println(errorMessage);
            }
        }
    }
}
