# mini-twitter-app
Java-based mini Twitter with GUI using Java Swing

Overview
--------

A simple local version of Twitter written in Java.

Environment Setup
-----------------

1. Install the Eclipse 4+ (`http://www.eclipse.org/`), and make sure Maven plugin is available.
2. (Optional) The Maven plugin is not availble in your Eclipse, install it using the update site (`http://www.eclipse.org/m2e/download/`).
3. Install the latest Maven command-line tool (`http://maven.apache.org/download.cgi`)

Importing the Project into Eclipse
----------------------------------------

1. File -> Import -> Maven -> Existing Maven Projects
2. Select the directory containing the pom.xml file
3. Finish

Building the Project for the First Time
----------------------------------------
1. Right-click on the project root folder -> Maven -> Update Project

Running the Project Locally
----------------------------------------
1. Locate the Driver.java in src/main/java source folder in edu.cpp.cs585.mini_twitter_gui package.  Right-click on it -> Run As -> Java Application.
2. When the program is running, you can add individual users or group users to the existing application.  Click on Open User View while the application is running to follow another user or to post Tweets.  User news feeds are updated in real-time.

Note
----------------------------------------
1. Both individual users and group users may be nested under a group user.
2. A group user may not be followed.
3. A Tweet is classified as a positive Tweet if it contains any of the following words: "good", "great", "excellent", "awesome".
