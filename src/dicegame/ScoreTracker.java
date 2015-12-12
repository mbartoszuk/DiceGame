package dicegame;

/**
 * @author Maria Bartoszuk, w1510769
 */

public class ScoreTracker {
    
    GameState human;
    GameState computer;
    int targetValue = 101;
    int humanScore = 0;
    int computerScore = 0;
    
    public ScoreTracker(GameState human, GameState computer) {
        this.human = human;
        this.computer = computer;
    }
    
    public void updatePoints() {
        humanScore = human.getCurrentScore();
        computerScore = computer.getCurrentScore();
        if (humanScore == computerScore && humanScore > targetValue) {
            human.tie();
            computer.tie();
        }
    }
    
    public Player getWinner() {
        if (humanScore > targetValue && humanScore > computerScore) {
            return Player.HUMAN;
        } else if (computerScore > targetValue && computerScore > humanScore) {
            return Player.COMPUTER;
        } else {
            return null;
        }
    }
}
