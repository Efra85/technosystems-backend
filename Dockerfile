# Estágio 1: Buildar a aplicação com Maven
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DiskpTests

# Estágio 2: Rodar a aplicação apenas com o Java
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT [ "java","-jar","app.jar" ]


