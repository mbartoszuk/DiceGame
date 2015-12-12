package dicegame;

import java.util.Random;
import javax.swing.ImageIcon;

/**
 * @author Maria Bartoszuk, w1510769
 */

public class Dice {
    
    public static final int DICENUMBER = 5;
    
    /**
     * Every die attribute represents single die face and is used whenever
     * this die face is a result of a particular roll.
     */
    Die die1 = new Die();
    Die die2 = new Die();
    Die die3 = new Die();
    Die die4 = new Die();
    Die die5 = new Die();
    Die die6 = new Die();
    
    /**
     * For convenience, we put each die in the array so we can find them
     * by index later.
     */
    Die[] allDice = new Die[]{die1, die2, die3, die4, die5, die6};
    
    /** Random number generator. */
    Random roll = new Random();
    
    /**
     * Initialize each die with its value and corresponding image to display
     * in the interface.
     */
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
    
    /** Generate 5 random die faces. */
    public Die[] roll() {
        Die[] rollingDice = new Die[DICENUMBER];
        
        for (int i = 0; i < DICENUMBER; i++) {
            int randomFaceIndex = roll.nextInt(Die.FACESNUMBER);
            rollingDice[i] = allDice[randomFaceIndex];
        }
        
        return rollingDice;
    }
}
