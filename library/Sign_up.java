package library;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */




import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
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

/**
 *
 * @author Bavly Badry
 */
public class Sign_up  {
    
    public static void Sign_Up(Stage primaryStage) {
        
        root.getChildren().clear();
        
        
        //sign_in button
                //Adding text-field to the form   
                TextField tf2=new TextField();    
                TextField tf3=new TextField();    
                PasswordField tf4=new PasswordField();
                PasswordField tf5=new PasswordField();
                
                tf2.setPromptText("Username");
                tf2.setLayoutX(160);
                tf2.setLayoutY(25);
                tf2.setMinWidth(240);
                
                tf3.setPromptText("Email");
                tf3.setLayoutX(160);
                tf3.setLayoutY(70);
                tf3.setMinWidth(240);
                
                tf4.setPromptText("Password");
                tf4.setLayoutX(160);
                tf4.setLayoutY(115);
                tf4.setMinWidth(240);
                
                
                tf5.setPromptText("Confirm password");
                tf5.setLayoutX(160);
                tf5.setLayoutY(160);
                tf5.setMinWidth(240);
                
                
                //RadioButton
                
                
                ToggleGroup group = new ToggleGroup();
                RadioButton button1 = new RadioButton("Male");  
                RadioButton button2 = new RadioButton("Female");  
                
                

                button1.setLayoutX(160);
                button1.setLayoutY(210);
                button1.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
                button1.setToggleGroup(group);
                button1.setTextFill(Color.WHITE);
                
                button2.setLayoutX(280);
                button2.setLayoutY(210);
                button2.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
                button2.setToggleGroup(group);  
                button2.setTextFill(Color.WHITE);
                
                //labels   
                Text User_name = new Text("Username");  
                Text Email = new Text("Email");  
                Text Password = new Text("Password");
                Text c_Password = new Text("Confirm \nPassword");  
                Text Gender = new Text("Gender");
                
                
                User_name.setLayoutX(20);
                User_name.setLayoutY(50);
                User_name.setFill(Color.WHITE);
                User_name.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
                
                Email.setLayoutX(20);
                Email.setLayoutY(90);
                Email.setFill(Color.WHITE);
                Email.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
                
                Password.setLayoutX(20);
                Password.setLayoutY(130);
                Password.setFill(Color.WHITE);
                Password.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
                
                c_Password.setLayoutX(20);
                c_Password.setLayoutY(170);
                c_Password.setFill(Color.WHITE);
                c_Password.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
                
                
                Gender.setLayoutX(20);
                Gender.setLayoutY(230);
                Gender.setFill(Color.WHITE);
                Gender.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
                
                
                //creating submit button   
                Button Submit=new Button ("Submit");  
                Label l2=new Label();
                Submit.setLayoutX(320);
                Submit.setLayoutY(260);
                Submit.setMinSize(100, 30);

                group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() 
        {
            public void changed(ObservableValue<? extends Toggle> ob, 
                                                    Toggle o, Toggle n)
            {
  
                RadioButton rb = (RadioButton)group.getSelectedToggle();
  
                if (rb != null) {
                    String s = rb.getText();
                    // change the label
                    l2.setText(s);
                }
            }
        });
                Submit.setOnAction(event -> {
            try {
                DatabaseActions.SignUp(tf2.getText().toString(), tf3.getText().toLowerCase(), tf4.getText().toString(), tf5.getText().toString(), l2.getText().toString(), primaryStage);
            } catch (SQLException ex) {
                Logger.getLogger(Sign_up.class.getName()).log(Level.SEVERE, null, ex);
            }
                });
                //group
                Pane pane = new Pane();
                pane.setMinWidth(400);
                pane.setMinHeight(290);
                pane.setLayoutX(200);
                pane.setLayoutY(100);
                pane.getChildren().addAll(User_name,tf2,Email,tf3,Password,tf4,c_Password,tf5,Gender,button1,button2,Submit);
                pane.setStyle("-fx-background-color: rgba(0,0,0,0.5); -fx-padding:10px; ");
                

                //background image
                Image img = new Image("Images/Sign_in.jpg");
                BackgroundImage bImg = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                Background bGround = new Background(bImg);
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
                Back_btn.setOnAction((event) -> {
                    Account.Login(primaryStage);
                });
                
                
                
                scene.setUserAgentStylesheet("CSS/SignUpButtons.css");
  
                //adding the the nodes to the GridPane's rows   
                root.getChildren().addAll(Back_btn,pane);

                root.setBackground(bGround);
                //creating Scene object   
 
 
    
                primaryStage.setTitle("Account");
                primaryStage.setScene(scene);
                primaryStage.show();
    }

    
}
