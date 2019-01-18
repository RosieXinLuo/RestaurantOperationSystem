package ResApp;

import java.io.*;
import java.util.Observable;

/**
 * Represents an ingredient stored in the kitchen inventory of a restaurant.
 * An ingredient has its own name, quantity stored, threshold, and inventory status.
 */
public class Ingredient extends Observable implements Serializable {

  private String ingredientName;

  private int quantity;

  /** The Integer represents threshold of this ingredient. */
  public static final int THRESHOLD = 20;

  /**
   * Instantiates a new App.Ingredient.
   *
   * @param ingredientName the ingredient name
   * @param quantity the quantity
   */
  public Ingredient(String ingredientName, int quantity) {

    this.ingredientName = ingredientName;
    this.quantity = quantity;
    this.addObserver(RestaurantSystem.getInventoryManager());
  }

  /**
   * Get ingredient name.
   *
   * @return the String representing this ingredient name
   */
  public String getIngredientName() {
    return this.ingredientName;
  }


  /**
   * Gets quantity.
   *
   * @return Integer representing the current stock quantity of this ingredient
   */
  public int getQuantity() {
    return this.quantity;
  }

  public void setQuantity(int Quantity) {
    this.quantity = Quantity;
    if (this.quantity < THRESHOLD) {
      setChanged();
      notifyObservers(this);
    }
  }

  /**
   * Gets threshold of this ingredient.
   *
   * @return An integer representing the threshold of this ingredient
   */
  public int getThreshold() {
    return THRESHOLD;
  }

  /**
   * Gets inventory status of a ingredient.
   *
   * @return A String that representing the inventory status of a ingredient
   */
  public String getStatus() {
    if (quantity < this.getThreshold() || quantity <= 0) {
      return "In Shortage";
    } else {
      return "Sufficient";
    }
  }
}
