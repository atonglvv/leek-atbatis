## User建表sql
create table user
(
    id   bigint auto_increment
        primary key,
    name varchar(10) null comment '姓名',
    age  int         null comment '年龄'
);