
package ResApp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;

/**
 * Represents a Dish in the Menu. Dish can set its own additions and subtractions from each
 * order's special need.
 */
public class Dish {

  private String itemName;
  private int quantity;
  private Order order;

  /** The Hash Map containing ingredients list of a dish. */
  private Map<Ingredient, Integer> ingredientsList;
  private ArrayList<String> preference;

  private double price;

  /**
   * Instantiates a new Dish.
   *
   * @param itemName String representing this dish's name
   * @param price Integer representing the price of this dish
   */
  public Dish(String itemName, double price) {
    this.itemName = itemName;
    this.price = price;
    this.ingredientsList = new HashMap<Ingredient, Integer>();
    this.preference = new ArrayList<>();
  }

  /**
   * Add an ingredient by quantity to this dish's ingredients list.
   *
   * @param newIngredient String representing the added ingredient's name
   * @param quantity Integer representing the quantity of the added ingredient
   */
  public void addIngredient(Ingredient newIngredient, int quantity) {
    if (ingredientsList.containsKey(newIngredient)) {
      ingredientsList.put(newIngredient, ingredientsList.get(newIngredient) + quantity);
    } else {
      this.ingredientsList.put(newIngredient, quantity);
    }
    try {
      LoggerHelper.makeALog(newIngredient.getIngredientName() + " with " + quantity + " is added ");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


  /**
   * Subtract an ingredient by quantity from this dish's ingredients list.
   *
   * @param newIngredient String representing the subtraction of ingredient's name
   * @param quantity Integer representing the quantity of the ingredient subtraction
   */
  public void subtractIngredient(Ingredient newIngredient, int quantity) {
    if (ingredientsList.containsKey(newIngredient)) {
      if (ingredientsList.get(newIngredient) <= quantity) {
        ingredientsList.remove(newIngredient);
      } else {
        ingredientsList.put(newIngredient, ingredientsList.get(newIngredient)-quantity);
      }
    }
  }

  /**
   * Ingredient list to string .
   *
   * @return String representing which ingredients and their quantity are needed to cook this dish
   */
  public String ingredientListToString() {
    String nameList = "";
    for (Ingredient eachIngredient : this.ingredientsList.keySet()) {
      nameList += eachIngredient + " x" + this.ingredientsList.get(eachIngredient) + " ";
    }
    return nameList;
  }

  /**
   * Gets price of this dish.
   *
   * @return Integer containing the price of this dish
   */
  public double getPrice() {
    return price;
  }

  public Order getOrder(){return order;}

  public void setOrder(Order order){this.order = order;}

  public ArrayList<String> getPreference() {
    return preference;
  }

  /**
   * Sets price of this dish.
   *
   * @param price Integer containing the price of this dish
   */
  public void setPrice(double price) {
    this.price = price;
  }

  public int getQuantity(){ return this.quantity;}
  public void setQuantity(int num){this.quantity = num;}

  /**
   * Gets dish's name.
   *
   * @return String representing this dish's name
   */
  public String getDishName() {
    return itemName;
  }

  /**
   * Print dish's information to strings.
   *
   * @return String representing this dish's information including name, price and ingredients needed
   */
  public String toString() {
    return getDishName()
            + "  $"+ getPrice() + " x" + this.quantity + System.lineSeparator() + this.preference;
  }

  /**
   * Gets ingredients list of a dish.
   *
   * @return the HashMap containing ingredients needed for cooking a dish
   */
  public Map<Ingredient, Integer> getIngredientsList() {
    return ingredientsList;
  }

  public void setIngredientsList(Map<Ingredient, Integer> ingredientsList) {
    this.ingredientsList = ingredientsList;
  }

}

