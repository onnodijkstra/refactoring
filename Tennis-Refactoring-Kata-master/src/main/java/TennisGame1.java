
public class TennisGame1 implements TennisGame {

    public static final String SEPARATOR = "-";
    public static final String ALL = "All";
    private int m_score1 = 0;
    private int m_score2 = 0;
    private String player1Name;
    private String player2Name;


    public TennisGame1(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName == "player1") {
            m_score1 += 1;
        }
        else {
            m_score2 += 1;
        }
    }

    public String getScore() {
        if (inWarmingUpOrScoresAreEqual()) {
            return this.getCompoundScoreNames(m_score1, m_score2);
        } else {
            if (m_score1 == m_score2 + 1) {
                return "Advantage player1";
            } else if (m_score2 == m_score1 + 1) {
                return "Advantage player2";
            } else if ((m_score1 - m_score2) >= 2) {
                return "Win for player1";
            }
            return "Win for player2";
        }
    }

    private boolean inWarmingUpOrScoresAreEqual() {
        return Math.max(m_score1, m_score2) < 4 || m_score1 == m_score2;
    }

    public String getCompoundScoreNames(int score1, int score2) {
        if (score1 == score2) {
            if(score1 < 3) {
                return ScoreName.getScoreName(score1) + SEPARATOR + ALL;
            }
            return ScoreName.Deuce.getNormalizedName();
        }
        return ScoreName.getScoreName(score1) + SEPARATOR + ScoreName.getScoreName(score2);
    }


    enum ScoreName {
        Deuce(-1), Love(0), Fifteen(1), Thirty(2), Forty(3);

        private int score;

        public int getScore() {
            return score;
        }

        ScoreName(int i) {
            this.score = i;
        }

        public static String getScoreName(int score) {
            for (ScoreName scoreName : ScoreName.values()) {
                if (score == scoreName.getScore()) {
                    return scoreName.getNormalizedName();
                }
            }
            return ScoreName.Deuce.getNormalizedName();
        }

        public String getNormalizedName() {
            return this.name().substring(0, 1).toUpperCase() + this.name().substring(1).toLowerCase();
        }
    }

}
