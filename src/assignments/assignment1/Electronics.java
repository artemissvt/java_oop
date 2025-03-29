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
        return ", electronics, " + brandName + ", " + warrantyYears;
    }
    public String toCSV() {
        return super.toCSV() + toStringPrinted();
    }
}
