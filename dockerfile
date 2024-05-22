FROM openjdk:17-jdk
COPY target/demo-0.0.1-SNAPSHOT.jar tictac-app.jar
ENTRYPOINT ["java", "-jar", "tictac-app.jar"]