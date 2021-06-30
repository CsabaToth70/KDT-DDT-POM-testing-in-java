# KDT-DDT-POM-testing-in-java
Automated testing with Keyword Driven-, Data-Driven-, and Page Object Model implementation.

## ABOUT THE PROJECT
<br>
This project task was given as the first step to learn and implement Selenium-based automated tests with 

1. Keyword Driven Tests; 
2. Data-Driven Tests; 
3. Page Object Model

frameworks.

![Selenium Easy](seleniumeasy.png)
<br>

## Applied technologies

Project SDK: java 11 version 11.09<br>
Project language level: 16 Records, patterns<br>
Applied browser: Google Chrome Version:  91.0.4472.106 (64 bit)<br>
Applied WebDriver: chromedriver<br>
Data resources format: .csv, .xlsx, <br>
Applied IDE: IntelliJ IDEA<br>

![](java.png)
![](selenium.png)

## Technical information and set up

Chromedriver have to be saved as 'chromedriver' named file also in the main project folder,
selected for proper version of the browser: https://sites.google.com/a/chromium.org/chromedriver/downloads

The KDT framework has been implemented in two different way: 
1. Excel-based (as the original exercise was defined)
       ./externals/testCases.xlsx
2. JUnit-based
        ./objects/object.properties


## User information

When you run these test suites, the browser has to open and close each test case, to ensure independent test results from each other.


The test cases contain click and type input data into fields of the webpage automatically, and some wait methods for the concerned fields to be clickable.
The lengths of waits were designed in our technical environment (network speed, browser-related WebDriver).

It could happen that sets of these "waits" should be increased by a further 3-5 seconds, due to different environments.
