this service is used to store data of all the users and employees in Bristle Co., Ltd
# Deployment

### Build into docker image
1. cd to $PROJECT_ROOT
2. cd to ./configuration and do git pull to make sure configuration submodule is at newest commit
3. mvn compile
4. mvn clean
5. mvn -Dcustomer-detail-service=localhost -DDB_PASSWORD=Microservice@39909204 -DDB_USERNAME=microservice -DHOST=localhost -Dorder-service=localhost -Dproduction-ticket-service=localhost -Duser-service=localhost package 
maven package execute tests and we want it to be able to load application context, thus the HOST environment variable is needed
after mvn package, there will be a .jar under $PROJECT_ROOT/target/user-service.<version>.jar. The docker file then uses this location to copy into the image
6. docker build -t user-service:<version> .

# Running this service locally
There are some preconfigured environment variables that need to be fulfilled and they are listed below

### running in Intellij from source code
edit run configurations and copy-paste below code block to add environment variables

    customer-detail-service=localhost;DB_PASSWORD=Microservice@39909204;DB_USERNAME=microservice;HOST=localhost;order-service=localhost;production-ticket-service=localhost;user-service=localhost
### running in terminal from packaged .jar
Once a jar is packaged, assuming the name of the .jar file is "user-service.0.0.1.jar" we can go to its directory and run 

    java -jar user-service.0.0.1.jar -Dcustomer-detail-service=localhost -DDB_PASSWORD=Microservice@39909204 -DDB_USERNAME=microservice -DHOST=localhost -Dorder-service=localhost -Dproduction-ticket-service=localhost -Duser-service=localhost

### running with docker from image
    docker pull andersonhsieh0330/user-service:<version>

assuming the image repository is user-service and tag(<version>) is 0.0.1

    docker run -p 8089:8089 -p 9099:9099 -ti --env customer-detail-service=localhost --env DB_PASSWORD=Microservice@39909204 --env DB_USERNAME=microservice --env HOST=host.docker.internal --env order-service=localhost --env production-ticket-service=localhost --env user-service=localhost user-service:0.0.1