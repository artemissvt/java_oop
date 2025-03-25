package week04hw;

import java.util.Scanner;

public class CoffeeShopMenu {
    public static void main(String[] args) {
        ReadOrder readOrder = new ReadOrder();
        Scanner input = new Scanner(System.in);

        int choice = 0;
        do {
            System.out.println("Welcome to Coffee Shop!");
            System.out.println("1. Add Order");
            System.out.println("2. Search Order by date");
            System.out.println("3. View total amount of a customer");
            choice = input.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Type your name: ");
                    String name = input.next();
                    input.nextLine();
                    System.out.println("Type the price of the product in euros: ");
                    double price = input.nextDouble();
                    input.nextLine();
                    System.out.println("Type the current date in form of yyyy/mm/dd: ");
                    String date = input.next();
                    input.nextLine();
                    readOrder.addOrder(name, price, date);
                    break;
                case 2:
                    System.out.println("Enter the date of the order you want to search: ");
                    input.nextLine();
                    String datel = input.nextLine();
                    readOrder.searchOrdersByDate(datel);
                    break;
                case 3:
                    System.out.println("Do you want to search the csv files or the database for this info? Please choose database/csv: ");
                    String preference2 = input.nextLine();
                    switch (preference2) {
                        case "csv":
                            System.out.println("Type the name of the customer you want the amount of: ");
                            String namec = input.nextLine();
                            input.nextLine();
                            readOrder.viewTotalAmountByCustomer(namec);
                    }


            } break;
        } while (choice != 0);
    }

}
