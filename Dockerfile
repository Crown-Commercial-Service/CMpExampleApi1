# Minimal Dockerfile for a Java Springboot application
# Note that this is using Java 8
# Memory settings and garbase collection settings are more important with this version due to how
# Java determines the number of Garbage collection threads
FROM openjdk:8-jdk-alpine

ARG JAR_FILE
COPY ${JAR_FILE} ./app.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Xms64m","-Xmx256m","-jar","/app.jar"]
