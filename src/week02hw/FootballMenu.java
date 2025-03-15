package week02hw;

import java.util.Scanner;

public class FootballMenu {
    public static void main(String[] args) {
        MatchRequest matchList= new MatchRequest();
        Scanner input = new Scanner(System.in);


        int choice = 0;
        do {
            System.out.println("Football Menu, please choose");
            System.out.println("1. Add game");
            System.out.println("2. Team performance");
            System.out.println("0. Exit");
            System.out.println("Enter your choice -->");
            choice = input.nextInt();

            switch (choice) {
                case 1:
                    for (int i = 0; i < 2; i++) {
                        System.out.println("Please type" + (i+1) + " team name: " );
                        String teamName = input.next();
                        input.nextLine();

                        System.out.println("Please type" + teamName + " goals scored: ");
                        int goals = input.nextInt();
                        input.nextLine();

                    }
            }
        } while (choice !=0);
    }
}
