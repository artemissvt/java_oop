package assignments.assignment1;

public class Clothing extends Products{
    protected String size;
    protected String color;
    protected String material;

    public Clothing(String name, double price, String size, String color, String material) {
        super(name, price);
        this.size = size;
        this.color = color;
        this.material = material;
    }

    public String toStringPrinted() {
        return ", clothing, " + size + ", " + color + ", " + material;
    }

    public String toCSV() {
        return super.toCSV() + toStringPrinted();
    }
}
