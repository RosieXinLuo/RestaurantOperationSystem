package ResApp.serverPage.EnterOrder;

import ResApp.*;
import ResApp.serverPage.ServerController;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;


import java.io.IOException;

/**
 * Represents a controller to enable sever enter order
 */

public class EnterOrderController {

    /**
     * Switches to server main page
     *
     */
    private static void Back() throws IOException {
        RestaurantSystem.showServerMainView();
    }

    /**
     * Server adds dish
     *
     * @param handleOrder Order representing order server is handling
     * @param selectDish Dish representing dish server is adding into
     */
    private static boolean addDish(Order handleOrder, Dish selectDish) throws IOException {

        boolean enoughIngredient = true;
        for (Ingredient ingredient : selectDish.getIngredientsList().keySet()) {
            if (ingredient.getQuantity() > Inventory.getInstance().getIngredients().get(ingredient.getIngredientName()).getQuantity()) {
                enoughIngredient = false;
            }
        }
        if(enoughIngredient){
            handleOrder.getDishes().add(selectDish);
            selectDish.setOrder(handleOrder);
            try {
                LoggerHelper.makeALog(selectDish.getDishName() + " is added into order " + handleOrder.getOrderNumber() +
                        " for table " + handleOrder.getTableNumber());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            ServerController.server.notifyInventoryManager();
            RestaurantSystem.showNotEnoughIngredient();
        }
        return enoughIngredient;
    }

    /**
     * Server adds dish
     *
     * @param handleDish Dish representing dish server is entering
     * @param preference Ingredient representing ingredient server adds
     */
    private static void addExtra(Dish handleDish, Ingredient preference) {
        handleDish.getIngredientsList().put(preference, handleDish.getIngredientsList().get(preference) + 1);
        if ("Beef,Cheese,Chicken,Pork,Tuna,Avocado".contains(preference.getIngredientName())) {
            handleDish.setPrice(handleDish.getPrice() + 1);
        } else if ("Tomato,Lettuce,Egg,Orange,Potato".contains(preference.getIngredientName())) {
            handleDish.setPrice(handleDish.getPrice() + 0.5);
        }
    }

    /**
     * Server adds dish
     *
     * @param handleDish Dish representing dish server is entering
     * @param preference Ingredient representing ingredient server minus
     */
    private static void minusLess(Dish handleDish, Ingredient preference) {
        handleDish.getIngredientsList().put(preference, handleDish.getIngredientsList().get(preference) - 1);
    }

    /**
     * Draw Menu for main dish
     *
     * @param itemName String representing main dish's name
     * @param newDish Dish representing main dish
     * @param extra Button representing ingredient additions
     * @param less Button representing ingredient subtractions
     */
    private static GridPane drawMenu2forMeal(String itemName, Dish newDish, Button extra, Button less) {
        //draw menu2 for preference
        GridPane menu2 = new GridPane();
        menu2.setPrefWidth(600);
        menu2.setPrefHeight(200);
        for (int j = 0; j < RestaurantSystem.getMenu().getMeals().get(itemName).getIngredientsList().size(); j++) {
            Ingredient eachIng = (Ingredient) RestaurantSystem.getMenu().getMeals().get(itemName).getIngredientsList().keySet().toArray()[j];
            Button ingButton = new Button(eachIng.getIngredientName());
            menu2.add(ingButton, j, 1);
            ingButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    less.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            minusLess(newDish, eachIng);
                            String pre = "Less " + eachIng.getIngredientName();
                            newDish.getPreference().add(pre);
                        }
                    });
                    extra.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            addExtra(newDish, eachIng);
                            String pre = "Extra " + eachIng.getIngredientName();
                            newDish.getPreference().add(pre);
                        }
                    });
                }

            });
        }
        return menu2;
    }

    /**
     * Draw Menu for drinks
     *
     * @param itemName String representing drink's name
     * @param newDish Dish representing drink dish
     * @param extra Button representing ingredient additions
     * @param less Button representing ingredient subtractions
     */
    private static GridPane drawMenu2forDrink(String itemName, Dish newDish, Button extra, Button less) {
        //draw menu2 for preference
        GridPane menu2 = new GridPane();
        menu2.setPrefWidth(600);
        menu2.setPrefHeight(200);
        for (int j = 0; j < RestaurantSystem.getMenu().getDrinks().get(itemName).getIngredientsList().size(); j++) {
            Ingredient eachIng = (Ingredient) RestaurantSystem.getMenu().getDrinks().get(itemName).getIngredientsList().keySet().toArray()[j];
            Button ingButton = new Button(eachIng.getIngredientName());
            menu2.add(ingButton, j, 1);
            ingButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    less.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            minusLess(newDish, eachIng);
                            String pre = "Less " + eachIng.getIngredientName();
                            newDish.getPreference().add(pre);
                        }
                    });
                    extra.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            addExtra(newDish, eachIng);
                            String pre = "Extra " + eachIng.getIngredientName();
                            newDish.getPreference().add(pre);
                        }
                    });
                }

            });
        }
        return menu2;
    }



    /**
     * Draw Border Pane for menu
     *
     */
    public static BorderPane drawMenu() throws IOException {

        //draw the right half menu page.
        BorderPane right = new BorderPane();
        right.setPrefWidth(300);
        right.setPrefHeight(400);

        ListView<Dish> listView = new ListView<>();

        right.setCenter(listView);


        //draw the left half menu page.
        Label EnterTableNum = new Label("Table# :");
        TextField tableNum = new TextField();
        tableNum.setPrefWidth(30);
        Label EnterPeopleNum = new Label("People# :");
        TextField peopleNum = new TextField();
        peopleNum.setPrefWidth(90);
        Button add = new Button("Add");
        Button extra = new Button("Extra");
        Button less = new Button("Less");

        Order newOrder = new Order();
        RestaurantSystem.getOrderManager().addToOrder(newOrder);


        //draw menu1 for menu items
        GridPane menu1 = new GridPane();
        menu1.setStyle("-fx-background-color: white;");
        menu1.setPrefHeight(200);
        menu1.setPrefWidth(600);
        menu1.add(EnterTableNum, 1, 1);
        menu1.add(tableNum, 2, 1);
        menu1.add(EnterPeopleNum, 3, 1);
        menu1.add(peopleNum, 4, 1);
        menu1.add(add, 6, 28);
        menu1.add(extra, 6, 27);
        menu1.add(less, 6, 26);


        //draw thw whole menu
        BorderPane left = new BorderPane();
        left.setPrefHeight(400);
        left.setPrefWidth(600);
        left.setTop(menu1);

        //add item buttons on menu1
        Button back = new Button("Back");
        back.setPrefWidth(50);
        back.setOnAction(new javafx.event.EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Back();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        Label quantity = new Label("quantity:");
        TextField num = new TextField();
        num.setPrefWidth(30);

        for (int i = 1; i <= 30; i++) {
            Label l1 = new Label("");
            menu1.add(l1, 6, i);
        }

        menu1.add(back, 6, 30);
        menu1.add(quantity, 6, 3);
        menu1.add(num, 6, 4);

        for (int i = 0; i < RestaurantSystem.getMenu().getMeals().size(); i++) {
            Label itemType = new Label("Meals");
            itemType.setStyle("-fx-background-color: white;");
            itemType.prefHeight(30);
            itemType.prefWidth(60);

            String itemName = String.valueOf(RestaurantSystem.getMenu().getMeals().keySet().toArray()[i]);
            Button dishButton = new Button(itemName);
            dishButton.setOnAction(new javafx.event.EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    Dish dish = RestaurantSystem.getMenu().newDish(RestaurantSystem.getMenu().getMeals().get(itemName));
                    dish.setQuantity(1);
                    left.setBottom(drawMenu2forMeal(itemName, dish, extra, less));

                    num.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            String dishQuantity = num.getText();
                            dish.setQuantity(Integer.valueOf(dishQuantity));
                        }
                    });
                    add.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            try {
                                if (addDish(newOrder, dish)) {
                                    listView.getItems().add(dish);
                                }

                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            });
            Label empty = new Label("       ");
            menu1.add(empty, 1, 2);
            menu1.add(itemType, 1, 3);
            menu1.add(dishButton, 1, i + 4);
        }


        for (int i = 0; i < RestaurantSystem.getMenu().getDrinks().size(); i++) {
            Label itemType = new Label("Drinks");
            itemType.prefHeight(30);
            itemType.prefWidth(60);

            String itemName = String.valueOf(RestaurantSystem.getMenu().getDrinks().keySet().toArray()[i]);
            Button dishButton = new Button(itemName);
            dishButton.setOnAction(new javafx.event.EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    Dish dish = RestaurantSystem.getMenu().newDish(RestaurantSystem.getMenu().getDrinks().get(itemName));
                    dish.setQuantity(dish.getQuantity() + 1);
                    left.setBottom(drawMenu2forDrink(itemName, dish, extra, less));

                    num.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            String dishQuantity = num.getText();
                            dish.setQuantity(Integer.valueOf(dishQuantity));
                        }
                    });
                    add.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            try {
                                if (addDish(newOrder, dish)) {
                                    listView.getItems().add(dish);
                                }

                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            });

            menu1.add(itemType, 2, 3);
            menu1.add(dishButton, 2, i + 4);
        }


        Button confirm = new Button("confirm");
        confirm.setOnAction(new EventHandler<ActionEvent>() {
            int TableNumber = 0;
            int PeopleNumber = 0;

            @Override
            public void handle(ActionEvent event) {
                if (tableNum.getText().matches("[1-6]") && peopleNum.getText().matches("[1-9]")) {
                    TableNumber = Integer.parseInt(tableNum.getText());
                    PeopleNumber = Integer.parseInt(peopleNum.getText());
                    newOrder.setNumPeople(PeopleNumber);
                    newOrder.setTableNumber(TableNumber);
                    Table table = (Table) RestaurantSystem.getTables().get(String.valueOf(newOrder.getTableNumber()));
                    table.setStatus("hasOrder");
                    table.addOrder(newOrder);
                    RestaurantSystem.getOrderManager().addToOrder(newOrder);
                    RestaurantSystem.getOrderManager().addToBeCooked(newOrder);
                    try {
                        RestaurantSystem.showServerMainView();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        RestaurantSystem.showNotice();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        });

        Button remove = new Button("remove");
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        remove.setOnAction(event -> removeDish(listView, newOrder));
        menu1.add(remove, 6, 29);


        menu1.add(confirm, 6, 6);

        BorderPane WholePage = new BorderPane();
        WholePage.setPrefHeight(500);
        WholePage.setPrefWidth(600);
        WholePage.setLeft(left);
        WholePage.setRight(right);
        for (Dish d : newOrder.getDishes()) {
            System.out.println(d.toString());
        }

        return WholePage;
    }


    /**
     * Draw Menu for main dish
     *
     * @param listView ListView representing dish to be removed
     * @param newOrder Order representing order that dish belongs to
     */
    private static void removeDish(ListView listView, Order newOrder){
        Dish removeD = (Dish)listView.getSelectionModel().getSelectedItem();
        listView.getItems().remove(removeD);
        newOrder.getDishes().remove(removeD);
    }


}
