package ResApp.ManagerPage.CheckRevenue;

import ResApp.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.io.IOException;

/**
 * Represents a controller enables manager switching to Check-Revenue-Page to check revenue.
 */

public class CheckRevenuePage {

    @FXML
    /** ChoiceBox representing date selection*/
    private ChoiceBox dateSelection;
    @FXML
    /** ObservableList storing all dates selection*/
    private ObservableList<String> dates = FXCollections.observableArrayList();
    @FXML
    /** Label representing daily payments*/
    private Label dailyPayments;
    @FXML
    /** Label representing revenue*/
    private Label revenue;

    @FXML
    /** Initialize the screen with information of dates selection and daily payments */
    private void initialize() {
        dailyPayments.setText("");
        for (Date date: RestaurantSystem.getRecordManager().getPayments().keySet()) {
            dates.add(date.toString());
        }
        this.dateSelection.setItems(dates);
        revenue.setText("");
    }

    @FXML
    /**
     * Manager prints daily payments
     *
     */
    private void printDailyPayments() {
        String selectedDate = dateSelection.getValue().toString();
        DailyPayments dp = RestaurantSystem.getRecordManager().getDailyPayments(selectedDate);
        String result = dp.printDailyPayments();
        System.out.println(result);
        dailyPayments.setText(result);
        String result2 = RestaurantSystem.getRecordManager().getRevenue().toString();
        revenue.setText(result2);
    }
}
