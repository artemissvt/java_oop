package week02hw;

import java.util.ArrayList;

public class MatchRequest {
    private ArrayList<FootballMatches> matches;

    public MatchRequest() {
        matches = new ArrayList<>();
    }

    public void addFootballMatches(int matchId, String homeTeam, String awayTeam, int homeScore, int awayScore) {
        FootballMatches match = new FootballMatches(matchId, homeTeam, awayTeam, homeScore, awayScore);
        matches.add(match);
    }

    public void printMatches(int matchId) {
        for (FootballMatches match : matches) {
            if (match.getMatchId() == matchId) {
                System.out.println(match);
                break;
            }
        }
    }

    public void teamPerformance(String teamName) {
        int wins = 0, losses = 0, draws = 0;

        for (FootballMatches match : matches) {
            if (match.getHomeTeam().equals(teamName)) {
                if (match.getHomeScore() > match.getAwayScore()) {
                    wins++;
                } else if (match.getAwayScore() > match.getHomeScore()) {
                    losses++;
                } else {
                    draws++;
                }
            }
        }
        System.out.println("Wins: " + wins + "\n" + "Losses: " + losses + "\n" + "Draws: " + draws);
    }

    // JUST FOR TESTING
    public static void main(String[] args) {
        MatchRequest orderList = new MatchRequest();
        orderList.addFootballMatches(1, "Manchester United", "Tottenham", 2, 3);
        orderList.addFootballMatches(2, "NewCastle", "Manchester City", 1,3);
        orderList.addFootballMatches(3, "Manchester City", "Liverpool",3,1);

        orderList.printMatches(2);
    }
}
