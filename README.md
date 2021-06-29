# VDCTest
Guidance on how to run Project:
Preresiquites:
+ Maven version 3.6.3
+ jdk Java 8 update 261
+ java version : 1.8.0_261
+ install npm (refer to https://stackjava.com/nodejs/huong-dan-cai-dat-cau-hinh-nodejs-npm-tren-windows.html)
+ install multiple html Reporter (refer to https://www.npmjs.com/package/multiple-cucumber-html-reporter )

1. clone this project on main branch to computer
2. open project by any compiler (prefer Intelilij). Reload pom.xml file to update dependencies.
3. open WeatherTC.java by src > test >merge_feature_file
4. execute WeatherTCMain.java , then 2 features files representing 2 test cases will be generated
5. Navigate to feature by main > resource > feature
6. Execute each feature file

Test Report Solution:
1. Download:
+ 1 folder : FULL-2021-06-25_15-37 :https://mphcmiuedu-my.sharepoint.com/:u:/g/personal/ititiu15042_student_hcmiu_edu_vn/EXnv6q2BEW5OsLRc6TyuyB0BBatROXYdr8KxUkuuXAv75g?e=ivRrXs   
+ jenkins.war file version https://get.jenkins.io/war-stable/2.277.4/ . Place them in the same folder
2. on the folder containing 2 files, run CMD : java -jar jenkins.war.
3.  Nagivate to http://localhost:8080/ when the command line above finish.
4.  Install as suggested by jenkins
5. Once it's done, enter username/password for accessing Jenkins (like admin/admin). Ignore the failed red thing when running, we will restore the setting later.
6. Login to Jenkins, Navigate to Manage Jenkins > Manage Plugins
7. Search for plugin called thinBackup, then install it(restart if needed)
8. Navigate to http://localhost:8080/ , Manage Jenkins > thinBackup( should be located at the bottom of page)
9. Click on Settings, Locate the folder where FULL-2021-06-25_15-37 is placed then save
10. Click Restore, select the mentioned folder above. Wait a few minutes and refresh back to Home page Jenkins
11. On Jenkins Homepage, click Build executor status > select Node master > Configure
12. In Node Properties Area, correct the path to suit with your local machine then save
13. For folder Report related, download VDC Report as uploaded in this repository
14. Navigate to  Manage Jenkins > Script console, input this code and run (this is to add css to report):  System.setProperty("hudson.model.DirectoryBrowserSupport.CSP", "") 
15. In VDCReport > report_generation > common, edit file in txt . You will see jsonDir and reportPath, modify it to suit your local machine then save
16. Finally, Navigate to Homepage Jenkins, on Tab Weather in your city, select job Weather-In-Your-City
17. Click Build Now. After finished, Check Automation HTML Report
