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
SET FOREIGN_KEY_CHECKS = 1;

