FROM adoptopenjdk/openjdk11:x86_64-alpine-jre-11.0.12_7
WORKDIR /
COPY release/*-release.jar /app.jar
COPY release/config/* /config/
ENTRYPOINT ["java","-jar","/app.jar"]

#port binding is 8080:8080
#mount ./database /database which should contains database