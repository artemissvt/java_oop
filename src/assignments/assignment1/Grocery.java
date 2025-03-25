package assignments.assignment1;

public class Grocery extends Products {
    protected double productWeightKg;
    protected String expirationDate;

    public Grocery(String productName, double productPrice, double productWeightKg, String expirationDate) {
        super(productName, productPrice);
        this.productWeightKg = productWeightKg;
        this.expirationDate = expirationDate;
    }

    public String toStringPrinted() {
        return "Product's weight in kg: " + productWeightKg + ", Products expiration date: " + expirationDate;
    }

    public String toString() {
        return super.toString() + "\n" + toStringPrinted();
    }
}
