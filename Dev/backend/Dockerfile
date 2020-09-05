FROM openjdk:8-jdk-alpine

RUN ./mvnw package && java -jar target/*-0.1.0.jar

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]

# ./mvnw package && java -jar target/*-0.1.0.jar
# sudo docker build -t zenite-backend .
# sudo docker run -p 7071:8080 zenite-backend