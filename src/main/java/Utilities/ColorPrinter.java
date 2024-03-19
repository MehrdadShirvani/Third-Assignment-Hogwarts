package Utilities;

public class ColorPrinter
{
    // methods to write colored text in terminal
    public static void printRed(String text) {
        System.out.println("\u001B[31m" + text + "\u001B[0m");
    }

    public static void printGreen(String text) {
        System.out.println("\u001B[32m" + text + "\u001B[0m");
    }

    public static void printYellow(String text) {
        System.out.println("\033[0;33m" + text + "\u001B[0m");
    }

    public static void printBlue(String text) {
        System.out.println("\u001B[34m" + text + "\u001B[0m");
    }
}
