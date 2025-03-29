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
        return ", grocery, " + productWeightKg + ", " + expirationDate;
    }

    public String toCSV() {
        return super.toCSV() + toStringPrinted();
    }
}
