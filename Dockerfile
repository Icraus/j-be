FROM openjdk:8-jdk-alpine
COPY release/*-release.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]

#port binding is 8081:8081