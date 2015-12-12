package dicegame;

/**
 * @author Maria Bartoszuk, w1510769
 */

/**
 * Represents state of the game for a single player and contains all the logic
 * (game rules) for state changes (rolls, re-rolls, keeping the dice, etc).
 */
public class GameState {
    
    /** How many rolls are allowed (first roll + re-rolls) in a single turn. */
    public static final int MAXROLLS_DURING_GAME = 3;
    public static final int MAXROLLS_WHEN_TIE = 1;
    
    /** Current score of the player tracked by this state. */
    int currentScore = 111;
    
    /** Which die faces the player has displayed at the moment. */
    Die[] currentDice = new Die[Dice.DICENUMBER];
    
    /**
     * Number of rolls (including first roll and re-rolls) that happened
     * in the current turn.
     */
    int howManyRolls = 0;
    
    /** How many rolls (including re-rolls) each player has. */
    int maxRolls = MAXROLLS_DURING_GAME;
    
    /**
     * Whether to keep a die face during next re-rolls or not.
     *
     * Each index in this array corresponds to a position of a die. If the array
     * holds true value at this index, the corresponding die should be kept,
     * i.e. not re-rolled.
     */
    boolean[] keepDecision = new boolean[Dice.DICENUMBER];
    
    /**
     * If this is a human game state, we notify computer player to act
     * on certain actions of the human. For example to finish the turn when
     * human updates the score.
     */
    ComputerPlayer computer = null;
    
    public void setComputer(ComputerPlayer computer) {
        this.computer = computer;
    }
    
    /** The dice currently displayed for this player. */
    public Die[] getCurrentDice() {
        return this.currentDice;
    }
    
    /**
     * Roll all dice (if called the first time in the current turn)
     * or re-roll the dice that are not kept (if called more times during
     * a turn).
     */
    public void rollDice() {
        Die[] newRoll = new Dice().roll();
        
        if (howManyRolls < getMaxRolls()) {
            for (int i = 0; i < newRoll.length; i++) {
                if (isDiceKept(i) == false) {
                    this.currentDice[i] = newRoll[i];
                }
            }
            this.howManyRolls = howManyRolls + 1;
            
            if (howManyRolls == getMaxRolls()) {
                this.updateCurrentScore();
            }
        }
    }
    
    /**
     * Returns the current score for the specific player.
     */
    public int getCurrentScore() {
        return this.currentScore;
    }
    
    /**
     * Updates the current score by the new dice values.
     */
    public void updateCurrentScore() {
        if (howManyRolls > 0) {
            if (computer != null) {
                computer.completeTurn();
            }
        
            for(Die die : currentDice) {
                this.currentScore = this.currentScore + die.getValue();
            }
            howManyRolls = 0;

            for (int i = 0; i < 5; i++) {
                dontKeepDice(i);
            }
        }
    }
    
    /**
     * Notes whether the die is kept (should not be re-rolled during
     * the re-roll).
     */
    public boolean isDiceKept(int dieIndex) {
        return this.keepDecision[dieIndex];
    }
    
    /**
     * Lets the player to keep the dice in the re-rolls if checked.
     */
    public void keepDice(int dieIndex) {
        if (howManyRolls > 0) {
            this.keepDecision[dieIndex] = true;
        }
    }
    
    /**
     * Lets the player to re-roll the dice if unchecked.
     */
    public void dontKeepDice(int dieIndex) {
        this.keepDecision[dieIndex] = false;
    }
    
    public boolean alreadyRolled() {
        if (howManyRolls > 0) {
            return true;
        }
        return false;
    }
    
    public int getMaxRolls() {
        return maxRolls;
    }
    
    public void tie() {
        this.maxRolls = MAXROLLS_WHEN_TIE;
    }
}
