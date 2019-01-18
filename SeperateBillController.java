package ResApp.serverPage.SeperateOrderPage;

import ResApp.Dish;
import ResApp.Order;
import ResApp.RestaurantSystem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;

/**
 * Represents a controller enables server to separate bill
 */
public class SeperateBillController {

    /**
     * Initialize the BoardPane with bill separation
     *
     */
    public static boolean toStartNew = false;
    public static BorderPane StartBillSeperation() {

        //left half: selection of dishes
        BorderPane left = new BorderPane();
        left.setPrefHeight(400);
        left.setPrefWidth(250);

        ListView<Dish> listView = new ListView<>();
        left.setCenter(listView);

        //order number selections.
        Label orderNumberLabel = new Label("Order# :");
        ChoiceBox orderSelection = new ChoiceBox();
        ObservableList<String> orderNumbers = FXCollections.observableArrayList();
        for (Integer orderNum: RestaurantSystem.getOrderManager().getOrders().keySet()) {
            orderNumbers.add(orderNum.toString());
        }
        orderSelection.setItems(orderNumbers);
        orderSelection.getSelectionModel().selectFirst();

        Button makeNewBill = new Button("Seperate Bill");
        Button printAsOne = new Button("Print As One Bill");
        Button finish = new Button("Finish");
        Button back = new Button("Back");

        orderSelection.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int newOrderNumber = Integer.valueOf(orderSelection.getValue().toString());
                Order newOrder = (Order)(RestaurantSystem.getOrderManager().getOrders().get(newOrderNumber));
                listView.getItems().clear();
                for (Dish d: newOrder.getDishes()) {
                    System.out.println("adding dish: " + d);
                    listView.getItems().add(d);
                }
            }
        });


        int toSelectOrderNumber = Integer.valueOf(orderSelection.getValue().toString());
        Order selectedOrder = (Order)(RestaurantSystem.getOrderManager().getOrders().get(toSelectOrderNumber));
        System.out.println(selectedOrder.getOrderNumber());

        // draw the middle part
        GridPane chooseToSeperateBill = new GridPane();
        chooseToSeperateBill.setStyle("-fx-background-color: white;");
        chooseToSeperateBill.setPrefHeight(400);
        chooseToSeperateBill.setPrefWidth(250);
        chooseToSeperateBill.add(orderNumberLabel, 1, 1);
        chooseToSeperateBill.add(orderSelection, 1, 2);
        chooseToSeperateBill.add(makeNewBill, 2, 1);
        chooseToSeperateBill.add(printAsOne, 3, 1);
        chooseToSeperateBill.add(finish, 3, 2);
        chooseToSeperateBill.add(back, 3,3);
        for (Dish d: selectedOrder.getDishes()) {
            System.out.println("adding dish: " + d);
            listView.getItems().add(d);
        }
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        // draw the right part
        GridPane right = new GridPane();
        chooseToSeperateBill.setStyle("-fx-background-color: white;");
        chooseToSeperateBill.setPrefHeight(300);
        chooseToSeperateBill.setPrefWidth(250);

        int rightIndex = 1;
        printAsOne.setOnAction(event -> printAsOneBill(right, selectedOrder));
        makeNewBill.setOnAction(event -> seperateNew(right, listView, selectedOrder, rightIndex));
        finish.setOnAction(event -> finishSeperatingBills(right, selectedOrder, rightIndex));
        back.setOnAction(event -> {
            try {
                RestaurantSystem.showServerMainView();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        BorderPane WholePage = new BorderPane();
        WholePage.setPrefHeight(500);
        WholePage.setPrefWidth(600);
        WholePage.setLeft(left);
        WholePage.setCenter(chooseToSeperateBill);
        WholePage.setRight(right);
        return WholePage;
    }

    /**
     *  Shows one bill GridPane
     *
     *  @param right GridPane representing the GridPane to show bill
     *  @param order Order representing the current order
     */
    private static void printAsOneBill(GridPane right, Order order) {
        right.getChildren().clear();
        Label oneBillLabel = new Label("");
        String oneBillInfo = "";
        try {
            oneBillInfo = RestaurantSystem.getOrderManager().printBill(order);
        } catch (IOException e) {
            e.printStackTrace();
        }
        oneBillLabel.setText(oneBillInfo);
        if(toStartNew)
            right.getChildren().clear();
//        right.add(oneBillLabel, 1, right.impl_getRowCount());
        toStartNew = true;
    }

    /**
     *  Server separates new bill from GridPane
     *
     *  @param right GridPane representing the GridPane to show bill
     *  @param listView ListView representing dishes stored in this bill
     *  @param order Order representing the current order
     *  @param i Integer representing row index
     */
    private static void seperateNew(GridPane right, ListView listView, Order order, int i) {
        Label seperatedBillLabel = new Label("");
        String seperatedBillInfo = "";
        ObservableList<Dish> seperatedDishes;
        seperatedDishes = listView.getSelectionModel().getSelectedItems();
        for(Dish d: seperatedDishes){
            Double dishPrice = order.getBill().subtractFromBill(d);
            seperatedBillInfo += d.getDishName() + "  $" + dishPrice + "x" + System.lineSeparator();
        }
        seperatedBillLabel.setText(seperatedBillInfo);
        if(toStartNew)
            right.getChildren().clear();
//        right.add(seperatedBillLabel, i, right.impl_getRowCount());
        toStartNew = false;
    }

    /**
     *  Server finishes separating bill from GridPane
     *
     *  @param right GridPane representing the GridPane to show bill
     *  @param order Order representing the current order
     *  @param i Integer representing row index
     */
    private static void finishSeperatingBills(GridPane right, Order order, int i) {
        Label restOfBill = new Label("");
        String restOfBillInfo = order.getBill().toString();
        restOfBill.setText(restOfBillInfo);
//        right.add(restOfBill, i, right.impl_getRowCount());
        toStartNew = true;
    }

}
