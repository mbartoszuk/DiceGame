package dicegame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBox;

/**
 * @author Maria Bartoszuk, w1510769
 */

/**
 * Updates whether a specific die (for given player and given die index) is kept
 * (for re-rolls) when relevant check boxes are checked in the interface.
 */
public class DiceTracker implements ActionListener {
    
    GameWindow game;
    GameState state;
    int dieIndex;
    
    public DiceTracker(GameWindow game, GameState state, int dieIndex) {
        this.game = game;
        this.state = state;
        this.dieIndex = dieIndex;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JCheckBox checkbox = (JCheckBox) e.getSource();
        boolean selected = checkbox.isSelected();
        if (selected == true) {
            this.state.keepDice(dieIndex);
        } else {
            this.state.dontKeepDice(dieIndex);
        }
        this.game.refreshInterface();
    }
}
