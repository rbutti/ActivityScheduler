#Step 1: Build the project using maven
FROM maven:3.5.2-jdk-8-alpine AS MAVEN_TOOL_CHAIN
MAINTAINER Ravikiran Butti <ravikiran763@gmail.com>
COPY pom.xml /tmp/
COPY src /tmp/src/
WORKDIR /tmp/
RUN mvn clean package

#Step 2: Create an image 
FROM openjdk:8-jdk
MAINTAINER Ravikiran Butti <ravikiran763@gmail.com>
COPY /tmp/target/activity-scheduler.jar /app/activity-scheduler.jar
CMD ["java", "-jar", "/app/activity-scheduler.jar"]