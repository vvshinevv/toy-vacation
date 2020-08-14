DROP TABLE IF EXISTS member;

CREATE TABLE member (
  member_no int(11) auto_increment comment '회원 번호' primary key,
  name varchar(20) not null comment '이름',
  password varchar(100) not null comment '비밀번호'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO member (name, password) VALUES ('aaa', '$2a$10$enArkElGI3lO7dx9Yk2r4eCU9TF2lpK8GYGmsnHMmyT2lJnTZhNfK');
INSERT INTO member (name, password) VALUES ('bbb', '$2a$10$KFMSpa6i/oVzO.mudEIcmuBRVsIt2IxF7JTNpUXJHGByjwRPjm4kq');
INSERT INTO member (name, password) VALUES ('ccc', '$2a$10$gqULTz4c4/OUp8xSCKxjkudVfdpO5m2ubVNepN0AFOkooegqnOKjO');