package assignments.assignment1;

import java.io.*;
import java.util.Scanner;

public class Products {
    protected String productName;
    protected double productPrice;
    private static int idCounter = 1;
    protected int productId;

    public Products(String productName, double productPrice) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productId = idCounter++;
    }


    public static void initializeIdCounter(String filePath) {
        int maxId = 0;
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length > 0) {
                    try {
                        int id = Integer.parseInt(parts[0]);
                        if (id > maxId) {
                            maxId = id;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        idCounter = maxId + 1;
    }

    public String toCSV() {
        return productId + ", " + productName + ", " + productPrice;
    }

    public String getProductName() {
        return productName;
    }
}
