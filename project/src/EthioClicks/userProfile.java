/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EthioClicks;

import EthioClicks.data.userProfileData;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class userProfile {
    
    public void getUserProfile(Stage primaryStage , Scene mainscene , userProfileData user) throws FileNotFoundException {
        

        GridPane grid = new GridPane(); 
        grid.setStyle(mainPogram.SCENE_STYLE); 
      
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        
        
        Text scenetitle = new Text("Detailed Information");        
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        //scenetitle.setFill(Color.BLUE);

        grid.add(scenetitle, 0, 0, 2, 1);

        ImageView imageView = new ImageView(user.getProfileImage().getImage());         
        imageView.setFitHeight(250); 
        imageView.setFitWidth(250); 
        imageView.setPreserveRatio(true);
        
        grid.add(imageView, 1 , 1);

        Label userName = new Label("Full Name:");
        userName.setStyle(mainPogram.STRING_STYLE);
        grid.add(userName, 0, 2);

        Label userFullName = new Label();
        userFullName.setText(user.getName());
        grid.add(userFullName, 1, 2);

        Label emailLabel = new Label("Email Address:");
        emailLabel.setStyle(mainPogram.STRING_STYLE);
        grid.add(emailLabel, 0, 3);

        Label email = new Label();
        email.setText(user.getEmail());
        grid.add(email, 1, 3); 
        
        Label gender = new Label("Gender: ");
        gender.setStyle(mainPogram.STRING_STYLE);
        grid.add(gender, 0, 4);

        Label genderBox = new Label();
        genderBox.setText(user.getGender());
        grid.add(genderBox, 1, 4); 
        
        Label phonenum = new Label("Phone Number: "); 
        phonenum.setStyle(mainPogram.STRING_STYLE);       
        grid.add(phonenum, 0, 5);

        
        Label phonenumBox = new Label ();
        phonenumBox.setText(user.getPhoneNumber());
        grid.add(phonenumBox, 1, 5); 
        

        Label userId = new Label("User Id: "); 
        userId.setStyle(mainPogram.STRING_STYLE);       
        grid.add(userId, 0, 6);

        Label userIdNum = new Label();
        userIdNum.setText(""+user.getUserId());
        grid.add(userIdNum, 1, 6);

        Button back = new Button("Back");        
        Button editBtn = new Button("Edit Profile");
        Button emailBtn = new Button("Call Now");

        back.setStyle(mainPogram.BUTTON_STYLE_BLUE);
        editBtn.setStyle(mainPogram.BUTTON_STYLE_BLUE);
        emailBtn.setStyle(mainPogram.BUTTON_STYLE_BLUE);

        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().addAll(back , editBtn ,emailBtn);
        grid.add(hbBtn, 1, 8); 
        
        editBtn.setOnAction(new EventHandler<ActionEvent>(){
         @Override

          public void handle(ActionEvent event){
           EditProfile editThisUser = new EditProfile();
            try{
               editThisUser.editProfileData(primaryStage,mainscene , user );
            }
           catch(Exception e){
            e.printStackTrace();
          }
           }
        });
        
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
        
        primaryStage.setResizable(false);
        primaryStage.setTitle("Ethioclicks: Show User Profile SCREEN");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
