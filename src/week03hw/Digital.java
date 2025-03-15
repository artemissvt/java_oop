package week03hw;

public class Digital extends Book {
    protected int kbsize;

    public Digital(int ISBN, String title, String author, double price, int kbsize) {
        super(ISBN, title, author, price);
        this.kbsize = kbsize;
    }

    public String toStringPrinted() {
        return "Book size in kb: " + kbsize;
    }

    public String toString() {
        return super.toString() + toStringPrinted();
    }
}
