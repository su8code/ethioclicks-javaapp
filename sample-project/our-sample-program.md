#### How To Add This Project into My Development Environment?


 This Project is a javafx GUI Application Created using the Netbeans v12.6 IDE to open and  successfully run the project inside your Development Environment make sure you have javafx  enabled IDE and You already have installed MySQL Program in Your Computer. 

#### Before Starting to Use this sample project make sure you have the following applications Installed on Your Computer

* Netbeans v12.6 IDE( [Download Netbeans IDE]( https://netbeans.apache.org/download/nb13/nb13.html))
* MySQL Server v5.6 ( [Download MySQL Server](https://downloads.mysql.com/archives/installer/?version=5.6.26))
* MySQL Workbench ( [Download MySQL Workbench](https://dev.mysql.com/downloads/workbench/))

`note: Since This project is created by using java JDK 8 you may get a problem to run the project on another version of JDK therefore please install Java Development Kit 8 to run the sample project `

If You Haven't Yet Configured Your IDE to Support JavaFX then goto the Link down bellow and Follow the Provided Step by Step Instruction's 

[Set Up JavaFX on Netbeans IDE ](https://kensoftph.com/set-up-javafx-and-scene-builder-in-netbeans-ide/#:~:text=Open%20your%20NetBeans%20IDE%20and%20go%20to%20Tools,NetBeans%20IDE%20and%20click%20on%20apply%20and%20ok.)

After You Have the JavaFX successfully configured and working on your java IDE you are good to go and run this application into your IDE. first download the source code of sample project you can get the project source code inside this repository either clone this repository or directly download the repository, the source code will be available in the project directory. 

```
  
  git clone https://github.com/su8code/ethioclicks-javaapp.git
  cd ethioclicks-javaapp
  cd project
   

```

In This Project there is a Database Library that enables this java program communicate with local MySQL server for Data Storage. since our java program uses the database connectivity you need to be famillier with the concept of JDBC and how java programs communicate with MySQL server's. you can get more information in this topic with the link provided bellow.

this project is created by using Java Database Connector Library Version 5.1.45 <br />
`You can Get The Java Mysql Connector library we have used for this project inside the " project/jdbc-library " folder of our project directory `

[Java JDBC Tutorial](https://www.javatpoint.com/java-jdbc)

### What is JDBC and How to Connect Java with Database Server

JDBC stands for Java Database Connectivity. JDBC is a Java API to connect and execute the query with the database. <br />
The Following blog will show you in Detail how you can enable JDBC support within your java project in Netbeans IDE 
[Blog: Connect MySQL Database Server with Java Project in Netbeans IDE](https://www.tutorialsfield.com/how-to-connect-mysql-database-in-java-using-netbeans)


### How To Successfully run The Project on Your Computer ?

#### step 1. open the project in netbeans IDE
#### step 2. add the Database Connector Driver into your project([How to Add Database Driver](https://www.tutorialsfield.com/how-to-connect-mysql-database-in-java-using-netbeans))
#### step 3. start your MySQL Server 
#### step 4: under your MySQL Workspace Create the Database named `ethioclicks_database` and inside that make a new table named `user_profile_data`


since the program stores the user personnal informations inside the `ethioclicks_database` database we need to set up and create the database as well as make a table  `user_profile_data` under the database as follows.

The MySQL Workbench Software Will Help Us Run MySQL much easily and you can directly copy the codes here and paste it under your MySQL Workbench to make the database and the table.

Creating Database
```
    CREATE DATABASE ethioclicks_database;
    
```
Creating Table

```

  CREATE TABLE `user_profile_data` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` text,
  `email` text,
  `phone` text,
  `gender` text,
  `profilePic` text,
  PRIMARY KEY (`id`)
 );


```


#### step 4. open the `JavaDatabaseClass.java` and Edit the Credentials accordingly (set the username as well as the password used to connect with your MySQL Server)


![db](https://user-images.githubusercontent.com/88676535/159984958-0a3bee37-fda7-48c0-bf08-6b23c1729e6a.JPG)


#### step 5:  after editing the credentials make sure you have started your MySQL Server and `Run` the project.

`Our Project is Tested and Works Well on softwares with version number as provided above, but if you use other version's of those  listed softwares above you may experience some errors and the project may not run  and the code may not work for you properly. we kindly request you to use software version's as we have specified above`

#### Successfully Running the project the Output of the sample program will look as follows

<br />

#### The Main Screen
![app-home](https://user-images.githubusercontent.com/88676535/159990819-1a895b2c-b462-40ab-b794-5352b620eb45.JPG)

<br />
<br />



#### Show All User Profile Screen
![profiles-list](https://user-images.githubusercontent.com/88676535/159990855-b533c0c1-cf62-4aad-addb-9f486fb2ba1e.JPG)

<br />
<br />

#### Detailed Profile Info Screen
![profile-detail](https://user-images.githubusercontent.com/88676535/159990889-5c7ceafa-28f9-49ab-8f4f-3696630bd91b.JPG)

<br />
<br />

#### Add New Profile Screen
![Add-New](https://user-images.githubusercontent.com/88676535/159990951-ce3d83df-ea28-4e6a-80e2-7b68b6af0776.JPG)

<br />
<br />


#### The Edit User Profile Screen
![edit-profile](https://user-images.githubusercontent.com/88676535/159990972-08de9cb9-9877-4193-80c0-954592cbb81f.JPG)

<br />
<br />


#### The Screen to show Succesfully Updated User Profile
![edit-success](https://user-images.githubusercontent.com/88676535/159990999-8f382298-fb96-4c8b-9624-a24f7812007f.JPG)

<br />
<br />
