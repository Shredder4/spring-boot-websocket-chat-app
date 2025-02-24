# Utilisation d'une image de base avec Java 21
FROM eclipse-temurin:21-jdk

# Définir le répertoire de travail dans le conteneur
WORKDIR /app

# Copier le fichier JAR généré par Maven
COPY target/*.jar app.jar

# Exposer le port sur lequel l'application écoute
EXPOSE 8080

# Lancer l'application
ENTRYPOINT ["java", "-jar", "app.jar"]
