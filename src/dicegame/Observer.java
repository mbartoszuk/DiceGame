package dicegame;

/**
 *
 * @author Maria
 */

public interface Observer {
    
    boolean canHandle(Object event);
    
    void handle(Object event);
}
