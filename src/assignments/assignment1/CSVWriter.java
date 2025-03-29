package assignments.assignment1;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVWriter {
    public static void appendToCSV(String filePath, List<Products> products) {
        try (FileWriter writer = new FileWriter(filePath, true)) {
            for (Products p : products) {
                writer.write(p.toCSV() + "\n");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
