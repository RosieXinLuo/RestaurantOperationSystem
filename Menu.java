package ResApp;

import java.io.*;
import java.util.*;

/**
 * Represents a App.Menu designed by a restaurant.
 * A App.Menu can print itself in console and build itself as a storage for server and cook to read.
 */
public class Menu {

  private HashMap<String, Dish> meals = new HashMap<String, Dish>();
  private HashMap<String, Dish> drinks = new HashMap<String, Dish>();
  private HashMap<String, Dish> sides = new HashMap<String, Dish>();

  private String filePath = "RestaurantApp/src/ResApp/menu.txt";

  /**
   * Build a App.Menu storage of all dishes names, their ingredients list and prices.
   * It can be used for server and cook to read for setting up each order in RestaurantApp.
   *
   * @throws IOException the io exception
   */
  public void buildMenu() {
    try (BufferedReader fileReader = new BufferedReader(new FileReader("src/ResApp/menu.txt"))) {
      String line = fileReader.readLine();
      while (line != null) {
        // Read from menu.txt
        String[] parts = line.split("\\|");
        String[] namePrice = parts[0].split("\\,");
        // Create dish objects. Each dish(line) in menu.txt should be different.
        // Set the name and price of this dish.
        String dishName = namePrice[0].trim();
        double dishPrice = Double.valueOf(namePrice[1]);
        Dish dish = new Dish(dishName, dishPrice);
        // Add all the ingredients needed to a dish for cooking.
        this.buildDish(parts, dish);
        // Add dish to the menu.
        if (parts[1].trim().equals("Meal")) {
          this.meals.put(dishName, dish);
        } else if (parts[1].trim().equals("Drink")) {
          this.drinks.put(dishName,dish);
        } else if (parts[1].trim().equals("Side")) {
         this.sides.put(dishName,dish);
        }
        line = fileReader.readLine();
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Add all the ingredient needed to a dish's ingredients list for cooking.
   *
   * @param strings representing name of all ingredients needed to cook a dish
   * @param dish    the dish
   */
  public void buildDish(String[] strings, Dish dish) {
    int j = 2;
    while (j != strings.length) {
      String[] ingredientNameQuantity = strings[j].trim().split("x");
      String ingredientName = ingredientNameQuantity[0].trim();
      int ingredientQuantity = Integer.valueOf(ingredientNameQuantity[1]);
      Ingredient ingredient = Inventory.getInstance().getIngredient(ingredientName);
      if (ingredient == null) {
        ingredient = new Ingredient(ingredientName, ingredientQuantity);
        Inventory.getInstance().addIngredient(ingredientName, 0);
      }
      dish.addIngredient(ingredient, ingredientQuantity);
      j++;
    }
  }


  /**
   * Gets meals.
   *
   * @return the meals
   */
  public HashMap<String, Dish> getMeals() {
    return meals;
  }

  /**
   * Gets drinks.
   *
   * @return the drinks
   */
  public HashMap<String, Dish> getDrinks() {
    return drinks;
  }

  /**
   * New dish dish.
   *
   * @param dish the dish
   * @return the dish
   */
  public Dish newDish(Dish dish) {
    String dishName = dish.getDishName();
    Double dishPrice = dish.getPrice();
    Dish newDish = new Dish(dishName, dishPrice);

    Map<Ingredient, Integer> ingList = new HashMap<Ingredient, Integer>();
    for (Ingredient ing: dish.getIngredientsList().keySet()) {
      ingList.put(ing, dish.getIngredientsList().get(ing));
    }
    newDish.setIngredientsList(ingList);
    return newDish;
  }
}
