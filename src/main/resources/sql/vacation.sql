DROP TABLE IF EXISTS member;
DROP TABLE IF EXISTS vacation;
DROP TABLE IF EXISTS vacation_application;

CREATE TABLE member (
  member_no int(11) auto_increment comment '회원 번호' primary key,
  name varchar(20) not null comment '이름',
  password varchar(100) not null comment '비밀번호'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO member (member_no, name, password) VALUES (1, 'aaa', '$2a$10$enArkElGI3lO7dx9Yk2r4eCU9TF2lpK8GYGmsnHMmyT2lJnTZhNfK');
INSERT INTO member (member_no, name, password) VALUES (2, 'bbb', '$2a$10$KFMSpa6i/oVzO.mudEIcmuBRVsIt2IxF7JTNpUXJHGByjwRPjm4kq');
INSERT INTO member (member_no, name, password) VALUES (3, 'ccc', '$2a$10$gqULTz4c4/OUp8xSCKxjkudVfdpO5m2ubVNepN0AFOkooegqnOKjO');

CREATE TABLE vacation (
  vacation_no int(11) auto_increment comment '휴가 정보 번호',
  member_no int(11) not null comment '회원 번호',
  total_vacation_cnt float(4) not null comment '총 휴가 일 수',
  left_vacation_cnt float(4) not null comment '남은 휴가 일 수',
  use_vacation_cnt float(4) not null comment '사용한 휴가 일 수',
  vacation_type varchar(10) not null comment '휴가 종류 (연차|보상 등등)',
  assign_vacation_dt datetime not null comment '휴가 지급 날짜',
  expire_vacation_dt datetime not null comment '휴가 만료 날짜',
  primary key (vacation_no)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO vacation ( member_no, total_vacation_cnt, left_vacation_cnt, use_vacation_cnt, vacation_type, assign_vacation_dt, expire_vacation_dt ) VALUES ( 1, 15.0, 15.0, 0.0, 'ANNUAL', '2020-01-01 00:00:00', '2021-08-10 23:59:59');
INSERT INTO vacation ( member_no, total_vacation_cnt, left_vacation_cnt, use_vacation_cnt, vacation_type, assign_vacation_dt, expire_vacation_dt ) VALUES ( 1, 1.0, 1.0, 0.0, 'BONUS', '2020-08-01 00:00:00', '2020-09-15 23:59:59');
INSERT INTO vacation ( member_no, total_vacation_cnt, left_vacation_cnt, use_vacation_cnt, vacation_type, assign_vacation_dt, expire_vacation_dt ) VALUES ( 2, 15.0, 15.0, 0.0, 'ANNUAL', '2020-01-01 00:00:00', '2021-08-10 23:59:59');
INSERT INTO vacation ( member_no, total_vacation_cnt, left_vacation_cnt, use_vacation_cnt, vacation_type, assign_vacation_dt, expire_vacation_dt ) VALUES ( 3, 15.0, 15.0, 0.0, 'ANNUAL', '2020-01-01 00:00:00', '2021-08-10 23:59:59');

CREATE TABLE vacation_application (
  vacation_application_no int(11) auto_increment comment '휴가 신청 번호',
  vacation_no int(11) not null comment '휴가 정보 번호',
  member_no int(11) not null comment '회원 번호',
  application_dt datetime not null comment '휴가 신청 날짜',
  vacation_start_dt datetime not null comment '휴가 시작 날짜',
  vacation_end_dt datetime not null comment '휴가 종료 날짜',
  use_vacation_cnt float(4) not null comment '휴가 사용 날짜',
  vacation_cancel_yn char(1) null default 'N' comment '휴가 취소 (Y|N)',
  vacation_cancel_dt datetime null comment '휴가 취소 날짜',
  primary key (vacation_application_no)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;