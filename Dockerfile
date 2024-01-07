FROM openjdk:17-jdk
ARG WAR_FILE=build/libs/*.war
COPY ${WAR_FILE} app.war
ENV PORT=${PORT:-8082}
CMD ["java", "-jar", "/app.war"]