package dicegame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Maria Bartoszuk, w1510769
 */

public class Thrower implements ActionListener {
    
    DiceGame game;
    Dice dice;
    
    public Thrower(DiceGame game, Dice dice) {
        
        this.game = game;
        this.dice = dice;
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        Die[] humanRolledDice = dice.roll();
        game.setDieFaces(humanRolledDice, Player.HUMAN);
        Die[] computerRolledDice = dice.roll();
        game.setDieFaces(computerRolledDice, Player.COMPUTER);
        
    }
    
}
