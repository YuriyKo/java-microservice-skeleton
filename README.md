# java-microservice-skeleton
**Skeleton of java microservice.**

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
