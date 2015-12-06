package dicegame;

import java.util.ArrayList;

/**
 *
 * @author Maria
 */

public class EventQueue {
    
    ArrayList<Observer> subscribedObservers = new ArrayList<>();
    
    public void publish(Object event) {
        
        for (Observer observer : subscribedObservers) {
            if (observer.canHandle(event)) {
                observer.handle(event);
            }
        }
    }
    
    public void subscribe(Observer observer) {
        
        this.subscribedObservers.add(observer);
    }
}
