package library;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.sql.rowset.serial.SerialBlob;
import static library.Library.Account_ID;


public class DatabaseActions {
    
                private static Connection conn= null;
                private static Statement st=null; 
                private static ResultSet rs=null;
                private static File PDF;
                private static File IMG;
                private static String search = "%";  
   
    private static void OpenBook(int id) throws SQLException, IOException {
                storeRecentlyReadBook(id);
                storeMostRead(id);
                InputStream input = null;
		FileOutputStream output = null;
		String Tmp= System.getProperty("java.io.tmpdir");
                conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","");
                st= conn.createStatement();
		String sql = "Select * From books where Book_id = "+id;
                rs= st.executeQuery(sql);
                String BookName= null;
		try {
                        
                        File theFile= null;
			//3. Set up a handle to the file
                        if (rs.next()){
                            BookName=rs.getString("Book_Name");
			theFile = new File(Tmp+BookName+".pdf");
			output = new FileOutputStream(theFile);
			
			
				
				input = rs.getBinaryStream("book");

				
				byte[] buffer = new byte[1024];
				while(input.read(buffer)>0){
					output.write(buffer);
					
				}
				

			}
		} catch (Exception exc){
			exc.printStackTrace();
		} 

        

        // Show open file dialog
        File file = new File(Tmp+BookName+".pdf");
       if (Desktop.isDesktopSupported()) {


                Desktop.getDesktop().open(file);

                       }
       conn.close();
        }
       
    private static boolean searchRecentlyReadBook(int Account_ID,int Book_ID) throws SQLException{
        conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","");
        st= conn.createStatement();
        String sql = "Select * from recently where Account_ID="+Account_ID+" AND Book_ID="+Book_ID;
        rs= st.executeQuery(sql);
        boolean isFound=rs.next();
        conn.close();
        return isFound;    
    }    
          
    private static void storeRecentlyReadBook(int Book_ID) throws SQLException{
                        conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","");
                        Statement st= conn.createStatement();
                        if (!searchRecentlyReadBook(Account_ID, Book_ID)){
			String sql = "INSERT INTO recently VALUES ("+Account_ID+","+Book_ID+");";
                        try{int rs= st.executeUpdate(sql);}
                        catch (SQLException ex){
                            System.out.println(ex);
                        }
                        }
                        conn.close();

    }
        
    public static FlowPane getBooks(String cat) throws SQLException{
        conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","");
        st= conn.createStatement();
        String sql=null;
        if(cat != null){
            sql="Select * FROM books WHERE Category='"+cat+"'"+" AND Book_Name LIKE '"+ search+"'";
        }
        else {
            sql="Select * From books WHERE Book_Name LIKE '"+ search+"'";
        }
        rs= st.executeQuery(sql);
        
        FlowPane fpan = new  FlowPane();
        fpan.setLayoutX(10);
        fpan.setLayoutY(160);
        fpan.setHgap(10);
        fpan.setVgap(15);
        fpan.setPrefWidth(800);
        VBox vbox=null;
        while (rs.next()){
            int id=rs.getInt("Book_id");
            vbox = new VBox();
            InputStream input= rs.getBinaryStream("Book_Cover");
            Image image = new Image(input);

            ImageView iv1 = new ImageView(image);
            iv1.setFitHeight(150);
            iv1.setFitWidth(150);
            
            Label lab2 = new Label(rs.getString("Book_Name"));
            lab2.setFont(Font.font(20));
            lab2.setStyle("-fx-font-weight: bold");


            Label lab3 = new Label(rs.getString("Author"));
            lab3.setFont(Font.font(16));

            Label lab4 = new Label(String.valueOf(rs.getInt("NoOfPages")));
            lab4.setFont(Font.font(14));
            vbox.getChildren().addAll(iv1,lab2,lab3,lab4);
            vbox.setAlignment(Pos.CENTER);
            vbox.setStyle("-fx-background-color: White");
            vbox.setOnMousePressed((event) -> {
                try {
                    conn.close();
                    OpenBook(id);
                } catch (SQLException ex) {
                    Logger.getLogger(DatabaseActions.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(DatabaseActions.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

            fpan.getChildren().addAll(vbox);
            }

    
        
        

        conn.close();
        return fpan;
    }
    
    public static FlowPane getBooks(int read) throws SQLException, IOException{
        conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","");
        st= conn.createStatement();
        Statement st2= conn.createStatement();
        String sql = null;
        
                    //Recently Read Books
                    switch (read) {
                        case 12:
                            sql="Select * FROM recently WHERE Account_ID="+Account_ID;  
                            break;
                        case 13:
                            sql = "SELECT * FROM books WHERE Most_Read>=5";
                            break;
                        case 14:
                            LocalDate date= LocalDate.now();
                            LocalDate FDate= date.minusDays(3);
                            sql="SELECT * FROM books WHERE Time_Added BETWEEN '"+FDate+"' AND '"+date+"'";
                            break;
                        default:
                            break;
                    }
        rs= st2.executeQuery(sql);
        FlowPane fpan = new  FlowPane();
        fpan.setLayoutX(10);
        fpan.setLayoutY(160);
        fpan.setHgap(10);
        fpan.setVgap(10);
        fpan.setPrefWidth(800);
        while (rs.next()){
            int id=rs.getInt("Book_id");
            String booksql;
            ResultSet bookrs = null;
            if (search !=null){
            booksql="SELECT * FROM books WHERE Book_id = "+id+" AND Book_Name LIKE '"+search+"'";
            bookrs= st.executeQuery(booksql);
            }
            while(bookrs.next()){
            VBox vbox = new VBox();
            InputStream input= bookrs.getBinaryStream("Book_Cover");
            Image image = new Image(input);

            ImageView iv1 = new ImageView(image);
            iv1.setFitHeight(150);
            iv1.setFitWidth(150);
            Label lab2 = new Label(bookrs.getString("Book_Name"));
            lab2.setFont(Font.font(20));
            lab2.setStyle("-fx-font-weight: bold");


            Label lab3 = new Label(bookrs.getString("Author"));
            lab3.setFont(Font.font(16));

            Label lab4 = new Label(String.valueOf(bookrs.getInt("NoOfPages")));
            lab4.setFont(Font.font(14));

            vbox.getChildren().addAll(iv1,lab2,lab3,lab4);
            vbox.setAlignment(Pos.CENTER);
            vbox.setStyle("-fx-background-color: White");
            vbox.setOnMousePressed((event) -> {
                try {
                    OpenBook(id);
                } catch (SQLException ex) {
                    System.out.print(ex);
                } catch (IOException ex) {
                    Logger.getLogger(DatabaseActions.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            fpan.getChildren().addAll(vbox);
            }

    }
        conn.close();
       return fpan;
        
       
    }
        
    public static void EmailValidation(String email,String pass,Stage stage) throws SQLException{
        
        StackPane secondaryLayout = new StackPane();


            Scene secondScene = new Scene(secondaryLayout, 230, 100);

            // New window (Stage)
            Stage newWindow = new Stage();
            newWindow.setTitle("");
            newWindow.setScene(secondScene);

            // Specifies the modality for new window.
            newWindow.initModality(Modality.WINDOW_MODAL);

        Label lbl = new Label();
        if ((email != null) && (pass !=null)){
            int n=CheckEmail(email, pass);
            switch (n) {
                case 1:
                    lbl.setText("Login successful");
                    newWindow.setOnCloseRequest((event) -> {
                try {
                    LIB_Home.Home(stage);
                } catch (SQLException ex) {
                    Logger.getLogger(DatabaseActions.class.getName()).log(Level.SEVERE, null, ex);
                }
                    });
                    break;
                case 2:
                    lbl.setText("Incorrect Password!\nPls try again");
                    break;
                case 3:
                    lbl.setText("Email Doesn't Exist\nPlease Sign Up!");
                    break;
                case 4:
                    lbl.setText("Invalid Email!");
                    break;
                default:
                    break;
            }
        }
        else {
            lbl.setText("Email or Password is empty");
        }
            
            secondaryLayout.getChildren().add(lbl);
            // Specifies the owner Window (parent) for new window
            newWindow.initOwner(stage);

            // Set position of second window, related to primary window.
            newWindow.setX(850);
            newWindow.setY(400);
            newWindow.setTitle("");
            newWindow.getIcons().add(new Image("Images/Alert.png"));   //"Shadow: Stage Icon =>> Stack_Over_Flow_Icon."
            newWindow.getIcons().add(new Image("Images/RedAlert.png"));
            newWindow.setResizable(false);
            
            newWindow.show();   
    }
     
    private static int CheckEmail(String email,String pass) throws SQLException{
        conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","");
        st= conn.createStatement();
        String regexPattern = "^(.+)@(\\S+)$";
        if(Pattern.compile(regexPattern).matcher(email).matches()){
            String query1= "Select * From account WHERE Email = '" + email + "' ";
            rs = st.executeQuery(query1);
                    if(rs.next()){
                    if(rs.getString("Password").toString().equals(pass)){    //Check the login to give access
                        Account_ID= rs.getInt("Account_ID");
                        conn.close();
                        return 1;
                    }
                    else {
                         conn.close();
                         return 2;
                    }
                    }
                    
                else {
                     conn.close();
                     return 3;
                }
        }
        else
        {
            conn.close();
            return 4;
        }
    }
     
   public static void SignUp(String Username,String Email,String Pass,String Pass2,String Gender,Stage stage) throws SQLException{
       Label lbl= new Label();
       

       
       StackPane secondaryLayout = new StackPane();


            Scene secondScene = new Scene(secondaryLayout, 230, 100);

            // New window (Stage)
            Stage newWindow = new Stage();
            newWindow.setTitle("");
            newWindow.setScene(secondScene);
            newWindow.initModality(Modality.WINDOW_MODAL);
       if((Username != null) && (Email != null) && (Pass != null) && (Pass2 != null) && (Gender != null)){
           if (CheckEmail(Email, "") == 4) {
           lbl.setText("Invalid Email");
            }
           else if (CheckEmail(Email,"")==2){
              lbl.setText("Email Exists\nPlease Login");
          }
          else{
              if (Pass.equals(Pass2)){
                  conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","");
                  st= conn.createStatement();
                  String sql = "INSERT INTO account VALUES ( null, '" + Username + "' , '" 
                          + Email + "' , '" + Pass + "' , '" 
                          + Gender + "' , 0 )";
                      int is = st.executeUpdate(sql);
                  
                  
                  if (is==1){
                      lbl.setText("Thanks For Sign Up <3");
                      newWindow.setOnCloseRequest(event ->{
                          Account.Login(stage);
                      });
                  }

              }
              else {
                  lbl.setText("Passwords Don't match");
              }
          }
      }
       else {
           lbl.setText("Please Fill Empty Fields");
       }
            // Specifies the modality for new window.

            secondaryLayout.getChildren().add(lbl);
            // Specifies the owner Window (parent) for new window
            newWindow.initOwner(stage);

            // Set position of second window, related to primary window.
            newWindow.setX(850);
            newWindow.setY(600);
            newWindow.setTitle("");
            newWindow.getIcons().add(new Image("Images/Alert.png"));   //"Shadow: Stage Icon =>> Stack_Over_Flow_Icon."
            newWindow.getIcons().add(new Image("Images/RedAlert.png"));
            newWindow.setResizable(false);
            
            newWindow.show();
            conn.close();
   }
   
   public static void storeBook(String BookName , String Author , String Category ,File pdf, File img,int NoOfPages,Stage stage) throws SQLException{
       
       Label lbl= new Label();
       Blob BIMG = new SerialBlob(toBlob(img));
       Blob BPDF = new SerialBlob(toBlob(pdf));
       if ((BIMG == null) || (BPDF == null)){
           System.out.println("Empty Blob");
       }
       LocalDate date = LocalDate.now();
       conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","");
       String sql = "INSERT INTO books VALUES (null, '"+BookName+"', '', '"
               + Author +"' , '"+Category+"', '', "+NoOfPages+", 0, '"+date+"')";
        st= conn.createStatement();
        int rs= st.executeUpdate(sql);
       if (rs==1){
           String query="UPDATE books SET Book_Cover=? , book=? WHERE Book_id = (Select Max(Book_id) from books);";
           PreparedStatement bt= conn.prepareStatement(query);
           bt.setBlob(1, BIMG);
           bt.setBlob(2, BPDF);
           try {int bs= bt.executeUpdate();}
           catch (SQLException ex){
               System.out.print(ex);
           }
       }
       lbl.setText(rs+"row inserted succussfully");
       
       

       
       StackPane secondaryLayout = new StackPane();


            Scene secondScene = new Scene(secondaryLayout, 230, 100);

            // New window (Stage)e
            Stage newWindow = new Stage();
            newWindow.setTitle("Alert");
            newWindow.setScene(secondScene);
            newWindow.initModality(Modality.WINDOW_MODAL);
       
            // Specifies the modality for new window.

            secondaryLayout.getChildren().add(lbl);
            // Specifies the owner Window (parent) for new window
            newWindow.initOwner(stage);

            // Set position of second window, related to primary window.
            newWindow.setX(850);
            newWindow.setY(600);
            newWindow.setTitle("");
            newWindow.getIcons().add(new Image("Images/Alert.png"));   //"Shadow: Stage Icon =>> Stack_Over_Flow_Icon."
            newWindow.getIcons().add(new Image("Images/RedAlert.png"));
            newWindow.setResizable(false);
            conn.close();
            newWindow.show();
       
   }
   
   private static Blob toBlob(File file){
            Path pdfPath = Paths.get(file.getAbsolutePath());
            byte[] pdf = null;
            try {
                pdf = Files.readAllBytes(pdfPath);
            }
            catch (IOException ex)
            {
                System.out.println(ex);
            }
             Blob blob = null ;
             try {
                 blob = new SerialBlob(pdf);
             } catch (SQLException ex) {
                 System.out.println(ex);
             }

             return blob;
   } 
   
   public static void setFile(File file,int n){
       if (n==1){
           PDF = file;
       }
       else 
           IMG = file;
   }
   
   public static File getFile(int n){
       if (n==1){
           return PDF;
       }
       else
           return IMG;
   }
   
   public static void searchbooks(String x){
       if (x == null) {
           search = "%";
       }
       else {
           search ="%"+ x+"%";
       }
   }
   
   public static void searchbooks(char x){
       int n= (int) x;
       if (n == 0) {
           search = "%";
       }
       else {
           search = x+"%";
       }
   }
   
   private static void storeMostRead(int Book_id) throws SQLException{
       conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","");
       st= conn.createStatement();
       String sql = "Update books set Most_Read="
               + "(Select Most_Read FROM books WHERE Book_id="+Book_id+")+1 WHERE Book_id="+Book_id;
       int rs=st.executeUpdate(sql);
       conn.close();
   }
   
   public static boolean isAdmin(int id) throws SQLException{
        int Account_ID = id; 
        conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","");
        st= conn.createStatement();
        String sql = "Select * from account where Account_ID="+Account_ID+" AND Admin= 1";
        rs= st.executeQuery(sql);
        boolean isAdmin=rs.next();
        conn.close();
        return isAdmin;        
   }

   public static void onclose() throws SQLException{
        conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","");
        st= conn.createStatement();
        String sql = "DELETE FROM recently where Account_ID= 0";
        int rs= st.executeUpdate(sql); 
        conn.close();
   }
   
   public static VBox AdminContents(Stage stage) throws SQLException{
       conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","");
       Statement st2= conn.createStatement();
       String sql = "Select * from account where Account_ID > 0 AND Account_ID !="+Account_ID;
       
       HBox hbox = new HBox();
       hbox.setSpacing(255); // spacing in username /admin /email
         
       VBox vbox1 = new VBox();
       vbox1.setSpacing(25);//upper space


       hbox.setPadding(new Insets(40,20,10,10));
       vbox1.setPadding(new Insets(40,20,10,10));

       Label t1 = new Label("Username");
       Label t2 = new Label("Email");
       Label t3 = new Label("Admin");
       hbox.getChildren().addAll(t1,t2,t3);
       vbox1.getChildren().add(hbox);
       
       
        ResultSet rs2 = st2.executeQuery(sql);
        while(rs2.next()){
        Pane Contents= new Pane();
        Label Username = new Label(rs2.getString("Username"));
        Label UserEmail = new Label(rs2.getString("Email"));
        UserEmail.setLayoutX(250);
        Button btn_2 = new Button();
        btn_2.setLayoutX(650);
        btn_2.setMinSize(60, 30);
        int id=rs2.getInt(1);
        btn_2.setText(DatabaseActions.isAdmin(rs2.getInt("Account_ID")) ? "Admin" : "User");
        btn_2.setOnAction((event) -> {
            try {
                conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","");
                Statement st = conn.createStatement();
                if(btn_2.getText() == "Admin"){
                String sql2="Update account set Admin = 0 where Account_ID="+id;
                int rs= st.executeUpdate(sql2);
                Administrator.Administrator(stage);
                        }
                else {    
                String sql2="Update account set Admin = 1 where Account_ID="+id;
                int rs= st.executeUpdate(sql2);
                Administrator.Administrator(stage);
                }
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseActions.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });
        Contents.getChildren().addAll(Username,UserEmail,btn_2);
        vbox1.getChildren().add(Contents);
        }
        return vbox1;
   }
   
}