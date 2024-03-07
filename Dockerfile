FROM amazoncorretto:21.0.2-alpine3.19
ENV PORT 8080
EXPOSE 8080
ARG JAR_FILE=target/*.jar
COPY ./target/wmm-be-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]