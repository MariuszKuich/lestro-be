FROM eclipse-temurin:17-jdk-focal
COPY target/pricing-service-0.0.1-SNAPSHOT.jar pricing-service-0.0.1-SNAPSHOT.jar
EXPOSE 50004
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=docker","/pricing-service-0.0.1-SNAPSHOT.jar"]