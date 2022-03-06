/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import static library.Library.category;
import static library.Library.root;
import static library.Library.scene;

/**
 *
 * @author dell
 */
public class BookDisplay {
    public static void bookDisplay(Stage primaryStage,int no) throws SQLException {
        root.getChildren().clear();
        String cat = null;
        Label lab1=new Label();
        lab1.setLayoutX(25);
        lab1.setLayoutY(74);
        lab1.setMaxWidth(400);
        lab1.setStyle("-fx-text-fill: ALICEBLUE; ");
        lab1.setFont(Font.font(30));
        final Effect glow = new Glow(1.0);
        lab1.setEffect(glow);
        switch (no){
            case 0:
                cat=category[no];
                lab1.setText(cat);
                break;
            case 6:
                cat=category[no];
                lab1.setText(cat);
                break;
            case 1:
                cat=category[no];
                lab1.setText(cat);
                break;
            case 7:
                cat=category[no];
                lab1.setText("Languages & Literature");
                break;
            case 2:
                cat=category[no];
                lab1.setText(cat);
                break;
            case 8:
                cat=category[no];
                lab1.setText(cat);
                break;
            case 3:
                cat=category[no];
                lab1.setText(cat);
                break;
            case 9:
                cat=category[no];
                lab1.setText(cat);
                break;
            case 4:
                cat=category[no];
                lab1.setText(cat);
                break;
            case 10:
                cat=category[no];
                lab1.setText(cat);
                break;
            case 5:
                cat=category[no];
                lab1.setText(cat);
                break;
            case 11:
                cat=category[no];
                lab1.setText("Military Science");
                break;
            case 12:
                lab1.setText("Recently Read");
                break;
            case 13:
                lab1.setText("Most Read");
                break;
            case 14:
                lab1.setText("Newly Added");
                break;
        }
        FlowPane fpan = null;
        try {
            fpan = (cat==null)? DatabaseActions.getBooks(no) : DatabaseActions.getBooks(cat);
        } catch (IOException ex) {
            Logger.getLogger(BookDisplay.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        
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
        Back_btn.setOnAction((event) ->
        {
            DatabaseActions.searchbooks(null);
            if(no<=11){
            try {
                LIB_Categories.LIB_Cat(primaryStage);
            } catch (SQLException ex) {
                Logger.getLogger(BookDisplay.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
            else 
                try {
                    LIB_Home.Home(primaryStage);
                } catch (SQLException ex) {
                Logger.getLogger(BookDisplay.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        HBox Letters = new HBox(10);
        Letters.setLayoutX(237);
        Letters.setMinWidth(550);
        Letters.setMinHeight(20);
        Letters.setLayoutY(23);
        Letters.setAlignment(Pos.CENTER);

        Label All= new Label("All");
        Letters.getChildren().add(All);
        
        All.setOnMousePressed((event) -> {
       DatabaseActions.searchbooks(null);

            try {
                bookDisplay(primaryStage, no);
            } catch (SQLException ex) {
                Logger.getLogger(BookDisplay.class.getName()).log(Level.SEVERE, null, ex);
            }

       
        });
        for ( int i=65;i<=90;i++){
            char j = (char)i;
            String k = String.valueOf(j);
            Label a = new Label(k);
            a.setOnMousePressed((event) -> {
                DatabaseActions.searchbooks(j);
                
                try {
                    bookDisplay(primaryStage, no);
                } catch (SQLException ex) {
                    Logger.getLogger(BookDisplay.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            //a.setOnMousePressed(value);
            Letters.getChildren().add(a);
        }
        Letters.setStyle("-fx-background-color: white; -fx-font-family: Times New Roman; -fx-font-size: 14px;");

        TextField txf = new TextField();
        txf.setLayoutX(500);
        txf.setLayoutY(80);
        
        Button bt2 = new Button("Search");
        bt2.setLayoutX(730);
        bt2.setLayoutY(83);
        bt2.setMinSize(60, 25);
        bt2.setOnAction((event) -> {
            DatabaseActions.searchbooks(txf.getText());
            try {
                    bookDisplay(primaryStage, no);
                } catch (SQLException ex) {
                    Logger.getLogger(BookDisplay.class.getName()).log(Level.SEVERE, null, ex);
                }
        });

    
        scene.setUserAgentStylesheet("Css/Book_Display.css");
        root.getChildren().addAll(Back_btn,Letters,txf,bt2,lab1,fpan);
        
        Image img_category = new Image("Images/Picture1.png");
        BackgroundImage bImg_category = new BackgroundImage(img_category, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Background bGround = new Background(bImg_category);
        root.setBackground(bGround);
        primaryStage.setResizable(false);

        primaryStage.setTitle(lab1.getText());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
