package ResApp.staffView;

import ResApp.RestaurantSystem;
import javafx.fxml.FXML;

import java.io.IOException;

/**
 * Represents a controller to show the main-log-in page
 */

public class MainLoginController {

    @FXML
    /**
     * Switches to Server LogIn Page
     *
     * @throws IOException the io exception
     */
    private void goServer() throws IOException {
        RestaurantSystem.showServerLogin();
    }

    @FXML
    /**
     * Switches to Cook LogIn Page
     *
     * @throws IOException the io exception
     */
    private void goCook() throws IOException {
        RestaurantSystem.showCookLogin();
    }

    @FXML
    /**
     * Switches to Manager LogIn Page
     *
     * @throws IOException the io exception
     */
    private void goManager() throws IOException {
        RestaurantSystem.showManagerLogin();
    }


}
