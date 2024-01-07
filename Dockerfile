FROM openjdk:17-jdk
RUN microdnf install findutils
# 작업 디렉토리 설정
WORKDIR /app
# Gradle Wrapper 및 설정 파일 복사
COPY gradlew .
COPY gradle ./gradle
# 의존성 설치
COPY build.gradle .
COPY settings.gradle .
# 소스 코드 복사
COPY src ./src
RUN ls
# 프로젝트 빌드
RUN ./gradlew clean build
RUN ls
# 애플리케이션 실행을 위한 명령
CMD ["java", "-jar", "build/libs/koala.war"]