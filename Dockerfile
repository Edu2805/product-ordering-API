FROM openjdk:17.0.2
WORKDIR /app
COPY ./target/*.jar ./application.jar
EXPOSE 8080

ENTRYPOINT java -jar application.jar