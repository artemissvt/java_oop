package assignments.assignment1;

import java.util.Scanner;

public class ProductsMenu {
    public static void main(String[] args) {
        ProductsList productsList = new ProductsList();
        Scanner input = new Scanner(System.in);
        productsList.add(new Electronics("Washing Machine", 380.0, "Whirlpool", 3));
        productsList.add(new Clothing("T-shirt", 12.0, "Medium", "Black", "Cotton"));
        productsList.add(new Grocery("Tomato", 3.0, 1, "30/03/2025"));


        int choice = 0;
        do {
            System.out.println("Welcome to Inventory.");
            System.out.println("1. Search product");
            System.out.println("2. Add product");
            System.out.println("3. Modify product");
            System.out.println("Please choose: ");
            choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                       System.out.println("Please enter the name of the product you want to search: ");
                       String name = input.nextLine();
                       productsList.searchProductsByName(name);
                       break;
                case 2:
                    System.out.println("Please enter the type of the product you want to add (Electronics, Clothing, Grocery): ");
                    String type = input.nextLine();

                    switch (type) {
                        case "Electronics":
                            System.out.println("Please enter the name of the product you want to add: ");
                            String productName = input.nextLine();
                            System.out.println("Please enter the price of the product you want to add: ");
                            double price = input.nextDouble();
                            System.out.println("Please enter the brand name of the product you want to add: ");
                            String brandName = input.nextLine();
                            input.nextLine();
                            System.out.println("Please enter the warranty period in years of the product you want to add: ");
                            int warranty = input.nextInt();
                            input.nextLine();
                            productsList.add(new Electronics(productName, price, brandName, warranty));
                        }
                    } break;
            } while (choice != 0);

        input.close();
        }
    }

