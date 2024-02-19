/*
-- DDL
create table host (
    id bigint not null auto_increment COMMENT '아이디',
    name varchar(255) not null COMMENT '이름',
    ip varchar(255) not null COMMENT '아이피',
    delete_flag bit default false not null COMMENT '삭제 여부',
    created_at datetime(6) not null COMMENT '등록 시간',
    created_by varchar(255) not null COMMENT '등록자',
    modified_at datetime(6) not null COMMENT '수정 시간',
    modified_by varchar(255) not null COMMENT '수정자',
    primary key (id)
);

create table watch (
	id bigint not null auto_increment COMMENT '아이디',
	host_id bigint not null COMMENT '호스트 아이디',
    event_occurrence varchar(255) not null COMMENT '사건 발생',
	event_type varchar(255) not null COMMENT '사건 유형',
	created_at datetime(6) not null COMMENT '사건 발생 시간',
	created_by varchar(255) not null COMMENT '사건 발생 주체',
	modified_at datetime(6) not null COMMENT '사건 수정 일시',
    modified_by varchar(255) not null COMMENT '사건 수정 주체',
    primary key (id)
);

create table watchresult (
	id bigint not null auto_increment COMMENT '아이디',
	host_id bigint not null COMMENT '호스트 아이디',
	watch_id bigint not null COMMENT '감시 아이디',
    event_result varchar(255) COMMENT '사건 결과',
    created_at datetime(6) not null COMMENT '사건 발생 시간',
    created_by varchar(255) not null COMMENT '사건 발생 주체',
    modified_at datetime(6) not null COMMENT '사건 수정 일시',
    modified_by varchar(255) not null COMMENT '사건 수정 주체',
	primary key (id)
);
*/
SELECT * FROM HOST;