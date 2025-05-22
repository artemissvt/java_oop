package assignments.assignment1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ModifyProductClothing {
    public static void modifyProductClothing(String filePath) {
        Products.initializeIdCounter("products.csv");

        try {
            Scanner input = new Scanner(System.in);
            List<String[]> allProducts = new ArrayList<>();
            List<String[]> clothingProducts = new ArrayList<>();

            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            boolean found = false;

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(", ");
                allProducts.add(parts);
                if (parts[3].equalsIgnoreCase("clothing")) {
                    clothingProducts.add(parts);
                    found = true;
                }
            }
            scanner.close();

            if (!found) {
                System.out.println("No products found.");
                return;
            }

            System.out.println("Clothing products:");
            for (String[] product : clothingProducts) {
                System.out.println("Product ID: " + product[0] + ", Product: " + product[1] + ", Price: " + product[2] + "€, Size: " + product[4] + ", Color: " + product[5] + ", Material: " + product[6]);
            }


            System.out.println("Type the ID of the product you want to modify: ");
            int targetId = Integer.parseInt(input.nextLine());

            boolean modified = false;
            for (String[] product : clothingProducts) {
                int currentId = Integer.parseInt(product[0]);

                if (targetId == currentId && product[3].equalsIgnoreCase("clothing")) {
                    modified = true;

                    System.out.println("Current details: ");
                    System.out.println("Product ID: " + product[0] + ", Product: " + product[1] + ", Price: " + product[2] + "€, Size: " + product[4] + ", Color: " + product[5] + ", Material" + product[6]);


                    System.out.println("What do you want to modify (name, price, size, color, material): ");
                    String newWhat = input.nextLine();

                    if (newWhat.equalsIgnoreCase("name")) {
                        System.out.println("Type the new name:");
                        String newName = input.nextLine();
                        product[1] = newName;
                    }

                    if (newWhat.equalsIgnoreCase("price")) {
                        System.out.println("Type the new price:");
                        double newPrice = input.nextDouble();
                        product[2] = String.valueOf(newPrice);
                    }

                    if (newWhat.equalsIgnoreCase("size")) {
                        System.out.println("Type the new size:");
                        String newSize = input.nextLine();
                        product[4] = newSize;
                    }
                    if (newWhat.equalsIgnoreCase("color")) {
                        System.out.println("Type the new color:");
                        String newColor = input.nextLine();
                        product[5] = newColor;
                    }

                    if (newWhat.equalsIgnoreCase("material")) {
                        System.out.println("Type the new material:");
                        String newMaterial = input.nextLine();
                        product[6] = newMaterial;
                    }
                }
            }

            if (!modified) {
                System.out.println("No matching product found.");
            }

            PrintWriter writer = new PrintWriter(new FileWriter(filePath));
            filePath = "products.csv";
            for (String[] product : allProducts) {
                writer.println(String.join(", ", product));
            }
            writer.close();

        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
