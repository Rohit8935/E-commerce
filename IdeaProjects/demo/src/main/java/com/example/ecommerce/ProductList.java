package com.example.ecommerce;

import com.example.demo.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class ProductList {

    //With help of TableView, we can view the table,and product as its type
    private TableView<Product> productTable;


    //creating the table
    public VBox createTable(ObservableList<Product>data) { //creating the table and binding the data we provide
        //columns
        //ID
        TableColumn id = new TableColumn("ID");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        //NAME
        TableColumn name = new TableColumn("NAME");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        //PRICE
        TableColumn price = new TableColumn("PRICE");
        price.setCellValueFactory(new PropertyValueFactory<>("price"));

    ////////
        //Now add these things to the table
        productTable = new TableView<>();
        productTable.getColumns().addAll(id, name, price);//added the columns
        productTable.setItems(data); //added the data
        productTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);


        VBox vBox = new VBox();
        vBox.setPadding(new Insets(10));
        vBox.getChildren().addAll(productTable);

        return vBox;

    }
    public VBox getDummyTable(){
        ObservableList<Product>data= FXCollections.observableArrayList();
        data.add(new Product(2,"iphone",1));
        data.add(new Product(2,"laptop",3));
        return createTable(data);
    }
    public VBox getAllProducts(){
        ObservableList<Product>data=Product.getAllProducts();
        return createTable(data);
    }
    public Product getSelectedProducts(){
        return productTable.getSelectionModel().getSelectedItem();
    }
    public VBox getProductsInCart(ObservableList<Product>data){
        return createTable(data);
    }
}