/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EthioClicks;

import EthioClicks.data.userProfileData;
import java.io.File;
import java.io.FileInputStream;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import java.io.FileNotFoundException; 
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;


import java.io.*; 
import java.net.URISyntaxException;
import java.nio.channels.FileChannel;
import java.nio.file.Files; 
import java.nio.file.*; 
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
 
public class userRegister {
    
    String imagePath = "none";
    public userRegister(){
        // data is passed here
    }
    public void userRegistration(Stage primaryStage , Scene mainscene)  throws FileNotFoundException {
        
        GridPane grid = new GridPane(); 
        grid.setStyle(mainPogram.SCENE_STYLE);       
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        
        Text scenetitle = new Text("Add New User Profile Data");        
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        //scenetitle.setStyle(userRegister.LABEL_STYLE);
        grid.add(scenetitle, 0, 0, 2, 1);


        ImageView imageView = new ImageView();         
        imageView.setFitHeight(120); 
        imageView.setFitWidth(200); 
        
        imageView.setPreserveRatio(true);
        
        grid.add(imageView, 1, 1);
        Label userName = new Label("Full Name:");
        userName.setStyle(mainPogram.STRING_STYLE);
        grid.add(userName, 0, 2);

        TextField userFullName = new TextField();
        userFullName.setPromptText("Your Full Name");
        grid.add(userFullName, 1, 2);
        
        Label emailLabel = new Label("Email Address:");
        emailLabel.setStyle(mainPogram.STRING_STYLE);
        grid.add(emailLabel, 0, 3);
       

        TextField email = new TextField();
        email.setPromptText("Your Email Address");
        grid.add(email, 1, 3); 
        
        Label gender = new Label("Gender: ");
        gender.setStyle(mainPogram.STRING_STYLE);
        grid.add(gender, 0, 4);

        TextField genderBox = new TextField();
        genderBox.setPromptText("Your Gender");
        grid.add(genderBox, 1, 4); 
        
        Label phonenum = new Label("Phone Number: ");        
        phonenum.setStyle(mainPogram.STRING_STYLE);
        grid.add(phonenum, 0, 5);

        TextField phonenumBox = new TextField ();
        phonenumBox.setPromptText("Type Phone Number");
        grid.add(phonenumBox, 1, 5); 
        
        Button back = new Button("Back");        
        Button profilePickerBtn = new Button("Upload Profile Pic");
        Button regBtn = new Button("Register");

        //set button style 
        back.setStyle(mainPogram.BUTTON_STYLE_BLUE);
        profilePickerBtn.setStyle(mainPogram.BUTTON_STYLE_BLUE);
        regBtn.setStyle(mainPogram.BUTTON_STYLE_BLUE);


        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().addAll(back , regBtn ,profilePickerBtn);
        grid.add(hbBtn, 1, 8); 

        Scene scene = new Scene(grid, 450, 525);
        
        back.setOnAction(new EventHandler<ActionEvent>() {            
            @Override
            public void handle(ActionEvent event) {
                primaryStage.setScene(mainscene);
                primaryStage.setResizable(false);
                primaryStage.setTitle("Ethioclicks: Simple User Profile Program in Java ");
                primaryStage.show();
            }
        });
        regBtn.setVisible(false);
        
        regBtn.setOnAction(new EventHandler<ActionEvent>() {            
            @Override
            public void handle(ActionEvent event) {
                
                            userProfileData user1 = new userProfileData();
         
                            user1.setUserId(1);
                            user1.setName(userFullName.getText());                           
                            user1.setEmail(email.getText());
                            user1.setGender(genderBox.getText());
                            user1.setPhoneNumber(phonenumBox.getText());

                            if(imagePath == "none"){
                               try{
                               user1.setUserProfile(getPathTempPic(user1.getGender()));
                               }catch(Exception e){ e.printStackTrace();
                              }
                            }else{
                            user1.setUserProfile(imagePath.replaceAll("file:/", ""));
                        }
           try {
                    mainPogram.getInstance().addNewUser(user1 , 0);
                    System.out.println("New User Added Successfully \'"+user1.getName()+"\'");

                GridPane grid2 = new GridPane(); 
                grid2.setStyle(mainPogram.SCENE_STYLE);       
                grid2.setAlignment(Pos.CENTER);
                grid2.setHgap(10);
                grid2.setVgap(10);
                grid2.setPadding(new Insets(25, 25, 25, 25));

        
        
                Text scenetitle = new Text("New Profile Added Successfully");        
                scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
                grid2.add(scenetitle, 0, 0, 2, 1);

                ImageView imageView = new ImageView(user1.getProfileImage().getImage());         
                imageView.setFitHeight(250); 
                imageView.setFitWidth(250); 
                imageView.setPreserveRatio(true);
        
                grid2.add(imageView, 1 , 1);

                Label userName = new Label("Full Name:");
                userName.setStyle(mainPogram.STRING_STYLE);
                grid2.add(userName, 0, 2);

                Label userFullName = new Label();
                userFullName.setText(user1.getName());
                grid2.add(userFullName, 1, 2);

                Label emailLabel = new Label("Email Address:");
                emailLabel.setStyle(mainPogram.STRING_STYLE);
                grid2.add(emailLabel, 0, 3);

                Label email = new Label();
                email.setText(user1.getEmail());
                grid2.add(email, 1, 3); 
        
                Label gender = new Label("Gender: ");
                gender.setStyle(mainPogram.STRING_STYLE);
                grid2.add(gender, 0, 4);

                Label genderBox = new Label();
                genderBox.setText(user1.getGender());
                grid2.add(genderBox, 1, 4); 
        
                Label phonenum = new Label("Phone Number: ");
                phonenum.setStyle(mainPogram.STRING_STYLE);        
                grid2.add(phonenum, 0, 5);

                Label phonenumBox = new Label ();
                phonenumBox.setText(user1.getPhoneNumber());
                grid2.add(phonenumBox, 1, 5); 

                Button backBtn = new Button("Back");        
                Button callBtn = new Button("Call Now");
                Button emailBtn = new Button("Send Email");

                //add button style
               backBtn.setStyle(mainPogram.BUTTON_STYLE_BLUE);
               callBtn.setStyle(mainPogram.BUTTON_STYLE_GREEN);
               emailBtn.setStyle(mainPogram.BUTTON_STYLE_GREEN);

                HBox hbBtn = new HBox(10);
                hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
                hbBtn.getChildren().addAll(backBtn  , callBtn ,emailBtn);
                grid2.add(hbBtn, 1, 8); 

        
        
                Scene scene2 = new Scene(grid2, 450, 525);

                backBtn.setOnAction(new EventHandler<ActionEvent>() {            
                    @Override
                    public void handle(ActionEvent event) {
                        primaryStage.setScene(scene);
                        primaryStage.setResizable(false);
                        primaryStage.setTitle("EthioClicks: Add New Profile SCREEN");
                        primaryStage.show();
                    }
                });

                primaryStage.setScene(scene2);
                primaryStage.setResizable(false);
                primaryStage.setTitle("EthioClicks: Successfully Added New Profile Data");

            primaryStage.show();
            } catch (Exception ex) {
                    System.out.println("Adding New Profile Failed: "+ex.toString());

                GridPane grid2 = new GridPane(); 
                grid2.setStyle(mainPogram.SCENE_STYLE);       
                grid2.setAlignment(Pos.CENTER);
                grid2.setHgap(10);
                grid2.setVgap(10);
                grid2.setPadding(new Insets(25, 25, 25, 25));

        
        
                Text scenetitle = new Text("Adding New Profile Record Failed");        
                scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));

                grid2.add(scenetitle, 0, 0, 2, 1);

                

                Label userName = new Label(ex.toString());
                grid2.add(userName, 1, 4);
                Button backBtn = new Button("Back");        
                Button retryBtn = new Button("Retry Again");
                Button emailBtn = new Button("Report Problem");

                // set style for the buttons 
               backBtn.setStyle(mainPogram.BUTTON_STYLE_GREEN);
               retryBtn.setStyle(mainPogram.BUTTON_STYLE_GREEN);
               emailBtn.setStyle(mainPogram.BUTTON_STYLE_GREEN);


                HBox hbBtn = new HBox(10);
                hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
                hbBtn.getChildren().addAll(backBtn  , retryBtn ,emailBtn);
                grid2.add(hbBtn, 1, 8); 

        
        
                Scene scene2 = new Scene(grid2, 450, 525);

                backBtn.setOnAction(new EventHandler<ActionEvent>() {            
                    @Override
                    public void handle(ActionEvent event) {
                        primaryStage.setScene(scene);
                        primaryStage.setResizable(false);
                        primaryStage.setTitle("EthioClicks: Add New Profile SCREEN");
                        primaryStage.show();
                    }
                });
                primaryStage.setScene(scene2);
                primaryStage.setResizable(false);
                primaryStage.setTitle("EthioClicks: Adding New Profile Failed");

            primaryStage.show();
            }
          }
        });
        profilePickerBtn.setOnAction(new EventHandler<ActionEvent>() {            
            @Override
            public void handle(ActionEvent event) {
                
                
                try{
                    FileChooser file = new FileChooser();
                    file.setTitle("EthioClicks Program Open Image File Dialog");  
                    
                    
                    File filename = file.showOpenDialog(primaryStage); 
                     FileChooser.ExtensionFilter extFilterJPG = 
                    new FileChooser.ExtensionFilter("JPG files (*.JPG)", "*.JPG");
                    FileChooser.ExtensionFilter extFilterjpg = 
                            new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg");
                    FileChooser.ExtensionFilter extFilterPNG = 
                            new FileChooser.ExtensionFilter("PNG files (*.PNG)", "*.PNG");
                    FileChooser.ExtensionFilter extFilterpng = 
                            new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
                    file.getExtensionFilters()
                    .addAll(extFilterJPG, extFilterjpg, extFilterPNG, extFilterpng);
                     if (filename != null) {
                        imagePath = filename.toURI().toString();
                        try {
                            imageView.setImage(new Image(new FileInputStream(filename.getAbsoluteFile())));
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(userRegister.class.getName()).log(Level.SEVERE, null, ex);
                        }
                     }
                     
                     
                    regBtn.setVisible(true);
                    
                }catch(Exception e){
                e.printStackTrace();
             }
                  
            }
        });
        
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("EthioClicks: Add New Profile SCREEN");
        
        primaryStage.show();
    }

  public String getPathTempPic(String str) throws URISyntaxException{
     String dirPath = new File(getClass().getProtectionDomain().getCodeSource().getLocation().toURI()).getParentFile().getPath()+"\\"; 
     String newDirName = "ethioClicksImages";
     File newDirectory = new File(dirPath+File.separator+newDirName);
      
    if(str.contains("female")){
          return dirPath+File.separator+newDirName+File.separator+"femaleAvatar.png";
    }else{
       return dirPath+File.separator+newDirName+File.separator+"maleAvatar.jpg";
    }
 }
}
