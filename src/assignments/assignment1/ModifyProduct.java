package assignments.assignment1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ModifyProduct {
    public static void modifyProduct(String filePath) {
        Products.initializeIdCounter("products.csv");

        try {
            Scanner input = new Scanner(System.in);
            List<String[]> matchingProducts = new ArrayList<>();

            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            filePath = "products.csv";

            System.out.println("What type of product do you want to modify? (Electronics, Clothing, Grocery): ");
            String type = input.nextLine();

            boolean found = false;

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String [] parts = line.split(", ");
                if (type.equalsIgnoreCase("electronics") && (parts[3].equals("electronics"))) {
                    matchingProducts.add(parts);
                    found = true;

                }
            }
            scanner.close();

            if (!found) {
                System.out.println("No matches found.");
            }

            for (String[] product : matchingProducts) {
                System.out.println("Product ID: " + product[0] + ", Product: " + product[1] + ", Price: " + product[2] + "€, Brand name: " + product[4] + ", Warranty period in years: " + product[5]);
            }

            System.out.println("Type the ID of the product you want to modify: ");
            int id = Integer.parseInt(input.nextLine());

        } catch(FileNotFoundException e) {
            System.out.println(e.getMessage());;
        }
    }
}
