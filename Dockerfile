# Minimal Dockerfile for a Java Springboot application
# Note that this is using Java 8
# Memory settings and garbase collection settings are more important with this version due to how
# Java determines the number of Garbage collection threads
# 
# The API was tested using JDK 11 (openjdk:11-jdk-slim).
# This works but a reflection warning is generated, the image is also considerably
# larger than the alpine based images.
#
FROM openjdk:8-jdk-alpine

# Build information
# Build information
ARG GIT_OWNER
LABEL git_owner=$GIT_OWNER
ENV GIT_OWNER=$GIT_OWNER

ARG GIT_REPO
LABEL git_repo=$GIT_REPO
ENV GIT_REPO=$GIT_REPO

ARG GIT_BRANCH
LABEL git_branch=$GIT_BRANCH
ENV GIT_BRANCH=$GIT_BRANCH

ARG GIT_COMMIT
LABEL git_commit=$GIT_COMMIT
ENV GIT_COMMIT=$GIT_COMMIT

ARG BUILD_TIME
LABEL build_time=$BUILD_TIME
ENV BUILD_TIME=$BUILD_TIME

ARG CCS_VERSION
LABEL ccs_version=$CCS_VERSION
ENV CCS_VERSION=$CCS_VERSION

##_PARAMETER_STORE_MARKER_##

ARG JAR_FILE
COPY ${JAR_FILE} ./app.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Xms64m","-Xmx256m","-jar","/app.jar"]
