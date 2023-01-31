FROM openjdk:17:alpine

WORKDIR /BulletBox

COPY BulletBox-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]