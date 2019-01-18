package ResApp.ReceiveIngredientPage;

import ResApp.RestaurantSystem;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import java.io.IOException;

/**
 * Represents a controller enables manager to receive ingredient.
 */

public class ReceiveController {
    @FXML
    /** TextField representing ingredient name*/
    private TextField IngName;
    @FXML
    /** TextField representing ingredient amount*/
    private TextField IngAmount;

    /**
     * Gets ingredient name
     *
     * @return String representing ingredient name
     */
    private String getIngName(){
        return this.IngName.getText();
    }
    /**
     * Gets ingredient amount
     *
     * @return Integer representing ingredient amount
     */
    private int getIngAmount(){
        return Integer.valueOf(this.IngAmount.getText());
    }

    @FXML
    /**
     * Manager receives ingredient and scan into inventory
     *
     * @throws IOException the io exception
     */
    private void receiveIngredient() throws IOException {
        RestaurantSystem.getInventory().addIngredient(getIngName(), getIngAmount());
        RestaurantSystem.showSuccessReceivePage();
    }

}

