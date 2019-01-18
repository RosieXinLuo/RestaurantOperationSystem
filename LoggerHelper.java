package ResApp;

import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.io.IOException;

/**
 * This is a logger helper class. It is used to log messages in the events.txt does something specific.
 * For example, to log the creation of orders, every time the cook confirms that they have received an order,
 * they made the dish, and that the dish has been delivered.
 * </p>
 * Also, it can be used to log error related to invalid input from the events.txt
 */
public class LoggerHelper {

  /** The Logger. */
  static Logger logger = Logger.getLogger("App.LoggerHelper");

  /**
   * Make a log message to record all transactions happened in the events.txt.
   *
   * @param logMessage String representing the log message
   * @throws IOException the io exception
   */
  public static void makeALog(String logMessage) throws IOException {
    logger.setUseParentHandlers(false);
    FileHandler handler = new FileHandler("log.txt", true);

    logger.addHandler(handler);
    handler.setFormatter(new SimpleFormatter());
    logger.info(logMessage);
    handler.close();
  }
}
