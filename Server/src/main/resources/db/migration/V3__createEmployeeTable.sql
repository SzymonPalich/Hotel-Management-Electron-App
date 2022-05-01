CREATE TABLE IF NOT EXISTS employee
(
    id              bigint(20)    not null AUTO_INCREMENT,
    first_name      varchar(50)   not null,
    last_name       varchar(50)   not null,
    email           varchar(50)   not null,
    phone_number    varchar(9)    not null,
    position        int(20)       not null,
    pesel           varchar(11)   not null,
    employment_date date          not null,
    dismissal_date  date          null,
    salary          decimal(8, 2) not null,

    primary key (id)
);

INSERT INTO `employee` (`id`, `name`, `surname`, `email`, `phone_number`, `position`, `pesel`, `employment_date`,
                        `dismissal_date`, `salary`)
VALUES (NULL, 'Mariusz', 'Pudzianowski', 'mariusz.pudzian@spurvago.com', '881223295', 1, '82783123142', '2010-03-12',
        NULL, 3000.50),
       (NULL, 'Adam', 'Małysz', 'malysz321@spurvago.com', '905984460', 2, '82730189231', '2015-07-21', NULL, 2500.00),
       (NULL, 'Krzysztof', 'Balicki', 'k.balicki@spurvago.com', '905984460', 3, '83718271892', '2020-09-28', NULL,
        30000.32);