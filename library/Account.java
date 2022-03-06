/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import static library.Library.root;
import static library.Library.scene;
import static library.DatabaseActions.EmailValidation;

/**
 *
 * @author ethan
 */
public class Account {
        
    public static void Login(Stage primaryStage){
        //log in
        
        root.getChildren().clear();
 
//"Shadow: Back Button."
        Button Back_btn = new Button();
        Back_btn.setLayoutX(20);
        Back_btn.setLayoutY(20);
        Back_btn.setMinSize(50, 30);
        
    Image Back_btn_IMG = new Image("Images/Back3.png");
    ImageView Back_btn_View = new ImageView(Back_btn_IMG);
    Back_btn_View.setFitHeight(24);
    Back_btn_View.setPreserveRatio(true);
    Back_btn.setGraphic(Back_btn_View);
    root.getChildren().addAll(Back_btn);
    
Back_btn.setOnAction((ActionEvent event) -> {
            try {
                LIB_Home.Home(primaryStage);
            } catch (SQLException ex) {
                Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
            }
    });
        

                
        Text Email = new Text("Email");
        Text Password = new Text("Password");  
        
        Email.setLayoutX(20);
        Email.setLayoutY(40);
        Email.setFill(Color.WHITE);
        Email.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
                
        Password.setLayoutX(20);
        Password.setLayoutY(85);
        Password.setFill(Color.WHITE);
        Password.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        
        //textfield
        TextField etf=new TextField();    
        PasswordField ptf=new PasswordField();    
        
        
        etf.setPromptText("Email");
        etf.setLayoutX(160);
        etf.setLayoutY(20);
        etf.setMinWidth(240);
       
        ptf.setPromptText("Password");
        ptf.setLayoutX(160);
        ptf.setLayoutY(65);
        ptf.setMinWidth(240);
        
        Button LOGIN=new Button ("Log In");  
                
                LOGIN.setLayoutX(320);
                LOGIN.setLayoutY(105);
                LOGIN.setMinSize(80, 25);
                //setting ID for the submit button so that the particular style rules can be applied to it.   
                LOGIN.setId("submit");  
        
        // Login Button Action
        LOGIN.setOnAction((event) -> {

            try {
                
                EmailValidation(etf.getText().toLowerCase(), ptf.getText(), primaryStage);
            } catch (SQLException ex) {
                Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
             
        //signin button
              
        Button SIGNUP=new Button ("Sign Up");  
                
                SIGNUP.setLayoutX(160);
                SIGNUP.setLayoutY(105);
                SIGNUP.setMinSize(80, 25);
                SIGNUP.setOnAction((event) -> {
                    Sign_up.Sign_Up(primaryStage);
                });
        
        Pane pane = new Pane();
                pane.setMinWidth(400);
                pane.setMinHeight(150);
                pane.setLayoutX(200);
                pane.setLayoutY(150);
                pane.getChildren().addAll(Email,etf,Password,ptf,LOGIN,SIGNUP);
                pane.setStyle("-fx-background-color: rgba(0,0,0,0.5);  -fx-padding:10px  ");
   
        Image img = new Image("Images/Sign_in.jpg");
        BackgroundImage bImg = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Background bGround = new Background(bImg);
                
        root.getChildren().addAll(pane);
        root.setBackground(bGround);
        
        
        
        scene.setUserAgentStylesheet("CSS/SignUpButtons.css");
        
        primaryStage.setTitle("Account");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
