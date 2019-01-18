package ResApp;

import java.io.*;
import java.util.Observer;
import java.util.Observable;

/**
 * Represents a manager to use this restaurant software. A manager can write email and print
 * overview of kitchen inventory.
 */
public class InventoryManager implements Observer {

  @Override
  public void update(Observable o, Object arg) {
    checkRequest();
  }

  /**
   * Generates the request.txt
   * to record which ingredient is requested and the amount
   * manager can cut and copy this as part of an email sent to distributor
   */
  public void checkRequest() {
    int i = 0;
    for (String ingredientName : Inventory.getInstance().getIngredients().keySet()) {
        try {
          FileOutputStream out = new FileOutputStream("request.txt", true);
          OutputStreamWriter outWriter = new OutputStreamWriter(out, "UTF-8");
          BufferedWriter bufWrite = new BufferedWriter(outWriter);
          //if an ingredient's current stock is below its threshold
          //the system would write one line of information to record this ingredient's name and requested amount
          if (Inventory.getInstance().getIngredients().get(ingredientName).getQuantity()
                  < Inventory.getInstance().getIngredients().get(ingredientName).getThreshold()){
          bufWrite.write(" Request: " + ingredientName + "  for amount 20");
            bufWrite.newLine();
          }
          else{i+=1;
          //if all ingredients' current stock is sufficient
            //the system would write two line of information to show no need to request
          if(i == Inventory.getInstance().getIngredients().size()){
            bufWrite.write(
                    "No ingredients are requested so far "); // write line of which ingredient is requested
            bufWrite.newLine();
            bufWrite.write("All ingredients in stock are sufficient ");
          }}

          bufWrite.close();
          outWriter.close();
          out.close();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }

  /** A manager can print inventory overview to check each ingredient's stock information. */
  public String printInventory() {
    String InventoryInfo = "";
    for (String eachIngredient : Inventory.getInstance().getIngredients().keySet()) {
      InventoryInfo +=
              eachIngredient+"\t"
                      + "            \t"
                      +"\t"+ Inventory.getInstance().getIngredients().get(eachIngredient).getQuantity()+"\t"
                      + "            \t"
      +"\t"+Inventory.getInstance().getIngredients().get(eachIngredient).getStatus()+"\t"
                      + "            \t"
                      +"\t"+Inventory.getInstance().getIngredients().get(eachIngredient).getThreshold()+"\t"
                      + System.lineSeparator();
    }
    return
            System.lineSeparator()
    +"Ingredient     Quantity in Stock      "
            + "              Status" + "                 Threshold"
            + System.lineSeparator()
            + InventoryInfo
            + System.lineSeparator();
  }

}
