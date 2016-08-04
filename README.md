# java-microservice-skeleton

Sample of microservice implemented in Java 8.

Frameworks used in project:

* [SparkJava](http://sparkjava.com/)
* [Guice](https://github.com/google/guice)
* [Gson](https://github.com/google/gson)
* [Project Lombok](https://projectlombok.org/)

The sample intend to run in Docker container with Nginx as a reverse proxy to this server.

## Getting Started

Use `gradle` to build install distribution of sample application.

And `docker-compose` to build and run it.

```
 git clone https://github.com/YuriyKo/java-microservice-skeleton
 cd java-microservice-skeleton
 gradle installDist
 cd docker
 docker-compose up
```

This will start application at `http://localhost/` with redirection to `https`.  

==Note!== Accept self-signed certificate - it's just for testing! 


## Explore API

The application supports OpenAPI Specification.
 
To start explore it - use [Swagger UI](http://swagger.io/swagger-ui/)

For quick start - Yes, run Swagger UI in Docker!

To build swagger-ui using a docker container:

```
 git clone https://github.com/swagger-api/swagger-ui
 cd swagger-ui
 docker build -t swagger-ui-builder .
 docker run -p 127.0.0.1:8080:8080 swagger-ui-builder
```

This will start Swagger UI at http://localhost:8080.

Copy and paste this URL `https://localhost/sample/api` and click `[Explore]`