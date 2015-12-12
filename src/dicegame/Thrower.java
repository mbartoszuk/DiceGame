package dicegame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Maria Bartoszuk, w1510769
 */

public class Thrower implements ActionListener {
    
    DiceGame game;
    GameState humanState;
    GameState computerState;
    
    public Thrower(DiceGame game, GameState humanState, GameState computerState) {
        
        this.game = game;
        this.humanState = humanState;
        this.computerState = computerState;
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        Dice dice = new Dice();
        Die[] humanRolledDice = dice.roll();
        humanState.setCurrentDice(humanRolledDice);
        Die[] computerRolledDice = dice.roll();
        computerState.setCurrentDice(computerRolledDice);
        game.refreshInterface();
        
    }
    
}
