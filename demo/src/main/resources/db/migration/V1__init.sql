-- V1__init.sql

use koaladb;
SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS `Student`;

-- Student 테이블 생성
CREATE TABLE Student (
                         id INT NOT NULL PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         baekjoon_name VARCHAR(255),
                         tistory_name VARCHAR(255),
                         semester_id INT NOT NULL,
                         study_id INT NOT NULL,
                         FOREIGN KEY (semester_id) REFERENCES Semester(id),
                         FOREIGN KEY (study_id) REFERENCES Study(id)
);

DROP TABLE IF EXISTS `Study`;
-- Study 테이블 생성
CREATE TABLE Study (
                       id INT NOT NULL PRIMARY KEY,
                       name VARCHAR(255) NOT NULL,
                       description VARCHAR(255) NOT NULL
);
DROP TABLE IF EXISTS `Semester`;

-- Semester 테이블 생성
CREATE TABLE Semester (
                          id INT NOT NULL PRIMARY KEY,
                          year INT NOT NULL,
                          term VARCHAR(255) NOT NULL
);
DROP TABLE IF EXISTS `Week`;

-- 주차(Week) 테이블 생성
CREATE TABLE Week (
                      id INT NOT NULL PRIMARY KEY,
                      week_number INT,
                      semester_id INT,
                      FOREIGN KEY (semester_id) REFERENCES Semester(id)
);
DROP TABLE IF EXISTS `Problem`;

-- 문제 테이블 생성
CREATE TABLE Problem (
                         id INT NOT NULL PRIMARY KEY,
                         solved_baekjoon INT,
                         written_tistory INT,
                         solved_baekjoon_week INT,
                         written_tistory_week INT,
                         student_id INT,
                         week_id INT,
                         FOREIGN KEY (student_id) REFERENCES Student(id),
                         FOREIGN KEY (week_id) REFERENCES Week(id)
);


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

SET FOREIGN_KEY_CHECKS = 1;