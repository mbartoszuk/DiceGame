package dicegame;

import java.util.Random;
import javax.swing.ImageIcon;

/**
 * @author Maria Bartoszuk, w1510769
 */

public class Dice {
    
    Die die1 = new Die();
    Die die2 = new Die();
    Die die3 = new Die();
    Die die4 = new Die();
    Die die5 = new Die();
    Die die6 = new Die();
    Die[] allDice = new Die[]{die1, die2, die3, die4, die5, die6};
    Random roll = new Random();
    
    public Dice() {
        
        die1.setValue(1);
        die1.setDieImage(new ImageIcon(getClass().getResource("/die1.png")));
        die2.setValue(2);
        die2.setDieImage(new ImageIcon(getClass().getResource("/die2.png")));
        die3.setValue(3);
        die3.setDieImage(new ImageIcon(getClass().getResource("/die3.png")));
        die4.setValue(4);
        die4.setDieImage(new ImageIcon(getClass().getResource("/die4.png")));
        die5.setValue(5);
        die5.setDieImage(new ImageIcon(getClass().getResource("/die5.png")));
        die6.setValue(6);
        die6.setDieImage(new ImageIcon(getClass().getResource("/die6.png")));
        
    }
    
    public Die[] roll() {
        
        Die[] rollingDice = new Die[5];
        
        for (int i = 0; i < 5; i++) {
            rollingDice[i] = allDice[roll.nextInt(6)];
        }
        
        return rollingDice;
        
    }
    
}
