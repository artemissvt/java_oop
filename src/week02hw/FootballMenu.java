package week02hw;

import java.util.Scanner;

public class FootballMenu {
    public static void main(String[] args) {
        MatchRequest matchList= new MatchRequest();
        Scanner input = new Scanner(System.in);

        // create the menu
        int choice = 0;
        do {
            System.out.println("Football Menu, please choose");
            System.out.println("1. Add game");
            System.out.println("2. Team performance");
            System.out.println("0. Exit");
            System.out.println("Enter your choice -->");
            choice = input.nextInt();


            switch (choice) {

                // option one is to add a game
                case 1:
                    System.out.println("Enter the match id: ");
                    int matchId = input.nextInt();
                    input.nextLine();
                    System.out.println("Enter the name of the Home Team: ");
                    String homeTeam = input.next();
                    input.nextLine();
                    System.out.println("Enter the name of the Away Team: ");
                    String awayTeam = input.next();
                    input.nextLine();
                    System.out.println("Enter the goals scored by the Home Team: ");
                    int homeTeamScore = input.nextInt();
                    input.nextLine();
                    System.out.println("Enter the goals scored by the Away Team: ");
                    int awayTeamScore = input.nextInt();
                    matchList.addFootballMatches(matchId, homeTeam, awayTeam, homeTeamScore, awayTeamScore);
                    break;

                // option two is to check a teams performance
                case 2:
                    System.out.println("Enter the name of the team you want to search: ");
                    input.nextLine();
                    String teamName = input.nextLine();
                    matchList.teamPerformance(teamName);
                    break;

                // option 0 terminates
                case 0:
                    System.out.println("Goodbye");
                    break;
            } break;
        } while (choice !=0);
    }
}
