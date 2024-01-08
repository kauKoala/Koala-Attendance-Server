FROM openjdk:17-jdk
ARG WAR_FILE
COPY ${WAR_FILE} app.war
ENV PORT=${PORT:-8082}
ENTRYPOINT ["java", "-jar", "/app.war"]

#FROM openjdk:17-jdk
#VOLUME /tmp
#ARG WAR_FILE
#COPY ${WAR_FILE} app.war
#ENV PORT=${PORT:-8082}
#ENTRYPOINT ["java", "-jar", "/app.war"]