# Highlander Server

Backend code to support highlander-app

## Technologies

- Docker
- Kotlin 1.2
- SpringBoot
- H2 inMemory Database

## How To Run

As Highlander server is a SpringBoot application you can find bellow both ways which was tested.

### Using gradle

Make sure you have at minimum jdk8 installed in your system

```bash
./gradlew bootRun
```

### Using docker

As highlander-server is an SpringBoot app we should generate jar file before build our docker image, to do that, use follow command

```bash
./gradlew bootRepackage
```

Then we can build our image using

```bash
docker build . -t highlander_server:1.0
```

So, now your have highlander_server image in your docker image catalog, so you can run it using

```bash
docker run -p 8080:8080 --name some_highlander highlander_server:1.0
```

### Test

To check if your application is running you may use CURL to get health infomartion

```bash
 curl -s http://localhost:8080/healthcheck
```