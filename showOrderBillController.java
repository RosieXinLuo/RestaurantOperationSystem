package ResApp.serverPage.PrintBillPage;

import ResApp.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

/**
 * Represents a controller enables server show order bill
 */

public class showOrderBillController {
    @FXML
    /** Label representing order detail*/
    private Label orderDetail;

    @FXML
    /**
     * Initialize the label orderDetail with text
     *
     */
    private void initialize(){
        this.orderDetail.setText("");

    }

    @FXML
    /**
     * Server prints the bill and stays on the server main page
     *
     * @param order Order representing order in the bill
     */
    void showBillPage(Order order) throws IOException {
        orderDetail.setText(RestaurantSystem.getOrderManager().printBill(order));
        RestaurantSystem.showServerMainView();
    }
}
