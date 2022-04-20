CREATE TABLE client
(
    id           bigint(20)  not null AUTO_INCREMENT,
    name         varchar(50) not null,
    surname      varchar(50) not null,
    email        varchar(50) null,
    phone_number varchar(50) null,

    primary key (id)
);