package com.aitribe.lms.Util;

import java.util.Scanner;

public class InputUtil {

    private InputUtil() {
    }

    public static String readNonEmpty(Scanner scanner, String prompt) {

        while (true) {

            System.out.println(prompt);
            String input = scanner.nextLine().trim();

            if (!input.isEmpty()) {
                return input;
            }

            System.out.println("Input cannot be empty. Please try again.");

        }

    }

    //Read int Input


    public static int readInt(Scanner scanner, String prompt) {

        while (true) {

            System.out.println(prompt);
            String input = scanner.nextLine().trim();

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid Number. Please enter a valid integer");
            }
        }
    }

    public static int readChoice(Scanner scanner, String prompt, int min, int max) {

        while (true) {

            int choice = readInt(scanner, prompt);
            if (choice >= min && choice <= max) {
                return choice;
            }
            System.out.println("Invalid choice. Please try again.");
        }
    }

}
