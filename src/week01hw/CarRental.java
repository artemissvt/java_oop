package week01hw;

import java.util.Scanner;

public class CarRental {
    public static void main(String[] args) {

        // we create a list for the cars contained on the car rental
        Car[] cars = new Car[5];
        cars[0] = new Car("YZN4261","volkswagen golf", 50000, false);
        cars[1] = new Car("ZAN7859", "opel astra gtc", 30000, true);
        cars[2] = new Car("VIM1023","toyota yaris", 10000, true);
        cars[3] = new Car("VAE9867","mazda 6", 60000, false);
        cars[4] = new Car("OME6859","volkswagen polo", 10000, true);

        Scanner in = new Scanner(System.in);
        int choice = 0;

        // we create the menu of the possible options the user has
        System.out.println("Welcome to the Car Rental. Please choose one of the following options: ");
        System.out.println("1. Rent a car");
        System.out.println("2. Return a car");
        System.out.println("3. Display all cars");
        System.out.println("4. Exit");
        System.out.println("Choose a number: ");
        choice = in.nextInt();

        switch (choice) {

            // first option is rent a car
            case 1:
                Scanner in1 = new Scanner(System.in);
                System.out.println("Please enter the plate number of the car you want to rent: ");
                String carChoice = in1.nextLine();

                // usign a for loop to check car plate number
                for (int i = 0; i < cars.length; i++) {
                    if (cars[i].carplatenum.equalsIgnoreCase(carChoice)) {
                        carChoice += cars[i].carplatenum + "\n";
                        if (cars[i].carstatus == true) {
                            System.out.println("Car is available");
                            Scanner in2 = new Scanner(System.in);
                            System.out.println("Do you want rent this car? (Y/N): ");
                            String rent = in2.nextLine();

                            switch (rent) {
                                case "Y":
                                    System.out.println("Car is now rented!");
                                    cars[i].carstatus = false;
                                    break;
                                case "N":
                                    System.out.println("Goodbye!");
                                    break;
                            }
                        } else {
                            System.out.println("Car is not available");
                            break;
                        }
                    }
                }

            // second option is return a car
            case 2:
                Scanner in3 = new Scanner(System.in);
                System.out.println("Please enter the plate number of the car you want to return: ");
                String carReturn = in3.nextLine();

                // again a for loop to check for the plate's number
                for (int i = 0; i < cars.length; i++) {
                    if (cars[i].carplatenum.equalsIgnoreCase(carReturn)) {
                        carReturn += cars[i].carplatenum + "\n";
                        if (cars[i].carstatus == false) {
                            System.out.println("Do you want to return this car? (Y/N): ");
                            String returnCar = in3.nextLine();

                            switch (returnCar) {
                                case "Y":
                                    Scanner in5 = new Scanner(System.in);
                                    System.out.println("Please type the kilometres the car now has: ");
                                    int kilometres = in5.nextInt();
                                    cars[i].carkm = kilometres;
                                    System.out.println("Car is now returned!");
                                    cars[i].carstatus = true;
                                    break;
                                case "N":
                                    System.out.println("Goodbye!");
                                    break;
                            }
                        } else {
                            System.out.println("The car you are trying to return, is already returned.");
                        }
                    }
                }

            // third option is display all of the cars
            case 3:
                System.out.println("Catalogue with all of the cars: ");
                for (int i = 0; i < cars.length; i++) {
                    System.out.println(
                            "Car model: " + cars[i].carmodel + ", "+
                            "plate number: " + cars[i].carplatenum + ", " +
                            "kilometres: " + cars[i].carkm + ", "+
                            "available: " + (cars[i].carstatus ? "Yes" : "No"));
                }

            // forth option is exit
            case 4:
                System.out.println("Exiting.");
        }
    }
}
