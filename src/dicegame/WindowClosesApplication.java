package dicegame;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Maria Bartoszuk, w1510769
 */

/**
 * Terminates the program run when the window frame is closed by the user.
 */
class WindowClosesApplication extends WindowAdapter {
    
    ScoreTracker tracker;
    
    WindowClosesApplication(ScoreTracker tracker) {
        this.tracker = tracker;
    }
    
    /** Exits the system when the window is closed.
     * Saves the won games for all the players.
     */
    @Override
    public void windowClosing(WindowEvent e) {
        tracker.save();
        System.exit(0);
    }
}
