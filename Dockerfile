# syntax=docker/dockerfile:1

FROM eclipse-temurin

LABEL mentainer="nitish.k@beehyv.com"

WORKDIR /app

EXPOSE 8080

ADD target/backend.jar /app/backend.jar
CMD ["java","-jar","backend.jar"]