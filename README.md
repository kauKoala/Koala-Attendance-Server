# Koala-Attendance-Server
[Java|Spring] Koala 조직을 위한 출석부 서버입니다.

### [V1] 초기 설정
Serverless + AWS Lambda + AWS CloudWatch + Slack + S3

1. 백준 아이디로 푼 문제 크롤링 -> 완료
2. 백준 연습 문제 푼 사람 크롤링 -> 백준 지원 X. 불가
3. 티스토리 크롤링 
  - [로그인O] 카카오 로그인 후 카테고리 & 날짜 및 시간 적용하여 검색 후 크롤링 
  - [로그인X] 웹페이지 넘어가면서 작성자 및 날짜 및 시간 크롤링 후 DB 저장 - Unique Constraint 적용

---

### [V2] 추후 웹 / 서버 개발
Java + Spring / JSP 추가

INPUT
1. 스터디 형태 등록 (스터디이름, 시작 날짜, 종료 날짜, 스케줄링 주기)
2. 스터디원 등록 (스터디 이름, 스터디 날짜)

OUTPUT
1. 특정 단일 주 선택 시 해당 주 참여자 내용 출석부 형식(o/x)으로 출력 
2. 특정 주간 선택 시 해당 주간 참여자 내용 출석부 형식(o/x)으로 출력 
