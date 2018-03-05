FROM openjdk:8-jdk-alpine
WORKDIR '/app'
COPY './build/libs/highlander-server-1.0.0.jar' '/app/highlander-server.jar'
EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/highlander-server.jar"]