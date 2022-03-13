# How to Make exe installer setup program...
## 
## Table of contents
* [General Info](#general-info)
* [Technologies we Used](#technologies)
* [making executable from Java Program](#convert-jar-program-to-executable-windows-application)
* [making executable from Java Program with 3rd Party jar files](#make-executable-from-java-program-that-includes-external-libraries)
* [About Inno Setup Compiler](#setup)
* [Other Available Setup Compilers](#setup)
* [References and Licences of 3rd party Softwares Used](#reference)

## General info
An installer can either install a new program on your computer or can update a program currently on your hard drive. It can also update or add files to your operating system. Most installers can be run by simply double-clicking the installer icon and then choosing the folder you want to install the software into. The nice thing about installers is that they do all the work for you packaging all you source files into a single setup program and at the end decompressing the program and writing the data on the hard drive. Once the installer is finished, you can often use the new or updated software right away. 

Installers are used for creating, extracting, and moving all the necessary files to run a computer program this is a documentation to show how installer program is created for a java program.for demonstraion we have a simple user profile viewr GUI application which is made using java Programming language , it needs many other files and folders to work properly  as well as it's own java runtime environment and it also includes it's own independently running Mysql Server.before proceeding to making installer the first thing to do is creating windows native executable .exe from the java program, there are many wrapper programs available we will be using Launch4j.exe which is Windows native executable (.exe) java application wrapper. Offers native splash screen, application icon, search for JRE or use bundled one, feedback on startup failure, passes command line arguments. after converting jar into executable .exe the next thing is to organize all the neccessary files together and convert them into single setup.exe program by using setup compiler program. we used Inno setup compiler for creating our installer setup.exe program . InnoSetup is an open source compiler to create installers on windows. It is free and provides a rich feature set. You can create professional looking installers for the end user. we will see how we goning to create our own setup.exe from java program also these steps can be applied for any exe executables you have created by any programming language available, i will explain you the basic concepts and steps involved in creating a simple setup of any exe file you want to deploy and share it to end users using installers.
## Technologies
Tools and Programs  Used We Used:
* Mysql Server V5.6  for the Database to store profile informations
* Launch4j to Create windows native Executable .exe from our jar executable 
* Inno Setup Compiler to pack our application exe our icons configuration files as well as all the folders into a single setup program
* Microsoft Visual C++ Distrbution X64  mysql is depends on this program to work properly
	
	
### Creating the installer using Inno Setup involves the following two steps:
* Create Inno Script file
* Compile the Script file

## Convert jar Program to executable Windows Application


## How to Create the Setup
To create this setup exe file the first thing we have to do is convert our program into executable format:

<br />
<br />
<br />
<br />
<br />
<br />

## Main Screen
![User Profile](images/app-starting-ui.jpeg)

<br />
<br />
<br />
<br />

## Profile Detailed Info Screen
![Profile Detail](images/user-detail-info.jpeg)

<br />
<br />
<br />
<br />

## Edit User Profile Screen
![Edit Profile](images/edit-ui.jpeg)

<br />
<br />
<br />
<br />

## Succesfully Updated User  Profile
![Updated Profile](images/updated.jpeg)
