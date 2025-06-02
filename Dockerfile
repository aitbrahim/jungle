# ----------------------------
# Étape 1 : build Maven
# ----------------------------
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app

# 1) Copier le pom.xml seul pour optimiser le cache Docker
COPY pom.xml .

# 2) Télécharger les dépendances (incl. mysql-connector-java)
RUN mvn dependency:go-offline

# 3) Copier tout le code source
COPY src ./src

# 4) Compiler et packager (génère les JAR dans target/)
RUN mvn clean package

# ----------------------------
# Étape 2 : image runtime
# ----------------------------
FROM openjdk:17-jdk-slim

# Installer les bibliothèques natives X11 nécessaires, si vous affichez Swing
RUN apt-get update && apt-get install -y --no-install-recommends \
      libxext6 libxrender1 libxtst6 libxi6 libxrandr2 \
      libglib2.0-0 libfreetype6 libfontconfig1 \
    && rm -rf /var/lib/apt/lists/*

WORKDIR /app

# 5) Copier le JAR « with dependencies » généré par Maven
COPY --from=build /app/target/app-jar-with-dependencies.jar app.jar

# 6) Exposer DISPLAY pour X11 (si Swing)
ENV DISPLAY=:0

# 7) Lancer l’application
CMD ["java", "-jar", "app.jar"]
