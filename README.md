# REST-Assured-Transaction-API
## Prerequisites
* Install jdk 8 or any LTS version
* Configure **JAVA_HOME** and **GRADLE_HOME**
* Download Allure 2.17.2 and configure environment path
* Stable internet connection
## How to run this project
* Clone the repo
* Open cmd in the root folder
* Give following commands:
```
gradle clean test
```
```
allure generate allure-results --clean -o allure-report
```
```
allure serve allure-results
```
## Screenshot
This is the snapshot of the allure reports:

![Screenshot_1](https://user-images.githubusercontent.com/71173675/152676873-59321129-513a-4a60-991a-da4d12279975.png)
