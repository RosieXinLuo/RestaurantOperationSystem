package ResApp;

import java.io.*;
import java.util.Map;
import java.util.HashMap;
import java.util.Observable;

/**
 * Represents an App.Inventory in the kitchen of a restaurant An inventory can be added into ingredient
 * object by employee or manager when it receives shipment.
 */
public class Inventory implements Serializable {

  private static Inventory inventory = new Inventory();
  /**
   * The constant filePath.
   */
  public static final String filePath = "../RestaurantApp/src/ResApp/inventory.ser";

  /** The Hash Map storing each ingredient objects in the inventory. */
  private static HashMap<String, Ingredient> ingredients;

  /* A private Constructor prevents any other
   * class from instantiating.
   */
  private Inventory() {
    ingredients = new HashMap<> ();;
    // Reads serializable objects from file.
    // Populates the record list using stored data, if it exists.
    File file = new File(filePath);
    if (file.exists() && file.length() != 0) {
      readFromFile(filePath);
    } else if (!file.exists()) {
      try {
        file.createNewFile();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * Gets instance.
   *
   * @return the instance
   */
  /* Static 'instance' method to get the App.RecordManager. */
  public static Inventory getInstance( ) {
    return inventory;
  }


  /**
   * Deserialize the map from given path.
   *
   * @param path the path of the serializable file
   * @throws ClassNotFoundException
   */
  protected static void readFromFile(String path) {
    try {
      InputStream file = new FileInputStream(path);
      InputStream buffer = new BufferedInputStream(file);
      ObjectInput input;
      input = new ObjectInputStream(buffer);
      try {
        ingredients = (HashMap<String, Ingredient>) input.readObject();
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
      }
      input.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Saves the current data to the serializable file. This function also can
   * be used to update file content .
   *
   * @param path the path
   * @throws IOException
   */
  protected static void saveToFile(String path)  {

    OutputStream file = null;
    try {
      file = new FileOutputStream(path);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    OutputStream buffer = new BufferedOutputStream(file);
    ObjectOutput output = null;
    try {
      output = new ObjectOutputStream(buffer);
      output.writeObject(ingredients);
//      output.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Add ingredient to inventory as an employee or manager receive it.
   *
   * @param ingredientName String representing the ingredient name
   * @param amount         Integer representing the amount
   * @throws IOException the io exception
   */
  public void addIngredient(String ingredientName, int amount) {
    if (ingredients.keySet().contains(ingredientName)) {
      int originalAmount = ingredients.get(ingredientName).getQuantity();
      ingredients.get(ingredientName).setQuantity(amount + originalAmount);
      ingredients.put(ingredientName, ingredients.get(ingredientName));
    } else {
      Ingredient newIngredient = new Ingredient(ingredientName, amount);
      ingredients.put(ingredientName, newIngredient);
    }
    saveToFile(filePath);
    try {
      LoggerHelper.makeALog(ingredientName+" with "+amount+" units is added into inventory");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Gets ingredient.
   *
   * @param ingredName the ingredient name
   * @return the ingredient
   */
  public Ingredient getIngredient(String ingredName) {
    if (ingredients.keySet().contains(ingredName)) {
      return ingredients.get(ingredName);
    }
    return null;
  }

  /**
   * Gets ingredients.
   *
   * @return the ingredients
   */
  public HashMap<String, Ingredient> getIngredients() {
    return ingredients;
  }
}
