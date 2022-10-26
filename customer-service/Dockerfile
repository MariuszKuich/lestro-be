FROM eclipse-temurin:17-jdk-focal
COPY target/customer-service-0.0.1-SNAPSHOT.jar customer-service-0.0.1-SNAPSHOT.jar
EXPOSE 50000
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=docker","/customer-service-0.0.1-SNAPSHOT.jar"]