-- V2__insert_example_data.sql

-- Student 테이블에 예시 데이터 삽입
INSERT INTO Student (id, name, baekjoon_name, tistory_name, semester_id, study_id)
VALUES
    (1, '전영서', '20wjsdudtj', 'jeonyoungseo', 3, 1),
    (2, '김태현', 'kimtaehyun98', 'kimtaehyun98', 1, 2);

-- Study 테이블에 예시 데이터 삽입
INSERT INTO Study (id, name, description)
VALUES
    (1, '기초알고리즘 스터디', '언어 사용과 알고리즘 기초에 대해 학습'),
    (2, '코딩테스트 스터디', '알고리즘 심화를 다루며 코딩테스트 준비');

-- Week 테이블에 데이터 추가
INSERT INTO Week (id, week_number, semester_id) VALUES
    (1, 1, 1),
    (2, 2, 2);

-- Semester 테이블에 데이터 추가
INSERT INTO Semester (id, year, term) VALUES
    (1, 2020, '1학기'),
    (2, 2022, '겨울방학');


-- Problem 테이블에 데이터 추가
INSERT INTO Problem (id, solved_baekjoon, written_tistory, solved_baekjoon_week, written_tistory_week, student_id, week_id) VALUES
    (1, 10, 5, 2, 1, 1, 1),
    (2, 15, 8, 3, 2, 2, 2);