package assignments.assignment1;

import java.io.*;
import java.util.Scanner;

public class SearchProduct {
    public static void searchInCSV(String filePath, String searchWord) {
        Products.initializeIdCounter("products.csv");
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            boolean found = false;

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String [] parts = line.split(", ");
                if (parts[1].contains(searchWord)) {
                    if (parts[3].equals("electronics")) {
                        System.out.println("Product ID: " + parts[0] + ", Product: " + parts[1] + ", Price: " + parts[2] + "€, Brand name: " + parts[4] + ", Warranty period in years: " + parts[5]);
                        found = true;
                    } else if (parts[3].equals("clothing")) {
                        System.out.println("Product ID: " + parts[0] + ", Product: " + parts[1] + ", Price: " + parts[2] + "€, Size: " + parts[4] + ", Color: " + parts[5] + ", Material: " + parts[6]);
                        found = true;
                    } else if (parts[3].equals("grocery")) {
                        System.out.println("Product ID: " + parts[0] + ", Product: " + parts[1] + ", Price per kg: " + parts[2] + "€, Weight in kg: " + parts[4] + ", Expiration date(YYYY-MM-DD): " + parts[5]);
                        found = true;
                    }
                }
            }
            if (!found) {
                System.out.println("No matches found.");
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter the CSV file path: ");
        String filePath = input.nextLine();

        System.out.print("Enter the word to search for: ");
        String searchWord = input.nextLine();

        searchInCSV(filePath, searchWord);

        input.close();
    }
}
