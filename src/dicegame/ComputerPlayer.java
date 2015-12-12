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
        for (int rollNumber = 1; rollNumber < computerState.getMaxRolls(); rollNumber++) {
            ArrayList<Integer> toKeep = diceToKeep(rollNumber);
            keepOnly(toKeep);
            computerState.rollDice();
            window.refreshInterface();
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
    
    /** Instructs the game to keep only the dice with given indexes. */
    public ArrayList<Integer> diceToKeep(int rollNumber) {
        ArrayList<Integer> toKeep = new ArrayList<>();
        for (int i = 0; i < Dice.DICENUMBER; i++) {
            if (computerState.getCurrentDice()[i].getValue() >= lowestDieValueToKeep(rollNumber)) {
                toKeep.add(i);
            }
        }
        return toKeep;
    }
    
    /**
     * Decides based on the re-roll turn, which dice to keep and which roll again.
     * Key method in the computer strategy.
     */
    public int lowestDieValueToKeep(int rollNumber) {
        int rerollsLeft = computerState.getMaxRolls() - rollNumber;
        //There are always 2 or 1 rerolls left.
        if (rerollsLeft == 2) {
            return 5;
        } else { //rerollsLeft == 1
            return 4; 
        }
    }
    
    /**
     * Strategy Explanation:
     * 
     * My strategy for the computer player is that I want him to keep all dice 5 and 6
     * for the first re-roll and 4, 5 and 6 for the second re-roll in every turn. Since
     * 5 and 6 are the highest numbers, I want the computer to always keep them. However,
     * when there are 2 more re-rolls left, he can risk 4, 3, 2 and 1s, and try to get a
     * better score for them. Then for the last re-roll I want him to take a smaller risk
     * and only re-roll 1, 2 and 3s, since I don't want the 4 to get changed for something
     * worse.
     * 
     * This is the strategy that I use for playing this game, and I think it is optimal
     * because it lets you take a risk, but at the same time stay safe with the dice of
     * higher value. The main advantage of it is that I keep the high value dice, which
     * can not get much higher anyway, and I try to get a better value for the low value
     * dice, since they can not get much worse anyway.
     * 
     * The main disadvantage of this strategy is that in a short game, it may
     * not be optimal to take big risks to try to get highest values. There is only a few
     * re-rolls available, so there is a smaller probability of getting the highest dice
     * values. If the game had longer turns, for example 100 re-rolls would be available,
     * this strategy would work better, allowing the computer to take big risks at the
     * beginning and play safe near the end.
     * 
     * However, if there is a high target value set for the game, it there is also a
     * good amount of opportunities to take risks.
     */
}
