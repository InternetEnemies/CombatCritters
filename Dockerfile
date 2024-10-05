
FROM amazoncorretto:22-alpine AS prod

WORKDIR /build
COPY . .

RUN ./gradlew clean bootJar

RUN cp critterSpring/build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar","--server.port=8080"]