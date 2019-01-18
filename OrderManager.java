package ResApp;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a functional manager to manage order to be added to cook, deliver, return or removed.
 */
public class OrderManager {

  /** A static HashMap representing all current orders in the restaurant. */
  // Has to be static since we only have one version of orders in the restaurant.
  private static Map<Integer, Order> orders = new HashMap<Integer, Order>();

  /** A static HashMap representing all returned orders from customers. */
  private static Map<Integer, Order> returningOrders = new HashMap<Integer, Order>();

  /** A static HashMap representing all orders to be delivered pass from cook. */
  private static Map<Integer, Order> toBeDelivered = new HashMap<Integer, Order>();

  /** A static HashMap representing all orders to be cooked pass from server. */
  private static Map<Integer, Order> toBeCooked = new HashMap<Integer, Order>();

  /**
   * Print bill for an order.
   *
   * @param order representing the order
   * @return Strings representing the information for a bill
   * @throws IOException the io exception
   */
  public String printBill(Order order) throws IOException {
    return order.getBill().toString();
  }

  /**
   * Add an order to the static HashMap representing all current orders in the restaurant.
   *
   * @param order representing an order
   */
  public void addToOrder(Order order) {
    orders.put(order.getOrderNumber(), order);
    try {
      LoggerHelper.makeALog(
          "Order "
              + order.getOrderNumber()
              + " at table "
              + order.getTableNumber()
              + " is added into orders storage Map - orders");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Add an order to the static HashMap representing all returned orders in the restaurant.
   *
   * @param order representing an order
   */
  public void addToReturningOrder(Order order) {
    returningOrders.put(order.getOrderNumber(), order);
    try {
      LoggerHelper.makeALog(
          "Order "
              + order.getOrderNumber()
              + " at table "
              + order.getTableNumber()
              + " is added into returning orders storage Map - returningOrders");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Add an order to the static HashMap representing all orders to be cooked pass from server.
   *
   * @param order representing an order
   */
  public void addToBeCooked(Order order) {
    toBeCooked.put(order.getOrderNumber(), order);
    orders.put(order.getOrderNumber(), order);
    try {
      LoggerHelper.makeALog(
          "Order "
              + order.getOrderNumber()
              + " at table "
              + order.getTableNumber()
              + " is added into to-be-cooked orders storage Map - toBeCooked");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Add an order to the static HashMap representing all orders to be delivered pass from cook
   *
   * @param order representing an order
   */
  public void addToBeDelivered(Order order) {
    toBeDelivered.put(order.getOrderNumber(), order);
    orders.put(order.getOrderNumber(), order);
    try {
      LoggerHelper.makeALog(
          "Order "
              + order.getOrderNumber()
              + " at table "
              + order.getTableNumber()
              + " is added into to-be-delivered orders storage Map - toBeDelivered");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Gets all current orders in the restaurant
   *
   * @return the static HashMap representing all current orders in the restaurant.
   */
  public Map<Integer, Order> getOrders() {
    return orders;
  }

  /**
   * Gets all returned orders in the restaurant
   *
   * @return the static HashMap representing all returned orders in the restaurant.
   */
  public Map<Integer, Order> getReturningOrders() {
    return returningOrders;
  }

  /**
   * Gets all orders to be delivered in the restaurant
   *
   * @return the static HashMap representing all orders to be delivered in the restaurant.
   */
  public Map<Integer, Order> getToBeDelivered() {
    return toBeDelivered;
  }

  /**
   * Gets all orders to be cooked in the restaurant
   *
   * @return the static HashMap representing all orders to be cooked in the restaurant.
   */
  public Map<Integer, Order> getToBeCooked() {
    return toBeCooked;
  }

  /**
   * Remove an order from current orders.
   *
   * @param i Integer representing order number
   */
  public void removeOrder(int i) {
    orders.remove(i);
    try {
      LoggerHelper.makeALog("Order " + i + " is removed from order storage Map - orders");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Remove an order from returned orders.
   *
   * @param i Integer representing order number
   */
  public void removeReturningOrders(int i) {
    returningOrders.remove(i);
    try {
      LoggerHelper.makeALog(
          "Order " + i + " is removed from returning orders storage Map - returningOrders");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Remove an order from orders to be delivered.
   *
   * @param i Integer representing order number
   */
  public void removeToBeDelivered(int i) {
    toBeDelivered.remove(i);
    removeOrder(i);
    try {
      LoggerHelper.makeALog(
          "Order " + i + " is removed from to-be-delivered orders storage Map - toBeDelivered");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Remove an order from orders to be cooked.
   *
   * @param i Integer representing order number
   */
  public void removeToBeCooked(int i) {
    toBeCooked.remove(i);
    try {
      LoggerHelper.makeALog(
          "Order " + i + " is removed from to-be-cooked orders storage Map - toBeCooked");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public String allOrdersToString(){
    String detail = " --  UndeliveredOrderList  -- " + System.lineSeparator();
    for(Integer orderNum: this.getToBeDelivered().keySet()){
      detail += getToBeDelivered().get(orderNum).toString() + System.lineSeparator();
    }
    detail += " --  UnCookedList  -- " + System.lineSeparator();
    for(Integer orderNum: this.getToBeCooked().keySet()){
      detail += getToBeCooked().get(orderNum).toString() + System.lineSeparator();
    }
    detail += "--------------------";
    return detail;
  }

}
