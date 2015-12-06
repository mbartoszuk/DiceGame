/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dicegame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBox;

/**
 *
 * @author Maria
 */
class CheckBoxListener implements ActionListener {
    Player player;
    int dieNumber;

    public CheckBoxListener(Player player, int dieNumber) {
        this.player = player;
        this.dieNumber = dieNumber;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JCheckBox checkBox = (JCheckBox) e.getSource();
        if (checkBox.isSelected()) {
            System.out.println("YES!" + player + dieNumber);
        } else {
            System.out.println("NO!" + player + dieNumber);
        }
    }
    
}
