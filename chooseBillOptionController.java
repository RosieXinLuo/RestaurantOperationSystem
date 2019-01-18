package ResApp.serverPage.PrintBillPage;

import ResApp.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Represents a controller enables server to choose bill.
 */

public class chooseBillOptionController {

    @FXML
    /** Textfield representing order number*/
    private ChoiceBox orderNum;
    @FXML
    /** ObservableList representing order numbesr*/
    private ObservableList<String> nums = FXCollections.observableArrayList();

    @FXML
    /**
     * Initialize the Page of choose-bill-option
     *
     * @throws IOException the io exception
     */
    private void initialize() {
        for (Integer orderNum: RestaurantSystem.getOrderManager().getOrders().keySet()) {
            nums.add(orderNum.toString());
        }
        this.orderNum.setItems(nums);
    }

    @FXML
    /**
     * Server prints the bill
     *
     * @throws IOException the io exception
     */
    private void printBill() throws IOException {
        //change table color
        String orderNumString = orderNum.getValue().toString();
        Order order = RestaurantSystem.getOrderManager().getOrders().get(Integer.parseInt(orderNumString));
        Table table = (Table) RestaurantSystem.getTables().get(String.valueOf(order.getTableNumber()));
        //set table status as "BillPrinted"
        table.setStatus("BillPrinted");
        helper(order);
    }

    @FXML
    /**
     * Generates show order bill page
     *
     */
    public void helper(Order order){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ShowOrderBill.fxml"));
            Parent root = loader.load();
            showOrderBillController sobController = loader.getController();
            sobController.showBillPage(order);
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

