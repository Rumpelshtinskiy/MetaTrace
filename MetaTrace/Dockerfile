#
# Build stage
#
FROM maven:3.8.3-openjdk-17 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

#
# Package stage
#
FROM eclipse-temurin:17-jdk-alpine
COPY --from=build /home/app/target/MetaTrace-1.0-SNAPSHOT-jar-with-dependencies.jar /usr/local/lib/MetaTrace.jar
EXPOSE 3000
ENTRYPOINT ["java","-jar","/usr/local/lib/MetaTrace.jar"]