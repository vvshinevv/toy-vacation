DROP TABLE IF EXISTS member;

CREATE TABLE member (
  member_no int(11) auto_increment comment '회원 번호',
  name varchar(20) not null comment '이름',
  password varchar(20) not null comment '비밀번호',
  primary key (member_no)
);

CREATE TABLE vacation (
  vacation_no int(11) auto_increment commnet '휴가 정보 번호',
  member_no int(11) not null comment '회원 번호',
  total_vacation_cnt int(4) not null comment '총 휴가 일 수',
  left_vacation_cnt int(4) not null comment '남은 휴가 일 수',
  use_vacation_cnt int(4) not null comment '사용한 휴가 일 수',
  vacation_type varchar(10) not null comment '휴가 종류 (연차|보상 등등)',
  assign_vacation_dt datetime not null comment '휴가 지급 날짜',
  expire_vacation_dt datetime not null comment '휴가 만료 날짜',
  primary key (vacation_no)
);

CREATE TABLE vacation_application (
  vacation_application_no int(11) auto_increment comment '휴가 신청 번호',
  vacation_no int(11) not null comment '휴가 정보 번호',
  member_no int(11) not null comment '회원 번호',
  application_dt datetime not null comment '휴가 신청 날짜',
  vacation_start_dt datetime not null comment '휴가 시작 날짜',
  vacation_end_dt datetime not null comment '휴가 종료 날짜',
  vacation_cancel_yn char(1) null default 'N' comment '휴가 취소 (Y|N)',
  vacation_cancel_dt datetime null comment '휴가 취소 날짜',
  primary key (vacation_application_no)
)

INSERT INTO memberDTOS VALUES (1, 'aaa', 'aaaa');
INSERT INTO memberDTOS VALUES (1, 'bbb', 'bbbb');
INSERT INTO memberDTOS VALUES (1, 'ccc', 'cccc');