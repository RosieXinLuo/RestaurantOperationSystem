package ResApp.ManagerPage.addNewStaff;

import ResApp.RestaurantSystem;
import ResApp.Staff;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

/**
 * Represents a controller enables to generate a screen show that staff is successfully added.
 */

public class EndPageController {

    @FXML
    /** Label representing new staff*/
    private Label newStaffAdded;

    @FXML
    /** Initialize the screen with information of staff added successfully */
    private void initialize(){
        this.newStaffAdded.setText("");
    }

    /**
     * Show the page of staff added successfully.
     *
     * @param staff added staff.
     * @throws IOException the io exception
     */
    @FXML
    void showEndPage(Staff staff) throws IOException {
        if (staff.getServerOrCook() == 0) {
            newStaffAdded.setText("A new server has been added to the system, id: " + staff.getId());
        } else {
            newStaffAdded.setText("A new cook has been added to the system, id: " + staff.getId());
        }
        RestaurantSystem.showManagerMainView();
    }
}
