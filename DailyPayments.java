package ResApp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/** Represents daily payment record for the restaurant and can add each bill into it. */
public class DailyPayments {
  /** The Date representing all the payments made on a particular day for the restaurant. */
  private Date date;
  /** The ArrayList to store bills which are recorded previously. */
  private ArrayList<Bill> AllPreviousBill;
  /** The Integer representing how many bills stored in the restaurant's daily payments */
  private int numBill;
  /** The Integer representing total daily income of the restaurant */
  private double dailyIncome;

  public DailyPayments() {
    date = new Date(); // current date
    AllPreviousBill = new ArrayList<Bill>();
    numBill = 0;
    dailyIncome = 0;
  }

  /**
   * Add bill into ArrayList storing bills which are recorded previously.
   *
   * @param bill representing a bill from an order
   * @throws IOException the io exception
   */
  public void addToPayments(Bill bill) throws IOException {
    System.out.println("asdkfaskldfja yoy o yoyoyoyoy");
    AllPreviousBill.add(bill);
    numBill += 1;
    dailyIncome += bill.getTotalPrice();
    System.out.println("daily Income is :" + dailyIncome);
    LoggerHelper.makeALog(
        "Bill for order "
            + bill.getOrderNum()
            + " is added into bill storage"
            + " ArrayList-payments");
  }

  /**
   * Gets how many bills stored in the restaurant's daily payments.
   *
   * @return Integer representing the number of bills stored in an restaurant's daily payments
   */
  public int getNumBill() {
    return numBill;
  }

  /**
   * Gets total daily income of the restaurant
   *
   * @return Double representing the total daily income made by the restaurant
   */
  public double getDailyIncome() {
    return dailyIncome;
  }

  /**
   * Gets date.
   *
   * @return Date representing a particular day for all the payments made on for the restaurant.
   */
  public Date getDate() {
    return date;
  }

  public String printDailyPayments() {
    String result = "";
    for (Bill b: AllPreviousBill) {
      result += b.toString() + "\n";
    }
    return result;
  }

  public void setDate(Date date) {
    this.date = date;
  }
}
