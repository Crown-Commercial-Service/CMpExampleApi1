# Example Api Project #

This is a small example that uses Java and Springboot to provision a simple REST api. It uses the Maven build system and makes use of the Spotify Docker plugin.

The api will listen for requests on **port 8080**. Note that this is the port number the build pipe lines will use when defining the ECS tasks and load balancer rules. Changing the api to use a different port will result in incorrect deployment of the resulting container.

It will respond to the following requests:

- /
- /greeting
- /greeting?name=Albert
- /systeminfo
- /systeminfo?detail=all
- /systeminfo?detail=properties
- /systeminfo?detail=environment

Note that the `systeminfo` requests can return detailed Java system property and environment variable information. Care should to taken to ensure this is only deployed for development purposes.

## Local Execution ##
Because the example api uses Java it needs to be compiled, a jar file file created and, ultimately, a Docker image created. To do this a Java JDK, and Apache Maven need to be installed and configured. The Dockerfile specifies OpenJDK version 8.

To build and execute locally execute:

```
mvn clean package
mvn spring-boot:run
```

This will start the api on port `8080`. The run a different port, for example `18080` use:

```
mvn -Dserver.port=18080 spring-boot:run
```

You may wish to use a different port when use an application and api are being run locally. See the example application repository, `CCSExampleApp1`.

Now the api is running locally it is possible make requests to it, for example using curl:

`curl http://localhost:8080/greeting`

will return a JSON object like: `{"id":1,"content":"Hello, World!"}`.

## Build Pipeline ##
The corresponding example build pipeline project is in the `CCSDevEnvironment` repository as `/terraform/build/api1`. The pipeline currently needs to be stored within the `CCSDevEnvironment` repository because it requires access to various Terraform modules.

## Dockerfile ##
The project includes a simple Docker file, this is used by the build pipeline to generate the container image. It is a standard file for a Java Springboot based project. It must expose port 8080. The name of the container image is defined in the Maven `pom.xml` file. For the example it is `ccs/api1`.

To build the Docker image locally execute:

`mvn install dockerfile:build`

Note that this is using the Spotify Maven plugin and Docker must also be installed locally. Do not try and build the image using `docker image build`.

The resulting image can be executed using:

`docker run -p 8080:8080 ccs/api`