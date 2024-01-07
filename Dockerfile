# Gradle 빌드 단계
FROM gradle:latest as builder
WORKDIR /app
COPY . /app
RUN gradle clean build -x test
# Tomcat 이미지에 WAR 파일 복사
FROM tomcat:10.1.17
RUN apt-get update \
    && apt-get install -y tzdata
ENV TZ=Asia/Seoul
COPY --from=builder /app/build/libs/koala.war /usr/local/tomcat/webapps/ROOT.war
CMD ["catalina.sh", "run"]