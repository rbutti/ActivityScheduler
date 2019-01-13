FROM openjdk:8-jdk
MAINTAINER Ravikiran Butti <ravikiran763@gmail.com>
EXPOSE 4567
VOLUME /data
COPY target/*.jar /app/activity-scheduler.jar
CMD ["java", "-jar", "/app/activity-scheduler.jar"]