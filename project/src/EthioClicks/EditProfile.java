package EthioClicks;

import java.io.File;
import java.io.FileInputStream;
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

public class EditProfile {
    String imagePath = "none";
    public static int userId;

    public EditProfile(){
    
    }
    
    public void editProfileData(Stage primaryStage , Scene mainscene , userProfileData user) throws FileNotFoundException {
        EditProfile.userId = mainPogram.getInstance().users.indexOf(user) + 1;
        
        System.out.print("Update Target User Id: "+this.userId);
        GridPane grid = new GridPane();   
        grid.setStyle(mainPogram.SCENE_STYLE);     
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        
        Text scenetitle = new Text("Edit User Profile of \" "+mainPogram.getInstance().users.get(userId-1).getName()+" \" ");        
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);


        ImageView imageView = new ImageView();         
        imageView.setFitHeight(120); 
        imageView.setFitWidth(200); 
        imageView.setImage(user.getProfileImage().getImage());
        imageView.setPreserveRatio(true);
        
        grid.add(imageView, 1, 1);
        Label userName = new Label("Full Name:");
        userName.setStyle(mainPogram.STRING_STYLE);   
        grid.add(userName, 0, 2);

        TextField userFullName = new TextField();
        userFullName.setText(user.getName());
        grid.add(userFullName, 1, 2);

        Label emailLabel = new Label("Email Address:");
        emailLabel.setStyle(mainPogram.STRING_STYLE);
        grid.add(emailLabel, 0, 3);

        TextField email = new TextField();
        email.setText(user.getEmail());
        grid.add(email, 1, 3); 
        
        Label gender = new Label("Gender: ");
        gender.setStyle(mainPogram.STRING_STYLE);
        grid.add(gender, 0, 4);

        TextField genderBox = new TextField();
        genderBox.setText(user.getGender());
        grid.add(genderBox, 1, 4); 
        
        Label phonenum = new Label("Phone Number: ");
        phonenum.setStyle(mainPogram.STRING_STYLE);        
        grid.add(phonenum, 0, 5);

        TextField phonenumBox = new TextField ();
        phonenumBox.setText(user.getPhoneNumber());
        grid.add(phonenumBox, 1, 5); 
        
        Button back = new Button("Back");        
        Button profilePickerBtn = new Button("Upload Profile Pic");
        Button regBtn = new Button("Update");

        // set button style for each
        back.setStyle(mainPogram.BUTTON_STYLE_BLUE);
        profilePickerBtn .setStyle(mainPogram.BUTTON_STYLE_BLUE);
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
                primaryStage.setTitle("Ethioclicks: Simple User Profile Program in Java");
                primaryStage.show();
            }
        });
        regBtn.setVisible(false);
        
        regBtn.setOnAction(new EventHandler<ActionEvent>() {            
            @Override
            public void handle(ActionEvent event) {
                
                            userProfileData user1 = new userProfileData();
                            
                            user1.setUserId(EditProfile.userId);
                            user1.setName(userFullName.getText());                           
                            user1.setEmail(email.getText());
                            user1.setGender(genderBox.getText());
                            user1.setPhoneNumber(phonenumBox.getText());

                            if(imagePath == "none"){
                               try{
                                 user1.setUserProfile(mainPogram.getInstance().users.get(userId-1).getUserProfile());
                               }catch(Exception e){ e.printStackTrace();
                              }
                            }else{
                            user1.setUserProfile(imagePath.replaceAll("file:/", ""));
                            
                        }
                try {
                    
                    try{
                              

                              mainPogram.getInstance().updateUserData(user1);
                   }catch(Exception e){
                       e.printStackTrace();
                 }

                GridPane grid2 = new GridPane(); 
                grid2.setStyle(mainPogram.SCENE_STYLE);       
                grid2.setAlignment(Pos.CENTER);
                grid2.setHgap(10);
                grid2.setVgap(10);
                grid2.setPadding(new Insets(25, 25, 25, 25));

        
        
                Text scenetitle = new Text(user.getName()+"'s Profile Updated Successfully");        
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
                Button emailBtn = new Button("Message");

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
                        primaryStage.setTitle("EthioClicks: Updated Successfully");
                        primaryStage.show();
                    }
                });
                primaryStage.setScene(scene2);
                primaryStage.setResizable(false);
                primaryStage.setTitle("EthioClicks: Updated Successfully");

            primaryStage.show();
            } catch (Exception ex) {
                    System.out.println("Adding New User Failed: "+ex.toString());

                GridPane grid2 = new GridPane();
                grid2.setStyle(mainPogram.SCENE_STYLE);        
                grid2.setAlignment(Pos.CENTER);
                grid2.setHgap(10);
                grid2.setVgap(10);
                grid2.setPadding(new Insets(25, 25, 25, 25));

        
        
                Text scenetitle = new Text("Registration Failed");        
                scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));

                grid2.add(scenetitle, 0, 0, 2, 1);

                

                Label userName = new Label(ex.toString());
                grid2.add(userName, 1, 4);
                Button backBtn = new Button("Back");        
                Button callBtn = new Button("Report");
                Button emailBtn = new Button("Send Email");

               backBtn.setStyle(mainPogram.BUTTON_STYLE_GREEN);
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
                        primaryStage.setTitle("Edit User Profile of \" "+mainPogram.getInstance().users.get(EditProfile.userId-1).getName()+" \" ");
                        primaryStage.show();
                    }
                });
                primaryStage.setScene(scene2);
                primaryStage.setResizable(false);
                primaryStage.setTitle("EthioClicks: Adding New User Failed");

            primaryStage.show();
            }


            }
        });
        profilePickerBtn.setOnAction(new EventHandler<ActionEvent>() {            
            @Override
            public void handle(ActionEvent event) {
                
                
                try{
                    FileChooser file = new FileChooser();
                    file.setTitle("Open File");  
                    
                    
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
        primaryStage.setTitle("Edit User Profile of \" "+mainPogram.getInstance().users.get(EditProfile.userId-1).getName()+" \" ");
        
        primaryStage.show();
    }
}
