create table PATIENT (
    pid int(4) primary key,
    firstName varchar(256) not null,
    lastName varchar(256) not null,
    birth_date date not null,
    gender varchar(128) not null,
    phone_number varchar(128) not null,
    address varchar(128) not null,
    suburb varchar(128) not null,
    state varchar(128) not null,
    post_code int(4) not null
)