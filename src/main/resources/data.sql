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
	host_id bigint not null COMMETN '호스트 아이',
    event_occurrence varchar(255) not null COMMENT '사건 발생',
	event_type varchar(255) not null COMMENT '사건 유형',
	event_result varchar(255) COMMENT '사건 결과',
	created_at datetime(6) not null COMMENT '사건 발생 시간',
	created_by varchar(255) not null COMMENT '사건 발생 주체',
	modified_at datetime(6) not null COMMENT '사건 수정 일시',
    modified_by varchar(255) not null COMMENT '사건 수정 주체',
    primary key (id)
);

 create table user_account (
        user_id varchar(50) not null COMMENT '아이디',
        user_password varchar(255) not null COMMENT '비밀번호',
        email varchar(100) COMMENT '이메일',
        created_at datetime(6) not null COMMENT '등록 시간',
        created_by varchar(255) not null COMMENT '등록자',
        modified_at datetime(6) not null COMMENT '수정 시간',
        modified_by varchar(255) not null COMMENT '수정자',
        primary key (user_id)
    )
*/

select * from host;

/* 임시 데이터 */
insert into host (name, ip, delete_flag, created_at, created_by, modified_at, modified_by) values ('Beulah', '245.206.158.39', false, now(), 'Davis', now(), 'Luciano');
insert into host (name, ip, delete_flag, created_at, created_by, modified_at, modified_by) values ('Margalit', '160.221.157.164', false, now(), 'Clayborne', now(), 'Alford');
insert into host (name, ip, delete_flag, created_at, created_by, modified_at, modified_by) values ('Zachary', '127.3.150.218', false, now(), 'Julianne', now(), 'Vale');
insert into host (name, ip, delete_flag, created_at, created_by, modified_at, modified_by) values ('Bobbi', '213.109.6.60', false, now(), 'Blythe', now(), 'Guenna');
insert into host (name, ip, delete_flag, created_at, created_by, modified_at, modified_by) values ('Vivia', '75.67.242.45', false, now(), 'Robinetta', now(), 'Bernetta');

insert into watch (host_id, event_occurrence, event_type, event_result, created_at, created_by, modified_at, modified_by) values (5, 'tellus nisi eu orci mauris lacinia sapien quis libero nullam sit amet turpis', 'Type1', 'curae duis faucibus accumsan odio curabitur convallis duis consequat dui nec nisi volutpat eleifend donec ut', now(), 'Agustin Abraham', now(), 'Clerkclaude Wyper');
insert into watch (host_id, event_occurrence, event_type, event_result, created_at, created_by, modified_at, modified_by) values (4, 'justo aliquam quis turpis eget elit sodales scelerisque mauris sit amet', 'Type2', 'eget rutrum at lorem integer tincidunt ante vel ipsum praesent blandit lacinia erat vestibulum sed', now(), 'Merl Tomas', now(), 'Caryl Georg');
insert into watch (host_id, event_occurrence, event_type, event_result, created_at, created_by, modified_at, modified_by) values (3, 'lectus suspendisse potenti in eleifend quam a odio in hac habitasse platea dictumst maecenas ut massa quis augue luctus tincidunt', 'Type1', 'vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae donec pharetra', now(), 'Malinda Tossell', now(), 'Ira Oddie');
insert into watch (host_id, event_occurrence, event_type, event_result, created_at, created_by, modified_at, modified_by) values (2, 'sit amet erat nulla tempus vivamus in felis eu sapien cursus vestibulum proin eu mi nulla ac enim', 'Type3', 'ut nulla sed accumsan felis ut at dolor quis odio consequat varius integer ac leo', now(), 'Tybie Thorington', now(), 'Dusty Gloyens');
insert into watch (host_id, event_occurrence, event_type, event_result, created_at, created_by, modified_at, modified_by) values (1, 'non mauris morbi non lectus aliquam sit amet diam in magna bibendum imperdiet nullam orci pede venenatis non sodales', 'Type2', 'pede justo eu massa donec dapibus duis at velit eu est congue elementum in hac habitasse platea dictumst morbi', now(), 'Kore Procter', now(), 'Guthry Teresa');
