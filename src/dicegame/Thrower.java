package dicegame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Maria Bartoszuk, w1510769
 */

/**
 * Represents the action of throwing the dice by all the players.
 */
public class Thrower implements ActionListener {
    
    GameWindow game;
    GameState humanState;
    GameState computerState;
    
    public Thrower(GameWindow game, GameState humanState, GameState computerState) {
        this.game = game;
        this.humanState = humanState;
        this.computerState = computerState;        
    }

    /**
     * Performs the action of rolling the dice for all the players
     * and refreshing the interface of the game to make sure correct
     * dice are displayed.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        humanState.rollDice();
        computerState.rollDice();
        game.refreshInterface();
    }  
}
