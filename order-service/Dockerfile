FROM eclipse-temurin:17-jdk-focal
COPY target/order-service-0.0.1-SNAPSHOT.jar order-service-0.0.1-SNAPSHOT.jar
EXPOSE 50007
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=docker","/order-service-0.0.1-SNAPSHOT.jar"]