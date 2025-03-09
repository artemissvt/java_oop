package week03hw;

import java.util.ArrayList;

public class MatchRequest {
    private ArrayList<FootballMatches> requests;
    public MatchRequest() {
        requests = new ArrayList<>();
    }


    public void addGoal(String teamName, int teamWins, int teamLosses, int teamDraws) {
        FootballMatches request = new FootballMatches(teamName, teamWins, teamLosses, teamDraws);
        requests.add(request);
    }

    public void displayMatches() {
        for (FootballMatches request : requests) {
            System.out.println(request);
        }
    }

    public void addFootballMatches(FootballMatches request) {
        requests.add(request);
    }

    public ArrayList<FootballMatches> getRequests() {
        return requests;
    }

    public static void main(String[] args) {
        MatchRequest queue = new MatchRequest();
        queue.addGoal("Milan", 2, 2, 1);
        queue.addGoal("Roma", 2, 1, 2);
        queue.displayMatches();
    }
}
