package ResApp.tableView;


import ResApp.Table;
import ResApp.RestaurantSystem;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

/**
 * Represents a controller to show table status for the restaurant
 */

public class tableController {

    @FXML
    /** Button represents Table1*/
    private Button Table1;
    @FXML
    /** Button represents Table2*/
    private Button Table2;
    @FXML
    /** Button represents Table3*/
    private Button Table3;
    @FXML
    /** Button represents Table4*/
    private Button Table4;
    @FXML
    /** Button represents Table5*/
    private Button Table5;
    @FXML
    /** Button represents Table6*/
    private Button Table6;

    @FXML
    /**
     * Initialize six tables with status and color
     *
     */
    private void initialize() {
        this.Table1.setStyle("-fx-background-color: white");
        this.Table2.setStyle("-fx-background-color: white");
        this.Table3.setStyle("-fx-background-color: white");
        this.Table4.setStyle("-fx-background-color: white");
        this.Table5.setStyle("-fx-background-color: white");
        this.Table6.setStyle("-fx-background-color: white");
        Table table1 = (Table)RestaurantSystem.getTables().get("1");
        Table table2 = (Table)RestaurantSystem.getTables().get("2");
        Table table3 = (Table)RestaurantSystem.getTables().get("3");
        Table table4 = (Table)RestaurantSystem.getTables().get("4");
        Table table5 = (Table)RestaurantSystem.getTables().get("5");
        Table table6 = (Table)RestaurantSystem.getTables().get("6");
            if (table1.getStatus().equals("hasOrder")) {
                this.Table1.setStyle("-fx-background-color: #ffa500; ");
            }else if(table1.getStatus().equals("isCooking")){
                this.Table1.setStyle("-fx-background-color: #ff6347; ");
            }else if(table1.getStatus().equals("ReadyDeliver")){
                this.Table1.setStyle("-fx-background-color: #9acd32; ");
            } else if (table1.getStatus().equals("BillReady")) {
                this.Table1.setStyle("-fx-background-color: #add8e6");
            }else if (table1.getStatus().equals("BillPrinted")) {
                this.Table1.setStyle("-fx-background-color: white");
            }
        if (table2.getStatus().equals("hasOrder")) {
            this.Table2.setStyle("-fx-background-color: #ffa500; ");
        }else if(table2.getStatus().equals("isCooking")){
            this.Table2.setStyle("-fx-background-color: #ff6347; ");
        }else if(table2.getStatus().equals("ReadyDeliver")){
            this.Table2.setStyle("-fx-background-color: #9acd32; ");
        }else if (table2.getStatus().equals("BillReady")) {
            this.Table2.setStyle("-fx-background-color: #add8e6");
        }else if (table2.getStatus().equals("BillPrinted")) {
            this.Table2.setStyle("-fx-background-color: white");
        }
        if (table3.getStatus().equals("hasOrder")) {
            this.Table3.setStyle("-fx-background-color: #ffa500; ");
        }else if(table3.getStatus().equals("isCooking")){
            this.Table3.setStyle("-fx-background-color: #ff6347; ");
        }else if(table3.getStatus().equals("ReadyDeliver")){
            this.Table3.setStyle("-fx-background-color: #9acd32; ");
        }else if (table3.getStatus().equals("BillReady")) {
            this.Table3.setStyle("-fx-background-color: #add8e6");
        }else if (table3.getStatus().equals("BillPrinted")) {
            this.Table3.setStyle("-fx-background-color: white");
        }
        if (table4.getStatus().equals("hasOrder")) {
            this.Table4.setStyle("-fx-background-color: #ffa500; ");
        }else if(table4.getStatus().equals("isCooking")){
            this.Table4.setStyle("-fx-background-color: #ff6347; ");
        }else if(table4.getStatus().equals("ReadyDeliver")){
            this.Table4.setStyle("-fx-background-color: #9acd32; ");
        }else if (table4.getStatus().equals("BillReady")) {
            this.Table4.setStyle("-fx-background-color: #add8e6");
        }else if (table4.getStatus().equals("BillPrinted")) {
            this.Table4.setStyle("-fx-background-color: white");
        }
        if (table5.getStatus().equals("hasOrder")) {
            this.Table5.setStyle("-fx-background-color: #ffa500; ");
        }else if(table5.getStatus().equals("isCooking")){
            this.Table5.setStyle("-fx-background-color: #ff6347; ");
        }else if(table5.getStatus().equals("ReadyDeliver")){
            this.Table5.setStyle("-fx-background-color: #9acd32; ");
        }else if (table5.getStatus().equals("BillPrinted")) {
            this.Table5.setStyle("-fx-background-color: #add8e6");
        }else if (table5.getStatus().equals("BillPrinted")) {
            this.Table5.setStyle("-fx-background-color: white");
            }
        if (table6.getStatus().equals("hasOrder")) {
            this.Table6.setStyle("-fx-background-color: #ffa500; ");
        }else if(table6.getStatus().equals("isCooking")){
            this.Table6.setStyle("-fx-background-color: #ff6347; ");
        }else if(table6.getStatus().equals("ReadyDeliver")){
            this.Table6.setStyle("-fx-background-color: #9acd32; ");
        }else if (table6.getStatus().equals("BillReady")) {
            this.Table6.setStyle("-fx-background-color: #add8e6");
        }else if (table6.getStatus().equals("BillPrinted")) {
            this.Table6.setStyle("-fx-background-color: white");
        }
    }


    @FXML
    /**
     * Switches to main LogIn Page
     *
     * @throws IOException the io exception
     */
    private void goLogin() throws IOException {
        RestaurantSystem.showLoginPage();
    }

}
