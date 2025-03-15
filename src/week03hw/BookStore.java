package week03hw;

public class BookStore {
    public static void main(String[] args) {
        BookList books = new BookList();

        books.add(new Printed(12846534, "1984", "George Orwell",
                10.12, "Hardcover", 123, "Penguin", 34));

        books.add(new Digital(46371869, "The catcher in the rye", "J.D. Salinger",
                12.00, 8000));
        books.add(new Audiobook(67483905, "The virgin suicides", "Jeffrey Eugenides",
                15.45, 190, "John Doe"));

        books.printBookStore();
    }
}
