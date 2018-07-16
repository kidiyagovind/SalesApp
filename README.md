# Sales Processing Application
A stand-alone java application for processing sales messages received in perticular format.


## Steps to Run the application
This is a maven project. It requires Java 1.8 compliance due to use of lambda expressions. 
The only dependency is to JUnit for running the included unit tests.
1. clone or download source from provided url
2. mvn compile
3. java -jar SalesApp-0.0.1-SNAPSHOT.jar THIRDPARTY(from target location of jar file)
4. if you want to test with random messages use java -jar SalesApp-0.0.1-SNAPSHOT.jar RANDOMMSG

## Resources for Sample data
Currently I have provided test data from ArrayMessageProvider class for testing purpose.