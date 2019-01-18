package ResApp;

import java.io.*;
import java.util.HashMap;
import java.util.Date;

/**
 * A singleton manager class that manages all the payments record on various dates.
 */
public class RecordManager implements Serializable {

  public static final String filePath = "/Users/HL/Desktop/Courses/csc207/group_0467/phase2/RestaurantApp/src/ResApp/record.ser";

  /** A static HashMap representing daily payments record in the restaurant. */
  private static HashMap<Date, DailyPayments> payments;
  /** A static boolean representing revenue from daily payments. */
  private static Double revenue; // Revenue so far.

  public RecordManager() {

    payments = new HashMap<Date, DailyPayments>();
    revenue = 0.0;
    // Reads serializable objects from file.
    // Populates the record list using stored data, if it exists.
    File file = new File(filePath);
    if (file.exists() && file.length() != 0) {
      try {
        readFromFile(filePath);
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
      }
    } else if (!file.exists()) {
      try {
        file.createNewFile();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * Deserialize the map from given path.
   *
   * @param path
   *            the path of the serializable file
   * @throws ClassNotFoundException
   */
  protected static void readFromFile(String path) throws ClassNotFoundException {
    try {
      InputStream file = new FileInputStream(path);
      InputStream buffer = new BufferedInputStream(file);
      ObjectInput input;
      input = new ObjectInputStream(buffer);
      payments = (HashMap<Date, DailyPayments>) input.readObject();
      revenue = 0.0;
      for (Date date: payments.keySet()) {
        revenue += payments.get(date).getDailyIncome();
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
      output.writeObject(payments);
      output.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Gets daily payments for a restaurant in a particular day.
   *
   * @param date representing a particular day
   * @return DailyPayments representing the daily payments for a restaurant in a particular day.
   */
  public DailyPayments getDailyPayments(Date date) {
    return payments.get(date);
  }

  public DailyPayments getDailyPayments(String date) {
    for (Date d: payments.keySet()) {
      if (d.toString().equals(date)) {
        return payments.get(d);
      }
    }
    return null;
  }

  /**
   * Add daily payment to the static HashMap of daily payments record in the restaurant.
   *
   * @param dailyPayments representing daily payments record in the restaurant
   * @throws IOException the io exception
   */
  public void addPayment(DailyPayments dailyPayments) {
    payments.put(dailyPayments.getDate(), dailyPayments);
    revenue += dailyPayments.getDailyIncome();
    saveToFile(filePath);
  }

  /**
   * returns the total revenue.
   *
   * @return the revenue as a double.
   */
  public static Double getRevenue() {
    return revenue;
  }

  /**
   * returns all the payments of all days.
   *
   * @return the payments.
   */
  public static HashMap<Date, DailyPayments> getPayments() {
    return payments;
  }
}
