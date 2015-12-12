package dicegame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingWorker;

/**
 * @author Maria Bartoszuk, w1510769
 */

/**
 * Represents the action of throwing the dice by all the players.
 */
public class Thrower implements ActionListener {
    
    GameWindow game;
    GameState humanState;
    ComputerPlayer player;
    
    public Thrower(GameWindow game, GameState humanState, ComputerPlayer player) {
        this.game = game;
        this.humanState = humanState; 
        this.player = player;
    }

    /**
     * Performs the action of rolling the dice for all the players
     * and refreshing the interface of the game to make sure correct
     * dice are displayed.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        /**
         * Anonymous class that is a SwingWorker. It runs the background task
         * of dice rolling in the Swing Worker thread, and after that finishes
         * refreshes the interface with done method on the Event Dispatch thread.
         */
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {

            /**
             * Runs the dice rolling logic for the human player in the 
             * Swing worker thread.
             * If it's not a re-roll, begins a separate thread to roll for
             * the computer player. Therefore dice rolling for both players
             * run in separate threads, neither of them being the main or
             * dispatch thread.
             */
            @Override
            protected Void doInBackground() throws Exception {
                Thread computerThread = null;
                if (humanState.alreadyRolled() == false) {
                    computerThread = new Thread(){
                        @Override
                        public void run() {
                            player.rollDice();
                        }
                    };
                    computerThread.start();
                }
                humanState.rollDice();
                if (computerThread != null) {
                    computerThread.join();
                }
                return null;
            }
            
            @Override
            public void done() {
                game.refreshInterface();
            }
        };
        worker.execute();
    }  
}
