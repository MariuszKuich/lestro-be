FROM eclipse-temurin:17-jdk-focal
COPY target/employee-panel-0.0.1-SNAPSHOT.jar employee-panel-0.0.1-SNAPSHOT.jar
EXPOSE 50006
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=docker","/employee-panel-service-0.0.1-SNAPSHOT.jar"]