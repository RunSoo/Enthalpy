DROP DATABASE IF EXISTS enthalpy;

CREATE DATABASE enthalpy;

USE enthalpy;

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user`(
	`id` varchar(20) NOT NULL PRIMARY KEY,
    `password` varchar(20) NOT NULL,
    `nickname` varchar(20) NOT NULL,
    `name` varchar(20) NOT NULL,
    `height` int unsigned NOT NULL,
    `weight` int unsigned NOT NULL,
    `gender` char(1) NOT NULL,
    `bmr` int unsigned default 0,
	`dob` timestamp NOT NULL,
    `goal` int
);
DROP TABLE IF EXISTS `MET`;
CREATE TABLE IF NOT EXISTS `MET`(
	`id` int NOT NULL auto_increment PRIMARY KEY,
	`activity` varchar(50) NOT NULL,
    `img` varchar(40) DEFAULT NULL,
    `value10` int NOT NULL);
-- 10배한 value로 저장하는게 좋을 것 같아
-- 소수점 아래 한자리까지 나와서...
-- MET 공식: (value10 * 0.35 * weight(user) * duration(board))

DROP TABLE IF EXISTS `board`;
CREATE TABLE IF NOT EXISTS `board`(
	`id` int NOT NULL auto_increment PRIMARY KEY,
    `title` varchar(100) NOT NULL,
    `userId` varchar(20) NOT NULL,
    `regDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `modifiedDate` timestamp,
    `activity_id` int NOT NULL,
    `duration` int NOT NULL,
    `contents` TEXT NOT NULL,
    `img` varchar(256) DEFAULT NULL, 
    `viewCnt` int NOT NULL DEFAULT 0,
    `likeCnt` int NOT NULL DEFAULT 0,
    FOREIGN KEY (userId) REFERENCES user(id) ON DELETE CASCADE,
    FOREIGN KEY (activity_id) REFERENCES MET(id) ON DELETE CASCADE) ;
    
-- INSERT INTO board (title, userId, regDate, activity_id, duration, contents) VALUES ('과거의 글..', 'apple', '2023-04-12', 1, 30, '안뜰까..?');
    
CREATE TABLE IF NOT EXISTS `comment`(
	`id` int NOT NULL auto_increment PRIMARY KEY,
    `boardId` int NOT NULL,
    `userId` varchar(20) NOT NULL,
    `contents` TEXT NOT NULL,
    `regDate` timestamp NOT NULL DEFAULT current_timestamp,
    `modifiedDate` timestamp,
    FOREIGN KEY (boardId) REFERENCES board(id) ON DELETE CASCADE,
    FOREIGN KEY (userId) REFERENCES user(id) ON DELETE CASCADE);
    
DROP TABLE IF EXISTS `dailyCal`;
CREATE TABLE IF NOT EXISTS `dailyCal`(
	`id` int NOT NULL auto_increment PRIMARY KEY,
    `userId` varchar(20) NOT NULL,
    `regDate` timestamp NOT NULL,
    `calories` int NOT NULL,
    FOREIGN KEY (userId) REFERENCES user(id) ON DELETE CASCADE);
    
CREATE TABLE IF NOT EXISTS `liked`(
	`userId` varchar(20) NOT NULL,
    `boardId` int NOT NULL,
    FOREIGN KEY (userId) REFERENCES user(id) ON DELETE CASCADE,
    FOREIGN KEY (boardId) REFERENCES board(id) ON DELETE CASCADE);


SELECT SUM(calories) FROM dailyCal WHERE userId = 'apple' AND MONTH(regDate) = MONTH('2023-05-00');

SELECT * FROM MET;

SELECT * FROM USER;


SELECT * FROM board;

SELECT LAST_INSERT_ID();

SELECT * FROM liked;

SELECT * FROM comment;

SELECT COUNT(*) FROM dailyCal WHERE userId = 'apple' AND DATE(regDate) = CURDATE() AND calories != 0;

SELECT * FROM dailyCal;

DELETE FROM dailyCal WHERE userId = 'apple' AND DATE(regDate) = DATE('2023-05-18 00:00:00') AND calories <= 0;

INSERT INTO dailyCal (userId, regDate, calories) VALUES ('apple', '2023-04-12', 500);

INSERT INTO dailyCal (userId, regDate, calories)
		VALUES (
			'apple', CURDATE(), (
			SELECT '30' * 0.35 * u.weight * m.value10
			FROM user u, MET m
			WHERE u.id = 'apple' AND m.id = '1')
		);
        
SELECT calories, DATE(regDate) AS regDate
		FROM dailyCal
		WHERE userId='apple';

INSERT INTO dailyCal (userId, regDate, calories) VALUES (
	'myId1234', '2023-05-19', 296);

INSERT INTO dailyCal (userId, regDate, calories)
		VALUES (
			'apple', CURDATE(), (
			SELECT 'apple' * 0.35 * u.weight * m.value10
			FROM user u, MET m
			WHERE u.id = 'apple' AND m.id = '1')
		);

UPDATE dailyCal
		SET calories=calories+(
			SELECT 30 * 0.35 * u.weight * m.value10
			FROM user u, MET m
			WHERE u.id = 'apple' AND m.id = 1)
		WHERE userId='apple' AND regDate=CURDATE();

SELECT b.id id, b.title title, u.nickname writer, b.userId userId, b.regDate regDate, b.modifiedDate modifiedDate, b.activity_id activity_id, b.duration duration, b.contents contents, b.img img, b.viewCnt viewCnt, b.likeCnt likeCnt
		FROM board b, user u
		WHERE b.userId=u.id;
        
SELECT * FROM dailyCal WHERE userId = 'apple' AND MONTH(regDate) = MONTH('2023-05-00');
        
UPDATE dailyCal
		SET calories=calories-(
			SELECT 30 * 0.35 * u.weight * m.value10
			FROM user u, MET m
			WHERE u.id = 'apple' AND m.id = 1)
		WHERE userId='apple' AND regDate=CURDATE();

-- img는 어케할지 모르게써...    



INSERT INTO `MET`(activity, value10)
VALUES 
('걷기(천천히)', 20), 
('걷기(시속 3km)', 25),
('걷기(시속 5.5km)', 38),
('조깅(시속 8km)', 80),
('달리기(시속 9.5km)', 100),
('달리기(시속 14km)', 140),
('실내자전거(50watt)', 30),
('자전거 타기(편안하게)', 35),
('실내자전거(100watt)', 55),
('자전거 타기(시속 20km)', 80),
('자전거 타기(시속 22.5km)', 100),
('자전거 타기(시속 26km)', 120),
('자전거 타기(시속 30km 이상)', 160),
('에어로빅(저강도)', 50),
('양궁', 35),
('배드민턴', 45),
('야구', 50),
('소프트볼', 50), 
('농구', 45),
('볼링', 30),
('카누잉', 30),
('댄스', 60),
('에어로빅(보통강도)', 60),
('발레', 60),
('펜싱', 60),
('낚시', 35),
('골프(걸어서 이동)', 44),
('체조', 40),
('승마', 40),
('아이스 스케이팅', 55),
('미니 트램펄린', 45),
('카약 타기', 50),
('스케이트보드 타기', 50),
('스키(보통 강도)', 60),
('스노클링', 50),
('서핑', 60),
('수영(보통 강도)', 45),
('탁구', 40),
('복식 테니스', 50),
('트램펄린', 35),
('배구(비경쟁적)', 30),
('레슬링', 60),
('웨이트 운동', 60),
('에어로빅(고강도)', 70),
('농구 경기', 80),
('미식축구(경쟁적)', 90),
('프리스비(얼티멋)', 80),
('하키(필드 또는 아이스)', 80),
('아이스스케이팅(소셜)', 70),
('유도', 100),
('가라테', 100),
('태권도', 100),
('라크로스', 80),
('등산', 80),
('라켓볼', 100),
('라켓볼(팀전)', 80),
('롤러스케이팅', 70),
('롤러스케이팅(빠르게)', 120),
('줄넘기(느리게)', 80),
('줄넘기(빠르게)', 120),
('스키 크로스컨트리(느리게)', 70),
('스키 크로스컨트리(보통)', 80),
('스키 크로스컨트리(오르막 질주)', 165),
('스키 크로스컨트리(고강도)', 90),
('스키 내리막(고강도)', 80),
('스킨 다이빙', 125),
('축구(일상적)', 70),
('축구(경쟁적)', 100),
('테니스', 70),
('볼리볼(경쟁적, 비치)', 80),
('계단 올라가기', 80),
('물에서 조깅', 80),
('코딩하기', 20);

SELECT * FROM MET;
