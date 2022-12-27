FROM openjdk:11


RUN mkdir "jarHolder"
RUN mkdir "jarHolder/json"
RUN mkdir "jarHolder/pdf"
ADD /target/demo1-0.0.1-SNAPSHOT.jar /jarHolder/backend.jar
ENTRYPOINT ["java", "-jar", "jarHolder/backend.jar"]
