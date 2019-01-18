package ResApp;
// A super class for all staffs in the restaurant.

import java.io.Serializable;
import java.util.Observable;

/**
 * Represents a staff with Observable with id, current order and server/cook..
 */
public abstract class Staff extends Observable implements Serializable {

    private int id;
    private Order currOrder;
    private int serverOrCook; //server0, cook1

  /**
   * Instantiates a new Staff.
   *
   * @param serverOrCook representing the server or cook
   */
  public Staff(int serverOrCook) {
        this.id = HRManager.getStaffs().size()+1;
        currOrder = null;
        this.serverOrCook = serverOrCook;
        this.addObserver(RestaurantSystem.getInventoryManager());
    }

  /**
   * Gets id.
   *
   * @return Integer representing the id
   */
  public int getId() {
        return id;
    }

  /**
   * Sets curr order.
   *
   * @param order representing the order
   */
  public void setCurrOrder(Order order) {
        currOrder = order;
    }

  /**
   * Gets current order.
   *
   * @return Order representing the current order
   */
  public Order getCurrOrder() {
        return currOrder;
    }

  /**
   * Gets server or cook.
   *
   * @return the attribitue of ServerOrCook
   */
  public int getServerOrCook() {
        return serverOrCook;
    }
}
