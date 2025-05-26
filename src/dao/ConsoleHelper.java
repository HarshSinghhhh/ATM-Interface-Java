package dao;

import java.util.Scanner;

public class ConsoleHelper {
    private static final Scanner sc = new Scanner(System.in);

    public static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = sc.nextLine();
            try {
                return Integer.parseInt(line.trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid number, please try again.");
            }
        }
    }

    public static double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = sc.nextLine();
            try {
                return Double.parseDouble(line.trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid amount, please enter a number.");
            }
        }
    }

    public static void pause() {
        System.out.println("\nPress Enter to continue...");
        sc.nextLine();
    }

    public static String readString(String string) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'readString'");
    }

    public static void printLine(String string) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'printLine'");
    }
}
