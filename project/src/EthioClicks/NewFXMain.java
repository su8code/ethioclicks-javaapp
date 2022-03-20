/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EthioClicks;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.geometry.Pos;

public class NewFXMain {
    
    ListView<ImageView> list = new ListView<ImageView>();
    Button btn = new Button("Back");
    
    public void startLister(Stage primaryStage , Scene mainscene) throws FileNotFoundException {
        VBox box = new VBox();
        Scene scene = new Scene(box, 450, 525);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Ethioclicks: Show All Profiles List SCREEN");
        btn.setStyle(mainPogram.BUTTON_STYLE_BLUE);
        
        
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                primaryStage.setTitle("Ethioclicks: Simple User Profile Program in Java ");
                primaryStage.setScene(mainscene);
                primaryStage.show();
                System.out.println("back to mainProgram");
            }
        });
        
        box.setAlignment(Pos.CENTER);
        box.setStyle(mainPogram.SCENE_STYLE);
        btn.setMinWidth(200);
        box.getChildren().addAll(list, btn);
        VBox.setVgrow(list, Priority.ALWAYS);        
 
        
        btn.setFont(Font.font("Verdana", 16));
 
        list.setItems(mainPogram.getInstance().usersProfilePic);
        list.setStyle("-fx-alignment: center;");
        list.setOnMouseClicked(new ListViewHandler(){
        @Override
        public void handle(javafx.scene.input.MouseEvent event) {
            System.out.print(mainPogram.getInstance().users.get(list.getSelectionModel().getSelectedIndex()).getName());
            userProfile user = new userProfile();
            try{
            user.getUserProfile(primaryStage, mainscene,mainPogram.getInstance().users.get(list.getSelectionModel().getSelectedIndex()) );
          
              }
           catch(Exception e){
            e.printStackTrace();
          }
        }
      });


      
        primaryStage.show();
    }  
}



class ListViewHandler implements EventHandler<MouseEvent> {
    @Override
    public void handle(MouseEvent event) {
        //this method will be overrided in next step
    }
 }

/*

listViewOnExit.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
        @Override
        public ListCell<String> call(ListView<String> stringListView) {
            ListCell<String> cell = new ListCell<String>(){
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    setText(item);
                }
            };
            cell.setAlignment(Pos.CENTER);
            return cell;
        }
    });

*/
