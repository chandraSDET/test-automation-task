About:
------
This project created to automate the given tasks

Designed Automation Framework using Selenium,Java,TestNG and followed Page Object Model

Project Structure:
------------------
src
  test
     java
       com.task
           config
             ConfigFileReader - To load property files
             ConfigManager    - To make sure have single instance of ConfigFileReader across test
           driver
             DriverFactory    - DriverFactory to create driver instance for different browsers
             DriverManger     - Used ThreadLocal for parallel running and make sure have single instance of WebDriver
           page
             AddListPage      - Methods created for different list type for Ex: Standard Inclusion/Exclusion (or) Replacement (or) Taxonomy Mapping
             DataFlowPage     - Methods created for a mapping
             DataViewPage     - Methods created for editing list for Standard Inclusion/Exclusion (or) Replacement (or) Taxonomy
             LeftNavigationPage - Methods created for to choose different feature for Ex:lists,DataFlow,DataView
             ProjectPage        - Methods created for to click site
             LoginPage          - Methods created for to Login
           Reports
             ExtentReport        - To create a Reports
             ExtentReportManger  - To make sure Reports work parallel running
           test
             TaskTests           - Test Methods for given tasks
           BaseTest              - To initialize WebDriver and ConfigFileReader Instance before running each test
           TestLister            - Contains TestNG methods to mark to given test fail or pass and same will be displayed in report

How Run Tests:
--------------
Clone the project from GitHub
Make sure install maven,And run tests using mvn test -DsuiteXmlFile="src/test/resources/suites/local.xml"
After running tests verify the report generated under target/extent-reports folder
             

