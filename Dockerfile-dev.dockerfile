FROM adoptopenjdk/openjdk11:x86_64-alpine-jre-11.0.12_7
COPY build/libs/*-SNAPSHOT.jar /app.jar
COPY samples/* /samples/
COPY database/* /database/
WORKDIR /
ENTRYPOINT ["java","-jar","/app.jar"]