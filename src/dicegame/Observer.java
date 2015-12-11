package dicegame;

/**
 * @author Maria Bartoszuk, w1510769
 */

public interface Observer {
    
    boolean canHandle(Object event);
    
    void handle(Object event);
}
