import java.util.ArrayList;
import java.util.Scanner;

public class ListMaker {
    private static final ArrayList<String> myList = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);
    private static boolean needsToBeSaved = false; // Track if list needs to be saved

    public static void main(String[] args) {
        char choice;
        boolean quit = false;

        do {
            displayMenu();
            choice = getRegExString(scanner, "[ADOSCQadoscq]");
            switch (choice) {
                case 'A', 'a':
                    addToList();
                    break;
                case 'D', 'd':
                    deleteFromList();
                    break;
                case 'O', 'o':
                    openList();
                    break;
                case 'S', 's':
                    saveList();
                    break;
                case 'C', 'c':
                    clearList();
                    break;
                case 'V', 'v':
                    viewList();
                    break;
                case 'Q', 'q':
                    quit = confirmQuit(scanner);
                    if (quit) {
                        // Check if list needs to be saved before quitting
                        if (needsToBeSaved) {
                            saveBeforeQuit();
                        }
                    }
                    break;
            }
        } while (!quit);
    }

    private static char getRegExString(Scanner scanner, String s) {
        return 0;
    }

    private static void displayMenu() {
        System.out.println("Options:");
        System.out.println("A - Add an item to the list");
        System.out.println("D - Delete an item from the list");
        System.out.println("O - Open a list file from disk");
        System.out.println("S - Save the current list file to disk");
        System.out.println("C - Clear all elements from the list");
        System.out.println("V - View the list");
        System.out.println("Q - Quit");
        System.out.println();
        System.out.println("Current list:");
        printList();
    }

    public static void addToList() {
        System.out.print("Enter the item to add: ");
        String newItem = scanner.nextLine();
        myList.add(newItem);
        needsToBeSaved = true; // Mark list as modified
        System.out.println("Item added successfully.");
    }

    public static void deleteFromList() {
        if (myList.isEmpty()) {
            System.out.println("The list is empty.");
            return;
        }
        System.out.println("Select an item to delete:");
        for (int i = 0; i < myList.size(); i++) {
            System.out.println((i + 1) + ". " + myList.get(i));
        }
        int indexToDelete = getRangedInt(scanner, "Enter the number of the item to delete", 1, myList.size()) - 1;
        myList.remove(indexToDelete);
        needsToBeSaved = true; // Mark list as modified
        System.out.println("Item deleted successfully.");
    }

    public static void openList() {
        // Logic to open a list file from disk
        // Prompt user to save current list if unsaved changes exist
        if (needsToBeSaved) {
            saveBeforeOpen();
        }
        // Implement file loading logic
        System.out.println("List loaded successfully.");
    }

    public static void saveList() {
        // Logic to save current list to a file
        // Implement file saving logic
        needsToBeSaved = false; // Mark list as saved
        System.out.println("List saved successfully.");
    }

    public static void clearList() {
        myList.clear();
        needsToBeSaved = true; // Mark list as modified
        System.out.println("All elements removed from the list.");
    }

    public static void viewList() {
        printList();
    }

    private static void printList() {
        if (myList.isEmpty()) {
            System.out.println("The list is empty.");
            return;
        }
        System.out.println("Current list items:");
        for (int i = 0; i < myList.size(); i++) {
            System.out.println((i + 1) + ". " + myList.get(i));
        }
    }

    private static boolean confirmQuit(Scanner pipe) {
        return getYNConfirm(pipe, "Are you sure you want to quit?");
    }

    private static String getRegExString(Scanner pipe, String prompt, String regEx) {
        String input;
        do {
            System.out.print(prompt + ": ");
            input = pipe.nextLine().trim();
        } while (!input.matches(regEx));
        return input;
    }

    private static int getRangedInt(Scanner pipe, String prompt, int low, int high) {
        int input;
        do {
            System.out.print(prompt + " [" + low + "-" + high + "]: ");
            while (!pipe.hasNextInt()) {
                System.out.print("Invalid input. Please enter an integer: ");
                pipe.next();
            }
            input = pipe.nextInt();
        } while (input < low || input > high);
        pipe.nextLine(); // Consume newline left-over
        return input;
    }

    private static boolean getYNConfirm(Scanner pipe, String prompt) {
        String input;
        do {
            System.out.print(prompt + " [Y/N]: ");
            input = pipe.nextLine().trim().toUpperCase();
        } while (!input.equals("Y") && !input.equals("N"));
        return input.equals("Y");
    }

    private static void saveBeforeQuit() {
        boolean save = getYNConfirm(scanner, "Save the current list before quitting? (Y/N)");
        if (save) {
            saveList();
        }
    }

    private static void saveBeforeOpen() {
        boolean save = getYNConfirm(scanner, "Save the current list before opening a new one? (Y/N)");
        if (save) {
            saveList();
        }
    }
}