# J-Customer app.

![mobile-client branch workflow](https://github.com/icraus/j-be/actions/workflows/CI.yaml/badge.svg??branch=mobile-client)


## Quick start
The application consists of **Backend**, **Web Client**, **Mobile app**:
- **Backend** is written in **Java/Spring** Boot and uses **SQLite3** database.
- **Web Client** is written in **AngularJS, Bootstrap, HTML/CSS**.
- **Mobile** Client is written in **Flutter**.

## Dependencies
- **Java 11 or higher** 
- **Spring boot 5.x**
- **Flutter**
- **andorid Emulator**

## Quick start for deployment
- The Easiest way is to download **deploy.cmd** and call it.

## Project structure
```
|-- FE
|   |-- release //Web client release folder
|   `-- src     //Web client src folder
|-- j-be
|   |-- build
|   |-- config
|   |-- database
|   |-- gradle
|   |-- release    //Release folder contains all reuired files
|   |-- samples
|   `-- src
`-- mobile-client
    |-- android
    |-- build\app\outputs\flutter-apk   //release folder contaisn all requried files 
    |-- ios
    |-- lib     //src code for mobile-client
    `-- test


```
## Getting started with Backend:
- clone the project using.
  ```
    git clone https://github.com/Icraus/j-be.git -b master
  ```
- run
  ```
  gradlew :release
  ```
  this should generate release folder which you can after that call it
  ```
  java -jar j-be-0.0.1-release.jar
  ```
  
- to use docker you have 2 options
    * for production call 
  ```
    docker build -f Dockerfile-prod.dockerfile -t <name-of-image> . // for production
    docker run --name <name-of-container> -p8080:8080 -v %CD%\release\database:/database <name-of-image>
  ```
   * for development 
  ```
    gradlew :build
    docker build -f Dockerfile-dev.dockerfile -t <name-of-image> . // for dev 
    docker run --name <name-of-container> -p8080:8080 <name-of-image>
  ```

## Getting started with Web Client:
- clone the project using.
  ```
    git clone https://github.com/Icraus/j-be.git -b FE
  ```
- run
  ```
  gradlew :release
  ```
  this should generate release folder which you can after that call it
  ```
  java -jar j-fe-0.0.1-release.jar
  ```

- to use docker
  * for production call
  ```
    docker build -f Dockerfile -t <name-of-image> . // for production
    docker run --name <name-of-container> -p8081:8081 <name-of-image>

## Getting started with Mobile Client:
- clone the project using.
  ```
    git clone https://github.com/Icraus/j-be.git -b mobile-client
  ```
- make sure you added flutter to your env path and run
  ```
  flutter pub get
  flutter build apk
  ```
  this should generate release folder which you can after that call it
  ```
  build\app\outputs\flutter-apk
  
  ????????????flutter-apk
  ???       app-release.apk
  ???       app.apk
  ???       app.apk.sha1
  ????????????release

  ```
  then you can drag and drop it to your emulator.

## Usage:
- To test the REST api
    * to get the first 5 customers 
    ```
    curl http://localhost:8080/rest/customers?start=0&count=5
    ```
    * to get the first 5 customers by name
    ```
    curl http://localhost:8080/rest/customers/name?name=whatever&start=0&count=5
    ```
    * to get the first 5 customers by countryCode
    ```
    curl http://localhost:8080/rest/customers/countryCode?countryCode=237&start=0&count=5
    ```
    * to get the customer by id
    ```
    curl http://localhost:8080/rest/customers/5
    ```
- To test the Web client
    open your browser and call
    ``` 
    http://localhost:8081/
    ```
- To test the Mobile client
  open your android emulator and drag and drop 
  ```build\app\outputs\flutter-apk\app-release.apk```

## Notes about the design:
  - First I have to say that I would have chosen other approaches to deal with
    these tasks as this is not a production code.
 - In production, I would choose other approaches to handle the country stuff differently
    * So for example, I would choose **GraphQL** *instead of* a **REST API** to do the **queries and mutations**
      graphQL gives you more flexibility, and you can actually relay on different approach instead of **REST/Http commands**
      you can implement it using **Websockets**, also this would be more helpful to solve the country/code/is valid problem without having to implement
      it this way
    * I would use **SQL views** instead of **normal queries(select)** which I can use **regex(which is supported by SQLite3)** to 
      get *country, code and valid* easier.
    * I would use **docker swarm or kubernetes** to *manage the service* instead of normal **docker** containers.
    * I would have used git submodules to manage dependencies between the three projects.
    
