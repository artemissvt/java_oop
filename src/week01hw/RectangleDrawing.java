package week01hw;

import java.util.Scanner;

public class RectangleDrawing {

    // we create a main function to run our program
    public static void main(String[] args) {

        // we create two variables for height and width
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter height: ");
        int height = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter width: ");
        int width = Integer.parseInt(scanner.nextLine());

        // we create a for loop to print our *
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (i == 0 || i == height - 1 || j == 0 || j == width - 1) {
                        System.out.print("* ");
                    } else {
                        System.out.print("  ");
                    }
                }
                System.out.println();
            }

    }
}
