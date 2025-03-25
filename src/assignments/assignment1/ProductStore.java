package assignments.assignment1;

import week03hw.BookList;

public class ProductStore {
    public static void main(String[] args) {
        ProductsList products = new ProductsList();

        products.add(new Electronics("Washing Machine", 380.0, "Whirpool", 3));
        products.add(new Clothing("T-shirt", 12.0, "Medium", "Black", "cotton"));
        products.add(new Grocery("Tomato", 3.0, 1, "30/03/2025"));

        products.printProducts();
    }
}
