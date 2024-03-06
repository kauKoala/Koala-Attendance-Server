# [KAU 알고리즘 동아리 출석부] Koala-Attendance-Server

## 1. 소개
> Koala (알고리즘 동아리) 내 스터디 출석부 자동화 서비스   

동아리에서 스터디를 운영할 때, 운영자들이 출석부를 작성하기 위해 카카오톡 창을 하나씩 확인해야 하는 불편함을 해소하고자 만든 출석부 자동화 서비스입니다.

## 2. 화면 구성 및 사용 방법
1. 메인 page   
메인 페이지에서는 현재 운영 및 모집 중인 스터디를 알 수 있습니다.   
이 때 학기 기준은 현재 년도 및 학기를 자동으로 가져옵니다.
- 1학기 - 3.1 ~ 6.20 
- 여름방학 - 6.20 ~ 9.1
- 2학기 - 9.1 ~ 12.20
- 겨울학기 - 12.20 ~ 2.28
<img width="800" alt="image" src="https://github.com/kauKoala/Koala-Attendance-Server/assets/81344634/0c2d5e9d-8512-445f-b018-ba6c980f7497">


2. 로그인 page   
아래 3, 4번 page에 접근하기 위해서는 admin 아이디/비밀번호 계정이 필요합니다.
<img width="800" alt="image" src="https://github.com/kauKoala/Koala-Attendance-Server/assets/81344634/b0a1f33c-f7fd-4600-9cde-7e281615d319">


4. 등록 page   
학생 및 스터디를 등록할 수 있습니다.   
학생의 백준 및 티스토리 이름은 각 플랫폼에서 가입 시 기입했던 이름을 기입해줍니다. (예. 전영서 / 20wjsdudtj / jeonyoungseo)
<img width="800" alt="image" src="https://github.com/kauKoala/Koala-Attendance-Server/assets/81344634/fb206cec-1494-4ca2-b557-914c3e9d946c">


5. 참가 page    
학생들을 스터디에 추가/삭제 할 수 있습니다.   
학생은 여러 명 선택 가능하며, 스터디는 한 개만 선택 가능합니다.   
<img width="800" alt="image" src="https://github.com/kauKoala/Koala-Attendance-Server/assets/81344634/03e472d0-d6e7-457e-8132-7297877460c9">



6. 출석부 조회 page   
년도와 학기를 순차적으로 선택한 후 학생 별 주차별 출석 내역을 볼 수 있습니다.   
각각 백준 / 티스토리 출석부를 의미하며 백준은 푼 갯수가, 티스토리는 포스팅 유무가 기입됩니다.      
'-' 로 표기된 내용은 아직 해당 history가 업데이트되지 않았음을 의미합니다.
<img width="800" alt="image" src="https://github.com/kauKoala/Koala-Attendance-Server/assets/81344634/39743bd9-e3bb-41c2-acb4-ec600e857460">
<img width="800" alt="image" src="https://github.com/kauKoala/Koala-Attendance-Server/assets/81344634/4f82aac9-ebaf-44bb-beeb-c6f9d1f23cdc">



## 3. build & execute
1. build with Dockerfile     
gradle 이미지 내에서 build한 후 build한 war 파일을 tomcat 이미지에 복사하여 실행       
[build 과정 블로그 포스팅 참고](https://youngseo-computerblog.tistory.com/146)
```
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
```

2. execute   
git clone 이후 사용자 개인이 쉽게 구현 가능합니다.   
- application-local.yml을 만들고 .gitignore에 포함시킵니다.      
- 이후 Supabase postgreSQL과 MongoDB, Java Spring localhost DB를 각각 만든 후 환경변수에 기입합니다.     
  [DB 설정 블로그 포스팅 참고](https://youngseo-computerblog.tistory.com/144)
- IntelliJ 환경변수를 local로 지정해준 후 실행시킵니다.     

## 4. 아키텍처
1. ERD   
<img src="images/img_7.png" alt="이미지 대체 텍스트" width="600"/>

2. 사용 기술       
<img src="https://github.com/kauKoala/Koala-Attendance-Server/assets/81344634/22d9a117-6d6f-4914-9242-26fac621caee" alt="이미지 대체 텍스트" width="400"/>

3. 배포 pipeline   
<img src="images/img_9.png" alt="이미지 대체 텍스트" width="500"/>

## 5. configuration
해당 출석부 편집 & 관리를 위한 계정 및 DB 관련 정보는 20wjsdudtj@gmail.com으로 연락주시기 바랍니다.
