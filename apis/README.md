# Idwall - API Test Automation Challenge

## Execute tests through dockerized Jenkins
* Pre-requisites: [Docker](https://docs.docker.com/install/) installed on host/local machine 
* Execute the following command in a Terminal 
```text
$ docker run -p 8080:8080 -p 50000:50000 sfrubio/sfrubio:desafio-qa-jenkins
```
* Ensure that the Docker container is pulled and running in your host. In terminal, run the following command:
```text
$ docker ps -a
CONTAINER ID        IMAGE               COMMAND                  CREATED             STATUS              PORTS                                              NAMES
339dc64edf95        08b0e7913330        "/sbin/tini -- /usr/…"   5 hours ago         Up 5 hours          0.0.0.0:8080->8080/tcp, 0.0.0.0:50000->50000/tcp   desafio-qa-jenkins
```
* Log in Jenkins with user `idwall` and password `desafio_qa`, access job [idwall-api-test](http://localhost:8080/job/idwall_api_test) and click on "Build with parameters"

![Jenkins Main Menu Example](https://github.com/sfrubio/desafios-qa/raw/master/apis/src/main/resources/jenkins-main-menu.png "Jenkins Main Menu Example")
* Fulfill the presented fields according to what data is required for test execution.
* In addition, it is allowed to choose the test group to be executed, on dropdown field "testGroup"
* To start test execution, click on button "Build"

![Jenkins Build With Parameters Example](https://github.com/sfrubio/desafios-qa/raw/master/apis/src/main/resources/jenkins-build-with-parameters.png "Jenkins Build With Parameters Example")
* After test execution, results will be placed in [Allure Reports](http://localhost:8080/job/idwall_api_test/allure/):

![Jenkins Allure Reports Example](https://github.com/sfrubio/desafios-qa/raw/master/apis/src/main/resources/jenkins-allure-reports.png "Jenkins Allure Reports Example")
## Run test cases with Maven
### Execute test cases with default parameters
```text
$ mvn clean test
```
### Execute test cases with test parameters
```text
$ mvn clean test -DvalidName="Valid Name" -DvalidNumber="Valid Document Number" -DvalidCompanyNumber="Valid Company Number" -DvalidBirthday=<Valid Date> -Dtoken=<Valid Token>
```
Available fields and default values:
* `-DvalidName=Test`
* `-DvalidNumber=00000000191`
* `-DvalidCompanyNumber=00000000000191`
* `-DvalidBirthday=1982-11-12`
* `-Dtoken=00000000-0000-0000-0000-000000000000`
### Execute test cases for a specific functionality
```text
$ mvn clean test -DtestGroup="functionality"
```
Functionalities available:
* all
* consultaPessoaDefault
* consultaCPF
* consultaEmpresaDefault
* expectedErrors
* manualApproval
* matrixReports
* userReport
### Run tests cases with blocker severity
```text
mvn clean test -DtestGroup="blocker"
```
### Generate Allure reports
```text
$ mvn allure:aggregate
```
The Allure report should be generated in folder `target/allure-results`, as defined on Surefire configurations

## Summary of the provided solution.
* Docker image based on the [Official Jenkins Docker image](https://github.com/jenkinsci/docker/blob/master/README.md), just modified Dockerfile to build a image with `/var/jenkins_home` not stored in a volume 
* Tests designed in Java language, using JUnit5 test framework and Apache Maven for build.
* Additional tools:
  * [Allure](https://docs.qameta.io/allure/) (for test report)
  * [Hamcrest](http://hamcrest.org/) (for assertions)
  * [RestAssured](http://rest-assured.io/) (for HTTP requests)
  * [Random Beans](https://github.com/benas/random-beans) (for randomized values)
  * [Awaitility](https://github.com/awaitility/awaitility) (for implementation of polling logic on asynchronous requests)
* Test classes and methods were tagged, in order to allow the test execution through command line, by defining which `testGroup` will be executed. 
* Test classes were separated per functionality, and placed in package `reports`, to fulfill _Unknown scenarios_ part of the challenge. The data provided in these classes comes from parameters passed through Maven properties / Jenkins parametrized build, in order not to provide real data in test source code.
* It was also included the test class `ExpectedErrorsTest.class` to fulfill _Known scenarios_ part of the challenge
* Since test steps, data and assertions are shared between test classes, they are implemented in 3 classes inside package `support`:
  * `ReportsData.class`: Containing the steps needed to create test data;
  * `ReportsSteps.class`: Containing the steps to execute requests, assert status codes and get response payloads;
  * `ReportsAssertions.class`: Containing the steps to made assertions according to test scenario.
* Created specific DTO classes in `/src/main/dtos/`, to help on request/response payload serialization/deserialization, as well as to keep assertions more descriptive.