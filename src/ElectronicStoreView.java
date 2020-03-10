//THIS IS THE VIEW

import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public class ElectronicStoreView extends Pane {
    ElectronicStore model;

    //widgets:
    private Button btnReset;
    private Button btnAdd;
    private Button btnRemove;
    private Button btnCompleteSale;

    private Label lblSales;
    private Label lblRevenue;
    private Label lblDollarsPerSale;
    private Label lblSummary;
    private Label lblPopItems;
    private Label lblStock;
    private Label lblCart;

    private TextField txtSales;
    private TextField txtRevenue;
    private TextField txtDollarsPerSale;

    private ListView<String> lstPopItems;
    private ListView<String> lstStock;
    private ListView<String> lstCart;


    public ElectronicStoreView(ElectronicStore model){
        this.model = model;

        //add stylesheet:
        getStylesheets().add("electronicStoreStyle.css");

        //Initialize store summary widgets
        lblSummary = new Label("Store Summary:");
        lblSales = new Label("# Sales:");
        lblRevenue = new Label("Revenue:");
        lblDollarsPerSale = new Label("$ / Sale:");
        txtSales = new TextField();
        txtSales.setEditable(false);
        txtRevenue = new TextField();
        txtRevenue.setEditable(false);
        txtDollarsPerSale = new TextField();
        txtDollarsPerSale.setEditable(false);
        //contain summary widgets
        VBox storeSummaryLabelContainer = new VBox(20, lblSales, lblRevenue, lblDollarsPerSale);
        VBox storeSummaryTextFieldContainer = new VBox(10, txtSales, txtRevenue, txtDollarsPerSale);
        HBox storeSummaryContainer = new HBox(20, storeSummaryLabelContainer,storeSummaryTextFieldContainer);

        //initialize lists and their corresponding labels
        lblPopItems = new Label("Most Popular Items:");
        lblStock = new Label("Store Stock:");
        lblCart = new Label("Current Cart:");
        lstPopItems = new ListView<>();
        lstStock = new ListView<>();
        lstCart = new ListView<>();

        //Initialize Buttons
        btnReset = new Button("Reset Store");
        btnAdd = new Button("Add to Cart");
        btnRemove = new Button("Remove from Cart");
        btnCompleteSale = new Button("Complete Sale");

        //contain buttons
        HBox buttonBox = new HBox(20, btnRemove, btnCompleteSale);

        //position widgets using containers
        VBox leftCol = new VBox(20, lblSummary, storeSummaryContainer, lblPopItems, lstPopItems, btnReset);
        VBox.setVgrow(lstPopItems, Priority.ALWAYS);
        VBox midCol = new VBox(20, lblStock, lstStock, btnAdd);
        VBox rightCol = new VBox(20, lblCart, lstCart, buttonBox);
        HBox totalContainer = new HBox(30, leftCol,midCol,rightCol);
        totalContainer.relocate(15, 10);
        totalContainer.setAlignment(Pos.TOP_CENTER);
        midCol.setMinWidth(270);
        rightCol.setMinWidth(270);
        midCol.setAlignment(Pos.TOP_CENTER);
        leftCol.setAlignment(Pos.TOP_CENTER);
        rightCol.setAlignment(Pos.TOP_CENTER);

        //set css classes for styling:
        lblSummary.getStyleClass().add("title");
        lblStock.getStyleClass().add("title");
        lblCart.getStyleClass().add("title");
        lblPopItems.getStyleClass().add("title");
        lstStock.getStyleClass().add("big-list");
        lstCart.getStyleClass().add("big-list");

        getChildren().addAll(totalContainer);
        update();
    }


    //updates all the widgets on the GUI
    public void update(){
        btnCompleteSale.setDisable(false);
        btnAdd.setDisable(false);
        btnRemove.setDisable(false);

        //string formatting tool for currency:
        Locale locale = new Locale("en", "US");
        NumberFormat moneyFormatter = NumberFormat.getCurrencyInstance(locale);

        //update stock list
        ArrayList<Product> stock = new ArrayList<>(Arrays.asList(model.getProducts()));
        updateList(lstStock, stock);

        //update store summary
        if(model.getDollarsPerSale() == 0)
            txtDollarsPerSale.setText("N/A");
        else
            txtDollarsPerSale.setText(moneyFormatter.format(model.getDollarsPerSale()));
        txtRevenue.setText(moneyFormatter.format(model.getRevenue()));
        txtSales.setText(String.valueOf(model.getSales()));
        lblCart.setText("Current Cart (" + moneyFormatter.format(model.getCartValue()) + "):");

        //update topSoldList
        updateList(lstPopItems, model.getTop3SoldProducts());

        //update cart
        updateList(lstCart, model.getCartList());

        //button enable conditions
        if(lstCart.getItems().isEmpty())
            btnCompleteSale.setDisable(true);
        if(lstCart.getSelectionModel().getSelectedIndex() < 0)
            btnRemove.setDisable(true);
        if(lstStock.getSelectionModel().getSelectedIndex() < 0)
            btnAdd.setDisable(true);
    }


    /**
     * Generates an ArrayList of the toString outputs of an ArrayList of products and displays them on a given List View
     * @param list a JavaFX ListView that displays the toString outputs of a list of products
     * @param products an ArrayList of products
     */
    private void updateList(ListView<String> list, ArrayList<Product> products){
        ArrayList<String> productString = new ArrayList<>();

        for (Product product : products) {
            if (list == lstStock) { //for the stock list view, make sure the shelf quantity is greater than 0
                if (product != null && product.getShelfQuantity() > 0)
                    productString.add(product.toString());
            } else {
                if (product != null)
                    productString.add(product.toString());
            }
        }
        list.setItems(FXCollections.observableArrayList(productString));
    }


    //getters & setters
    public Button getBtnReset() {return btnReset;}
    public Button getBtnAdd() {return btnAdd;}
    public Button getBtnRemove() {return btnRemove;}
    public Button getBtnCompleteSale() {return btnCompleteSale;}
    public ListView<String> getLstStock() { return lstStock;}
    public ListView<String> getLstCart() { return lstCart;}
    public void setModel(ElectronicStore model){this.model = model;}

}
