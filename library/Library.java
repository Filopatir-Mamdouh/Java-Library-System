package library;


import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application; 
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
         
public class Library extends Application
{
   public static Pane root = new Pane();
   public static Scene scene = new Scene(root, 800, 600);
   public static int Account_ID = 0;
   public static String category[]=
   {
           "History",
           "Geography",
           "Physics",
           "Chemistry",
           "Mathematics",
           "Technology",
           "Music",
           "Languages_and_Literature",
           "Philosophy",
           "Psychology",
           "Business",
           "Military_Science"
       };
   
   @Override
   public void start(final Stage primaryStage) throws SQLException {
       
       
       LIB_Home.Home(primaryStage);
       primaryStage.setOnCloseRequest((event) -> {
           try {
               DatabaseActions.onclose();
           } catch (SQLException ex) {
               Logger.getLogger(Library.class.getName()).log(Level.SEVERE, null, ex);
           }
       });
   }

   public static void main(String[] args) throws SQLException {
       
      launch(args);
   }


}



