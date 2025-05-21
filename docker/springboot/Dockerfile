# 1. lépés: Maven build környezet
FROM maven:3.9.6-eclipse-temurin-21 AS build

# Munkakönyvtár beállítása
WORKDIR /app

# Másold be az összes forráskódot
COPY . /app

# Futtasd a Maven buildet
RUN mvn clean install -DskipTests

# 2. lépés: Alkalmazás futtatásához szükséges környezet
FROM eclipse-temurin:21-jdk-alpine

# Munkakönyvtár beállítása
WORKDIR /app

# A Maven build eredményeként létrejövő .jar fájl átmásolása
COPY --from=build /app/target/*.jar app.jar

# Az alkalmazás futtatása
ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-jar", "app.jar"]
