package ResApp;

import java.util.HashMap;

/**
 * Represents a table with table number, status, and current order.
 *
 * */
public class Table {
    private String tableNum;
    private String Status;
    private Order currOrder;

  /**
   * Instantiates a new Table.
   *
   * @param num String representing the table number
   */
  public Table(String num) {
        this.tableNum = num;
        this.Status = "empty";
        this.currOrder = null;
    }

  /**
   * Get status.
   *
   * @return string representing status
   */
  public String getStatus() {
        return this.Status;
    }

  /**
   * Set status.
   *
   * @param newStatus String representing the new status
   */
  public void setStatus(String newStatus) {
        this.Status = newStatus;
    }

  /**
   * Add order.
   *
   * @param order representing the order
   */
  public void addOrder(Order order) {
        this.currOrder = order;
    }

}
