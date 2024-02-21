/*
-- DDL
create table host (
    id bigint not null auto_increment COMMENT '아이디',
    user_account_user_id varchar(50) not null COMMENT '유저 아이디',
    name varchar(255) not null COMMENT '이름',
    ip varchar(255) not null COMMENT '아이피',
    delete_flag enum ('DETELETE','CREATE') not null COMMENT '삭제 여부',
    created_at datetime(6) not null COMMENT '등록 시간',
    created_by varchar(255) not null COMMENT '등록자',
    modified_at datetime(6) not null COMMENT '수정 시간',
    modified_by varchar(255) not null COMMENT '수정자',
    primary key (id)
);

create table watch (
	id bigint not null auto_increment COMMENT '아이디',
	host_id bigint not null COMMETN '호스트 아이',
	user_account_user_id varchar(50) not null COMMENT '유저 아이디',
    event_occurrence varchar(255) not null COMMENT '사건 발생',
	event_type enum ('LOGIN','LOGOUT','LOGIN_FAIL','ETC') not null COMMENT '사건 유형',
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
insert into user_account (user_id, user_password, email, created_at, created_by, modified_at, modified_by) values ('yuni', 'jH1*E2iQ', 'vtrotman0@epa.gov', now(), 'Cindi Acres', now(), 'Vlad Trotman');
insert into user_account (user_id, user_password, email, created_at, created_by, modified_at, modified_by) values ('yuni_test', 'jH1*E2iQ', 'uriahCourtes@epa.gov', now(), 'Anne-marie Bonaire', now(), 'Uriah Courtes');

insert into host (user_id, name, ip, delete_flag, created_at, created_by, modified_at, modified_by) values ('yuni', 'Anne-marie', '91.143.91.108', 'CREATE', now(), 'Anne-marie Bonaire', now(), 'Anne-marie Ellens');
insert into host (user_id, name, ip, delete_flag, created_at, created_by, modified_at, modified_by) values ('yuni_test', 'Uriah', '176.143.215.48', 'CREATE', now(), 'Uriah Lebel', now(), 'Uriah Courtes');
insert into host (user_id, name, ip, delete_flag, created_at, created_by, modified_at, modified_by) values ('yuni', 'Stephan', '32.214.155.124', 'CREATE', now(), 'Stephan McMorland', now(), 'Stephan Radsdale');
insert into host (user_id, name, ip, delete_flag, created_at, created_by, modified_at, modified_by) values ('yuni_test', 'Saree', '124.152.67.139', 'CREATE', now(), 'Saree Lehmann', now(), 'Saree Morford');
insert into host (user_id, name, ip, delete_flag, created_at, created_by, modified_at, modified_by) values ('yuni', 'Tabatha', '71.92.115.22', 'CREATE', now(), 'Tabatha Skepper', now(), 'Tabatha McKibbin');

insert into watch (host_id, user_id, event_occurrence, event_type, event_result, created_at, created_by, modified_at, modified_by) values (1, 'yuni', 'quis odio consequat varius integer ac leo pellentesque ultrices mattis odio donec', 'LOGOUT', 'sollicitudin mi sit amet lobortis sapien sapien non mi integer ac neque duis bibendum morbi non quam nec dui', now(), 'Nate Ravenhills', now(), 'Briny Blofeld');
insert into watch (host_id, user_id, event_occurrence, event_type, event_result, created_at, created_by, modified_at, modified_by) values (2, 'yuni_test', 'eget congue eget semper rutrum nulla nunc purus phasellus in felis donec semper sapien a libero nam', 'LOGIN_FAIL', 'elementum nullam varius nulla facilisi cras non velit nec nisi vulputate nonummy maecenas tincidunt lacus at velit vivamus vel nulla', now(), 'Berny Drewet', now(), 'Neddy Prendeguest');
insert into watch (host_id, user_id, event_occurrence, event_type, event_result, created_at, created_by, modified_at, modified_by) values (3, 'yuni_test', 'primis in faucibus orci luctus et ultrices posuere cubilia curae', 'LOGOUT', 'erat eros viverra eget congue eget semper rutrum nulla nunc purus phasellus in felis donec', now(), 'Fraser Lande', now(), 'Dalton Horsell');
insert into watch (host_id, user_id, event_occurrence, event_type, event_result, created_at, created_by, modified_at, modified_by) values (4, 'yuni', 'vel augue vestibulum ante ipsum primis in faucibus orci luctus', 'ETC', 'in ante vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae duis', now(), 'Jarrett Bottomley', now(), 'Karrah Neeson');
insert into watch (host_id, user_id, event_occurrence, event_type, event_result, created_at, created_by, modified_at, modified_by) values (5, 'yuni', 'maecenas pulvinar lobortis est phasellus sit amet erat nulla tempus vivamus in felis eu sapien cursus vestibulum', 'ETC', 'faucibus orci luctus et ultrices posuere cubilia curae mauris viverra diam vitae quam suspendisse potenti nullam', now(), 'Mag Camamill', now(), 'Saunder Linay');