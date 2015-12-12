package dicegame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;

/**
 * @author Maria Bartoszuk, w1510769
 */

/**
 * Knows of the scores of all players and determines which is a winner.
 */
public class ScoreTracker implements Reseter, ActionListener, FocusListener {
    
    //target value if not changed by the human player
    public static final int DEFAULT_TARGET_VALUE = 101;
    
    GameState human;
    GameState computer;
    int targetValue = DEFAULT_TARGET_VALUE;
    int humanScore = 0;
    int computerScore = 0;
    int humanGamesWon = 0;
    int computerGamesWon = 0;
    
    public ScoreTracker(GameState human, GameState computer) {
        this.human = human;
        this.computer = computer;
    }
    
    /**
     * Refreshes tracker state with new score of each player. In particular,
     * determines whether it's a tie (i.e. scores match and are above target value).
     * Modifies game state accordingly with the tie method.
     */
    public void updatePoints() {
        humanScore = human.getCurrentScore();
        computerScore = computer.getCurrentScore();
        if (humanScore == computerScore && humanScore > targetValue) {
            human.tie();
            computer.tie();
        }
    }
        
    /** Returns a winning player enum, or null if there is no winner yet. */
    public Player getWinner() {
        if (humanScore > targetValue && humanScore > computerScore) {
            return Player.HUMAN;
        } else if (computerScore > targetValue && computerScore > humanScore) {
            return Player.COMPUTER;
        } else {
            return null;
        }
    }
    
    public int getHumanGamesWon() {
        return humanGamesWon;
    }
    
    public int getComputerGamesWon() {
        return computerGamesWon;
    }
    
    /**
     * Notifies score tracker that the current game has finished, so it can
     * modify total games won statistics.
     */
    public void gameFinished() {
        if (this.getWinner() == Player.HUMAN) {
            this.humanGamesWon = humanGamesWon + 1;
        } else if (this.getWinner() == Player.COMPUTER) {
            this.computerGamesWon = computerGamesWon + 1;
        }
    }
    
    /** Saves the number of total games won for each player. */
    public void save() {
        String directory = System.getProperty("user.dir");
        
        File humanGamesWonFile = new File(directory, "humangameswon.txt");
        writeScore(humanGamesWonFile, this.humanGamesWon);
        
        File computerGamesWonFile = new File(directory, "computergameswon.txt");
        writeScore(computerGamesWonFile, this.computerGamesWon);
    }
    
    /** Loads the number of total games won for each player from a file. */
    public void load() {
        String directory = System.getProperty("user.dir");
        
        File humanGamesWonFile = new File(directory, "humangameswon.txt");
        this.humanGamesWon = readScore(humanGamesWonFile);
        
        File computerGamesWonFile = new File(directory, "computergameswon.txt");
        this.computerGamesWon = readScore(computerGamesWonFile); 
    }
    
    /** Resets the scores for all players to their initial values. */
    @Override
    public void reset() {
        this.humanScore = 0;
        this.computerScore = 0;
    }

    /** 
     * Updates the target value from the interface.
     * Triggered when the user hits the enter key in the text field.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JTextField targetInput = (JTextField) e.getSource();
        this.targetValue = Integer.parseInt(targetInput.getText());
    }

    /** 
     * Left empty, must implement focus listener but we only care about
     * focus lost event.
     */
    @Override
    public void focusGained(FocusEvent e) {
    }

    /** 
     * Updates the target value from the interface.
     * Triggered when the user looses focus of the text field.
     */
    @Override
    public void focusLost(FocusEvent e) {
        JTextField targetInput = (JTextField) e.getSource();
        this.targetValue = Integer.parseInt(targetInput.getText());
    }
    
    /** Returns integer read from the given file or 0 if file not present. */
    private static int readScore(File file) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
            return scanner.nextInt();
        } catch (FileNotFoundException e) {
            System.err.println("Could not read score file "
                    + file.getAbsolutePath() + ". Assuming no games won.");
            return 0;
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }
    
    /** Writes given integer to a given file. Overwrites the existing content. */
    private static void writeScore(File file, int score) {
        try {
            file.createNewFile();
        } catch (IOException e) {
            System.err.println("Failed to create file: "
                    + file.getAbsolutePath());
        }
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(file);
            writer.format("%d", score);
        } catch (FileNotFoundException ex) {
            System.err.println("File not found: " + file.getAbsolutePath());
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
        System.err.println("Saved score to "+ file.getAbsolutePath());
    }
}
