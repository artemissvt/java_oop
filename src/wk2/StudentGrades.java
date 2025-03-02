package wk2;

import java.util.InputMismatchException;
import java.util.Scanner;

public class StudentGrades {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        boolean ok = false;

        int n = 0;
        do {
            try {
                System.out.println("Enter the number of the course: ");
                n = in.nextInt();
                ok = true;
            } catch (InputMismatchException e) {
                System.out.println("Please enter a number:");
                in.nextLine();
            }
        } while (!ok);
        System.out.println("You typed: " + n);
    }

}
