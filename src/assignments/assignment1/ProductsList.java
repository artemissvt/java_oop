package assignments.assignment1;

import java.util.ArrayList;

public class ProductsList {
    private static ArrayList<Products> products;
    public ProductsList() {
        products = new ArrayList<>();
    }
    public void add(Products product) {
        products.add(product);
    }

    public void printProducts() {
        for (Products product : products) {
            System.out.println(product);
        }
    }

    public void searchProductsByName(String productName) {
        boolean found = false;
        for (Products product : products) {
            if (product.getProductName().equalsIgnoreCase(productName)){
                System.out.println("Product Name: " + product.productName + ", Price: " + product.productPrice);
                found = true;
                break;
                }
            } if (! found) {
            System.out.println("Product not found");
        }
    }
}
