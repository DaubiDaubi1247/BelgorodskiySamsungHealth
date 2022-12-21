FROM openjdk:11

RUN mkdir "jarHolder"
ADD /target/demo1-0.0.1-SNAPSHOT.jar backend1.jar
ENTRYPOINT ["java", "-jar", "backend1.jar"]
