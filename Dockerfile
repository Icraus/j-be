FROM openjdk:8-jdk-alpine
COPY build/libs/*-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]

#port binding is 8081:8081