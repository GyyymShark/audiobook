FROM openjdk:17
ARG JAR_FILE=build/libs/myceleb-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} /myceleb/app.jar
ENTRYPOINT ["java","-jar","/myceleb/app.jar"]
