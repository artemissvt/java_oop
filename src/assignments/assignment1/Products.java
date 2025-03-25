package assignments.assignment1;

public class Products {
    protected String productName;
    protected double productPrice;

    public Products(String productName, double productPrice) {
        this.productName = productName;
        this.productPrice = productPrice;
    }

    public String toString() {
        return "Product Name: " + productName + ", Product Price: " + productPrice;
    }

    public String getProductName() {
        return productName;
    }
}
