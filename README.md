# About the project "job4j_cars"

A simple application for selling cars. It consists of a set of tables where you can view advertisements sorted in various ways. The application allows adding, deleting, and modifying the status of advertisements.

---

### The technology stack used for this application:
```
- Java 17
- Spring boot 2.7.6
- Hibernate 5.6.11.Final
- PostgreSql 42.5.1
- Liquibase 4.15.0
- Lombok 1.18.22
- Log4j 1.2.17
- Thymeleaf 3.0.15
- Bootstrap 5.2.3
```
---

### Here are the environment requirements for the application:

**Java 17:** The environment should have Java 17 installed. You can download and install Java Development Kit (JDK) 17 from the official Oracle website or use a package manager for your operating system.

**Maven 3.8:** Maven is a build automation tool used for managing Java projects. You'll need to have Maven 3.8 installed on your system. Maven can be downloaded from the Apache Maven website or installed via a package manager.

**PostgreSQL 14:** PostgreSQL is a popular open-source relational database management system. You'll need to have PostgreSQL 14 installed and properly configured in your environment. You can download PostgreSQL from the official PostgreSQL website and follow the installation instructions specific to your operating system.
Additionally, make sure that your operating system is compatible with these versions of Java, Maven, and PostgreSQL. It's recommended to check the documentation and system requirements for each specific software to ensure compatibility and proper installation.

---

### Instructions to run

1.Download the project: Obtain the project source code by downloading it from a repository.

2.Set up the database: Assuming you have PostgreSQL 14 installed and running, you can create a database named "cars" using the following SQL command:
```create database cars```;

3.Configure the project: Navigate to the project directory and locate the configuration files. Look for any configuration files (such as application.properties) that contain the database connection details. Update these files with the correct database credentials (such as username, password, and connection URL) to connect to your PostgreSQL database.

4.Build the project: Open a terminal or command prompt, navigate to the project’s root directory, and run the Maven build command to compile the project and resolve its dependencies. 
Use the following command:
```mvn clean install```

5.Run the application: After a successful build, locate the main class of the project. In your case, the main class is Main located in the ru.job4j.cars package. Execute the main class, either through your IDE’s run functionality or via the command line.

6.Open the application in a browser: Once the application is running, it should be accessible through a URL specified in the project’s configuration (http://localhost:8080/posts/”)

---

### Interactive view of the application
Once the application is running it will direct you to a main page which displays nav tab and body with the list of all posts.
In Nav tab you will find the below tags:

**New** – displays only posts related to new cars.

**Used** – displays only posts related to used cars.

**Electric** – displays only posts related to electric cars.

**Category** – allows you to sort the posts by the following criteria:
- With photo
- On sale
- Sold
- Recently posted (the last 3 days)

**Add Post** – this tag appears only for logged-in users and allows adding new post.

**Search** – allows searching posts by brand name.

**Sign up || Log in**

---

*Main page:*

![Image Link](https://github.com/dilshod-musakhanov/job4j_cars/blob/main/imgForReadme/main_page.jpg)

*Sign un form:*

![Image link](https://github.com/dilshod-musakhanov/job4j_cars/blob/main/imgForReadme/sign_up_page.jpg)

*Log in form:*

![Image link](https://github.com/dilshod-musakhanov/job4j_cars/blob/main/imgForReadme/log_in_page.jpg)

*One post page*

Whenever you click on a photo or the car name it will direct you to a specific post page where you can view more details. 

![Image link](https://github.com/dilshod-musakhanov/job4j_cars/blob/main/imgForReadme/one_post_page.jpg)

If the user is the owner of the post, then additional buttons will be displayed to delete the post or mark the car as sold.

![Image link](https://github.com/dilshod-musakhanov/job4j_cars/blob/main/imgForReadme/one_post_while_logged_in.jpg)

*Notifications*

For any errors or success in updating the post the related notifications will pop up. Upon closing notification it will be directed to the main page.

![Image link](https://github.com/dilshod-musakhanov/job4j_cars/blob/main/imgForReadme/error_messages.jpg)

---

**Author:** Dilshod Musakhanov

**Contact:** musakhanov@yahoo.com

**Created:** 06/29/2023