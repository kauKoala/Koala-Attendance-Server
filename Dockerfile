FROM openjdk:17-jdk
WORKDIR /app
COPY . .
RUN ./gradlew bootWar
CMD ["java", "-jar", "build/libs/koala.war"]
