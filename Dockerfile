FROM tomcat:8.5.46-jdk11-openjdk
VOLUME /tmp
RUN rm -Rf /usr/local/tomcat/webapps/ROOT
ARG WAR_FILE
COPY ${WAR_FILE} /usr/local/tomcat/webapps/ROOT.war
ENV PORT=${PORT:-8080}
EXPOSE ${PORT}
CMD ["catalina.sh", "run"]

#FROM openjdk:17-jdk
#VOLUME /tmp
#ARG WAR_FILE
#COPY ${WAR_FILE} app.war
#ENV PORT=${PORT:-8082}
#ENTRYPOINT ["java", "-jar", "/app.war"]