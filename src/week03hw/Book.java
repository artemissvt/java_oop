package week03hw;

public class Book {
    protected int ISBN;
    protected String title;
    protected String author;
    protected double price;

    public Book(int ISBN, String title, String author, double price) {
        this.ISBN = ISBN;
        this.title = title;
        this.author = author;
        this.price = price;
    }

    public String toString() {
        return "Book ISBN: " + ISBN + ", Title: " + title + ", Author: " + author + ", Price: " + price;
    }
}
