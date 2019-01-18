package ResApp;

import java.io.IOException;
import java.util.*;

/**
 * Represents an Order from a table that a server enters.
 * An order can store dishes ordered by a table by adding into or removed a dish, and printed into a bill
 */
public class Order {
  /** The Integer representing table number for this order. */
  private int tableNumber;
  /** The Integer representing number of customers for this order. */
  private int numPeople;
  /** The Integer representing order number. */
  private int orderNumber;
  /** The Integer representing total number of orders for the restaurant. */
  private static int totalOrderNumber = 1;
  /** The Bill representing a bill attached to this order. */
  private Bill bill;
  /** The list representing which dishes are ordered for this order. */
  private ArrayList<Dish> dishes;
  private boolean isDelivered = false;

  private ArrayList<Dish> cookedDishes;

  /**
   * Instantiates a new App.Order.
   *
   */
  public Order() {
    this.bill = new Bill(tableNumber);
    this.bill.setOrder(this);
    this.dishes = new ArrayList<Dish>();
    this.orderNumber = totalOrderNumber;
    totalOrderNumber += 1;
    this.cookedDishes = new ArrayList<Dish>();
  }

  /**
   * Add new dish to this order.
   *
   * @param dish representing the dish
   */
  public void addDish(Dish dish) {
    dishes.add(dish);
    try {
      LoggerHelper.makeALog(dish.getDishName()+" is added into order "+this.orderNumber+
              " for table "+this.getTableNumber());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public boolean getIsDelivered(){
    return this.isDelivered;
  }

  public void changeIsDelivered(){
    this.isDelivered = true;
  }

  public ArrayList getCookedDishes(){
    return this.cookedDishes;
  }

  /**
   * Remove dish from this order.
   *
   * @param dish representing the dish to be removed
   */
  public void removeDish(Dish dish) {
    dishes.remove(dish);
  }

  /**
   * Gets table number this order belongs to.
   *
   * @return Integer representing the table number
   */
  public int getTableNumber() {
    return this.tableNumber;
  }

  /**
   * Gets bill from this order.
   *
   * @return the bill
   */
  public Bill getBill() {
    return this.bill;
  }

  /**
   * Gets dishes ordered from this order.
   *
   * @return ArrayList representing dishes ordered from this order
   */
  public ArrayList<Dish> getDishes() {
    return dishes;
  }

  /**
   * Gets number of customers for this order.
   *
   * @return Integer representing number of customers for this order
   */
  public int getNumPeople() {
    return numPeople;
  }

  /**
   * Gets order number.
   *
   * @return Integer representing the order number
   */
  public int getOrderNumber() {
    return orderNumber;
  }

  @Override
  /**
   * returns String version of self.
   */
  public String toString(){
    String detail = "--  Order #" + String.valueOf(this.orderNumber) +"  --" + System.lineSeparator();
    for(int i = 0; i<this.getDishes().size(); i++){
      detail += this.getDishes().get(i).getDishName()
              +"$"+this.getDishes().get(i).getPrice()
              + "  x" + String.valueOf(this.getDishes().get(i).getQuantity())
              + System.lineSeparator();
      if(this.getDishes().get(i).getPreference().size() != 0){
        detail += this.getDishes().get(i).getPreference().toString()+System.lineSeparator();
      }
    }
    detail += "----------------" + System.lineSeparator();
    return detail;
  }

  /**
   * Sets the table number
   */
  public void setTableNumber(int tableNumber) {
    this.tableNumber = tableNumber;
  }

  /**
   * Sets number of people.
   */
  public void setNumPeople(int numPeople) {
    this.numPeople = numPeople;
  }
}