package dicegame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DiceGame {
    
    EventQueue events = new EventQueue();

    public static void main(String[] args) {
        
        DiceGame game = new DiceGame();
        game.events.subscribe(new Observer(){

            @Override
            public boolean canHandle(Object event) {
                return event instanceof DieKeptEvent;
            }

            @Override
            public void handle(Object event) {
                DieKeptEvent e = (DieKeptEvent) event;
                System.out.println(e.player + " " + e.dieNumber);
            }
            
        });
        
        JFrame mainFrame = new JFrame("Dice Game");
        JPanel mainPanel = game.makeMainPanel();
        mainFrame.setContentPane(mainPanel);
        
        mainFrame.addWindowListener(new WindowClosesApplication());
        
        mainFrame.setSize(800, 600);
        mainFrame.setVisible(true);
    }

    private JPanel makeMainPanel() {
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBackground(Color.white);
        Player[] allPlayers = new Player[]{Player.COMPUTER, Player.HUMAN};
        for (int row = 0; row < allPlayers.length; row++) {
            
            //making the left column (player names)
            JLabel playerName = new JLabel();
            playerName.setText(allPlayers[row].name());
            
            //grid setting of the name cell
            GridBagConstraints playerNameConstraints = new GridBagConstraints();
            playerNameConstraints.insets = new Insets(10, 10, 10, 10);
            playerNameConstraints.weighty = 0.5;
            playerNameConstraints.fill = GridBagConstraints.VERTICAL;
            playerNameConstraints.gridx = 0;
            playerNameConstraints.gridy = row;
            
            mainPanel.add(playerName, playerNameConstraints);
            
            //making the right column (rows of dice)
            JPanel dieLayout = this.makeDiceRow(allPlayers[row]);
            
            //grid setting of the die cell
            GridBagConstraints dieLayoutConstraints = new GridBagConstraints();
            dieLayoutConstraints.weightx = 0.5;
            dieLayoutConstraints.weighty = 0.5;
            dieLayoutConstraints.fill = GridBagConstraints.HORIZONTAL;
            dieLayoutConstraints.gridx = 1;
            dieLayoutConstraints.gridy = row;

            mainPanel.add(dieLayout, dieLayoutConstraints);
        }
        return mainPanel;
    }

    //make a row of 5 dice with checkboxes
    private JPanel makeDiceRow(Player player) {
        
        JPanel dieLayout = new JPanel();
        dieLayout.setLayout(new FlowLayout());
        for (int i = 1; i <= 5; i++) {
            dieLayout.add(dieFace(player, i));
        }
        
        JLabel totalPlayerScore = new JLabel();
        totalPlayerScore.setText("Total score: 0 points");
        JPanel totalPlayerScoreContainer = new JPanel();
        totalPlayerScoreContainer.setLayout(new BoxLayout(totalPlayerScoreContainer, BoxLayout.X_AXIS));
        totalPlayerScoreContainer.add(Box.createHorizontalGlue());
        totalPlayerScoreContainer.add(totalPlayerScore);
        
        JPanel row = new JPanel();
        row.setLayout(new BoxLayout(row, BoxLayout.Y_AXIS));
        row.add(dieLayout);
        row.add(totalPlayerScoreContainer);
        
        return row;
    }
    
    //pair an individual die with its corresponding checkbox
    private JPanel dieFace(Player player, int dieNumber) {
        
        JCheckBox singleCheck = new JCheckBox();
        singleCheck.addActionListener(new CheckBoxListener(player, dieNumber, events));
        JPanel checkContainer = new JPanel();
        checkContainer.setLayout(new FlowLayout());
        checkContainer.add(singleCheck);
        
        JLabel singleDie = new JLabel();
        singleDie.setIcon(new ImageIcon(DiceGame.class.getResource("/die1.png")));
        JPanel dieContainer = new JPanel();
        dieContainer.setLayout(new FlowLayout());
        dieContainer.add(singleDie);
        
        JPanel die = new JPanel();
        die.setLayout(new BoxLayout(die, BoxLayout.Y_AXIS));
        die.add(checkContainer);
        die.add(dieContainer);
        return die;
    }
}
