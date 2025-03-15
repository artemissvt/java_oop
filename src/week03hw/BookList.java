package week03hw;

import java.util.ArrayList;

public class BookList {
    private ArrayList<Book> books;

    public BookList() {
        books = new ArrayList<>();
    }

    public void add(Book book) {
        books.add(book);
    }

    public void printBookStore() {
        for (Book book : books) {
            System.out.println(book);
        }
    }
}
