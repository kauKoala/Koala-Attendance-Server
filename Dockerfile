FROM tomcat:latest
VOLUME /tmp
ARG WAR_FILE
COPY ${WAR_FILE} /usr/local/tomcat/webapps/app.war
ENV PORT=${PORT:-8082}
CMD ["catalina.sh", "run"]