# Dice Roll Simulator

## Requirements
For building and running the application you need:

+ JDK 11

+ SpringBoot 2.4.0

+ H2 Database

## External Platform Used
Heroku cloud platform for application deployment.

## Running the application locally
There are several ways to run a Spring Boot application on your local machine. One way is to execute the main method in the com.diceroll.DiceRollAppApplication class from your IDE.

+ Download the zip or clone the Git repository.
+ Unzip the zip file (if you downloaded one)
+ Open Command Prompt and Change directory (cd) to folder containing pom.xml
+ Open Eclipse
  + File -> Import -> Existing Maven Project -> Navigate to the folder where you unzipped the zip
  + Select the project
+ Choose the Spring Boot Application file (search for @SpringBootApplication)
+ Right Click on the file and Run as Java Application


Alternatively you can use the Spring Boot Maven plugin like so:



```sh
mvn spring-boot:run
```

Working application available in Heroku 
-----------------------------------------

```sh
https://dice-roll-simulator-app.herokuapp.com/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/dice-controller/diceRoll
```

### Tables Used

DiceRoll

| Field Name |  Type |
|----------|--------------|
|Id                       | Integer |
|noOfDice                       | Integer |
|noOfSides                       | Integer | 
|noOfRolls                       | Integer | 

DiceRollResult
| Field Name |  Type |
|----------|--------------|
|Id                       | Integer |
|sum                       | Integer |
|occurence                       | Integer | 


