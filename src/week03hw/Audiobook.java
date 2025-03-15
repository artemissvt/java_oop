package week03hw;

public class Audiobook extends Book {
    protected int durationMin;
    protected String narrator;

    public Audiobook(int ISBN, String title, String author, double price, int durationMin, String narrator) {
        super(ISBN, title, author, price);
        this.durationMin = durationMin;
        this.narrator = narrator;
    }

    public String toStringPrinted() {
        return "Audiobook duration: " + durationMin + "min, Narrator: " + narrator;
    }

    public String toString() {
        return super.toString() + toStringPrinted();
    }
}


