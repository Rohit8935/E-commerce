package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.FileNotFoundException;

public class UserInterface {
    GridPane loginPage; //GridPane allows to add multiple nodes in multiple rows column
    HBox headerBar; //HBox is layout pane to arrange the node in single row
    HBox footerBar;
    Button SignIn;
    Customer loggedInCustomer;
    Label welcomelabel;


    VBox body;  //VBox layout pane arrange the nodes in single vertical column

    ObservableList<Product>itemsInCart= FXCollections.observableArrayList();

    com.example.ecommerce.ProductList productList=new com.example.ecommerce.ProductList();
    VBox productPage;
    Button placeOrderButton=new Button("Place Order");


            public BorderPane createContent(){
                BorderPane root=new BorderPane();
                root.setPrefSize(800,600);
                //root.getChildren().addAll(loginPage);
                root.setTop(headerBar);
               // root.setCenter(loginPage);
                  body=new VBox();
                  body.setPadding(new Insets(10));
                  body.setAlignment(Pos.CENTER);
                root.setCenter(body);
              productPage=productList.getAllProducts();
               body.getChildren().add(productPage);
               root.setBottom(footerBar);
                return root;
            }
    public UserInterface() throws FileNotFoundException { //Constructor
        createLoginPage();
        createHeaderBar();
        createFooterBar();
    }


            private void createLoginPage(){
                Text userNameText=new Text("User Name");
                Text passwordText=new Text("Password");

                TextField userName=new TextField("RR123@gmail.com");
                PasswordField password=new PasswordField();
                password.setText("122");

                userName.setPromptText("Type your user name here");
                password.setPromptText("Type your password here");

                Label messagelabel=new Label("Hi");
                 Button loginbutton=new Button("Login");
                 Label label=new Label("HI");

                loginPage=new GridPane();
                loginPage.add(userNameText,0,0);
                loginPage.add(userName,1,0);
                loginPage.add(passwordText,0,1);

                loginPage.add(password,1,1);
                  loginPage.setAlignment(Pos.CENTER);
                  loginPage.setHgap(10);
                  loginPage.setVgap(10);
                  loginPage.add(loginbutton,1,2);
                  loginPage.add(label,0,2);

                  loginbutton.setOnAction(new EventHandler<ActionEvent>() {
                      @Override
                      public void handle(ActionEvent actionEvent) {
                          String name=userName.getText();
                          String pass=password.getText();
                          Login login=new Login();
                          loggedInCustomer= login.customerLogin(name,pass);

                          if(loggedInCustomer!=null){
                              messagelabel.setText("Welcome"+loggedInCustomer.getName());
                              welcomelabel.setText("Welcome - "+loggedInCustomer.getName());
                              headerBar.getChildren().add(welcomelabel);
                              body.getChildren().clear();
                              body.getChildren().add(productPage);
                          }
                          else {
                              messagelabel.setText("Log In Failed! please give correct username and password");
                          }
                      }
                  });

            }
            private void createHeaderBar(){
                Button homeButton=new Button();
                Image image =new Image("C:\\Users\\Rohit\\IdeaProjects\\demo\\src\\download.png");
                ImageView imageView=new ImageView();
                imageView.setImage(image);
                imageView.setFitHeight(60);
                imageView.setFitHeight(60);
                homeButton.setGraphic(imageView);

               TextField Search=new TextField();
               Search.setPromptText("Search here");
               Search.setPrefWidth(280);

               Button searchbutton=new Button("Search");


               SignIn=new Button("Sign In");
               welcomelabel=new Label();

               Button cartButton=new Button("Cart");
               Button OrderButton=new Button("Orders");

               headerBar=new HBox();
               headerBar.setPadding(new Insets(20));
               headerBar.setSpacing(10);
               headerBar.setAlignment(Pos.CENTER);
               headerBar.getChildren().addAll(homeButton,Search,searchbutton,SignIn,cartButton);

               SignIn.setOnAction(new EventHandler<ActionEvent>() {
                   @Override
                   public void handle(ActionEvent actionEvent) {
                       body.getChildren().clear();  //remove everything
                       body.getChildren().add(loginPage); //put login page
                       headerBar.getChildren().remove(SignIn);
                   }
               });

               cartButton.setOnAction(new EventHandler<ActionEvent>() {
                   @Override
                   public void handle(ActionEvent actionEvent) {
                       body.getChildren().clear();
                       VBox prodpage=productList.getProductsInCart(itemsInCart);
                       prodpage.setAlignment(Pos.CENTER);
                       prodpage.setSpacing(10);
                       prodpage.getChildren().add(placeOrderButton);
                       body.getChildren().add(prodpage);
                       footerBar.setVisible(false);// all the case need to be handeled for this
                   }
               });

               placeOrderButton.setOnAction(new EventHandler<ActionEvent>() {
                   @Override
                   public void handle(ActionEvent actionEvent) {
                       //need list of product and customer

                       if(itemsInCart==null){
                           //please select a product first to place order;
                           showDialog("please add some product first in the cart to place order");
                           return;
                       }if(loggedInCustomer==null){
                           showDialog("please login first");
                           return;
                       }
                       int count=Order.placeMultipleOrder(loggedInCustomer,itemsInCart);
                       if(count!=0){
                           showDialog("Order for "+count+" products is placed Successfully!!");
                       }else {
                           showDialog("Order failed!!");
                       }
                   }
               });
               homeButton.setOnAction(new EventHandler<ActionEvent>() {
                   @Override
                   public void handle(ActionEvent actionEvent) {
                       body.getChildren().clear();
                       body.getChildren().add(productPage);
                       footerBar.setVisible(true);
                       if(loggedInCustomer==null && headerBar.getChildren().indexOf(SignIn)==-1){
                           headerBar.getChildren().add(SignIn);
                       }
                   }
               });
            }
    private void createFooterBar(){


        Button buyNowButton=new Button("Buy Now");
        Button addToCartButton=new Button("Add to Cart");

        footerBar=new HBox();
        footerBar.setPadding(new Insets(20));
        footerBar.setSpacing(10);
        footerBar.setAlignment(Pos.CENTER);
        footerBar.getChildren().addAll(buyNowButton,addToCartButton);

        buyNowButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Product product=productList.getSelectedProducts();
                if(product==null){
                    //please select a product first to place order;
                    showDialog("please select a product first to place order");
                    return;
                }if(loggedInCustomer==null){
                    showDialog("please login first");
                    return;
                }
                boolean status=Order.placeOrder(loggedInCustomer,product);
                if(status==true){
                    showDialog("Order is placed Successfully!!");
                }else {
                    showDialog("Order failed!!");
                }
            }
        });

        addToCartButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Product product=productList.getSelectedProducts();
                if(product==null){
                    //please select a product first to place order;
                    showDialog("please select a product first to add it to the Cart");
                    return;
                }
                itemsInCart.add(product);
                showDialog("selected item is added to the Cart Successfully");
            }
        });
            }
            private void showDialog(String message){
                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText(message);
                alert.setTitle("Message");
                alert.showAndWait();
            }
}
