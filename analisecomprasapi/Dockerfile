# Fase de build com Maven e JDK 24
FROM maven:3.9.9-eclipse-temurin-24 AS build

WORKDIR /app
COPY pom.xml .
COPY src ./src

# Compilar o projeto
RUN mvn clean package -DskipTests

# Fase de execução com JRE 24
FROM eclipse-temurin:24-jre-alpine

WORKDIR /app
COPY --from=build /app/target/analisecomprasapi-*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]