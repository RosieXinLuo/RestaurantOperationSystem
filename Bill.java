package ResApp;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a bill generated from the order it belongs to.
 * A bill can be added into dishes from the order it belongs to and stores a total price from the order.
 */
public class Bill {

    public static final double TAX = 1.13;
    public static final double GRATUITY = 1.5;
    private int tableNumber;
  /** The Hash Map representing which dishes have already been delivered to the table from the order
   * and stored into this bill. */
  private Map<Dish, Integer> deliveredDishes;
  /** The double representing the total price of this bill. */
  private double totalPrice;
  /** the order this bill belongs to. */
  private Order order;

  /**
   * Instantiates a new App.Bill.
   *
   * @param tableNumber the table number
   */
  public Bill(int tableNumber) {
    this.tableNumber = tableNumber;
    this.deliveredDishes = new HashMap<Dish, Integer>();
    this.order = null;
    this.totalPrice = 0;
  }

  public int getTableNum(){
    return this.tableNumber;
  }

  public int getOrderNum(){
    return this.order.getOrderNumber();
  }

  /**
   * Add a dish to this bill.
   *
   * @param dish representing a dish
   */
  public void addToBill(Dish dish) {
    if (this.deliveredDishes.containsKey(dish)) {
      this.deliveredDishes.put(dish, this.deliveredDishes.get(dish) + 1);
    } else {
      this.deliveredDishes.put(dish, 1);
    }
    if (order.getNumPeople() >= 8) {
        this.totalPrice += dish.getPrice() * TAX * GRATUITY;
    } else {
        this.totalPrice += dish.getPrice() * TAX;
    }
  }

  /**
   * Substract a dish to this bill.
   *
   * @param dish representing a dish
   */
  public double subtractFromBill(Dish dish) {
    if (this.deliveredDishes.containsKey(dish)) {
      this.deliveredDishes.remove(dish);
    }
    Double dishPrice = 0.0;
    if (order.getNumPeople() >= 8) {
      dishPrice = dish.getPrice() * TAX * GRATUITY;
      dishPrice = (double) Math.round(dishPrice * 100d) / 100d;
    } else {
      dishPrice =  dish.getPrice() * TAX;
      dishPrice = (double) Math.round(dishPrice * 100d) / 100d;
    }
    this.totalPrice -= dishPrice;
    return dishPrice;
  }

    public double getTotalPrice() {
        return totalPrice;
    }


  public String toString() {
    String dishList = "";
    for (Dish dish : deliveredDishes.keySet()) {
      dishList +=
          dish.getDishName()
              + "  $"
              + String.valueOf(dish.getPrice())
              + "x"
              + String.valueOf(deliveredDishes.get(dish))
              + System.lineSeparator();
    }

    return "- App.Bill for table "
        + String.valueOf(tableNumber)
        + " - "
        + System.lineSeparator()
        + dishList
        + "Total:  $"
        + String.valueOf(this.totalPrice)
            + System.lineSeparator();
  }

    public void setOrder(Order order) {
        this.order = order;
    }

    //to be deleted.!!!!!!!
  public void setBillValue(Double price){
    this.totalPrice = price;
  }
}
