# Etapa de build
FROM maven:3.9.6-amazoncorretto-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -X -DskipTests

# Etapa de execução
FROM openjdk:17-ea-10-jdk-slim
WORKDIR /app
COPY --from=build /app/target/*.jar /app/apieletronico.jar
ENTRYPOINT ["java", "-jar", "apieletronico.jar"]
