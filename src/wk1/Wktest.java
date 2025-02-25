package wk1;

import java.util.Scanner;

public class Wktest {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Type an integer: ");
        int input = in.nextInt();
        //System.out.println(input);
        if (input > 10) {
            System.out.println("more than 10");
        } else if (input == 10) {
            System.out.println("equals to 10");
        } else {
            System.out.println("less than 10");
        }
    }
}


