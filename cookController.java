package ResApp.cookPage;

import ResApp.Dish;
import ResApp.*;
import ResApp.RestaurantSystem;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

import javafx.scene.layout.*;

/**
 * Represents a controller to control the cook front-end page.
 * The cook controller
 */
public class cookController {
    /**
     * TextField representing cook log-in ID
     */
    @FXML
    private TextField CookID;
    /**
     * Representing cook instance
     */
    private static Cook cook;


    /**
     * Switch screen to Cook Log In Page
     *
     * @throws IOException the io exception
     */
    @FXML
    private void goLoginPage() throws IOException {
        RestaurantSystem.showLoginPage();
    }

    /**
     * Switch screen from Log-In to Cook Main Page
     *
     * @throws IOException the io exception
     */
    @FXML
    private void goCookMainPage() throws IOException {
        //if TextField is empty, it will generate a log-in invalid input error page
        if (CookID.getText().isEmpty()) {
            RestaurantSystem.showInvalidEnter();
        } else {
            //Cook log-in ID should be 1 - 9
            //otherwise it will generate a log-in invalid input error page
            if (CookID.getText().matches("[0-9]")) {
                String serverID = CookID.getText();
                //Check if this log-in ID has been constructed by manager
                if (RestaurantSystem.getHRManager().getCooks().containsKey(Integer.parseInt(serverID))) {
                    cook = (Cook) RestaurantSystem.getHRManager().getCooks().get(Integer.parseInt(serverID));

                    RestaurantSystem.showCookMainView();
                } else {

                    RestaurantSystem.showInvalidEnter();
                }
            } else {
                RestaurantSystem.showInvalidEnter();
            }
        }
    }

    /**
     * Switch screen to Receive Ingredient Page
     *
     * @throws IOException the io exception
     */
    @FXML
    private void goReceiveIngredientPage() throws IOException {
        RestaurantSystem.showReceiveIngredientPage();
    }

    /**
     * Cook accepts order.
     *
     * @param listViewWaitOrder ListView representing the list to view waiting order
     * @throws IOException the io exception
     */
    public static void acceptOrder(ListView listViewWaitOrder) throws IOException {

        Order order = (Order) listViewWaitOrder.getSelectionModel().getSelectedItem();
        if (order == null) {
            RestaurantSystem.showNoOrderNow();
        } else {
            if (cook.getCurrOrder() == null) {
                Table table = (Table) RestaurantSystem.getTables().get(String.valueOf(order.getTableNumber()));
                table.setStatus("isCooking");
                RestaurantSystem.getOrderManager().getToBeCooked().remove(order.getOrderNumber());
                cook.setCurrOrder(order);
                //current cook is cooking the order.
                cook.prepareOrder(order);
                System.out.println("cook order");
                RestaurantSystem.showCookMainView();
            } else {
                RestaurantSystem.showCookCanNotAcceptOrderNotice();
            }

        }
    }

    /**
     * Cook finishes dish.
     *
     * @param listViewCookingOrder ListView representing the list to view cooking order
     * @throws IOException the io exception
     */
    public static void finishDish(ListView listViewCookingOrder) throws IOException {

        Dish dish = (Dish)listViewCookingOrder.getSelectionModel().getSelectedItem();
        if(dish == null){
            RestaurantSystem.showNoDishNow();
        }else {
            cook.getCurrOrder().getCookedDishes().add(dish);
            cook.getCurrOrder().getDishes().remove(dish);
            if (cook.getCurrOrder().getDishes().size() == 0) {
                RestaurantSystem.getOrderManager().addToBeDelivered(cook.getCurrOrder());
                Table table = (Table) RestaurantSystem.getTables().get(String.valueOf(cook.getCurrOrder().getTableNumber()));
                table.setStatus("ReadyDeliver");
                cook.setCurrOrder(null);
            }
        }
        RestaurantSystem.showCookMainView();
    }



    /**
     * BorderPane representing cook main page with several buttons
     *
     */
    public static BorderPane drawToBeCookedList() {
        ToolBar toolBar = new ToolBar();
        Button accept = new Button("Accept Order");
        Button finish = new Button("Finish Dish");
        toolBar.getItems().addAll(accept, finish);
        BorderPane cookMain = new BorderPane();
        BorderPane subCookMain = new BorderPane();
        cookMain.setBottom(toolBar);
        cookMain.setPrefWidth(800);
        cookMain.setPrefHeight(400);
        cookMain.setLeft(subCookMain);
        ListView<Order> listViewWaitOrder = new ListView<>();
        ListView<Dish> listViewCookingDishesOfOrder = new ListView<>();
        listViewWaitOrder.setPrefSize(250, 400);
        listViewCookingDishesOfOrder.setPrefSize(250, 400);

        for (int orderNum : RestaurantSystem.getOrderManager().getToBeCooked().keySet()) {
            listViewWaitOrder.getItems().add(RestaurantSystem.getOrderManager().getToBeCooked().get(orderNum));
        }

        if (cook.getCurrOrder() != null) {
            for (int i = 0; i < cook.getCurrOrder().getDishes().size(); i++) {
                listViewCookingDishesOfOrder.getItems().add(cook.getCurrOrder().getDishes().get(i));
            }
        }
        subCookMain.setLeft(listViewWaitOrder);
        subCookMain.setRight(listViewCookingDishesOfOrder);

        //set the action for waiting order listView.
        listViewWaitOrder.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        accept.setOnAction(event -> {
            try {
                acceptOrder(listViewWaitOrder);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        // set the action for cooking order listView.
        listViewCookingDishesOfOrder.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        finish.setOnAction(event -> {
            try {
                finishDish(listViewCookingDishesOfOrder);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        return cookMain;
    }

}
