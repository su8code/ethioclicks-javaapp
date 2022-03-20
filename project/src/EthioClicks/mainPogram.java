package EthioClicks;

import EthioClicks.data.userProfileData;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.image.Image;
import java.sql.*;
import java.time.LocalDateTime;
import javafx.collections.ListChangeListener;
import javafx.scene.control.LabelBuilder;
import javafx.scene.text.Font;
import javafx.scene.layout.*;

public class mainPogram extends Application {

    //Global Style Configuration Variables for all 

    public static String BUTTON_STYLE_GREEN = "-fx-background-color: #09DCA4; -fx-text-fill: white;";
    public static String BUTTON_STYLE_BLUE = "-fx-background-color: #3D79E1; -fx-text-fill: white;";
    public static String SCENE_STYLE = "-fx-background-color: #F9F9F9;";
    public static String STRING_STYLE = "-fx-text-fill: #609AFF; -fx-font-size: 16px; -fx-font-weight: bold;";

    public static String sceneTitle = "Ethioclicks: Simple User Profile Program in Java ";
    //mainPogram.BUTTON_STYLE_BLUE 

    private static boolean isDataInitialized = false;
    private static mainPogram instance = new mainPogram();
    ArrayList<userProfileData> users = new ArrayList<userProfileData>();     
    ObservableList<ImageView> usersProfilePic = FXCollections.observableArrayList();
    
    JavaDatabaseClass DB;
    
    public static mainPogram getInstance(){        
        return instance;
    }


    @Override
    public void start(Stage primaryStage) throws FileNotFoundException, SQLException, URISyntaxException {
        try{
             JavaDatabaseClass d = new JavaDatabaseClass();
        }catch(Exception e){ 
                mainPogram.getInstance().sceneTitle = e.toString();
                System.out.println("DB: "+e.toString());
       } 
        //initialize users profile data
        initialize();
        
        usersProfilePic.addListener(new ListChangeListener<ImageView>(){       
            
            @Override
            public void onChanged(ListChangeListener.Change<? extends ImageView> change) {
            while (change.next()) {
               // System.out.println(change.getAddedSubList());
            }
                System.out.println("clicked");
          }
           
    });

        Button regbtn = new Button();
        regbtn.setMinWidth(200);
        regbtn.setText("Add New");
        
        Button profilebtn = new Button();
        profilebtn.setMinWidth(200);
        profilebtn.setText("Show All");
       
        Button exitbtn = new Button();
        exitbtn.setMinWidth(200);
        exitbtn.setText("Exit");

        regbtn.setStyle(mainPogram.BUTTON_STYLE_BLUE);
        profilebtn.setStyle(mainPogram.BUTTON_STYLE_BLUE);
        exitbtn.setStyle(mainPogram.BUTTON_STYLE_BLUE);

        regbtn.setFont(Font.font("Verdana", 18));
        profilebtn.setFont(Font.font("Verdana", 18));
        exitbtn.setFont(Font.font("Verdana", 18));

        VBox root = new VBox();
        
        root.setAlignment(Pos.CENTER);
        root.setSpacing(5);
        //Label icon = createIconLabel(iconName);
        root.getChildren().addAll( regbtn, profilebtn , exitbtn);
        
        Scene scene = new Scene(root,  450, 525);
        root.setStyle(mainPogram.SCENE_STYLE);


        regbtn.setOnAction(new EventHandler<ActionEvent>() { 
           
            @Override
            public void handle(ActionEvent event) {
                try{
                userRegister reg = new userRegister();
                reg.userRegistration(primaryStage, scene);
                }
                catch(Exception e){
                    mainPogram.getInstance().sceneTitle = e.toString();
                }
            }
        });
        
        
        
        profilebtn.setOnAction(new EventHandler<ActionEvent>() {            
            @Override
            public void handle(ActionEvent event) {
              
                NewFXMain profile ;
                try {
                    profile =  new NewFXMain();
                    profile.startLister(primaryStage , scene);
                   } catch (FileNotFoundException ex) {
                    //Logger.getLogger(mainPogram.class.getName()).log(Level.SEVERE, null, ex);
                    mainPogram.getInstance().sceneTitle = ex.toString();
                }
           
                System.out.println("Profile Button is Clicked");
            }
        });

        exitbtn.setOnAction(new EventHandler<ActionEvent>() {            
            @Override
            public void handle(ActionEvent event) {
               System.out.println("Closing The Program...");
               System.exit(0);
            }
        });
        
         //getFileStat();
        //scene.getStylesheets().add("/data/style.css");

        primaryStage.setTitle(mainPogram.getInstance().sceneTitle);
        primaryStage.setScene(scene);
        Image icon = new Image(new FileInputStream(mainPogram.getInstance().getPath()+File.separator+"icon.png"));
        
       primaryStage.getIcons().add(icon);
                
        primaryStage.setOnCloseRequest( new EventHandler<WindowEvent>(){
           @Override
           public void handle(WindowEvent e){
               System.out.println("Program exiting Good bye ");
               System.out.println("Good Bye");
               
               
               //todo: save any changes to the database before the program is closed
           }
        });

        primaryStage.show();
        
    }
    
    public void initialize() throws FileNotFoundException, SQLException{

    try{
         DB = new JavaDatabaseClass();
         DB.fetchDataToApp();
}catch(Exception e){  
      mainPogram.getInstance().sceneTitle = e.toString();
      System.out.println("DB: "+e.toString());
} 
      try{
          System.out.println(getPath());
       }catch(Exception e){
             e.printStackTrace();
       }
    }
    
    public void addNewUser(userProfileData newUser , int num) throws SQLException, FileNotFoundException, URISyntaxException, IOException{

        moveFile(newUser.getUserProfile() , newUser);
        newUser.setUserId(mainPogram.getInstance().users.size()+1);
        mainPogram.getInstance().users.add(newUser);  
        System.out.println("inside add new user for : "+newUser.getName());

try{
        DB = new JavaDatabaseClass();}catch(Exception e){        
            System.out.println("DB: "+e.toString());
            mainPogram.getInstance().sceneTitle = e.toString();
} 
        if(num == 1){
               //program fetching from DB at this point don't add the data to to database
                System.out.println("addNewUser: Fetching from Database... "); 
         }
        else{
            DB.addNewUserToDatabase(newUser);
            System.out.println("Adding to Database... ");
        }
        
        System.out.println("instance: "+getInstance().users+"  > UserID:"+newUser.getUserId());
        System.out.println("after adding "+newUser.getName());

       try{
             getInstance().usersProfilePic.add(newUser.getProfileImage()); 
             System.out.println("Pic: "+newUser.getName()+" > ");
         }catch(Exception e){             
             System.out.println("adding to a list of Profile Pictures  failed: "+e.toString());
        }  
    }
  public void updateUserData(userProfileData newUser)throws SQLException , FileNotFoundException{
     mainPogram.getInstance().users.set(EditProfile.userId-1 , newUser);
     mainPogram.getInstance().usersProfilePic.set(EditProfile.userId-1 , newUser.getProfileImage());
     System.out.println(">>>UU---00000ser Name:  "+mainPogram.getInstance().users.get(EditProfile.userId-1).getName());

     try{
         moveFile(newUser.getUserProfile() , newUser);
         JavaDatabaseClass.updateUserData(newUser);
       }catch(Exception e){

     }
     
     System.out.println("\n mainPogram>updateUserData()\n **> User Name: "+newUser.getName());
  }
 public String moveFile(String source , userProfileData newUser) throws URISyntaxException, IOException{
    
     String  name = newUser.getName();
     int userId = newUser.getUserId();

     System.out.println("Moving From Source: "+source);
     String fe = "";
	int i = source.lastIndexOf('.');
    if (i > 0)
      {
	fe = source.substring(i+1);
      }

     LocalDateTime now = LocalDateTime.now();

     String destination = "";


        String dirPath = getPath();
        String newDirName = "ethioClicksImages";
        File newDirectory = new File(dirPath+File.separator+newDirName);

        //Create directory for non existed path.

        boolean isCreated = newDirectory.mkdirs();
        if (isCreated) {
            System.out.printf("1. Successfully created directories, path:%s",
                    newDirectory.getCanonicalPath());
        } else if (newDirectory.exists()) {
            System.out.printf("1. Directory path already exist, path:%s",
                    newDirectory.getCanonicalPath());
        } else {
            System.out.println("1. Unable to create directory");
            
        }

     destination = dirPath+File.separator+newDirName+File.separator;
     destination += "ethioClicksImage-"+name.split(" ")[0]+userId+"."+fe;
     
     try{

         File fileToCopy = new File(source);
         FileInputStream inputStream = new FileInputStream(fileToCopy);
         FileChannel inChannel = inputStream.getChannel();

         File newFile = new File(destination);
         FileOutputStream outputStream = new FileOutputStream(newFile);
         FileChannel outChannel = outputStream.getChannel();

         inChannel.transferTo(0, fileToCopy.length(), outChannel);

         inputStream.close();
         outputStream.close();
         System.out.println("\nFrom\n"+source+"\n>> to >>\n"+destination+"\n\t**File Moved Successfully");
         
         newUser.setUserProfileCachedAddress(destination);
         System.out.println("Temporary: "+newUser.getUserProfileCachedAddress());
         
     }catch(Exception e){ 
       e.printStackTrace();
    }
     
   return destination;
  }
 
 public String getPath() throws URISyntaxException{
     String jarLocation = new File(getClass().getProtectionDomain().getCodeSource().getLocation().toURI()).getParentFile().getPath()+"\\"; 
     return jarLocation;
 }
 
 public void getFileStat(){
    /*  int count = 0;
       File dir = new File("C:\\Users\\Fen\\Documents\\NetBeansProjects\\JavaFXApplication3\\build\\ethioClicksImages");
      String[] file = dir.list();

      for(String str: file) {
         count++;
         System.out.print(str+" ");
      }

      System.out.println("\nTotal files = " + file.length );
  */
 }
    public static void main(String[] args) {
        launch(args);
    }    
}