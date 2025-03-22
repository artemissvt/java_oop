package week04hw;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class ReadOrder {
    private ArrayList<Order> orders;
    private static final String fileName = "orders.csv";

    public ReadOrder() {
        orders = new ArrayList<>();
        loadOrders();
    }

    private void loadOrders() {
        try {
            File file = new File(fileName);
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String name = parts[0].trim();
                    double price = Double.parseDouble(parts[1].trim());
                    String date = parts[2].trim();
                    orders.add(new Order(name, price, date));
                }
            }
            sc.close();
        } catch (FileNotFoundException fe) {
            System.out.println(fe.getMessage());
        }
    }

    public void addOrder(String name, double price, String date) {
        Order newOrder = new Order(name, price, date);
        orders.add(newOrder);

        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write(name + "," + price + "," + date + "\n");
            System.out.println("Order added successfully!");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void searchOrdersByDate(String searchDate) {
        boolean found = false;
        for (Order order : orders) {
            if (order.getDate().equals(searchDate)) {
                System.out.println("customer name: " + order.name + ", price: " + order.price + ", date: " + order.date);
                found = true;
            } else {
                System.out.println("No orders found for this date.");
            }
        }
    }

    public void viewTotalAmountByCustomer(String name) {
        double total = 0;
        boolean found = false;
        for (Order order : orders) {
            if (Objects.equals(order.name, name)) {
                total += order.price;
                found = true;
            }
        }
        if (found) {
            System.out.println(name + " has spent a total of $" + total);
        } else {
            System.out.println("No orders found for this customer.");
        }
    }
}
