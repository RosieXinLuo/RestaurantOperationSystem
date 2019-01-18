package ResApp;

import ResApp.*;
import ResApp.ManagerPage.ManagerControl;
import ResApp.cookPage.cookController;
import ResApp.serverPage.EnterOrder.EnterOrderController;
import ResApp.serverPage.ServerController;
import ResApp.serverPage.SeperateOrderPage.SeperateBillController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.*;
import javafx.application.Application;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.util.Date;

import java.io.IOException;
import java.util.HashMap;

import ResApp.*;
import javafx.scene.*;

/**
 * Represents main class for restaurant management app
 */

public class RestaurantSystem extends Application {

  /** BorderPane represents main layout*/
  private static BorderPane mainLayout;
  /** Stage represents primary stage*/
  private static Stage primaryStage;
  /** new static inventory*/
  private static Inventory inventory = Inventory.getInstance();
  /** new HR manager*/
  private static HRManager hrManager = new HRManager();
  /** new record manager*/
  private static RecordManager recordManager = new RecordManager();
  /** new order manager*/
  private static OrderManager orderManager = new OrderManager();
  /** new inventory manager*/
  private static InventoryManager inventoryManager = new InventoryManager();
  /** Hash Map representing tables stored in the restaurant*/
  private static HashMap<String, Table> Tables = new HashMap<>();
  /** new static Menu*/
  private static Menu menu = new Menu();

  /**
   * Instantiates a new Restaurant system.
   *
   * @throws IOException the io exception
   */
  public RestaurantSystem() throws IOException {
    for (int i = 1; i < 7; i++) {
      Table newTable = new Table(String.valueOf(i));
      Tables.put(String.valueOf(i), newTable);
    }
    menu.buildMenu();
  }

  /**
   * Gets tables.
   *
   * @return the tables
   */
  public static HashMap getTables() {
    return Tables;
  }

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {
    launch(args);
  }

  /**
   * Gets hr manager.
   *
   * @return the hr manager
   */
  public static HRManager getHRManager() {
    return hrManager;
  }

  /**
   * Gets record manager.
   *
   * @return the record manager
   */
  public static RecordManager getRecordManager() {
    return recordManager;
  }

  /**
   * Gets inventory manager.
   *
   * @return the inventory manager
   */
  public static InventoryManager getInventoryManager() {
    return inventoryManager;
  }

  /**
   * Gets menu.
   *
   * @return the menu
   */
  public static Menu getMenu() {
    return menu;
  }

  /**
   * Gets order manager.
   *
   * @return the order manager
   */
  public static OrderManager getOrderManager() {
    return orderManager;
  }

  @Override
  /**
   * Gets start
   *
   */
  public void start(Stage primaryStage) throws IOException {
    this.primaryStage = primaryStage;
    this.primaryStage.setTitle("ResApp");
    showRootPage();
  }

  /**
   * Gets inventory.
   *
   * @return the inventory
   */
  public static Inventory getInventory() {
    return inventory;
  }

  /**
   * Switches to root Page
   *
   * @throws IOException the io exception
   */
  private void showRootPage() throws IOException {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(RestaurantSystem.class.getResource("staffView/LoginPage.fxml"));
    mainLayout = loader.load();
    Scene scene = new Scene(mainLayout);
    this.primaryStage.setScene(scene);
    this.primaryStage.show();
  }

  /**
   * Switches to main LogIn Page
   *
   * @throws IOException the io exception
   */
  public static void showLoginPage() throws IOException {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(RestaurantSystem.class.getResource("staffView/LoginPage.fxml"));
    mainLayout = loader.load();
    Scene scene = new Scene(mainLayout);
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  /**
   * Switches to Server LogIn Page
   *
   * @throws IOException the io exception
   */
  public static void showServerLogin() throws IOException {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(RestaurantSystem.class.getResource("serverPage/serverLogin.fxml"));
    BorderPane serverLogin = loader.load();
    mainLayout.setCenter(serverLogin);
  }

  /**
   * Switches to Cook LogIn Page
   *
   * @throws IOException the io exception
   */
  public static void showCookLogin() throws IOException {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(RestaurantSystem.class.getResource("cookPage/cookLogin.fxml"));
    BorderPane cookLogin = loader.load();
    mainLayout.setCenter(cookLogin);
  }

  /**
   * Switches to Manager LogIn Page
   *
   * @throws IOException the io exception
   */
  public static void showManagerLogin() throws IOException {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(RestaurantSystem.class.getResource("ManagerPage/ManagerLogin.fxml"));
    BorderPane ManagerLogin = loader.load();
    mainLayout.setCenter(ManagerLogin);
  }

  /**
   * Switches to Server main Page
   *
   * @throws IOException the io exception
   */
  public static void showServerMainView() throws IOException {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(RestaurantSystem.class.getResource("serverPage/serverMainPage.fxml"));
    mainLayout = loader.load();
    mainLayout.setLeft(ServerController.drawLeftOrderListPane());
    Scene scene = new Scene(mainLayout);
    primaryStage.setScene(scene);
    primaryStage.show();
    showTableOverview();
  }

  /**
   * Switches to Enter Order Page
   *
   * @throws IOException the io exception
   */
  public static void showEnterOrderPage() throws IOException {

    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(RestaurantSystem.class.getResource("serverPage/EnterOrder/menu.fxml"));
    mainLayout = loader.load();
    mainLayout.setCenter(EnterOrderController.drawMenu());
    Scene scene = new Scene(mainLayout);
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  /**
   * Switches to Separate Bill Page
   *
   * @throws IOException the io exception
   */
  public static void seperateBillPage() throws IOException {

    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(
        RestaurantSystem.class.getResource("serverPage/SeperateOrderPage/SeperateBill.fxml"));
    mainLayout = loader.load();
    mainLayout.setCenter(SeperateBillController.StartBillSeperation());
    Scene scene = new Scene(mainLayout);
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  /**
   * Switches to Notice Page
   *
   * @throws IOException the io exception
   */
  public static void showNotice() throws IOException {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(RestaurantSystem.class.getResource("serverPage/EnterOrder/Notice.fxml"));
    BorderPane noticePage = loader.load();

    Stage stage = new Stage();
    stage.setTitle("Error");
    stage.initModality(Modality.WINDOW_MODAL);
    stage.initOwner(primaryStage);
    Scene scene = new Scene(noticePage);
    stage.setScene(scene);
    stage.showAndWait();
  }

  /**
   * Switches to manager main Page
   *
   * @throws IOException the io exception
   */
  public static void showManagerMainView() throws IOException {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(RestaurantSystem.class.getResource("ManagerPage/managerMainPage.fxml"));
    mainLayout = loader.load();
    Scene scene = new Scene(mainLayout);
    primaryStage.setScene(scene);
    primaryStage.show();
    showTableOverview();
  }

  /**
   * Switches to cook main Page
   *
   * @throws IOException the io exception
   */
  public static void showCookMainView() throws IOException {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(RestaurantSystem.class.getResource("cookPage/cookMainPage.fxml"));
    mainLayout = loader.load();
    mainLayout.setLeft(cookController.drawToBeCookedList());
    Scene scene = new Scene(mainLayout);
    primaryStage.setScene(scene);
    primaryStage.show();
    showTableOverview();
  }

  /**
   * Switches to table overview Page
   *
   * @throws IOException the io exception
   */
  public static void showTableOverview() throws IOException {
    helper3("tableView/TablePage.fxml");
  }

  /**
   * Switches to add-new-staff Page
   *
   * @throws IOException the io exception
   */
  public static void showAddNewStaffPage() throws IOException {
    helper3("ManagerPage/addNewStaff/addNewStaffPage.fxml");
  }

  /**
   * Switches to show-inventory Page
   *
   * @throws IOException the io exception
   */
  public static void showInventoryPage() throws IOException {
    helper2("ManagerPage/InventoryPage/inventory.fxml","inventory overview");
  }

  /**
   * Switches to show-receive-ingredient Page
   *
   * @throws IOException the io exception
   */
  public static void showReceiveIngredientPage() throws IOException {
    helper2("ReceiveIngredientPage/ReceivePage.fxml","Receive Ingredient");
  }

  /**
   * Switches to show-success-receive Page
   *
   * @throws IOException the io exception
   */
  public static void showSuccessReceivePage() throws IOException {
    helper2("ReceiveIngredientPage/successReceive.fxml","Added Notice");
  }

  /**
   * Switches to show-print--bill Page
   *
   * @throws IOException the io exception
   */
  public static void showPrintBillOptionsPage() throws IOException {
    helper2("serverPage/PrintBillPage/ChooseBillOptions.fxml","Choose Bill");
  }

  /**
   * Switches to show-error-invalid-input Page4
   *
   * @throws IOException the io exception
   */
  public static void showErrorPage4() throws IOException {
    helper2("SetThresholdPage/ErrorPage4.fxml","Threshold Set Error");
  }

  /**
   * Switches to show-daily-income page
   *
   * @throws IOException the io exception
   */
  public static void showDailyIncomePage() throws IOException {
    helper2("ManagerPage/CheckRevenue/ShowIncomePage.fxml","DailyIncome overview");
  }

  /**
   * Switches to show-add-new-staff-error-page
   *
   * @throws IOException the io exception
   */
  public static void showAddStaffErrorPage() throws IOException {
    helper2("ManagerPage/addNewStaff/AddError.fxml","Error");
  }
  /**
   * Switches to show-Success-Set-Threshold Page
   *
   * @throws IOException the io exception
   */
  public static void showSuccessSetThresholdPage() throws IOException {
    helper2("SetThresholdPage/successSetThreshold.fxml","Threshold Set");
  }

  /**
   * Switches to show-Threshold Page
   *
   * @throws IOException the io exception
   */
  public static void showThresholdPage() throws IOException {
    helper2("SetThresholdPage/ThresholdPage.fxml","Threshold SetUp");
  }

  /**
   * Switches to show-Cook-Can-Not-Accept-Order-Notice Page
   *
   * @throws IOException the io exception
   */
  public static void showCookCanNotAcceptOrderNotice() throws IOException {
    helper2("cookPage/CannotAcceptOrderError.fxml","Notice");
  }

  /**
   * Switches to show-No-Order Page
   *
   * @throws IOException the io exception
   */
  public static void showNoOrderNow() throws IOException {
    helper1("cookPage/NoOrderNotice.fxml");
  }

  /**
   * Switches to show-No-Dish-Now Page
   *
   * @throws IOException the io exception
   */
  public static void showNoDishNow() throws IOException {
    helper1("cookPage/NoDishNotice.fxml");
  }

  /**
   * Switches to show-Can-Not-Enter-Order page
   *
   * @throws IOException the io exception
   */
  public static void showCanNotEnterOrderPage() throws IOException {
    helper1("serverPage/CanNotEnterOrderNotice.fxml");
  }

  /**
   * Switches to show-Not-Enough-Ingredient page
   *
   * @throws IOException the io exception
   */
  public static void showNotEnoughIngredient() throws IOException {

    helper1("serverPage/EnterOrder/NotEnoughIngredient.fxml");
  }

  /**
   * Switches to show-Invalid-Enter page
   *
   * @throws IOException the io exception
   */
  public static void showInvalidEnter() throws IOException {
    helper1("staffView/InvalidEnterNotice.fxml");
  }

  /**
   * Switches to show-Check-Orders page
   *
   * @throws IOException the io exception
   */
  public static void showCheckOrders() throws IOException {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(RestaurantSystem.class.getResource("ManagerPage/CheckOrders/CheckOrders.fxml"));
    BorderPane ordersPage = loader.load();
    ordersPage.setCenter(ManagerControl.drawCheckOrdersView());
    Stage OrderStage = new Stage();
    OrderStage.initModality(Modality.WINDOW_MODAL);
    OrderStage.initOwner(primaryStage);
    Scene scene = new Scene(ordersPage);
    OrderStage.setScene(scene);
    OrderStage.showAndWait();
  }

  /**
   * Build BorderPane with filename
   *
   * @throws IOException the io exception
   */
  public static void helper1(String filename)throws IOException{
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(RestaurantSystem.class.getResource(filename));
    BorderPane Page = loader.load();
    Stage Stage = new Stage();
    Stage.initModality(Modality.WINDOW_MODAL);
    Stage.initOwner(primaryStage);
    Scene scene = new Scene(Page);
    Stage.setScene(scene);
    Stage.showAndWait();
  }

  /**
   * Build BorderPane with filename and title
   *
   * @throws IOException the io exception
   */
  public static void helper2(String filename, String title)throws IOException{
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(RestaurantSystem.class.getResource(filename));
    BorderPane Page = loader.load();
    Stage Stage = new Stage();
    Stage.setTitle(title);
    Stage.initModality(Modality.WINDOW_MODAL);
    Stage.initOwner(primaryStage);
    Scene scene = new Scene(Page);
    Stage.setScene(scene);
    Stage.showAndWait();
  }

  /**
   * Switch page helper1
   *
   * @throws IOException the io exception
   */
  public static void helper3(String filename)throws IOException{
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(
            RestaurantSystem.class.getResource(filename));
    BorderPane Page = loader.load();
    mainLayout.setLeft(Page);
  }

  /**
   * Switch page helper2
   *
   * @throws IOException the io exception
   */
  public static void helper4(String filename)throws IOException{
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(RestaurantSystem.class.getResource(filename));
    mainLayout = loader.load();
    mainLayout.setLeft(cookController.drawToBeCookedList());
    Scene scene = new Scene(mainLayout);
    primaryStage.setScene(scene);
    primaryStage.show();
    showTableOverview();
  }

}
