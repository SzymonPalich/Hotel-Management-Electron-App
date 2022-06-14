CREATE TABLE IF NOT EXISTS employee
(
    id              bigint(20)    not null AUTO_INCREMENT,
    first_name      varchar(50)   not null,
    last_name       varchar(50)   not null,
    email           varchar(50)   not null,
    password        varchar(120)  not null,
    phone_number    varchar(9)    not null,
    position        varchar(25)   not null,
    pesel           varchar(11)   not null,
    employment_date date          not null,
    dismissal_date  date          null,
    salary          decimal(8, 2) not null,

    primary key (id)
);

INSERT INTO `employee` (`id`, `first_name`, `last_name`, `email`, `password`, `phone_number`, `position`, `pesel`,
                        `employment_date`,
                        `dismissal_date`, `salary`)
VALUES (NULL, 'Mariusz', 'Pudzianowski', 'mariusz.pudzian@spurvago.com',
        '$2a$10$RGZ7sPf4vxkE3s8h8jHGvuxL3/HbaPp1A4B4pfGZZ2Z9c3c2fwOMe', '881223295', 'ROLE_MANAGER', '82783123142',
        '2010-03-12',
        NULL, 3000.50),
       (NULL, 'Adam', 'Małysz', 'malysz321@spurvago.com',
        '$2a$10$RGZ7sPf4vxkE3s8h8jHGvuxL3/HbaPp1A4B4pfGZZ2Z9c3c2fwOMe', '905984460', 'ROLE_TECHNICIAN', '82730189231',
        '2015-07-21', NULL, 2500.00),
       (NULL, 'Krystian', 'Zieliński', 'kzielinski@spurvago.com',
        '$2a$10$RGZ7sPf4vxkE3s8h8jHGvuxL3/HbaPp1A4B4pfGZZ2Z9c3c2fwOMe', '905984460', 'ROLE_MAID', '83718271892',
        '2020-09-28', NULL,
        30000.32),
       (NULL, 'Joanna', 'Kowalska', 'jkowalska@spurvago.com',
        '$2a$10$RGZ7sPf4vxkE3s8h8jHGvuxL3/HbaPp1A4B4pfGZZ2Z9c3c2fwOMe', '505656205', 'ROLE_RECEPTIONIST', '81018271333',
        '2020-07-21', NULL,
        30000.11);