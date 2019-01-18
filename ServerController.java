package ResApp.serverPage;

import ResApp.*;
import ResApp.Order;
import ResApp.RestaurantSystem;

import ResApp.Table;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

/** Represents a controller to control the server front-end page. The server controller */
public class ServerController {

  @FXML
  /** TextField representing server log-in ID */
  private TextField ServerID;
  /** RestaurantSystem representing the main class of restaurant APP */
  private RestaurantSystem Restaurant;
  /** Server representing server */
  public static Server server;


  /**
   * Initialize the Page of Server
   *
   */
  @FXML
  private void initialize() {
    ObservableList<String> data = FXCollections.observableArrayList("1", "2", "3");
    //        TobeDeliverDishes.
  }

  /**
   * Server accept deliver order
   *
   * @throws IOException the io exception
   */
  private static void acceptDeliverOrder(ListView listViewReadyOrder) throws IOException {
    Order order = (Order) listViewReadyOrder.getSelectionModel().getSelectedItem();
    if (order == null) {
      RestaurantSystem.showNoOrderNow();
    } else {
      RestaurantSystem.getOrderManager().getToBeDelivered().remove(order.getOrderNumber());
      server.setCurrOrder(order);
    }
    RestaurantSystem.showServerMainView();
  }

  /**
   * Server deliver dish
   *
   * @throws IOException the io exception
   */
  private static void deliverDish(ListView listViewCookingOrder) throws IOException {
    Dish dish = (Dish) listViewCookingOrder.getSelectionModel().getSelectedItem();
    if (dish == null) {
      RestaurantSystem.showNoDishNow();
    } else {
      server.getCurrOrder().getCookedDishes().remove(dish);
      server.getCurrOrder().getBill().addToBill(dish);
      DailyPayments currDate = new DailyPayments();
      currDate.addToPayments(server.getCurrOrder().getBill());
      RestaurantSystem.getRecordManager().addPayment(currDate);
      if (server.getCurrOrder().getCookedDishes().size() == 0
          && server.getCurrOrder().getDishes().size() == 0) {
        server.getCurrOrder().changeIsDelivered();
        Table table =
            (Table)
                RestaurantSystem.getTables()
                    .get(String.valueOf(server.getCurrOrder().getTableNumber()));
        table.setStatus("BillReady");
        server.setCurrOrder(null);
      }
    }
    // switch to Sever-Main page
    RestaurantSystem.showServerMainView();
  }

  /**
   * Server return dish
   *
   * @throws IOException the io exception
   */
  private static void returnDish(ListView listViewCookingOrder) throws IOException {
    Dish dish = (Dish) listViewCookingOrder.getSelectionModel().getSelectedItem();
    if (dish == null) {
      RestaurantSystem.showNoDishNow();
    } else {
      server.getCurrOrder().getDishes().add(dish);
      server.getCurrOrder().getCookedDishes().remove(dish);
      RestaurantSystem.getOrderManager().addToBeCooked(server.getCurrOrder());
      Table table =
          (Table)
              RestaurantSystem.getTables()
                  .get(String.valueOf(server.getCurrOrder().getTableNumber()));
      table.setStatus("hasOrder");
    }
    // switch to Sever-Main page
    RestaurantSystem.showServerMainView();
  }

  /**
   * BorderPane representing list of Left-Order
   *
   */
  public static BorderPane drawLeftOrderListPane() {
    ToolBar toolBar = new ToolBar();
    Button delivered = new Button("Dish Delivered");
    Button accept = new Button("Accept To Deliver Order");
    Button returnDish = new Button("Send Dish Back");

    toolBar.getItems().add(delivered);
    toolBar.getItems().add(accept);
    toolBar.getItems().add(returnDish);
    BorderPane left = new BorderPane();
    left.setBottom(toolBar);
    left.setPrefWidth(500);
    left.setPrefHeight(460);
    ListView<Order> listViewReadyOrder = new ListView<>();
    ListView<Dish> listViewReadyDishOfOrder = new ListView<>();
    listViewReadyOrder.setPrefSize(250, 460);
    listViewReadyDishOfOrder.setPrefSize(250, 460);
    accept.setOnAction(
        event -> {
          try {
            acceptDeliverOrder(listViewReadyOrder);
          } catch (IOException e) {
            e.printStackTrace();
          }
        });
    delivered.setOnAction(
        event -> {
          try {
            deliverDish(listViewReadyDishOfOrder);
          } catch (IOException e) {
            e.printStackTrace();
          }
        });
    returnDish.setOnAction(
        event -> {
          try {
            returnDish(listViewReadyDishOfOrder);
          } catch (IOException e) {
            e.printStackTrace();
          }
        });

    for (int orderNum : RestaurantSystem.getOrderManager().getToBeDelivered().keySet()) {
      listViewReadyOrder
          .getItems()
          .add(RestaurantSystem.getOrderManager().getToBeDelivered().get(orderNum));
    }
    if (server.getCurrOrder() != null) {
      for (int i = 0; i < server.getCurrOrder().getCookedDishes().size(); i++) {
        listViewReadyDishOfOrder
            .getItems()
            .add((Dish) server.getCurrOrder().getCookedDishes().get(i));
      }
    }
    left.setLeft(listViewReadyOrder);
    left.setRight(listViewReadyDishOfOrder);
    return left;
  }

  @FXML
  /**
   * Switch screen to Server Log-In Page
   *
   * @throws IOException the io exception
   */
  private void goLoginPage() throws IOException {
    RestaurantSystem.showLoginPage();
  }

  @FXML
  /**
   * Switch screen to Server Main-Page
   *
   * @throws IOException the io exception
   */
  private void goTablePage() throws IOException {
    // Server ID cannot be empty
    // Otherwise it will generate invalid input page
    if (ServerID.getText().isEmpty()) {
      RestaurantSystem.showInvalidEnter();
    } else {
      // Server ID should be restricted to digits of 0-9, then it switches into server main-page
      // Otherwise it will generate invalid input page
      if (ServerID.getText().matches("[0-9]")) {
        String serverID = ServerID.getText();

        if (RestaurantSystem.getHRManager().getServers().containsKey(Integer.parseInt(serverID))) {
          server = (Server) RestaurantSystem.getHRManager().getServers().get(Integer.parseInt(serverID));

          RestaurantSystem.showServerMainView();
        } else {

          RestaurantSystem.showInvalidEnter();
        }
      } else {
        RestaurantSystem.showInvalidEnter();
      }
    }
  }

  @FXML
  /**
   * Switch screen to Server receive-ingredient Page
   *
   * @throws IOException the io exception
   */
  private void goReceiveIngredientPage() throws IOException {
    RestaurantSystem.showReceiveIngredientPage();
  }

  @FXML
  /**
   * Switch screen to Server enter-order Page
   *
   * @throws IOException the io exception
   */
  private void goEnterOrderPage() throws IOException {
    if (server.getCurrOrder() == null) {

      RestaurantSystem.showEnterOrderPage();
    } else {
      RestaurantSystem.showCanNotEnterOrderPage();
    }
  }

  @FXML
  /**
   * Switch screen to Server print-bill Page
   *
   * @throws IOException the io exception
   */
  private void printBillOptions() throws IOException {
    RestaurantSystem.showPrintBillOptionsPage();
  }

  @FXML
  /**
   * Switch screen to Server seperate-bill Page
   *
   * @throws IOException the io exception
   */
  private void seperateBills() throws IOException {
    RestaurantSystem.seperateBillPage();
  }
}
