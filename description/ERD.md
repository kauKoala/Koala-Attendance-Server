# ERD
뭐가 뭘 물고 있는지 잘 확인 !!


Student

| id            | int | not null | PK |  |
|---------------| --- | --- | --- | --- |
| name          | varchar(255) | not null |  |  |
| baekjoon_name | varchar(255) |  |  |  |
| tistory_name  | varchar(255) |  |  |  |
| semester_id   | int | not null | FK |  |
| study_id      | int | not null | FK |  |

Study

| id | int | not null | PK |  |
| --- | --- | --- | --- | --- |
| name | varchar(255) | not null |  | 기초, 코딩테스트 .. |
| description | varchar(255) | not null |  |  |

Semester

| id   | int          | not null | PK | 코알라 몇 기 ? |
|------|--------------|----------| --- | --- |
| year | int          | not null |  |  |
| term | varchar(255) | not null |  | 1학기, 여름방학, 2학기, 겨울방학 |

주차

| id          | int | not null | PK |  |
|-------------|-----| --- | --- | --- |
| week_number | int |  |  |  |
| semester_id | int |  | FK |  |

문제

| id                   | int | not null | PK |  |
|----------------------| --- | --- | --- | --- |
| solved_baekjoon      |  |  |  | 지금까지 푼 백준 문제  |
| written_tistory      |  |  |  | 지금까지 쓴 tistory 글 |
| solved_baekjoon_week |  |  |  | 이번주에 푼 백준 문제  |
| written_tistory_week |  |  |  | 이번주에 쓴 tistory 글 |
| student_id           |  |  | FK | 학생 id |
| week_id              |  |  | FK | 주차 id |