package dicegame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Maria Bartoszuk, w1510769
 */

/**
 * Represents the updates in scores for all the players.
 */
public class ScoreUpdate implements ActionListener {
    
    GameWindow game;
    GameState humanState;
    GameState computerState;

    public ScoreUpdate(GameWindow game, GameState humanState, GameState computerState) {  
        this.game = game;
        this.humanState = humanState;
        this.computerState = computerState; 
    }

    /**
     * Updates the scores for both players and refreshes the interface to
     * make sure the scores are displayed correctly.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        this.humanState.updateCurrentScore();
        this.computerState.updateCurrentScore();
        
        this.game.refreshInterface();
    }
}
