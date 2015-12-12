package dicegame;

/**
 * @author Maria
 */

public class GameState {
    
    int currentScore = 0;
    Die[] currentDice = new Die[5];
    boolean alreadyScored = true;
    
    public Die[] getCurrentDice() {
        return this.currentDice;
    }
    
    public void setCurrentDice(Die[] newRoll) {
        if (alreadyScored == true) {
            this.alreadyScored = false;
            this.currentDice = newRoll;
        }
    }
    
    public int getCurrentScore() {
        return this.currentScore;
    }
    
    public void updateCurrentScore() {
        if (alreadyScored == false) {
            for(Die die : currentDice) {
                this.currentScore = this.currentScore + die.getValue();
            }
        }
        alreadyScored = true;
    }
    
}
