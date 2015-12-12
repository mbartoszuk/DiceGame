package dicegame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Maria
 */

public class ScoreUpdate implements ActionListener {
    
    DiceGame game;
    GameState humanState;
    GameState computerState;
    
    public ScoreUpdate(DiceGame game, GameState humanState, GameState computerState) {
        
        this.game = game;
        this.humanState = humanState;
        this.computerState = computerState;
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        this.humanState.updateCurrentScore();
        this.computerState.updateCurrentScore();
        
        this.game.refreshInterface();
    }
    
}
