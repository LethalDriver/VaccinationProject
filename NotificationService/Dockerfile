# Start with a base image containing Java runtime
FROM openjdk:17-jdk-slim

# Add Maintainer Info
LABEL maintainer="LethalDriver@example.com"

# Add a volume pointing to /tmp
VOLUME /tmp


# The application's jar file
ARG JAR_FILE=target/my-application-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container
ADD ${JAR_FILE} my-application.jar

# Run the jar file
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/my-application.jar"]