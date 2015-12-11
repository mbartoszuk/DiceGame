package dicegame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBox;

/**
 * @author Maria Bartoszuk, w1510769
 */

class CheckBoxListener implements ActionListener {
    
    Player player;
    int dieNumber;
    EventQueue events;

    public CheckBoxListener(Player player, int dieNumber, EventQueue events) {
        
        this.player = player;
        this.dieNumber = dieNumber;
        this.events = events;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        JCheckBox checkBox = (JCheckBox) e.getSource();
        events.publish(new DieKeptEvent(player, dieNumber, checkBox.isSelected()));
    }
    
}
