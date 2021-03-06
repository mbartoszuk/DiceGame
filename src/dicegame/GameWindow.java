package dicegame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @author Maria Bartoszuk, w1510769
 */

/**
 * This class composes the interface that the user interacts with. It has different
 * parts to it which contain various components and place those components
 * in specific places on the interface.
 */
public class GameWindow implements Reseter {
    
    JLabel[] humanDice = new JLabel[5];
    JLabel[] computerDice = new JLabel[5];
    JLabel humanTotalScore;
    JLabel computerTotalScore;
    
    GameState humanState = new GameState();
    GameState computerState = new GameState();
    
    JCheckBox[] humanCheckBoxes = new JCheckBox[5];
    JCheckBox[] computerCheckBoxes = new JCheckBox[5];
    
    JLabel humanGamesWon;
    JLabel computerGamesWon;
    
    JFrame mainFrame;
    
    ComputerPlayer computer;
    
    ScoreTracker tracker = new ScoreTracker(humanState, computerState);

    GameWindow() {
        computer = new ComputerPlayer(computerState, this);
        humanState.setComputer(computer);
        tracker.load();
    }

    /** Main function of the whole program. */
    public static void main(String[] args) {
        GameWindow game = new GameWindow();
        game.showFrame();
        game.refreshInterface();
    }
    
    private void showFrame() {
        mainFrame = new JFrame("Dice Game");
        JPanel mainPanel = this.makeMainPanel();
        mainFrame.setContentPane(mainPanel);
        
        mainFrame.addWindowListener(new WindowClosesApplication(tracker));
        
        mainFrame.setSize(1000, 400);
        mainFrame.setVisible(true);
    }

    /** Makes the main panel with the names of players, dice and check boxes.*/
    private JPanel makeMainPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBackground(Color.white);
        Player[] allPlayers = new Player[]{Player.COMPUTER, Player.HUMAN};
        for (int row = 0; row < allPlayers.length; row++) { 
            
            //making the left column (player names)
            JLabel playerName = new JLabel();
            if (allPlayers[row] == Player.HUMAN) {
                this.humanGamesWon = playerName;
            } else if (allPlayers[row] == Player.COMPUTER) {
                this.computerGamesWon = playerName;
            }
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
        
        //grid settings of the aside
        GridBagConstraints asideConstraints = new GridBagConstraints();
        asideConstraints.insets = new Insets(10, 10, 10, 10);
        asideConstraints.weightx = 0;
        asideConstraints.weighty = 0.5;
        asideConstraints.fill = GridBagConstraints.VERTICAL;
        asideConstraints.gridx = 2;
        asideConstraints.gridy = 0;
        asideConstraints.gridheight = 2;
        
        //making the aside with buttons
        mainPanel.add(makeAside(), asideConstraints);
        
        return mainPanel;
    }

    /** Makes the panel on the right with all the action buttons. */
    private JPanel makeAside() {   
        JPanel target = new JPanel();
        target.setLayout(new FlowLayout());
        
        //adding target value label
        JLabel targetLabel = new JLabel("Target Value: ");
        target.add(targetLabel);
        
        //adding target value input box
        JTextField targetValue = new JTextField(
                String.valueOf(ScoreTracker.DEFAULT_TARGET_VALUE), 5);
        target.add(targetValue);
        targetValue.addActionListener(tracker);
        targetValue.addFocusListener(tracker);
        
        JPanel buttons = new JPanel();
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.Y_AXIS));
        
        buttons.add(target);
        
        //adding the throw button
        JButton throwButton = new JButton("Throw dice");
        buttons.add(throwButton);
        throwButton.addActionListener(new Thrower(this, humanState, computer));
        
        //adding the score button
        JButton scoreButton = new JButton("Score displayed points");
        buttons.add(scoreButton);
        scoreButton.addActionListener(new ScoreUpdate(this, humanState, computerState));
        
        JPanel aside = new JPanel();
        aside.setLayout(new BorderLayout());
        
        aside.add(buttons, "North");
        
        //adding the new game button
        JButton newGameButton = new JButton("Start new game");
        aside.add(newGameButton, "South");
        newGameButton.addActionListener(new ResetGame(new Reseter[]{tracker, humanState, computerState, this}));
        
        return aside;
    }

    /** Makes a row of 5 dice with the check boxes. */
    private JPanel makeDiceRow(Player player) {
        JPanel dieLayout = new JPanel();
        dieLayout.setLayout(new BoxLayout(dieLayout, BoxLayout.X_AXIS));
        for (int i = 0; i < 5; i++) {
            dieLayout.add(makeDieFace(player, i));
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
        
        if (player == Player.HUMAN) {
            humanTotalScore = totalPlayerScore;
        } else {
            computerTotalScore = totalPlayerScore;
        }
        
        return row;
    }
    
    /** Pairs an individual die with its corresponding check box. */
    private JPanel makeDieFace(Player player, int dieNumber) {
        JCheckBox singleCheck = new JCheckBox();
        GameState state;
        if (player == Player.HUMAN) {
            state = humanState;
        } else { 
            state = computerState;
        }
        
        singleCheck.addActionListener(new DiceTracker(this, state, dieNumber));
        if (player == Player.HUMAN) {
            humanCheckBoxes[dieNumber] = singleCheck;
        } else {
            computerCheckBoxes[dieNumber] = singleCheck;
        }
        
        JPanel checkContainer = new JPanel();
        checkContainer.setLayout(new FlowLayout());
        checkContainer.add(singleCheck);
        
        JLabel singleDie = new JLabel();
        if (player == Player.HUMAN) {
            humanDice[dieNumber] = singleDie;
        } else {
            computerDice[dieNumber] = singleDie;
        }
        singleDie.setIcon(Die.BLANKDIE.getDieImage());
        JPanel dieContainer = new JPanel();
        dieContainer.setLayout(new FlowLayout());
        dieContainer.add(singleDie);
        
        JPanel die = new JPanel();
        die.setLayout(new BoxLayout(die, BoxLayout.Y_AXIS));
        die.add(checkContainer);
        die.add(dieContainer);
        return die;
    }

    /** Makes the interface look like the current state of the game. */
    public void refreshInterface() {
        this.setDieFaces(humanState.getCurrentDice(), Player.HUMAN);
        this.setDieFaces(computerState.getCurrentDice(), Player.COMPUTER);
        
        int currentScoreHuman = humanState.getCurrentScore();
        int currentScoreComputer = computerState.getCurrentScore();
        
        this.humanTotalScore.setText("Total score: " + currentScoreHuman + " points");
        this.computerTotalScore.setText("Total score: " + currentScoreComputer + " points");
        
        for (int i = 0; i < 5; i++) {
            boolean checked = humanState.isDiceKept(i);
            JCheckBox checkbox = humanCheckBoxes[i];
            checkbox.setSelected(checked);
        }
        
        this.tracker.updatePoints();
        Player winner = this.tracker.getWinner();
        if (winner == Player.HUMAN) {
            JOptionPane.showMessageDialog(mainFrame, "You win!");
            tracker.gameFinished();
        }
        if (winner == Player.COMPUTER) {
            JOptionPane.showMessageDialog(mainFrame, "You loose.");
            tracker.gameFinished();
        }
        
        this.humanGamesWon.setText(Player.HUMAN.name() + " : "
                + tracker.getHumanGamesWon());
        this.computerGamesWon.setText(Player.COMPUTER.name() + " : "
                + tracker.getComputerGamesWon());
    }
    
    /** Sets the die images to the interface, so that they correspond with their values. */
    private void setDieFaces(Die[] rolledFaces, Player player) { 
        for (int i = 0; i < 5; i++) {
            JLabel rolledDieNumber;
            if (player == Player.HUMAN) {
                rolledDieNumber = humanDice[i];
            } else { //player == Player.COMPUTER
                rolledDieNumber = computerDice[i];
            }
            
            Die rolledDieFace = rolledFaces[i];
            ImageIcon rolledDieImage = rolledDieFace.getDieImage();
            rolledDieNumber.setIcon(rolledDieImage);
        } 
    }

    /** Resets the interface to its initial state. */
    @Override
    public void reset() {
        this.refreshInterface();
    }
}
