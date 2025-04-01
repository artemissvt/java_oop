package assignments.assignment1;

import java.util.Scanner;

public class ProductsMenu {
    public static void main(String[] args) {
        Products.initializeIdCounter("products.csv");
        Scanner input = new Scanner(System.in);
        String filePath = "products.csv";

        int choice = 0;
        do {
            System.out.println("Welcome to Inventory.");
            System.out.println("1. Search product");
            System.out.println("2. Add product");
            System.out.println("3. Modify product");
            System.out.println("Please choose (1, 2, 3): ");
            choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    filePath = "products.csv";
                    Scanner in = new Scanner(System.in);
                    System.out.print("Enter the product you want to search: ");
                    String searchWord = input.nextLine();
                    SearchProduct.searchInCSV(filePath, searchWord);
                    input.close();
                    break;
                case 2:
                    AddProduct.addProduct(filePath);
                    break;
                case 3:
                    System.out.println("Type the kind of product you want to modify (electronics, grocery, clothing): ");
                    String kind = input.nextLine();

                    switch (kind) {
                        case "electronics":
                            ModifyProductElectronics.modifyProductElectronics(filePath);
                            break;
                        case "grocery":
                            ModifyProductGrocery.modifyProductGrocery(filePath);
                            break;
                        case "clothing":
                            ModifyProductClothing.modifyProductClothing(filePath);
                            break;
                    }
                    break;
            } break;
        } while (choice != 0);
    }
}