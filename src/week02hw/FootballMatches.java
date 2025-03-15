package week02hw;

public class FootballMatches {
    private String teamName;
    private int teamWins;
    private int teamLosses;
    private int teamDraws;
    private boolean teamWon;

    public FootballMatches(String teamName, int teamWins, int teamLosses, int teamDraws) {
        this.teamName = teamName;
        this.teamWins = teamWins;
        this.teamLosses = teamLosses;
        this.teamDraws = teamDraws;
        this.teamWon = false;
    }

    public void wins(){
        this.teamWon = true;
    }

    /*public boolean isTeamWon(){
        return teamWon;
    }*/

    public String getTeamName() {
        return teamName;
    }

    public String toString() {
        return teamName + ", team wins: " + teamWins + ", team losses: " + teamLosses + ", team draws: " + teamDraws;
    }

    public static void main(String[] args) {
        FootballMatches request = new FootballMatches("Milan", 1, 2, 2);
        System.out.println(request);
    }
}