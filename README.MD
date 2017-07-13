##Git Branches
###master
contains simple spring boot application with tomcat version 8.0.32, spring boot version 1.5.4
####Build
build the project with `gradlew build -x test`
This should result in a tomcat-1.0.0.war file
####Startup the application
java -jar -Dspring.profiles.active=dev -Dserver.port=8085 -Xms256m -Xmx256m -XX:MaxMetaspaceSize=128m -Xrs tomcat-1.0.0.war
###Tomcat_8.5.15
contains simple spring boot application with tomcat version 8.5.15, spring boot version 1.5.4
####Build
build the project with `gradlew build -x test`
This should result in a tomcat-2.0.0.war file
####Startup the application
java -jar -Dspring.profiles.active=dev -Dserver.port=8085 -Xms256m -Xmx256m -XX:MaxMetaspaceSize=128m -Xrs tomcat-2.0.0.war
##The test
Perform a GET operation on http://localhost:8085/tomcat/api/performance/schema

###Expected result
####On Tomcat 8.0.32
OK performance
####On Tomcat 8.5.15
Bad performance. First call takes almost 2 seconds, subsequent call within a time window of 10 seconds are fast. If more than 10 seconds
between calls gives a processing time like the first call (between 1-2 seconds) 

