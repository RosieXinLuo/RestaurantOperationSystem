package ResApp.ManagerPage.InventoryPage;

import ResApp.RestaurantSystem;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Represents a controller enables to generate Inventory Page
 */

public class inventoryController {
    @FXML
    /** Label representing label of Inventory */
    private Label InventoryOverview;

    @FXML
    /** Initialize the screen with information of inventory print information */
    private void initialize() {
        this.InventoryOverview.setText(RestaurantSystem.getInventory().toString());
        this.InventoryOverview.setText(RestaurantSystem.getInventoryManager().printInventory());
    }

}
