CREATE TABLE IF NOT EXISTS maintenance_ticket
(
    id                bigint(20)   not null AUTO_INCREMENT,
    room_id           bigint(20)   not null,
    employee_id       bigint(20)    default null,
    name              varchar(50)  not null,
    description       varchar(300) not null,
    parts_price       decimal(8, 2) default null,
    technician_report varchar(300)  default null,
    finalization_date date          default null,

    PRIMARY KEY (id),
    CONSTRAINT maintenance_room_fk FOREIGN KEY (room_id) REFERENCES room (id),
    CONSTRAINT maintenance_employee_fk FOREIGN KEY (employee_id) REFERENCES employee (id)
);

INSERT INTO `maintenance_ticket`(`room_id`, `name`, `description`)
VALUES (1, 'Telewizor', 'Rozbita matryca'),
       (2, 'Klamka', 'Wyrwana klamka'),
       (3, 'Kran', 'Cieknacy kran');