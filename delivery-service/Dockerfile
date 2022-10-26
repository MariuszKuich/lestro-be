FROM eclipse-temurin:17-jdk-focal
COPY target/delivery-service-0.0.1-SNAPSHOT.jar delivery-service-0.0.1-SNAPSHOT.jar
EXPOSE 50001
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=docker","/delivery-service-0.0.1-SNAPSHOT.jar"]