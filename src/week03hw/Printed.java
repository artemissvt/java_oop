package week03hw;

public class Printed extends Book {
    protected String type;
    protected int numOfPages;
    protected String publisher;
    protected int availableCopies;

    public Printed(int ISBN, String title, String author, double price, String type, int numOfPages, String publisher, int availableCopies) {
        super(ISBN, title, author, price);
        this.type = type;
        this.numOfPages = numOfPages;
        this.publisher = publisher;
        this.availableCopies = availableCopies;

    }

    public String toStringPrinted() {
        return "Book type: " + type + ", Number of pages: " + numOfPages + ", Publisher: " + publisher + ", Available copies: " + availableCopies;
    }

    public String toString() {
        return super.toString() + toStringPrinted();
    }

}
