package dicegame;

/**
 *
 * @author Maria
 */

public class DieKeptEvent {
    
    Player player;
    int dieNumber;
    boolean dieKept;
    
    public DieKeptEvent(Player player, int dieNumber, boolean dieKept) {
        
        this.player = player;
        this.dieNumber = dieNumber;
        this.dieKept = dieKept;
    }
}
