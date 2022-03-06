package library;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import static library.Library.root;
import static library.Library.scene;

public class LIB_Categories{
    
    
    public static void LIB_Cat(Stage primaryStage) throws SQLException{
    
        root.getChildren().clear();
        
        Text Head_Line = new Text();
        Head_Line.setText(" '' A room without books is like a body without a soul '' ");
        Head_Line.setLayoutX(130);
        Head_Line.setLayoutY(45);

        Head_Line.setFill(Color.ALICEBLUE);
        Head_Line.setFont(Font.font(java.awt.Font.SERIF, 25));
        final Effect glow = new Glow(1.0);
        Head_Line.setEffect(glow);
        
        Button b1 = new Button("History");
        b1.setLayoutX(100);
        b1.setLayoutY(90 + 15);
        b1.setMinSize(250, 50);
        b1.setAlignment(Pos.CENTER);
        b1.setOnAction((ActionEvent event) -> {
            try {
                BookDisplay.bookDisplay(primaryStage,0);
            } catch (SQLException ex) {
                Logger.getLogger(LIB_Categories.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        Button b2 = new Button("Geography");
        b2.setLayoutX(100);
        b2.setLayoutY(170  + 15);
        b2.setMinSize(250, 50);
        b2.setAlignment(Pos.CENTER);

        b2.setOnAction((ActionEvent event) -> {
            try {
                BookDisplay.bookDisplay(primaryStage,1);
            } catch (SQLException ex) {
                Logger.getLogger(LIB_Categories.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        Button b3 = new Button("Physics");
        b3.setLayoutX(100);
        b3.setLayoutY(250 + 15);
        b3.setMinSize(250, 50);
        b3.setAlignment(Pos.CENTER);
        
        b3.setOnAction((ActionEvent event) -> {
            try {
                BookDisplay.bookDisplay(primaryStage,2);
            } catch (SQLException ex) {
                Logger.getLogger(LIB_Categories.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        Button b4 = new Button("Chemistry");
        b4.setLayoutX(100);
        b4.setLayoutY(330 + 15);
        b4.setMinSize(250, 50);
        b4.setAlignment(Pos.CENTER);
        b4.setOnAction((ActionEvent event) -> {
            try {
                BookDisplay.bookDisplay(primaryStage,3);
            } catch (SQLException ex) {
                Logger.getLogger(LIB_Categories.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        Button b5 = new Button("Mathematics");
        b5.setLayoutX(100);
        b5.setLayoutY(410 + 15);
        b5.setMinSize(250, 50);
        b5.setAlignment(Pos.CENTER);        
        b5.setOnAction((ActionEvent event) -> {
            try {
                BookDisplay.bookDisplay(primaryStage,4);
            } catch (SQLException ex) {
                Logger.getLogger(LIB_Categories.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        Button b6 = new Button("Technology");
        b6.setLayoutX(100);
        b6.setLayoutY(490 + 15);
        b6.setMinSize(250, 50);
        b6.setAlignment(Pos.CENTER);
        b6.setOnAction((ActionEvent event) -> {
            try {
                BookDisplay.bookDisplay(primaryStage,5);
            } catch (SQLException ex) {
                Logger.getLogger(LIB_Categories.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        
        Button b7 = new Button("Music");
        b7.setLayoutX(450);
        b7.setLayoutY(90 + 15);
        b7.setMinSize(250, 50);
        b7.setAlignment(Pos.CENTER);
        b7.setOnAction((ActionEvent event) -> {
            try {
                BookDisplay.bookDisplay(primaryStage,6);
            } catch (SQLException ex) {
                Logger.getLogger(LIB_Categories.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        Button b8 = new Button("Language & Literature");
        b8.setLayoutX(450);
        b8.setLayoutY(170 + 15);
        b8.setMinSize(250, 50);
        b8.setAlignment(Pos.CENTER);
        b8.setOnAction((ActionEvent event) -> {
            try {
                BookDisplay.bookDisplay(primaryStage,7);
            } catch (SQLException ex) {
                Logger.getLogger(LIB_Categories.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        Button b9 = new Button("Philosophy");
        b9.setLayoutX(450);
        b9.setLayoutY(250 + 15);
        b9.setMinSize(250, 50);
        b9.setAlignment(Pos.CENTER);
        b9.setOnAction((ActionEvent event) -> {
            try {
                BookDisplay.bookDisplay(primaryStage,8);
            } catch (SQLException ex) {
                Logger.getLogger(LIB_Categories.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        Button b10 = new Button("Psychology");
        b10.setLayoutX(450);
        b10.setLayoutY(330 + 15);
        b10.setMinSize(250, 50);
        b10.setAlignment(Pos.CENTER);
        b10.setOnAction((ActionEvent event) -> {
            try {
                BookDisplay.bookDisplay(primaryStage,9);
            } catch (SQLException ex) {
                Logger.getLogger(LIB_Categories.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        Button b11 = new Button("Business");
        b11.setLayoutX(450);
        b11.setLayoutY(410 + 15);
        b11.setMinSize(250, 50);
        b11.setAlignment(Pos.CENTER);
        b11.setOnAction((ActionEvent event) -> {
            try {
                BookDisplay.bookDisplay(primaryStage,10);
            } catch (SQLException ex) {
                Logger.getLogger(LIB_Categories.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        Button b12 = new Button("Military Science");
        b12.setLayoutX(450);
        b12.setLayoutY(490 + 15);
        b12.setMinSize(250, 50);
        b12.setAlignment(Pos.CENTER);
        b12.setOnAction((ActionEvent event) -> {
            try {
                BookDisplay.bookDisplay(primaryStage,11);
            } catch (SQLException ex) {
                Logger.getLogger(LIB_Categories.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        

        scene.setUserAgentStylesheet("Css/CategoriesButtons.css");
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
            try {
                LIB_Home.Home(primaryStage);
            } catch (SQLException ex) {
                Logger.getLogger(LIB_Categories.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        root.getChildren().addAll(Back_btn,Head_Line,b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11,b12);
        
        Image img_category = new Image("Images/category.jpg");
        BackgroundImage bImg_category = new BackgroundImage(img_category, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Background bGround = new Background(bImg_category);
        root.setBackground(bGround);
        
        primaryStage.setResizable(false);
        primaryStage.setAlwaysOnTop(true);
        primaryStage.setTitle("Categories");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

  
   
    
}
