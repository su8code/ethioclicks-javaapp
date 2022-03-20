package EthioClicks;

import EthioClicks.data.userProfileData;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.io.*;

import java.net.URISyntaxException;
public class JavaDatabaseClass {



    public static int usersCount = 0; 
    public static final String DRIVER = "com.mysql.jdbc.Driver";
    public static String DB_PORT = "3306";
    public static String DB_URL = "jdbc:mysql://localhost:"+JavaDatabaseClass.DB_PORT+"/ethioclicks_database";
    

    private static final String userName = "root";
    private static final String pass = "";
    public static  Connection conn = null;
    public static Statement statement = null;
    public static ResultSet result = null;
    public static PreparedStatement p;
    

    public JavaDatabaseClass() throws SQLException, FileNotFoundException , URISyntaxException, IOException{

           String configFile = mainPogram.getInstance().getPath()+"config\\ethioclicks-config.ini";
           
           File configFileObj = new File(configFile);

            if(configFileObj.exists()){
                 System.out.println(" *+*    File Name:> ethioclicks-config.ini  Exists    ***\n>"+configFile);
             }{
                  System.out.println(" *-*    File Name:> doesn't  Exists    ***");
                  try {
                        String dirPath = mainPogram.getInstance().getPath();
                        String newDirName = "config";
                        File newDirectory = new File(dirPath+File.separator+newDirName);
                        
                        if(newDirectory.mkdir()){
                      
                                byte bWrite [] = {11,21,3,40,5};
                                OutputStream os = new FileOutputStream(newDirectory+File.separator+"ethioclicks-config.ini");
                                byte[] bytes = "port:>3306".getBytes();
                                os.write( bytes );   // writes the bytes
                                os.close();
                    }else { 
                        System.out.println("Unable to Create Configuration File");
                       }
                        
                     } catch (IOException e) {
                        System.out.print("Exception: "+e.toString());
                  }
             }
            
                 	
           try {
                    Class.forName(JavaDatabaseClass.DRIVER);
          
               } catch (ClassNotFoundException ex) {
                    System.out.println("SQL Exception Error: "+ex.toString());
             }
             
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:"+JavaDatabaseClass.DB_PORT , JavaDatabaseClass.userName , JavaDatabaseClass.pass);
          try{
            
                if(conn != null)
                {
                    System.out.println("\tDB Creating Connected Successfully");
                }
                else {
                    System.out.println("\n\t There is Problem on Your DB Configuration!!");
                }}catch(Exception e){}
                
                try{
                statement = conn.createStatement();
                String sql = "CREATE DATABASE ethioclicks_database";
                statement.executeUpdate(sql);

                String tbl = "CREATE TABLE `user_profile_data`( `id` int(11) NOT NULL AUTO_INCREMENT, `name` text, `email` text, `phone` text,  `gender` text,  `profilePic` text,  PRIMARY KEY (`id`))";
                statement.executeUpdate(tbl);
               }catch(Exception e){
                    System.out.println("Exception: "+e.toString());
                }       
            

       System.out.println("DataBase URL: "+JavaDatabaseClass.getDbUrl());
    }
    
    public void fetchDataToApp() throws SQLException, FileNotFoundException{
                
         try {
                    Class.forName(JavaDatabaseClass.DRIVER);
          
               } catch (ClassNotFoundException ex) {
                    System.out.println("SQL Exception Error: "+ex.toString());
             }
             
            Connection conn = DriverManager.getConnection(JavaDatabaseClass.getDbUrl() , JavaDatabaseClass.userName , JavaDatabaseClass.pass);
          try{
            
                if(conn != null)
                {
                    System.out.println("\tDB Connected Successfully");
                }
                else {
                    System.out.println("\n\t There is Problem on Your DB Configuration!!");
                }}catch(Exception e){}


                
                userProfileData singleUserData;
                System.out.println("in fetchdata program");
                
                try{
                statement = conn.createStatement();
                String sql = "CREATE DATABASE ethioclicks_database";
                statement.executeUpdate(sql);

                String tbl = "CREATE TABLE `user_profile_data`( `id` int(11) NOT NULL AUTO_INCREMENT, `name` text, `email` text, `phone` text,  `gender` text,  `profilePic` text,  PRIMARY KEY (`id`))";
                statement.executeUpdate(tbl);
               }catch(Exception e){
                    System.out.println("Exception: "+e.toString());
                }
                
                result = statement.executeQuery("select * from user_profile_data");
                
                while(result.next()){
                                    System.out.println("loop started");

                   singleUserData =  new  userProfileData();
                   
                   singleUserData.setUserId(result.getInt(1));
                   singleUserData.setName(result.getString(2));
                   singleUserData.setEmail(result.getString(3));
                   singleUserData.setPhoneNumber(result.getString(4));
                   singleUserData.setGender(result.getString(5));
                   singleUserData.setUserProfile(result.getString(6));
                   
                   System.out.println("\n\n fetched "+result.getString(2)+" successfully \n\n");
                   

                 try{
                    mainPogram.getInstance().addNewUser(singleUserData , 1);
                 }
                   catch(Exception e){
                          System.out.println("addNEwITem: "+e.toString());
                   }

                    
                  // mainPogram.getInstance().usersProfilePic.add(singleUserData.getProfileImage());
               }

           System.out.println("loading Database to main App Finished");                 
    }
     
    public void addNewUserToDatabase(userProfileData user) throws SQLException {
         try {
                    Class.forName(JavaDatabaseClass.DRIVER);
          
               } catch (ClassNotFoundException ex) {
                    System.out.println("SQL Exception Error: "+ex.toString());
             }
             
            Connection conn = DriverManager.getConnection(JavaDatabaseClass.getDbUrl() ,JavaDatabaseClass.userName , JavaDatabaseClass.pass);
          try{
            
                if(conn != null)
                {
                    //d.insertDB();
                    System.out.println("\t Database Connected Successfully");
                }
                else {
                    System.out.println("\n\tThere is Problem on Your Derby Configuration!!");
                }}catch(Exception e){}
        try{
        p = conn.prepareStatement("insert into user_profile_data(name,email,phone ,gender,profilePic) values(?,?,?,?,?)");
        
        p.setString( 1 , user.getName());
        p.setString( 2 , user.getEmail());
        p.setString( 3 , user.getPhoneNumber());
        p.setString( 4 , user.getGender());
        p.setString( 5 , user.getUserProfile());
        
        p.execute();
        p.close();
           System.out.println("Successfully Added New User \'"+user.getName()+"\' to Database");
       }  catch(Exception e){
           System.out.println("Error Adding New User \'"+user.getName()+"\' To Database: "+e.toString());
       }

    }

  public static void updateUserData(userProfileData user)throws SQLException{
            try {
                    Class.forName(JavaDatabaseClass.DRIVER);

                    } catch (ClassNotFoundException ex) {
                         System.out.println("SQL Exception Error: "+ex.toString());
                 }

                 Connection conn = DriverManager.getConnection(JavaDatabaseClass.getDbUrl() ,JavaDatabaseClass.userName , JavaDatabaseClass.pass);
               try{

                     if(conn != null)
                     {
                    //d.insertDB();
                    System.out.println("\tDatabase Connected Successfully");
                }
                else {
                    System.out.println("\n\tThere is Problem on Your Database Configuration!!");
                }}catch(Exception e){}
                try{
                p = conn.prepareStatement("update user_profile_data set name= ? , email = ? , phone = ? , gender = ? , profilePic = ?  where id=?");

                p.setString( 1 , user.getName());
                p.setString( 2 , user.getEmail());
                p.setString( 3 , user.getPhoneNumber());
                p.setString( 4 , user.getGender());
                p.setString( 5 , user.getUserProfile());
                p.setInt( 6 , user.getUserId());
        
                p.executeUpdate();
                p.close();
                System.out.println("Successfully Updated \'"+user.getName()+"\' in Database");
             }  catch(Exception e){
                System.out.println("Error Updating User \'"+user.getName()+"\' in Database: "+e.toString());
           }

          finally{
               conn.close();
          }
    }
  public static String getDbUrl(){
     try{
                        String dirPath = mainPogram.getInstance().getPath();
                        String newDirName = "config";
                        File newDirectory = new File(dirPath+File.separator+newDirName);
                        InputStream is = new FileInputStream(newDirectory+File.separator+"ethioclicks-config.ini");
                        
                        InputStreamReader isReader = new InputStreamReader(is);

                        BufferedReader reader = new BufferedReader(isReader);
                        StringBuffer sb = new StringBuffer();
                        String str;
                        while((str = reader.readLine())!= null){
                           sb.append(str);
                        }
                        System.out.println(sb.toString());
                        String data = sb.toString();
                        String port = data.substring(data.lastIndexOf('>')+1);
                        return  "jdbc:mysql://localhost:"+port+"/ethioclicks_database";
                       
             
     }catch(Exception e){
        return  JavaDatabaseClass.DB_URL;
     }
  }  
}