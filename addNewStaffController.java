package ResApp.ManagerPage.addNewStaff;

import ResApp.RestaurantSystem;
import ResApp.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.collections.*;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Represents a controller enables manager to add staff.
 */

public class addNewStaffController {

    @FXML
    /** ChoiceBox representing types of staff*/
    private ChoiceBox staffTypes;
    /**
     * ObservableList representing types of staff
     */
    private ObservableList<String> types = FXCollections.observableArrayList("Server", "Cook");

    @FXML
    /**
     * Initialize the Page of add-new-staff
     *
     * @throws IOException the io exception
     */
    private void initialize() {
        this.staffTypes.setItems(types);
        this.staffTypes.getSelectionModel().selectFirst();
    }

    @FXML
    /**
     * Switch screen to Cook Log-In Page
     *
     * @throws IOException the io exception
     */
    private void goLoginPage() throws IOException {
        RestaurantSystem.showLoginPage();
    }

    @FXML
    /**
     * Manager adds new staff
     *
     * @throws IOException the io exception
     */
    private void addNewStaff() throws IOException {
        String staffType = staffTypes.getValue().toString();
        if (staffType.equals("Server")) {
            Server newServer = new Server();
            RestaurantSystem.getHRManager().addServer(newServer);
            showSuccessfulPage(newServer);
        } else if (staffType.equals("Cook")) {
            Cook newCook = new Cook();
            RestaurantSystem.getHRManager().addCook(newCook);
            showSuccessfulPage(newCook);
        }
    }

    /**
     * Screen to End page.
     *
     * @param staff the staff added
     */
    public void showSuccessfulPage(Staff staff) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EndPage.fxml"));
            Parent root = (Parent) loader.load();
            EndPageController epController = loader.getController();
            epController.showEndPage(staff);
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
