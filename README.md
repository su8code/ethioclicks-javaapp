# Documentation to make exe installer Setup program
## 
## Table of contents
* [General Info](#general-info)
* [Technologies we Used](#technologies-we-used)
* [Making executable from Java Program](#convert-jar-program-to-executable-windows-application)
* [About Inno Setup Compiler](#about-inno-setup-compiler)
* [Creating our Setup Program](#Creating-our-setup)

## General info
An installer can either install a new program on your computer or can update a program currently on your hard drive. It can also update or add files to your operating system. Most installers can be run by simply double-clicking the installer icon and then choosing the folder you want to install the software into. The nice thing about installers is that they do all the work for you packaging all your source files into a single setup program and at the end decompressing the program and writing the data on the hard drive. Once the installer is finished, you can often use the new or updated software right away. 

This is a Documentation to show how installer program is created for a java program. for demonstraion purpose we have a simple user profile viewer GUI application, our java program includes other external mysql connecter library and some configuration file we also planned to include our own java runtime environment which enables the app to run on any windows computer including windows that don't have java already installed on and also we planned to embedd the MySQL Database Server program which would be an independently running MySQL Server we would like to embedd the server and make it part of our Application Program and for such scenarios and for projects with a lot of independently running software components and resources setup installers are very helpfull and great tools for packaging program files and software components together into a single and relatively small size setup program which makes distributing the final application to end users very easy. 
## Technologies We Used

Tools and Programs We Used:

* MySQL Server V5.6 ([Download MySQL](https://dev.mysql.com/downloads/mysql/5.6.html))
* Launch4j ([Download Launch4j](http://launch4j.sourceforge.net/)) used to create the exe 
* Inno Setup Compiler([Download Inno Setup Compiler](https://jrsoftware.org/isdl.php)) used to make the final setup program
* Microsoft Visual C++ Redistributable X64([Download Visual C++ Redistributable](https://www.microsoft.com/en-in/download/details.aspx?id=48145)]))
  
 
Before proceeding to making installer for java programs the first thing we have to do is creating windows native executable .exe from the java jar program, from so many available options out there we will use Launch4j.exe which is the best Windows native executable (.exe) java application wrapper available , which Offers native splash screen, application icon, search for JRE or use bundled one, feedback on startup failure, passes command line arguments... much more. after converting jar into executable .exe we will add java runtime , MySQL Database Server and Microsoft Visual C++ together with our executable program  and we will convert them into a single Setup.exe installer program by using a software that creates an installer. InnoSetup is an open source compiler to create installers on windows. 

## Convert jar Program to executable Windows Application


To get Started you can clone this repository and open our sample javafx project with your java IDE or you can directly proceed into making an executable program from a jar file by select your own java project.

![profile-detail](https://user-images.githubusercontent.com/88676535/159993868-180fc4eb-cab8-49b6-8641-ebd8527484be.JPG)

[Click Here to get the Sample Java Project provided](sample-project/our-sample-program.md)

Our Sample Project which is created using the Apache Netbeans IDE 12.6 is a simple javafx program that will store user profile data and displays each stored user profile data into the display list , it also includes features for adding new user profile and editing already available user profile data as well. the profile data is stored into the locally available mysql server, based on java database connectivity our app directly uses the local mysql server which will be later bundled together with our application. for storing user profile data and it consists of a database connector library jar for enabling the connection between the local database and our app.


##### The First Step is Getting The Jar Files from Java Source Code

After successully configuring and testing the project we have to get the jar binary file from the finished final javafx project source code , if you know how to do it yourself it's great but if you don't know how to do it it's also awesome you are going to learn how to do it by yourself with this documentation, The steps used to export the java source code into a jar executable program varies from one IDE to other IDE's because of this we have provided a link down bellow to show you in detail and clear way to  get the jar executable file on mostly used java Integrated Development Environment's but if you are using another type of java IDE kindly search for how you can export the java source code into jar executable binary file for your specific IDE. 


* [How to get .jar file in Eclipse IDE](https://www.tutorialspoint.com/eclipse/eclipse_create_jar_files.htm)
* [How to get .jar file in Netbeans IDE](https://www.softwaretestinghelp.com/netbeans-ide-for-java/)
* [How to get .jar file in Intellij IDEA](https://blog.karthicr.com/posts/2016/07/10/creating-an-executable-jar-in-intellij-idea/)

Even if the steps for Extracting  the jar file vary from one IDE to other IDE , in all integrated development environments after successfully building and exporting the project our IDE will create the jar file inside our project folder including the libraries our project is configured to include(external libraries will be stored inside the lib folder along our .jar file).

After Successfully making your jar file you will get an output like this
![jar](https://user-images.githubusercontent.com/88676535/159991165-e846a440-35d4-44e1-abee-d81a1a3a5eaa.JPG)

Since our sample java project includes external database connector library our IDE have created a 'lib; folder along our jar file

The Next Step is Converting the jar file into exe executable windows application, even if there are so many different options available for converting the jar into .exe executable windows application among these various methods the popular one is by using the launch4j.exe application. 

Launch4j is a cross-platform tool for wrapping Java applications distributed as jars in lightweight Windows native executables. The executable can be configured to search for a certain JRE version or use a bundled one(we will use our own bundled jre), and it's possible to set runtime options, like the initial/max heap size. The wrapper also provides better user experience through an application icon, a native pre-JRE splash screen, a custom process name, and a Java download page in case the appropriate JRE cannot be found.

– Launch4j's website
[Download Launch4j exe Here](http://launch4j.sourceforge.net/)

After successfullly finishing the above now we will have our jar file and all the neccessary libraries in our hand the next step will be to convert this jar program into an executable program , so please follow the following steps one by one.
 
Let's set our Folder Structure suitable for creating the executable as well as well organized for making the setup later on.

#### step 1. Create a new folder under your Desktop or other place and name it `setup-installer-folder`
#### step 2. Goto inside `setup-installer-folder` and create another folder and name it 'bin'
#### step 3. Then Copy the extracted jar file and the `lib` folder into the newly created `bin` folder
#### step 4. Copy the `config` and `ethioClicksImages` folders and the `icon.png` image from files folder of this repository into your `setup-installer-folder` folder 

After successfully doing all the above steps you will have a folder structure like this as show on the picture below

![setup-installer-strctr](https://user-images.githubusercontent.com/88676535/159991241-dc317e68-d948-4edb-ae7f-b6103439cfd3.JPG)

#### Then next we will add our own java runtime environment.
we can get the Java runtime from our already installed Java Runtime software Path or if we don't have Java Runtime already installed we have to download the java JRE runtime from the oracle official website. <br />
Click Here to Donwload JRE [Oracle Download Java Runtime Environment](https://www.java.com/en/download/)

#### step 5. First create jre folder inside the `setup-installer-folder/bin`  folder

![bin-with-jre](https://user-images.githubusercontent.com/88676535/159991283-a246dfd3-2584-4d6c-8ccf-648269c9bc83.JPG)

#### step 6.  Goto inside the java runtime installation folder and copy the `bin` and and `lib` folders as shown in the picture below and paste them into the newly created jre folder inside the `setup-installer-folder/bin/jre`. 

![program-files-folder-jre](https://user-images.githubusercontent.com/88676535/159991339-98a5d787-18e9-4545-981a-b4938e47e6f3.JPG)

### Next start Lanch4j app and directly start the process of converting jar into exe

#### step 7. open  the launch4j application ( if you haven't yet downloaded the app the download link is available above ) since i'm using the Lanch4j v3.8 the first sreen you will get will look like as follows if you are using the same version of launch4j(launch4j v3.8) like mine.

![home](https://user-images.githubusercontent.com/88676535/159993676-484be92b-96bf-4f14-968a-51b21ca215f7.jpg)


#### step 8.  Goto `Basic` Tab and specify 3 things

![basic-tab](https://user-images.githubusercontent.com/88676535/159993639-0b6f7811-70da-4ad0-9247-87c80efbfb09.jpg)

* set the output File name and the directory you want it to be stored ( here the exe output should be inside `setup-installer-folder` as shown in the picture) (#1)
* the jar executable file we had it inside the bin folder (#2)
* and thirdly the icon you want to use for the exe program(it's optional).(#3)

#### step 9. Goto `ClassPath` Tab and do the followings based on the numbers you see on the screen

![classpath-tab](https://user-images.githubusercontent.com/88676535/159993607-2afab09d-6366-4e94-a126-add87ed619f0.JPG)

 * first tick the `Custom Classpath` after clicking the checkbox it will activate the view bellow the checkbox (#1)
 * then down below the checkbox on `*Main Class` column tap on the button indicated with the number 2  and select the jar file once again
 * after selecting the jar file located inside the bin folder lanch4j app will populate and fill the input boxes automatically but on our scenario we have to edit and make them suitable for our specific project so let's click on the database connecter jar file you see and goto the Edit Item.
 * inside Edit item add `bin/` before the path to database connecter library as shown in the picture and finally tap on accept button the next sreen you will see look as follows  <br /> 
 
![classpath-tab2](https://user-images.githubusercontent.com/88676535/159993563-10763cc9-8193-4b2c-a0cd-d3299c51a95d.JPG)
 
* in the `*Main Class` column specify the main Class as follows edit the value inside th input box with the class name `EthioClicks.mainPogram` 
 
![fixJavaClass](https://user-images.githubusercontent.com/88676535/159977963-daee2452-c456-4753-bc52-19400a4a8857.JPG)

#### step 10. Done we have successfully configured the basic setting for the launch4j app to create our exe windows application

but one more thing left, we want our executable program to use our own bundled java runtime. fortunately launch4j program allows us to specify which jre to use for our app during program execution. to apply these settings we have to set some values inside the jre tab goto 'jre' tab in launch4j app and type the path to our jre in the provided input box as shown in the picture bellow then finally start the process of creating exe by clicking the icon button you see pointed by the arrow labeled 2 <br />
![run](https://user-images.githubusercontent.com/88676535/159993439-9c377d06-acf7-41e4-9eba-2e60d531885a.JPG)

#### step 11. Finally launch4j will successfully build our exe file and store it on the output path with a name we have previously specified 
![successfull](https://user-images.githubusercontent.com/88676535/159993475-fbdce0bb-8f4b-4d84-baf5-e1c29ceb5f8d.JPG)

## About Inno Setup Compiler 

Inno Setup is a free installer for Windows programs. First introduced in 1997, Inno Setup today rivals and even surpasses many commercial installers in feature set and stability.
Inno Setup supports all Windows versions and allows you to create an EXE file that contains all of your application's files, which will be displayed in an interface with a great design. This will help you create the perfectly customized installation process.

Installations are created by means of scripts, which are ASCII text files with a format somewhat similar to .INI files. (No, it's not as complicated as you might be thinking! there is GUI interface that helps you create the script authomatically and we will modify it based on our specific requirement and our neeed). Unicode Inno Setup also supports UTF-8 encoded text files. <br />

Scripts have an ".iss" (meaning Inno Setup Script) extension. The script controls every aspect of the installation. It specifies which files are to be installed and where, what shortcuts are to be created and what they are to be named, and so on. <br />

InnoSetup script file is a simple text file which is similar to .INI files with the extension .ISS. In this script file the contents are arranged in sections. These scripts are easy to understand and uses a simple syntax. The section starts with the Section Name which is enclosed in square brackets. Each section handles a specific function of the installation.

![Inno_Setup_screenshot](https://user-images.githubusercontent.com/88676535/158381524-da8cec50-b41c-4ebc-9dea-c62789c6d03f.png)

`Setup:` This section consists of settings and application related information like application name, publisher name etc. <br />
`Languages:` List of languages supported.   <br />
`Tasks:` Tasks to be performed by the setup during the installation. <br />
`Files:` Files to be copied to the User's system.  <br />
`Icons:` The Application shortcuts: Start menu folders, etc. are defined here. <br />
`Run:`  Any executable to be executed after the installation is completed. <br />


Script files are usually edited from inside the Setup Compiler program. After you have finishing writing the script, the next and final step is select "Compile" in the Setup Compiler. What this does is create a complete, ready-to-run Setup program based on your script. By default, this is created in a directory named "Output" under the directory containing the script.  <br />
 
To give you an idea of how this all works, start the Setup Compiler, click File | Open, and select one of the script files in the Examples subdirectory located under the Inno Setup directory. (It may be helpful to use the sample scripts as a template for your own scripts.)



## Creating Our Setup

### Creating the installer using Inno Setup involves the following two steps:
* Create Inno Script file
* Compile the Script file

### Creating Script File is Straight forward and simple 

First Download Inno Setup Compiler Program
Download link: [Inno Download Link](https://jrsoftware.org/isdl.php)

open up Inno Setup Compiler and click on the option "create a new script file using the script wizard" you see on the screen and it will directly open the GUI interface for you. but if you rather prefer writting all the code by yourself very good you can go ahead click start empty script and start typing the script by yourself but if you are not famillier with the Inno  Script and you mostly want easy ways the GUI Option is prolific. as simple as there is a form you have to fill and the app will generate the script file for you , fantastic and time saving.

![inno](https://user-images.githubusercontent.com/88676535/158459942-d5b65d66-565a-455a-832c-4f7ab58a5e5f.JPG) <br />

On The Popup Form Specify The Application Name , Application Version Number and some other basic informations related to the application and Click next. Another Input Box Will Popup and will Ask you to specify the Destination Folder weather to allow users change the destination name or use the specific folder name  but almost all the default values are all very nice and we can leave them untouched  and proceed by clicking next. 

![appp](https://user-images.githubusercontent.com/88676535/158593941-a918de43-7465-487b-b9c2-f23bc1a77c66.JPG) <br />

At this stage we will click on  the Main Application Executable section and directly select the exe inside our folder 'setup-installer-folder' which is the executable generated by launch4j. <br />

Here Comes the time to see the importance of creating a single folder and collecting all thefiles and folders in a single folder "setup-installer-folder", In Other Application files section you only need to select "setup-installer-folder" folder once and all the other files and folders will be added recursively into our setup program. after setting up this click next and go to the next screen and set the options you want based on your need, but at the last it will ask you to compile the script. `Don't say Yes to Run the Script ` there are some  more things we got to do. we have to modify the script and add our own code.

#####  At this time i believe we all have our own basic inno script code cool ,  before rolling into the next step let's save our script we have to save our script file on a place we remember. 


## Modifying the Inno Script 


Before Running the Script and directly getting into compiling the setup process once again we need to make sure what we have and prepare all the requird files , folders and resources for our program. as we stated on the introduction section of our documentation we have a plan to embedd the MySQL server into our application. MySQL is open source relational database management system developed using C and  C++ programming languages that is being used to, manage database systems , retrieving data from database tables and so many more <br />

MySQL is released under an open-source license. So you have nothing to pay to use it.<br />
MySQL is a very powerful program in its own right. It handles a large subset of the functionality of the most expensive and powerful database packages. <br />
MySQL works very quickly and works well even with large data. <br />
MySQL is customizable. The open-source GPL license allows programmers to modify the MySQL software to fit their own specific environments. <br />

## Embedding the MySQL Server

Download the MySQL server from their Official Website. [Download MySQL](https://dev.mysql.com/downloads/mysql/5.6.html) <br />
For this Demonstration we Used MySQL Server 5.6 Software You can Download it oon the Official Website with the link provided above

![mysql-server](https://user-images.githubusercontent.com/88676535/158581992-bc15e834-5c0e-4a61-a86a-f28423800a89.JPG)

After Downloading MySQL Server into your Computer let's setup a place for the server in our setup directoy. let's create a folder named 'mysql' inside our "setup-installer-folder"

![mysql](https://user-images.githubusercontent.com/88676535/158582813-8a4f91f4-d833-4a85-9267-9f414fccbf94.JPG)

After Creating a folder We have to go into inside the MySQL Server Installation Folder for this case the folder named 'MySQL Server 5.6 ' and then we have to Copy all the contents inside that MySQL Server 5.6 directory and paste them into our new folder 'mysql' directory inside "setup-installer-folder"
![mysql-server-dir](https://user-images.githubusercontent.com/88676535/158583836-d1179790-e040-4b83-b8f1-00fd53367e01.JPG)

##### Well Now We Have Done Adding MySQL server Program into Our Program Directory the next how we are going to use it.

Once Copied into our own directory together with our executable Application How are We Going to Use the MySQL Server?

## Configure the MySQL Server to start after the setup program have finished extracting

Well We will start and activate our MySQL Server after the Setup has finished extracting all the program files and folders. we will achieve this kind of capability by exploiting the functionality of  `[RUN]` Section of the Inno Setup Compiler  Since the Inno Setup Compiler `[RUN]` section of Script allows Any executable to be executed after the installation is completed we will alter the `[RUN]` section and execute some mysql commands to create myslqld instance , register the port on firewall and register the mysqld instance as a windows service as well as creating a new mysql user with full privelege on all the  available database and tables.

The Inno Script Code that will Add MySQL as a Windows Service , Registers the port in the firewall Setting and Starts The MySQL Server when the system boots as well us which create user is available bellow or you can use your mysql skill's and you can make the code better. you can copy the code bellow and modify the code inside the `[RUN]` section of the Inno Setup Script. 

![mysql-code](https://user-images.githubusercontent.com/88676535/158587265-c2e59d81-86f0-43cc-bcab-eb302e1d478c.JPG)

```
Filename: {app}\mysql\bin\mysqld.exe; Parameters:--install mysql;StatusMsg: Installation du service mysql;Description: Installation du service mysql; Flags: runhidden 
Filename: net.exe; Parameters:start mysql; StatusMsg: Initialisation du service mysql; Description: Initialisation du service mysql; Flags: runhidden
Filename: "{sys}\netsh.exe"; Parameters: "firewall add portopening TCP 3306 ""Port MySQL"""; StatusMsg: "Enregistrement par défaut port MySQL ..."; Flags: runhidden; MinVersion: 0,5.01.2600sp2
Filename: "{sys}\netsh.exe"; Parameters: "firewall set service type = fileandprint mode = enable"; StatusMsg: "Activation du partage de fichier et d'imprimante ..."; Flags: runhidden; MinVersion: 0,5.01.2600sp2
; grant privileges 
Filename: {app}\mysql\bin\mysql.exe; Parameters:"-u root  -e ""CREATE USER 'user'@'%' IDENTIFIED BY 'yourPassword'"; StatusMsg: Installation du service mysql;Description: Installation du service mysql; Flags: runhidden 
Filename: {app}\mysql\bin\mysql.exe; Parameters:"-u root  -e ""GRANT ALL ON *.* TO 'user'@'%'"; StatusMsg: Installation du service mysql;Description: Installation du service mysql; Flags: runhidden 
Filename: {app}\mysql\bin\mysql.exe; Parameters:"-u root  -e ""REVOKE ALL ON *.* FROM 'root'@'localhost';"; StatusMsg: Installation du service mysql;Description: Installation du service mysql; Flags: runhidden 
Filename: {app}\mysql\bin\mysql.exe; Parameters:"-u root  -e ""REVOKE ALL ON *.* FROM 'root'@'%';"; StatusMsg: Installation du service mysql;Description: Installation du service mysql; Flags: runhidden 
;Filename: {app}\mysql\bin\mysql.exe; Parameters:"--p 3308 -u root   "; StatusMsg: Installation du service mysql;Description: Installation du service mysql; Flags: runhidden 
```

##### Create the Database and the Table

Since Our sample java program uses the database `ethioclicks_database`  to store and manage the personal records of all the users added to the program our program needs the database to be present inside the MySQL server we have added together with our Setup so inorder to do that we have added the following line of code into our Inno Setup Script code . the following code will allow us to create the database `ethioclicks_database` and the table `user_profile_data` inside the MySQL Database Server , therefore we have to add the following lineof code into our `[RUN]` section. you can directlly click the copy button you see on the top right corner of the code down bellow or copy it yourself and paste it into the inno script `[RUN]` Section.

```
Filename: {app}\mysql\bin\mysql.exe; Parameters:"-u root  -e ""CREATE DATABASE ethioclicks_database;"; StatusMsg: Creating the ethioclicks_database Database;Description: Creating the ethioclicks_database Database mysql; Flags: runhidden 
Filename: {app}\mysql\bin\mysql.exe; Parameters:"-u root  -e ""CREATE TABLE `user_profile_data` (`id` int(11) NOT NULL AUTO_INCREMENT,`name` text,`email` text,  `phone` text,`gender` text, `profilePic` text, PRIMARY KEY (`id`)); "; StatusMsg: Creating Table user_profile_data mysql;Description: Creating Table user_profile_data mysql; Flags: runhidden 

```


## Configure the app to responsibly Destroy and Remove the Database Instance automatically when the user Uninstall our program 

Since Our Database Server Runs independently from our main program , we should have to Specify our setup program to remove the MySQL server Instance upon the user uninstalling our program from their computer system. Inside the `[UNINSTALL]` SECTION we have to stop the Database Service and Remove It. 

goto your Inno Setup Script and find the 
`[UninstallRun]` section of code but incase if it is not present in your Inno Setup Script you have to write it yourself( mostly it's not foudn so you have to copy the code bellow and paste it into the last line of your Inno Script Code). copy the following code and paste it inside the last line of your Script ( remove  `[UninstallRun]  ` if you already have it in your Script code)

``` 
[UninstallRun]   
; uninstall mysql and tomcat as services
Filename: net.exe; Parameters:stop mysql; StatusMsg: Initialisation du service mysql; 
Filename: "{app}\mysql\bin\mysqld.exe"; Parameters: "--remove mysql"
```


## Add Microsoft Visual C++ Redistributable

Since Most Part of MySQL is Developed using C also some part in C++ and for the MySQL Server to work properlly the Microsoft Visual C++ Redistribution (MSVC) libraries have to be available and already installed on the computer system. The Visual C++ Redistributable Packages install and register all Visual C++ libraries. so for computers tha't don't have Microsoft Visual C++ Redistributable pre installed and configured we have two options 
   * to Either Show Error Message and Ask Users to Download the Microsoft Visual C++ Redistributable by themselves
   * or Bundle the Microsoft Visual C++ Redistributable Software and make it Part of our Setup and install it automatically
   
The Second Option looks Good and Reasonable. so what we did is we downloaded the program and included inside our setup file, goto the official website of microsoft and download the Microsoft Visual C++ Redistributable.
##### Download Microsoft Visual C++ Redistributable

[Download](https://www.microsoft.com/en-in/download/details.aspx?id=48145))

after downloading the exe copy and paste it into the `bin` folder inside the `setup-installer-folder` then after coping the program successfully open the InnoScript file and goto the `[RUN]` Section of the script  add the following line of codes , what these codes down bellow will do is the code will run the exe file that we have previously added into our setup folder and it will automatically opens up the app and the dialog to start the installation of microsoft visual c++ distribution will be shown to the user.


```

Filename: {app}\bin\VC_redist.x64.exe; Parameters: "/q:a /c:""VCREDI~2.EXE /q:a /c:""""msiexec /i vcredist.msi /qn"""" """; WorkingDir: {app}\bin; StatusMsg: Installing Microsoft Visual C++ ...

```

Note: we can add capability to check for the existence of Microsoft Visual C++ Redistribution by reading some Windows Registry Key values but in this project we haven't gone much deep this far.


#### After You Have Finished doing all the above steps you can now compile the Inno Script. it may take some time but finally Inno Setup compiler will produce the setup exe and store the exe inside the  a folder named 'Output' you can get it inside  the  "setup-installer-folder"  or if you don't find it there it will be  Stored inside the Documents folder.



[Inno Documentation](https://documentation.help/Inno-Setup/)
