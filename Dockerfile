FROM openjdk:21-oracle
MAINTAINER Omar
COPY target/*.jar my-backend-test.jar
ENTRYPOINT [ "java", "-jar", "my-backend-test.jar" ]