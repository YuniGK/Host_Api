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
	watch_id bigint not null COMMENT '감시 아이디',
    event_result varchar(255) COMMENT '사건 결과',
    created_at datetime(6) not null COMMENT '사건 발생 시간',
    created_by varchar(255) not null COMMENT '사건 발생 주체',
    modified_at datetime(6) not null COMMENT '사건 수정 일시',
    modified_by varchar(255) not null COMMENT '사건 수정 주체',
	primary key (id)
);
*/

select * from host;

/* 임시 데이터 */
insert into host (id, name, ip, delete_flag, created_at, created_by, modified_at, modified_by) values (1, 'Deina', '124.151.146.203', false, now(), 'Deina Spalton', now(), 'Deina Heal');
insert into host (id, name, ip, delete_flag, created_at, created_by, modified_at, modified_by) values (2, 'Baxy', '124.151.146.200', false, now(), 'Baxy Stieger', now(), 'Baxy Flook');
insert into host (id, name, ip, delete_flag, created_at, created_by, modified_at, modified_by) values (3, 'Asia', '31.35.253.191', false, now(), 'Asia Brunnstein', now(), 'Asia Pragnell');
insert into host (id, name, ip, delete_flag, created_at, created_by, modified_at, modified_by) values (4, 'Livy', '82.106.57.73', false, now(), 'Livy Bakster', now(), 'Livy Sheals');
insert into host (id, name, ip, delete_flag, created_at, created_by, modified_at, modified_by) values (5, 'Lay', '13.37.165.65', false, now(), 'Lay Hymus', now(), 'Lay Kitchiner');

insert into watch (id, event_type, event_occurrence, created_at, created_by, modified_at, modified_by) values (1, 'Type A', 'Type C', now(), 'Hill Suddards', now(), 'Thain Schlagtmans');
insert into watch (id, event_type, event_occurrence, created_at, created_by, modified_at, modified_by) values (2, 'Type D', 'Type D', now(), 'Willem Lattie', now(), 'Dulcie Whybray');
insert into watch (id, event_type, event_occurrence, created_at, created_by, modified_at, modified_by) values (3, 'Type C', 'Type C', now(), 'Cyril Foulgham', now(), 'Michelina Goves');
insert into watch (id, event_type, event_occurrence, created_at, created_by, modified_at, modified_by) values (4, 'Type D', 'Type C', now(), 'Jeremy Isworth', now(), 'Tomasine Laffling');
insert into watch (id, event_type, event_occurrence, created_at, created_by, modified_at, modified_by) values (5, 'Type A', 'Type A', now(), 'Arlene Olivia', now(), 'Petey Goggins');

insert into watchresult (id, watch_id, event_result, created_at, created_by, modified_at, modified_by) values (1, 5, 'Reverse-engineered value-added challenge', now(), 'Domingo Guillford', now(), 'Verney MacKettrick');
insert into watchresult (id, watch_id, event_result, created_at, created_by, modified_at, modified_by) values (2, 4, 'Future-proofed eco-centric Graphic Interface', now(), 'Merlina Forstall', now(), 'Roderick Cogger');
insert into watchresult (id, watch_id, event_result, created_at, created_by, modified_at, modified_by) values (3, 3, 'Team-oriented needs-based implementation', now(), 'Magdaia McNeilly', now(), 'Roderick');
insert into watchresult (id, watch_id, event_result, created_at, created_by, modified_at, modified_by) values (4, 2, 'Ameliorated secondary success', now(), 'Neala Brafield', now(), 'Joyan Pacher');
insert into watchresult (id, watch_id, event_result, created_at, created_by, modified_at, modified_by) values (5, 1, 'Future-proofed tertiary portal', now(), 'Yankee Kingzeth', now(), 'Ellary Summerlad');
