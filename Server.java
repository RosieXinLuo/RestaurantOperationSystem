package ResApp;

/**
 * Represents a server employed in the restaurant.
 * A server can enter an order for customers for each table, deliver dish and return order to cook.
 * */
public class Server extends Staff{ //Observable

  /**
   * Instantiates a new Server.
   */
   public Server() {
        super(0);
    }

  /**
   * Notifies the inventory manager to check request..
   */
   public void notifyInventoryManager() {
       setChanged();
       notifyObservers();
   }
}
