# utils


英文文档都是jHipster自动生成的，懒得修改了，有兴趣就看，中文注释是用来解释下关键步骤

This application was generated using JHipster 5.4.2, you can find documentation and help at [https://www.jhipster.tech/documentation-archive/v5.4.2](https://www.jhipster.tech/documentation-archive/v5.4.2).

This is a "microservice" application intended to be part of a microservice architecture, please refer to the [Doing microservices with JHipster][] page of the documentation for more information.

This application is configured for Service Discovery and Configuration with Consul. On launch, it will refuse to start if it is not able to connect to Consul at [http://localhost:8500](http://localhost:8500). For more information, read our documentation on [Service Discovery and Configuration with Consul][].

## Development


下载代码后第一次启动这个项目，需要向运行以下代码

    ./gradlew


For further instructions on how to develop with JHipster, have a look at [Using JHipster in development][].


由于项目的数据库mongodb是在docker运行的，然后挂载持久化卷，需要因此需要运行以下代码

    docker-compose -f src/main/docker/mongodb.yml up -d

其他内容的话，看看就好啦

## 本项目使用的mongodb的连接框架的参考资料

https://www.baeldung.com/spring-data-mongodb-gridfs

## Building for production

To optimize the utils application for production, run:

    ./gradlew -Pprod clean bootWar

To ensure everything worked, run:

    java -jar build/libs/*.war


Refer to [Using JHipster in production][] for more details.

## Testing

To launch your application's tests, run:

    ./gradlew test

For more information, refer to the [Running tests page][].

### Code quality

Sonar is used to analyse code quality. You can start a local Sonar server (accessible on http://localhost:9001) with:

```
docker-compose -f src/main/docker/sonar.yml up -d
```

Then, run a Sonar analysis:

```
./gradlew -Pprod clean test sonarqube
```

For more information, refer to the [Code quality page][].

## Using Docker to simplify development (optional)

You can use Docker to improve your JHipster development experience. A number of docker-compose configuration are available in the [src/main/docker](src/main/docker) folder to launch required third party services.

For example, to start a mongodb database in a docker container, run:

    docker-compose -f src/main/docker/mongodb.yml up -d

To stop it and remove the container, run:

    docker-compose -f src/main/docker/mongodb.yml down

You can also fully dockerize your application and all the services that it depends on.
To achieve this, first build a docker image of your app by running:

    ./gradlew bootWar -Pprod jibDockerBuild

Then run:

    docker-compose -f src/main/docker/app.yml up -d

For more information refer to [Using Docker and Docker-Compose][], this page also contains information on the docker-compose sub-generator (`jhipster docker-compose`), which is able to generate docker configurations for one or several JHipster applications.

## Continuous Integration (optional)

To configure CI for your project, run the ci-cd sub-generator (`jhipster ci-cd`), this will let you generate configuration files for a number of Continuous Integration systems. Consult the [Setting up Continuous Integration][] page for more information.

[JHipster Homepage and latest documentation]: https://www.jhipster.tech
[JHipster 5.4.2 archive]: https://www.jhipster.tech/documentation-archive/v5.4.2
[Doing microservices with JHipster]: https://www.jhipster.tech/documentation-archive/v5.4.2/microservices-architecture/
[Using JHipster in development]: https://www.jhipster.tech/documentation-archive/v5.4.2/development/
[Service Discovery and Configuration with Consul]: https://www.jhipster.tech/documentation-archive/v5.4.2/microservices-architecture/#consul
[Using Docker and Docker-Compose]: https://www.jhipster.tech/documentation-archive/v5.4.2/docker-compose
[Using JHipster in production]: https://www.jhipster.tech/documentation-archive/v5.4.2/production/
[Running tests page]: https://www.jhipster.tech/documentation-archive/v5.4.2/running-tests/
[Code quality page]: https://www.jhipster.tech/documentation-archive/v5.4.2/code-quality/
[Setting up Continuous Integration]: https://www.jhipster.tech/documentation-archive/v5.4.2/setting-up-ci/




