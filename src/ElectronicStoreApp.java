//THIS IS THE CONTROLLER


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;


public class ElectronicStoreApp extends Application {
    private ElectronicStore model;
    private ElectronicStoreView view;


    @Override
    public void start(Stage primaryStage) throws Exception {
        model = ElectronicStore.createStore();
        view = new ElectronicStoreView(model);

        view.getBtnAdd().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                handleAdd();
            }
        });


        view.getBtnRemove().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                handleRemove();
            }
        });

        view.getBtnCompleteSale().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                handleSale();
            }
        });

        view.getBtnReset().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    start(primaryStage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        view.getLstStock().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                view.update();
            }
        });

        view.getLstCart().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                view.update();
            }
        });

        Scene scene = new Scene(view, 800, 400);
        primaryStage.setTitle(model.getName());
        primaryStage.setResizable(false);
        primaryStage.setScene(scene); //set size of window
        primaryStage.show();
    }


    public void handleAdd(){
        String productDescription = view.getLstStock().getSelectionModel().getSelectedItem();
        int productIndex = searchForProductIndex(productDescription);

        if(productIndex != -1)
            model.addToCart(productIndex);

        //update the view
        view.update();
    }


    public void handleRemove(){
        String productDescription = view.getLstCart().getSelectionModel().getSelectedItem();
        int productIndex = searchForProductIndex(productDescription);
        int listIndex = view.getLstCart().getSelectionModel().getSelectedIndex();

        if(productIndex != -1)
            model.removeFromCart(productIndex, listIndex);

        //update the view
        view.update();
    }


    /**
     * @param productDescription the toString output of the selected product
     * @return the index that in the products array that the product description matches
     */
    private int searchForProductIndex(String productDescription){
        ArrayList<Product> productList = new ArrayList<>(Arrays.asList(model.getProducts()));
        for(int i = 0; i < productList.size(); ++i){
            Product p = productList.get(i);
            if(p != null && p.toString().equals(productDescription)){
                return i;
            }
        }
        return -1;
    }


    public void handleSale(){
        model.sellCart();
        view.update();
    }
}
