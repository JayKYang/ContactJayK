-- DB & 유저 생성 및 권한 부여
create database contactjayk;

create user "jayk"@"%" identified by "imsi00";

grant all PRIVILEGES ON contactjayk.*  to jayk@"%" identified by "imsi00";

flush PRIVILEGES;

-- MEMBER
create table member(
	email varchar(30) primary key,
	password varchar(255) not null,
	name varchar(10) not null,
	regdate date not null,
	tel varchar(13),
	company varchar(20)
);

use contactjayk;

insert into member(email,password,name,regdate,tel,company) values("dumbyura@naver.com",password(""),"양진규",curdate(),01043962468,null);

commit; 

select * from member;

-- Board

create table portfolioboard(
	no int primary key,
	subject varchar(100) not null,
	imagefile varchar(100),
	regdate datetime not null,
	content varchar(4000) not null,
	writer varchar(30) not null
);

create table guestbook(
	no int primary key,
	nickname varchar(10) not null,
	content varchar(1000) not null,
	regdate datetime not null,
	writer varchar(30)
);

CREATE TABLE guestreply(
	rep_no INT PRIMARY KEY,
	ref_no INT,
	nickname VARCHAR(30) NOT NULL,
	email varchar(30),
	regdate DATETIME NOT NULL,
	content VARCHAR(100) NOT NULL,
	ref INT,
	reflevel INT,
	refstep INT
);

CREATE TABLE businesscard (
	no INT(11) NOT NULL primary key,
	name VARCHAR(10),
	company VARCHAR(20),
	tel VARCHAR(13),
	email VARCHAR(30) NOT NULL,
	senddate DATETIME NOT NULL,
	comment VARCHAR(50)
);
