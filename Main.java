import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    // setting array size bigger than 100 so we can test adding values
    static int capacity = 150;
    static int[] numbers = new int[capacity];
    static int size = 0; // keeps track of how many numbers are actually stored

    public static void main(String[] args) {

        // reading numbers from the file
        readFile("input.txt");

        Scanner scnr = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Search");
            System.out.println("2. Modify");
            System.out.println("3. Add");
            System.out.println("4. Remove");
            System.out.println("5. Display");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            choice = scnr.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Enter number to search: ");
                    int target = scnr.nextInt();
                    int index = search(target);

                    if (index != -1)
                        System.out.println("Number found at index: " + index);
                    else
                        System.out.println("Number not found.");
                    break;

                case 2:
                    // try-catch required for modify
                    try {
                        System.out.print("Enter index to modify: ");
                        int modifyIndex = scnr.nextInt();

                        System.out.print("Enter new value: ");
                        int newValue = scnr.nextInt();

                        modify(modifyIndex, newValue);

                    } catch (Exception e) {
                        System.out.println("Invalid index.");
                    }
                    break;

                case 3:
                    // try-catch required for add
                    try {
                        System.out.print("Enter number to add: ");
                        int value = scnr.nextInt();
                        add(value);
                    } catch (Exception e) {
                        System.out.println("Array is full.");
                    }
                    break;

                case 4:
                    System.out.print("Enter index to remove: ");
                    int removeIndex = scnr.nextInt();
                    remove(removeIndex);
                    break;

                case 5:
                    display();
                    break;

                case 0:
                    System.out.println("Program ended.");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 0);

        scnr.close();
    }

    // reads numbers from the input file and stores them in the array
    public static void readFile(String filename) {
        try {
            File file = new File(filename);
            Scanner scnr = new Scanner(file);

            while (scnr.hasNextInt() && size < capacity) {
                numbers[size] = scnr.nextInt();
                size++;
            }

            scnr.close();
            System.out.println("File loaded. Total numbers: " + size);

        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }

    // searches for a number and returns its index
    // returns -1 if number is not in the array
    public static int search(int target) {
        for (int i = 0; i < size; i++) {
            if (numbers[i] == target) {
                return i;
            }
        }
        return -1;
    }

    // changes the value at a given index
    // throws exception if index is invalid
    public static void modify(int index, int newValue) throws Exception {

        if (index < 0 || index >= size) {
            throw new Exception();
        }

        int oldValue = numbers[index];
        numbers[index] = newValue;

        System.out.println("Old value: " + oldValue);
        System.out.println("New value: " + newValue);
    }

    // adds a new number at the end of the array
    // throws exception if array is full
    public static void add(int value) throws Exception {

        if (size >= capacity) {
            throw new Exception();
        }

        numbers[size] = value;
        size++;

        System.out.println("Number added.");
    }

    // removes the number at the given index
    // shifts remaining elements to the left
    public static void remove(int index) {

        if (index < 0 || index >= size) {
            System.out.println("Invalid index.");
            return;
        }

        int removedValue = numbers[index];

        for (int i = index; i < size - 1; i++) {
            numbers[i] = numbers[i + 1];
        }

        size--;

        System.out.println("Removed value: " + removedValue);
    }

    // prints the current array
    public static void display() {
        System.out.println("\nCurrent Array:");
        for (int i = 0; i < size; i++) {
            System.out.print(numbers[i] + " ");
        }
        System.out.println();
    }
}
