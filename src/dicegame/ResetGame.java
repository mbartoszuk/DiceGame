package dicegame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Maria Bartoszuk, w1510769
 */

/**
 * Action Listener for New Game button. Resets all resetable objects
 * in the game.
 */
class ResetGame implements ActionListener {
    
    Reseter[] allReseters;

    public ResetGame(Reseter[] allReseters) {
        this.allReseters = allReseters;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (Reseter reseter: allReseters) {
            reseter.reset();
        }
    }
    
}
