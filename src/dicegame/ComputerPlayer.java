package dicegame;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author Maria Bartoszuk, w1510769
 */

public class ComputerPlayer {
    
    Random random = new Random();
    GameState computerState;
    GameWindow window;
    
    ComputerPlayer(GameState computerState, GameWindow window) {
        this.computerState = computerState;
        this.window = window;
    }
    
    /** Lets the computer player know that he can roll his dice now. */
    public void rollDice() {
        computerState.rollDice();
    }
    
    /**
     * Lets the computer player know that he follow up his turn,
     * perhaps with re-rolls.
     */
    public void completeTurn() {
        // rollNumber starts with 1 because we have already rolled once.
        for (int rollNumber = 1; rollNumber < GameState.MAXROLLS; rollNumber++) {
            if (shouldReroll()) {
                ArrayList<Integer> toKeep = diceToKeep();
                keepOnly(toKeep);
                computerState.rollDice();
                window.refreshInterface();
            }
        }
        computerState.updateCurrentScore();
    }

    /** Instructs the game to keep only the dice with given indexes. */
    private void keepOnly(ArrayList<Integer> dieIndexToKeep) {
        for (int dieIndex = 0; dieIndex < Dice.DICENUMBER; dieIndex++) {
            boolean shouldKeepThisDie = dieIndexToKeep.contains(dieIndex);
            if (shouldKeepThisDie == true) {
                computerState.keepDice(dieIndex);
            } else {
                computerState.dontKeepDice(dieIndex);
            }
        }
    }
    
    public boolean shouldReroll() {
        return random.nextBoolean();
    }
    
    public ArrayList<Integer> diceToKeep() {
        ArrayList<Integer> toKeep = new ArrayList<>();
        for (int i = 0; i < Dice.DICENUMBER; i++) {
            if (random.nextBoolean() == true) {
                toKeep.add(i);
            }
        }
        return toKeep;
    }
}
