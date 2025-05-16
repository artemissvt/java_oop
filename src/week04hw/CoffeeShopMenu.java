package week04hw;

import java.sql.*;
import java.util.Scanner;

public class CoffeeShopMenu {
    public static void main(String[] args) {
        ReadOrder readOrder = new ReadOrder();
        Scanner input = new Scanner(System.in);

        int choice = 0;
        do {

            // create menu
            System.out.println("Welcome to Coffee Shop!");
            System.out.println("1. Add Order");
            System.out.println("2. Search Order by date");
            System.out.println("3. View total amount of a customer");
            choice = input.nextInt();

            switch (choice) {

                //  add order
                case 1:
                    System.out.println("Type your name: ");
                    String name = input.next();
                    input.nextLine();

                    System.out.println("Type the price of the product in euros: ");
                    double price = input.nextDouble();
                    input.nextLine();

                    System.out.println("Type the current date in format yyyy-mm-dd: ");
                    String date = input.next(); // should be 'yyyy-mm-dd'
                    input.nextLine();

                    // add to database
                    try {
                        Connection conn = null;
                        String url = "jdbc:mysql://localhost:3306/coffeeshop";
                        String user = "coffeshop";
                        String password = "coffeshop";
                        conn = DriverManager.getConnection(url, user, password);

                        String query = "INSERT INTO userinfo (Username, Totalamount, Date) VALUES (?, ?, ?)";
                        PreparedStatement stmt = conn.prepareStatement(query);
                        stmt.setString(1, name);
                        stmt.setDouble(2, price);
                        stmt.setString(3, date);

                        int rowsInserted = stmt.executeUpdate();
                        if (rowsInserted > 0) {
                            System.out.println("Order saved successfully.");
                        }

                        stmt.close();
                        conn.close();
                    } catch (SQLException se) {
                        System.out.println("Database error: " + se.getMessage());
                    }
                    break;



                // search order by date
                case 2:
                    System.out.println("Enter the date of the order you want to search: ");
                    input.nextLine();
                    String datel = input.nextLine();
                    readOrder.searchOrdersByDate(datel);
                    break;

                // search the total amount of a certain customer
                case 3:
                    System.out.println("Do you want to search the csv files or the database for this info? Please choose database/csv: ");
                    input.nextLine();
                    String preference2 = input.nextLine();

                    switch (preference2) {
                        case "csv":
                            System.out.println("Type the name of the customer you want the amount of: ");
                            String namec = input.nextLine();
                            input.nextLine();
                            readOrder.viewTotalAmountByCustomer(namec);

                        case "database":
                            try {
                                Connection conn = null;
                                String url = "jdbc:mysql://localhost:3306/coffeeshop";
                                String user = "coffeshop";
                                String password = "coffeshop";
                                conn = DriverManager.getConnection(url, user, password);

                                Scanner username1 = new Scanner(System.in);
                                System.out.print("Enter username to search orders: ");
                                String username = input.nextLine();


                                String sql = "SELECT * FROM userinfo WHERE Username = ?";
                                PreparedStatement stmt = conn.prepareStatement(sql);
                                stmt.setString(1, username);
                                ResultSet rs = stmt.executeQuery();

                                while (rs.next()) {
                                    int id = rs.getInt("UserId");
                                    String username2 = rs.getString("Username");
                                    double Totalamount = rs.getDouble("Totalamount");
                                    Date Date = rs.getDate("Date");

                                    System.out.println("Order #" + id + ": " + "Total amount" + Totalamount + " Date " + Date);
                                }
                                rs.close();
                                stmt.close();


                            }
                            catch (SQLException se) {
                                System.out.println(se.getMessage());
                            }
                    }


            } break;
        } while (choice != 0);
    }

}
