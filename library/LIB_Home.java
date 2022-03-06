package library;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
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
import static library.Library.Account_ID; 

public class LIB_Home 
{

    
    
  public static void Home(Stage primaryStage) throws SQLException
  {
      root.getChildren().clear();
    
    
Text Head_Line = new Text();
Head_Line.setText(" '' Don't Judge a Book by Its Cover '' ");
Head_Line.setLayoutX(215);
Head_Line.setLayoutY(50);

Head_Line.setFill(Color.ALICEBLUE);
Head_Line.setFont(Font.font(java.awt.Font.SERIF, 25));
final Effect glow = new Glow(1.0);
Head_Line.setEffect(glow);

    Button Categories_btn = new Button("Categories");
    Categories_btn.setLayoutX(50);
    Categories_btn.setLayoutY(250);
    Categories_btn.setMinSize(200, 50);
    Image Categories_IMG = new Image("Images/Categories.png");
    ImageView Categories_imageView = new ImageView(Categories_IMG);
    Categories_btn.setGraphic(Categories_imageView);

//"Shadow: Button 1 Action Handler."
    Categories_btn.setOnAction((event) ->
    {
        try {
            LIB_Categories.LIB_Cat(primaryStage);
        } catch (SQLException ex) {
            Logger.getLogger(LIB_Home.class.getName()).log(Level.SEVERE, null, ex);
        }
    });


    Button Newly_Added_btn = new Button("Newly Added");
    Newly_Added_btn.setLayoutX(570);
    Newly_Added_btn.setLayoutY(250);
    Newly_Added_btn.setMinSize(200, 50);
    Newly_Added_btn.setOnAction((event) -> {
        try {
              BookDisplay.bookDisplay(primaryStage, 14);
          } catch (SQLException ex) {
              Logger.getLogger(LIB_Home.class.getName()).log(Level.SEVERE, null, ex);
          }
    });
    
    Image NewlyAdded_IMG = new Image("Images/Newly added.png");
    ImageView NewlyAdded_imageView = new ImageView(NewlyAdded_IMG);
    Newly_Added_btn.setGraphic(NewlyAdded_imageView);

//"Shadow: Button 2 Action Handler."

    Button Most_Read_btn = new Button("Most Read");
    Most_Read_btn.setLayoutX(50);
    Most_Read_btn.setLayoutY(350);
    Most_Read_btn.setMinSize(200, 50);
    Most_Read_btn.setOnAction((event) ->
    {
          try
          {
              BookDisplay.bookDisplay(primaryStage, 13);
          }
          catch (SQLException ex)
          {
              Logger.getLogger(LIB_Home.class.getName()).log(Level.SEVERE, null, ex);
          }
    });
    
    Image MostRead_IMG = new Image("Images/Most read.png");
    ImageView MostRead_imageView = new ImageView(MostRead_IMG);
    Most_Read_btn.setGraphic(MostRead_imageView);
    
    Button Recently_Read_btn = new Button("Recently Read");
    Recently_Read_btn.setLayoutX(570);
    Recently_Read_btn.setLayoutY(350);
    Recently_Read_btn.setMinSize(200, 50);
    Recently_Read_btn.setOnAction((event) ->
    {
        try {
              BookDisplay.bookDisplay(primaryStage, 12);
          } catch (SQLException ex) {
              Logger.getLogger(LIB_Home.class.getName()).log(Level.SEVERE, null, ex);
          }
    });
    
    Image RecentlyRead_IMG = new Image("Images/Recently read.png");
    ImageView RecentlyRead_imageView = new ImageView(RecentlyRead_IMG);
    Recently_Read_btn.setGraphic(RecentlyRead_imageView);

    
    Button Account_btn = new Button("Account");
    Account_btn.setLayoutX(313);
    Account_btn.setLayoutY(450);
    Account_btn.setMinSize(200, 50);
    if (Account_ID != 0){
        Account_btn.setText("Sign out");
    }
    Image Account_IMG = new Image("Images/Account.png");
    ImageView Account_imageView = new ImageView(Account_IMG);
    Account_btn.setGraphic(Account_imageView);
    Account_btn.setOnAction((event) -> {
          try {
              if (Account_ID != 0){
                  Account_ID = 0;
                  LIB_Home.Home(primaryStage);
              }
              else {
                  Account.Login(primaryStage);
              } } catch (SQLException ex) {
              Logger.getLogger(LIB_Home.class.getName()).log(Level.SEVERE, null, ex);
          }
    });

 Button Admin_btn = new Button("Add Book");
    Admin_btn.setLayoutX(650);
    Admin_btn.setLayoutY(550);
    Admin_btn.setMinSize(150, 50);
    
//"Shadow: Admin Button's Icon."    
    Image Admin_btn_IMG = new Image("Images/Admin.png");
    ImageView Admin_btn_View = new ImageView(Admin_btn_IMG);
    Admin_btn_View.setFitHeight(24);
    Admin_btn_View.setPreserveRatio(true);
    Admin_btn.setGraphic(Admin_btn_View);
    
//"Shadow: Admin Action Handler."
    Admin_btn.setOnAction((ActionEvent event) ->
{
    Admin.admin(primaryStage);
});

//Administrator Not Add Book
 Button Admin_btn_2 = new Button("Administrator");
    Admin_btn_2.setLayoutX(25);
    Admin_btn_2.setLayoutY(550);
    Admin_btn_2.setMinSize(150, 50);
    
//"Shadow: Admin Button's Icon."    
    Image Admin_btn_2_IMG = new Image("Images/Admin.png");
    ImageView Admin_btn_2_View = new ImageView(Admin_btn_2_IMG);
    Admin_btn_2_View.setFitHeight(24);
    Admin_btn_2_View.setPreserveRatio(true);
    Admin_btn_2.setGraphic(Admin_btn_2_View);
    
//"Shadow: Admin Action Handler."
    Admin_btn_2.setOnAction((ActionEvent event) ->
{

          try {
              Administrator.Administrator(primaryStage);
          } catch (SQLException ex) {
              Logger.getLogger(LIB_Home.class.getName()).log(Level.SEVERE, null, ex);
          }
    
}); 
    
    
    
    
    
//"Shadow: External CSS File Which We Have Mentioned Previously."
scene.setUserAgentStylesheet("Css/HomeButtons.css");
root.getChildren().addAll(Head_Line, Categories_btn, Newly_Added_btn, Most_Read_btn, Recently_Read_btn, Account_btn);
if (DatabaseActions.isAdmin(Account_ID))
{
    root.getChildren().addAll(Admin_btn,Admin_btn_2);
}
//"Shadow: Background Image And It's Css."
Image img = new Image("Images/Books1.jpg");
BackgroundImage bImg = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
Background bGround = new Background(bImg);
root.setBackground(bGround);
primaryStage.setScene(scene);
primaryStage.setResizable(false);    //"Shadow: Resizing_Freez."
primaryStage.getIcons().add(new Image("Images/Stack_Over_Flow_icon2.png"));   //"Shadow: Stage Icon =>> Stack_Over_Flow_Icon."
primaryStage.setTitle("Library Home");
primaryStage.show();
  }
  

  
}

