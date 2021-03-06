FROM adoptopenjdk/openjdk11:x86_64-alpine-jre-11.0.12_7
WORKDIR /
COPY build/libs/*-SNAPSHOT.jar /app.jar
COPY samples/* /samples/
COPY database/* /database/
ENTRYPOINT ["java","-jar","/app.jar"]