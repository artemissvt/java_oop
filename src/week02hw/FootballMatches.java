package week02hw;

public class FootballMatches {
    private int matchId;
    private String homeTeam;
    private String awayTeam;
    private int homeTeamScore;
    private int awayTeamScore;


    public FootballMatches(int matchId, String homeTeam, String awayTeam, int homeTeamScore, int awayTeamScore) {
        this.matchId = matchId;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeTeamScore = homeTeamScore;
        this.awayTeamScore = awayTeamScore;
    }


    public String toString() {
        return "Match ID: " + matchId + "\n" + "Home Team: " + homeTeam + "\n" + "Away Team: " + awayTeam + "\n" + "Score: " + homeTeamScore + " - " + awayTeamScore + "\n";
    }

    // JUST FOR TESTING
    public static void main(String[] args) {
        FootballMatches a = new FootballMatches(1, "Manchester United", "Tottenham", 2, 3);
        System.out.println(a);
    }

    public int getMatchId() {
        return matchId;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public int getHomeScore() {
        return homeTeamScore;
    }

    public int getAwayScore() {
        return awayTeamScore;
    }

}