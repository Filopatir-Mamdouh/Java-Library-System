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
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import static library.Library.root;
import static library.Library.scene;

/**
 *
 * @author ethan
 */
public class Administrator
{

    public static void Administrator(Stage primaryStage) throws SQLException
    {

root.getChildren().clear();

//"Shadow: Back Button."
Button Back_btn = new Button();
Back_btn.setId("B");
Back_btn.setTranslateX(25);
Back_btn.setTranslateY(25);
Back_btn.setMinSize(50, 30);
        
Image Back_btn_IMG = new Image("Images/Back3.png");
ImageView Back_btn_View = new ImageView(Back_btn_IMG);
Back_btn_View.setFitHeight(24);
Back_btn_View.setPreserveRatio(true);
Back_btn.setGraphic(Back_btn_View);

Back_btn.setOnAction((ActionEvent event1) ->
    {
        try
        {
            LIB_Home.Home(primaryStage);
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
    });    

VBox vbox1 = DatabaseActions.AdminContents(primaryStage);
        ScrollPane ee = new ScrollPane(vbox1);
    root.getChildren().addAll(ee,Back_btn);
    scene.setUserAgentStylesheet("Css/Administrator.css");

Image img = new Image("Images/Admin.jpg");
BackgroundImage bImg = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
Background bGround = new Background(bImg);
root.setBackground(bGround);

    
primaryStage.setScene(scene);
primaryStage.setTitle("Administrator");
primaryStage.show();
    
   
//End
    }

}
