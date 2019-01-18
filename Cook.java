package ResApp;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

/** Represents a cook employed in the restaurant. A cook can cook each dish from a order. */
public class Cook extends Staff {

  /**
   * Instantiates a new App.Cook.
   *
   */
  public Cook() {
    super(1);
  }

  /**
   * Prepare to cook dish in an order.
   *
   * @param order representing an order
   */
  public void prepareOrder(Order order) throws IOException {
    RestaurantSystem.getOrderManager().removeToBeCooked(order.getOrderNumber());
    for (Dish d : order.getDishes()) {
      boolean cooked = cookDish(d);
      if (!cooked) { // not enough ingredient for cooking a dish in an order.
        order.removeDish(d);
        try {
          LoggerHelper.makeALog(
              d.getDishName()
                  + " cannot be cooked and removed from order "
                  + order.getOrderNumber()
                  + " for table "
                  + order.getTableNumber()
                  + " due to shortage of ingredients in the inventory");
        } catch (IOException e) {
          e.printStackTrace();
        }
      } else {//if all the ingredients for cooking a dish is enough, cook can prepare the dish
        LoggerHelper.makeALog(d.getDishName() + " is prepared to cook");
      }
    }
    if (order.getDishes().size() >= 1) {
      RestaurantSystem.getOrderManager().addToBeDelivered(order);
    }
  }

  /**
   * Cook dish boolean by checking if ingredients are enough
   *
   * @param dish representing a dish
   * @return the boolean representing if a dish can be cooked or not
   */
  public boolean cookDish(Dish dish) {
    // first to check if we have enough ingredient for cooking a dish
    for (Ingredient i : dish.getIngredientsList().keySet()) {
      if (Inventory.getInstance().getIngredients().keySet().contains(i.getIngredientName())) {
        int originalAmount = Inventory.getInstance().getIngredients().get(i.getIngredientName()).getQuantity();
        int costQuantity = (dish.getIngredientsList().get(i));
        if (originalAmount <= costQuantity) {
          setChanged();
          notifyObservers(i); // notify manager that an ingredient is out of stock.
          return false;
        }
      } // else we don't have this ingredient, notify manager or not?
    }
    // after making sure that we have enough ingredient, we can begin to cook
    for (Ingredient i : dish.getIngredientsList().keySet()) {
      if (Inventory.getInstance().getIngredients().keySet().contains(i.getIngredientName())) {
        int originalAmount = Inventory.getInstance().getIngredients().get(i.getIngredientName()).getQuantity();
        int costQuantity = (dish.getIngredientsList().get(i));
        Inventory.getInstance().getIngredients().get(i.getIngredientName()).setQuantity(originalAmount - costQuantity);
        System.out.println("cost");
      }
    }
    return true;
  }
}
