package assignments.assignment1;

public class Electronics extends Products {
    protected String brandName;
    protected int warrantyYears;

    public Electronics(String productName, double productPrice, String brandName, int warrantyYears) {
        super(productName, productPrice);
        this.brandName = brandName;
        this.warrantyYears = warrantyYears;
    }

    public String toStringPrinted() {
        return "Product Name: " + productName + "Product's warranty period (years): " + warrantyYears;
    }
    public String toString() {
        return super.toString() + toStringPrinted();
    }
}
