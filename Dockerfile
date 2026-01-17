FROM clipse-temurin:17-jdk-jammy
MAINTAINER Omar
COPY target/*.jar my-backend-test.jar
ENTRYPOINT [ "java", "-jar", "my-backend-test.jar" ]