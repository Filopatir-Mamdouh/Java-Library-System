/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

import java.io.File;
import java.nio.file.Path;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import static library.Library.category;
import static library.Library.root;
import static library.Library.scene;

/**
 *
 * @author ethan
 */
public class Admin
{
    

    
    
    public static void admin(Stage primaryStage)
    {
        
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
    
            Back_btn.setOnAction((ActionEvent event1) ->
    {
        try {
            LIB_Home.Home(primaryStage);
        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
    });
         
        Label Book_Name = new Label("Book Name"); 
        TextField Book_Name_Text = new TextField(); 
        Book_Name.setLayoutX(25);
        Book_Name.setLayoutY(125 - 50);
        Book_Name_Text.setLayoutX(145);
        Book_Name_Text.setLayoutY(124 - 50);
        
        Label Author = new Label("Author"); 
        TextField Author_Text = new TextField();
        Author.setLayoutX(25);
        Author.setLayoutY(175 - 50);
        Author_Text.setLayoutX(145);
        Author_Text.setLayoutY(174 - 50);
      
        
        //i'm here don't lose it
        
        Label Category = new Label("Category"); 
        Category.setLayoutX(25);
        Category.setLayoutY(225 - 50 + 35 - 25);


        // Create a combo box
        ComboBox listView = new ComboBox(FXCollections.observableArrayList(category));
        listView.setPrefSize(180, 20);
        listView.setLayoutX(145);
        listView.setLayoutY(225 - 50 + 35 - 25);

                
        
        //end is here don't lose it
        
        
        Label NOP = new Label("Number of \n     Pages"); 
        TextField NOP_Text = new TextField();
        NOP.setLayoutX(25);
        NOP.setLayoutY(325 + 110 + 50 - 100 - 25 - 10);
        NOP_Text.setLayoutX(145);
        NOP_Text.setLayoutY(326 + 110 + 50 - 100 - 25);
        
       
        Label book = new Label("Book");
        book.setLayoutX(25);
        book.setLayoutY(395 - 75 + 50 - 100 - 25);
        
        Label Book_Text = new Label("No File Selected");
        Book_Text.setStyle("-fx-font-size: 15px;");
        Book_Text.setLayoutX(230);
        Book_Text.setLayoutY(450 - 75 + 50 - 50 - 5 - 100 - 25);
        
 
        FileChooser fileChooser2 = new FileChooser();
        Label pdfpath = null;
        Button Select2 = new Button("Browse");
        Select2.setLayoutX(150);
        Select2.setLayoutY(396 - 75 + 50 - 100 - 25);
        Select2.setMinSize(65, 25);
        Path PDF;
        Select2.setOnAction(e ->
        {
            File selectedFile2=null;
            selectedFile2 = fileChooser2.showOpenDialog(primaryStage);
            if (selectedFile2 != null){
            Book_Text.setText(selectedFile2.getName());
            DatabaseActions.setFile(selectedFile2, 1);
            }
                        
            
        });
        

        fileChooser2.getExtensionFilters().addAll(
   new FileChooser.ExtensionFilter("PDF Files", "*.pdf")
                                                );

        Label Book_Cover = new Label("Book Cover"); 
        Book_Cover.setLayoutX(25);
        Book_Cover.setLayoutY(450 - 75 + 50 - 100 - 25);
         
        Button Select1 = new Button("Browse");
        Select1.setLayoutX(150);
        Select1.setLayoutY(450 - 75 + 50 - 100 - 25);
        Select1.setMinSize(65, 25);
        
        Label Book_Cover_Text = new Label("No File Selected");
        Book_Cover_Text.setStyle("-fx-font-size: 15px;");
        Book_Cover_Text.setLayoutX(230);
        Book_Cover_Text.setLayoutY(450 - 75 + 50 - 100 - 25);
        
        
        
        Label imgpath = null;
        FileChooser fileChooser1 = new FileChooser();
        Select1.setOnAction(e ->
        {
            File selectedFile1 =null;
            selectedFile1 = fileChooser1.showOpenDialog(primaryStage);
            if (selectedFile1 != null){
            Book_Cover_Text.setText(selectedFile1.getName());
            DatabaseActions.setFile(selectedFile1, 0);
            }
            
            
        });
                
                        fileChooser1.getExtensionFilters().addAll(
   new FileChooser.ExtensionFilter("PNG Files", "*.png")
  ,new FileChooser.ExtensionFilter("JPG Files", "*.jpg")
                                                );
                        
        // Submit Button
        Button Submit = new Button("Submit");
        Submit.setLayoutX(25);
        Submit.setLayoutY(490 + 50 - 100 - 25 + 20);
        Submit.setMinSize(80, 25);
        
        
        
        Submit.setOnAction((ActionEvent event) ->
        {
        try {
            int N_O_P= Integer.parseInt(NOP_Text.getText().toString());
            File pdf = DatabaseActions.getFile(1);
            File IMG = DatabaseActions.getFile(2);                                                           
            DatabaseActions.storeBook(Book_Name_Text.getText().toString(), Author_Text.getText().toString(), listView.getValue().toString(), pdf, IMG, N_O_P, primaryStage);
            Admin.admin(primaryStage);
        } catch (SQLException ex) {
            System.out.println(ex);
            StackPane secondaryLayout = new StackPane();


            Scene secondScene = new Scene(secondaryLayout, 230, 100);

            // New window (Stage)e
            Stage newWindow = new Stage();
            newWindow.setTitle("Alert");
            newWindow.setScene(secondScene);
            newWindow.initModality(Modality.WINDOW_MODAL);
       
            // Specifies the modality for new window.

            secondaryLayout.getChildren().add(new Label("Please Check your inputs"));
            // Specifies the owner Window (parent) for new window
            newWindow.initOwner(primaryStage);

            // Set position of second window, related to primary window.
            newWindow.setX(400);
            newWindow.setY(300);
            newWindow.setTitle("");
            newWindow.getIcons().add(new Image("Images/Alert.png"));   //"Shadow: Stage Icon =>> Stack_Over_Flow_Icon."
            newWindow.getIcons().add(new Image("Images/RedAlert.png"));
            newWindow.setResizable(false);
            
            newWindow.show();
        }
        });
        
        
scene.setUserAgentStylesheet("Css/AddBook.css");
root.getChildren().addAll( Book_Name, Book_Name_Text, Author, Author_Text, Category, book, Select1, Back_btn , Book_Cover , Select2, Submit , NOP, NOP_Text, listView, Book_Cover_Text, Book_Text);

//"Shadow: Background Image And It's Css."

Image img = new Image("Images/Admin2.png");
BackgroundImage bImg = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
Background bGround = new Background(bImg);
root.setBackground(bGround);


primaryStage.setScene(scene);
primaryStage.setResizable(false);    //"Shadow: Resizing_Freez."
primaryStage.setTitle("Administrator");
primaryStage.show();
    }
}
