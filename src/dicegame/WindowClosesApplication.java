package dicegame;

/**
 * @author Maria Bartoszuk, w1510769
 */

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author Maria Bartoszuk, w1510769
 */
class WindowClosesApplication extends WindowAdapter {

    @Override
    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }
    
}
