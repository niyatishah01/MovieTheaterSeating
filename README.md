# MovieTheaterSeating
Best fit / Closest fit approach to the Movie Theater Seating Problem (derived from the classic NP hard bin packing problem)

## Problem Statement
Implement an algorithm for assigning seats within a movie theater to fulfill reservation requests.
Assume the movie theater has the seating arrangement of 10 rows x 20 seats.
Design a program to maximize both customer satisfaction and theater utilization.

## Getting Started
These instructions will get you a copy of the project up and running on your local machine for executing and testing purposes. 

### Prerequisites
Jdk 1.7+
Java access from command prompt(Java Path Variable set)

### Installation
Download source code from Git repository
Unzip the file.
The first step should create a folder named MovieTheaterSeating in the target download folder.

### Steps to compile the program solution
Open your terminal window / command prompt.
Go to the folder where the unzipped file is saved. 
Navigate to the folder "src".
Run the command:
  javac Main.java

### Steps to execute the program
Run the following command to start the application
  java Main
It will ask you to enter a path to the input file. For example:
  ```
Enter input file path
/Users/username/Desktop/test.txt
  ```
The test.txt file has a list of resource identifiers and the number of seats they requested. Example Input File Rows:
 ```
R001 2
R002 4
R003 4
R004 3
  ```
The order of the lines in the file reflects the order in which the reservation requests were received. Each line in
the file will be comprised of a reservation identifier, followed by a space, and then the number of seats requested. The reservation
identifier will have the format: "R####".

### Steps to run JUnit tests
Open your terminal window / command prompt.
Go to the folder where the unzipped file is saved. 
Navigate to the folder "src".
To compile, run the command:
  ```
  javac -cp .:"/Library/Java/JavaVirtualMachines/jdk1.8.0_121.jdk/Contents/Home/lib/*" MovieTheaterSeatingTests.java
  ```  
  (Here -cp defines the classpath link to the jar on your local drive.)
To execute test cases, run the command:
  ```
  java -cp .:"/Library/Java/JavaVirtualMachines/jdk1.8.0_121.jdk/Contents/Home/lib/*" org.junit.runner.JUnitCore MovieTheaterSeatingTests
  ```
## Expected output
A file with seat allocation data that is optimal for Theater utilization and customer satisfaction.

## Assumptions
The solution is developed for one venue/theater screen for the above mentioned layout.
It assumes that the seat rows are labeled in a fashion where, row A is farthest from the screen and J is the closest to the screen.
It assumes the metric for customer satisfaction is obtaining seating as farther away from the screen and obtaining all requested seats consecutively.
It assumes the metric for maximum theater utilization is having lesser "holes"/vacant seats sporadically and allocating as many seats as possible together.
It assumes that the maximum seats a user can request to reserve cannot be greater than the total number of seats in the theater's row. (For this solution, user cannot request more than 20 seats).
