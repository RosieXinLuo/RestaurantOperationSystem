package ResApp.ManagerPage;

import ResApp.Order;
import ResApp.RestaurantSystem;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.io.IOException;

/**
 * Represents a controller to control the manager front-end page.
 * The manager controller
 */

public class ManagerControl {
    /**
     * TextField representing manager log-in ID
     */
    @FXML
    private TextField ManagerID;
    /**
     * TextField representing manager log-in password
     */
    @FXML
    private TextField ManagerPassWord;

    @FXML
    private Button checkOrders;



    /**
     * Switch screen to Manager Log In Page
     *
     * @throws IOException the io exception
     */
    @FXML
    private void goLoginPage() throws IOException {
        RestaurantSystem.showLoginPage();
    }

    /**
     * Switch screen from Log-In to Manager Main-Page
     *
     * @throws IOException the io exception
     */
    @FXML
    private void goManagerMainPage() throws IOException {
        String id = ManagerID.getText();
        String password = ManagerPassWord.getText();
        if(id.equals("1")) {
            if (password.equals("123")) {
                RestaurantSystem.showManagerMainView();
            }
        }else{
            RestaurantSystem.showInvalidEnter();
        }
    }

    /**
     * Switch screen to Add-New-Staff Page
     *
     * @throws IOException the io exception
     */
    @FXML
    private void goAddNewStaffPage() throws IOException {
        RestaurantSystem.showAddNewStaffPage();
    }

    /**
     * Switch screen to Check-Inventory Page
     *
     * @throws IOException the io exception
     */
    @FXML
    private void goInventoryPage() throws IOException {
        RestaurantSystem.showInventoryPage();
        RestaurantSystem.showManagerMainView();
    }

    /**
     * Switch screen to Receive-Ingredient Page
     *
     * @throws IOException the io exception
     */
    @FXML
    private void goReceiveIngredientPage() throws IOException {
        RestaurantSystem.showReceiveIngredientPage();
    }

    /**
     * Switch screen to Daily-Income Page
     *
     * @throws IOException the io exception
     */
    @FXML
    private void goDailyIncomePage() throws IOException {
        RestaurantSystem.showDailyIncomePage();
        RestaurantSystem.showManagerMainView();
    }

    /**
     * Switch screen to Set-Threshold Page
     *
     * @throws IOException the io exception
     */
    @FXML
    private void goSetThresholdPage() throws IOException {
        RestaurantSystem.showThresholdPage();
        RestaurantSystem.showManagerMainView();
    }

    @FXML
    private void goCheckOrder() throws IOException {
        RestaurantSystem.showCheckOrders();
    }

    public static BorderPane drawCheckOrdersView(){
        BorderPane pane = new BorderPane();
        ListView<Order> listViewAllOrder = new ListView<>();
        for(Integer orderNum: RestaurantSystem.getOrderManager().getToBeDelivered().keySet()){
            listViewAllOrder.getItems().add(RestaurantSystem.getOrderManager().getToBeDelivered().get(orderNum));
        }
        for(Integer orderNum: RestaurantSystem.getOrderManager().getToBeCooked().keySet()){
            listViewAllOrder.getItems().add(RestaurantSystem.getOrderManager().getToBeCooked().get(orderNum));
        }
        pane.setCenter(listViewAllOrder);
        return pane;
    }


}
