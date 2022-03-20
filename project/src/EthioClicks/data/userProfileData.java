package EthioClicks.data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
 
public class userProfileData {
   private int userId;
   private String fullName;
   private String email;
   private String gender;
   private String phoneNumber;
   private String userProfile;
   private String cachedUserProfileAddress;

   public ImageView getProfileImage() throws FileNotFoundException{
        ImageView userProfilePic = new ImageView(); 
        userProfilePic = new ImageView(new Image(new FileInputStream(this.userProfile)));         
        userProfilePic.setFitHeight(420); 
        userProfilePic.setFitWidth(320); 
        userProfilePic.setPreserveRatio(true);
        userProfilePic.setId("profile-"+this.userId); 
      return userProfilePic;   
   }
   
      public void setName(String name){       
       this.fullName = name;    
   }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(String userProfile) {
        this.userProfile = userProfile;
    }

   public String getUserProfileCachedAddress() {
        return cachedUserProfileAddress;
    }

    public void setUserProfileCachedAddress(String userProfileCacheAddrs) {
        this.cachedUserProfileAddress = userProfileCacheAddrs;
    }
   
   public String getName(){
       return this.fullName;
   }
}