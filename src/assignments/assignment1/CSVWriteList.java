package assignments.assignment1;

import java.util.ArrayList;
import java.util.List;

public class CSVWriteList {
    public static void main(String[] args) {
        List<Products> products = new ArrayList<>();
        products.add(new Clothing("shirt", 30.00, "medium", "white", "silk"));
        products.add(new Electronics("laptop", 999.99, "dell", 2));

        String filePath = "products.csv";
        CSVWriter.appendToCSV(filePath, products);
    }
}
