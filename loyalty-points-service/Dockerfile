FROM eclipse-temurin:17-jdk-focal
COPY target/loyalty-points-service-0.0.1-SNAPSHOT.jar loyalty-points-service-0.0.1-SNAPSHOT.jar
EXPOSE 50002
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=docker","/loyalty-points-service-0.0.1-SNAPSHOT.jar"]