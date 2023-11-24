# EngLearn App

This project was created to assist learners who are studying
English and requiring a tool to enhance their effectiveness...
But the main purpose is to get marks in my OOP course...
### Features: 

- Looking up words in dictionary.
- Translate paragraphs.
- Word can be pronounced.
- Add new words, modify words or delete from dictionary.
- Games assist users learning English.
- Vivid GUI.

## Installation

#### - In order to run this application, you need these things below:

- An IDE that can run javafx application(eg: Intellij, Eclispe, ...). Because i use IntelliJ so i will this app to guide
. If you guys use the others, please do the same sequence.
- JDK 8 or one of the SDK versions which support javafx. 
- SQL server.
- A tool to open SQL server(eg: XAMPP, Workbench, ...).

#### - Now follow these steps below to install this app correctly:


##### Step 1: 
- Download this project.
##### Step 2:
- Go to this link: https://github.com/Typhon0/AnimateFX and you scroll down until see this:
![img_4.png](img_4.png)
- Copy all of this code.
- Go to this ![img_5.png](img_5.png) 
- And go to tab libraries and click the "+" button on the top-left corner then click "From Maven".
![img_6.png](img_6.png)
![img_7.png](img_7.png)
- And it will pop up a dialog like this
![img_8.png](img_8.png)
- Patse all what you did copy before in the text box and click OK.
##### Step 3:
- When you open the project you will see the project structure similar to this: ![img.png](img.png)
- Add two folders name "lib" to your libraries by clicking right click in jar files inside those folders and click "Add as library".
- Do some additional steps to install animation library.
##### Step 4:
- Open your SQL server first with your tool, It will look like this in XAMPP![img_2.png](img_2.png)
- Open the tool that manage your SQL server and go to tab "SQL" and insert these codes:
```bash
  CREATE DATABASE dictionary
  USE dictionary
  CREATE TABLE dicttable (
    word VARCHAR(255),
    pronunciation VARCHAR(255),
    definition MEDIUMTEXT
  )
  CREATE TABLE vocab (
    Vocabulary VARCHAR(255),
    Pronunciation VARCHAR(255),
    Meaning MEDIUMTEXT
  )
```
- After that, Run this code by clicking button "Go" if you use phpMyAdmin.
- when these codes above did run, go to tab "Import" and import the "output.sql" and "questionvocab.sql" ![img_3.png](img_3.png).
- When all done,  go to App.java right click inside and click "Run App.main()"![img_1.png](img_1.png).
- After clicking that, you successfully install and run this app. Good Luck!

## Tech Stack
**Server:** mySQl.

**Dev:** Javafx, Voicerrs, Maven, AnnimateFX;

## Author
[Lê Đức](https://www.github.com/Lee-412)

[Lê Hiển](https://www.github.com/LeHien2818)
