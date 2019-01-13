FROM openjdk:8-jre
MAINTAINER Ravikiran Butti <ravikiran763@gmail.com>

ENTRYPOINT ["/usr/bin/java", "-jar", "/usr/share/activity-scheduler/activityScheduler.jar"]

# Add Maven dependencies (not shaded into the artifact; Docker-cached)
ADD target/lib           /usr/share/activity-scheduler/lib
# Add the service itself
ARG JAR_FILE
ADD target/${JAR_FILE} /usr/share/activity-scheduler/activityScheduler.jar