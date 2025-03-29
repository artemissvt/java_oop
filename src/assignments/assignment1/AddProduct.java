package assignments.assignment1;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class AddProduct {
    public static void addProduct(String filePath) {
        Products.initializeIdCounter("products.csv");

        Scanner input = new Scanner(System.in);
        List<Products> newProducts = new ArrayList<>();

        System.out.println("Enter the type of product (Electronics, Clothing, Grocery): ");
        String type = input.nextLine();

        System.out.println("Enter Product Name: ");
        String name = input.nextLine();

        System.out.println("Enter Product Price in Euros: ");
        double price = input.nextDouble();
        input.nextLine();

        switch (type) {
            case "electronics":
                System.out.println("Enter Brand Name: ");
                String brand = input.nextLine();

                System.out.println("Enter Warranty Period in Years: ");
                int warranty = input.nextInt();

                newProducts.add(new Electronics(name, price, brand, warranty));
                break;

            case "clothing":
                System.out.println("Enter Size: ");
                String size = input.nextLine();

                System.out.println("Enter Color: ");
                String color = input.nextLine();

                System.out.println("Enter Material: ");
                String material = input.nextLine();

                newProducts.add(new Clothing(name, price, size, color, material));
                System.out.println(" ");
                break;

            case "grocery":
                System.out.println("Enter Product Weight in Kg: ");
                double weight = input.nextDouble();
                input.nextLine();

                System.out.println("Enter Expiration Date (YYYY-MM-DD): ");
                String expiration = input.nextLine();

                newProducts.add(new Grocery(name, price, weight, expiration));
                break;

            default:
                System.out.println("Invalid product type");
                return;
        }
        CSVWriter.appendToCSV(filePath, newProducts);
    }
}
