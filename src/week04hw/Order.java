package week04hw;

public class Order {

    // initialize variables
    String name;
    double price;
    String date;

    public Order(String name, double price, String date) {
        this.name = name;
        this.price = price;
        this.date = date;
    }
    public String getDate() {
        return date;
    }
}
