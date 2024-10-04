FROM amazoncorretto:22-alpine

WORKDIR /combatcritters-run

CMD ["./gradlew", "clean", "bootJar"]

COPY critterSpring/build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar","--server.port=8080"]