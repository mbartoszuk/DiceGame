/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dicegame;

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
