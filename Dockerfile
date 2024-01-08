FROM tomcat:10.1.17
RUN apt-get update
RUN apt-get install -y tzdata
ARG WAR_FILE=build/libs/koala.war
COPY ${WAR_FILE} /usr/local/tomcat/webapps/ROOT.war
ENV TZ=Asia/Seoul
CMD ["catalina.sh", "run"]

#FROM openjdk:17-jdk
#VOLUME /tmp
#ARG WAR_FILE
#COPY ${WAR_FILE} app.war
#ENV PORT=${PORT:-8082}
#ENTRYPOINT ["java", "-jar", "/app.war"]