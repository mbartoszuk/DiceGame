package dicegame;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author Maria Bartoszuk, w1510769
 */

/**
 * Terminates the program run when the window frame is closed by the user.
 */
class WindowClosesApplication extends WindowAdapter {
    @Override
    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }   
}
